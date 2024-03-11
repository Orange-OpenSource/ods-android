/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalCategories
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.about.appAboutConfiguration
import com.orange.ods.app.ui.components.tabs.tabs
import com.orange.ods.app.ui.modules.about.AboutCustomizationViewModel
import com.orange.ods.app.ui.utilities.extension.isDarkModeEnabled
import com.orange.ods.app.ui.utilities.extension.isOrange
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.tab.OdsScrollableTabRow
import com.orange.ods.compose.component.tab.OdsTabRow
import com.orange.ods.compose.extension.orElse
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.module.about.ui.navigation.navigateToOdsAbout
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.annotation.ExperimentalOdsApi
import com.orange.ods.theme.typography.OdsTextStyle
import com.orange.ods.xml.theme.OdsXml
import com.orange.ods.xml.utilities.extension.xml
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class, ExperimentalOdsApi::class)
@Composable
fun MainScreen(themeConfigurations: List<OdsThemeConfigurationContract>, mainViewModel: MainViewModel = viewModel()) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val mainState = rememberMainState(
        themeState = rememberThemeState(
            currentThemeConfiguration = rememberSaveable {
                mutableStateOf(
                    getCurrentThemeConfiguration(
                        mainViewModel.getUserThemeName(),
                        themeConfigurations
                    )
                )
            },
            darkModeEnabled = rememberSaveable { mutableStateOf(isSystemInDarkTheme) },
            themeConfigurations = themeConfigurations
        )
    )

    // Change isSystemInDarkTheme() value to make switching theme working with custom color
    val configuration = LocalConfiguration.current.apply {
        isDarkModeEnabled = mainState.themeState.darkModeEnabled
    }

    with(OdsTheme.xml) {
        themeConfiguration = mainState.themeState.currentThemeConfiguration
        uiMode = if (mainState.themeState.darkModeEnabled) OdsXml.UiMode.Dark else OdsXml.UiMode.Light
    }

    var changeThemeDialogVisible by remember { mutableStateOf(false) }

    CompositionLocalProvider(
        LocalConfiguration provides configuration,
        LocalAppBarManager provides mainState.appBarState,
        LocalThemeManager provides mainState.themeState,
        LocalGuideline provides mainState.themeState.currentThemeConfiguration.guideline,
        LocalRecipes provides mainViewModel.recipes,
        LocalCategories provides mainViewModel.categories,
        LocalUiFramework provides mainState.uiFramework
    ) {
        AppBarActionsHandler(navigate = mainState.navigationState.navController::navigate, onChangeThemeActionClick = { changeThemeDialogVisible = true })
        OdsTheme(
            themeConfiguration = mainState.themeState.currentThemeConfiguration,
            darkThemeEnabled = configuration.isDarkModeEnabled
        ) {
            val topBarScrollBehavior: TopAppBarScrollBehavior?
            val modifier: Modifier

            if (mainState.appBarState.type == Screen.TopAppBarType.Large) {
                topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
                val nestedScrollConnection = remember { topBarScrollBehavior.nestedScrollConnection }
                modifier = Modifier.nestedScroll(nestedScrollConnection)
            } else {
                topBarScrollBehavior = null
                modifier = Modifier
            }

            val appAboutConfiguration = appAboutConfiguration()
            var aboutConfiguration by remember { mutableStateOf(appAboutConfiguration) }
            val aboutDemoConfiguration = viewModel<AboutCustomizationViewModel>().aboutConfiguration()
            Scaffold(
                modifier = modifier,
                topBar = {
                    Surface(elevation = if (isSystemInDarkTheme()) 0.dp else AppBarDefaults.TopAppBarElevation) {
                        Column {
                            SystemBarsColorSideEffect(mainState)
                            AppBar(
                                appBarState = mainState.appBarState,
                                upPress = mainState.navigationState::upPress,
                                scrollBehavior = topBarScrollBehavior
                            )
                            if (mainState.navigationState.currentScreen?.hasTabs == true && mainState.appBarState.tabsState.hasTabs) {
                                AppBarTabs(appBarTabsState = mainState.appBarState.tabsState)
                            } else {
                                mainState.appBarState.clearAppBarTabs()
                            }
                        }
                    }
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = mainState.shouldShowBottomBar,
                        enter = fadeIn(tween(100)),
                        exit = fadeOut(tween(100))
                    ) {
                        BottomBar(
                            items = BottomBarItem.entries.toTypedArray(),
                            currentRoute = mainState.navigationState.currentRoute.orEmpty(),
                            navigateToRoute = { route ->
                                if (route == BottomBarItem.About.route) {
                                    aboutConfiguration = appAboutConfiguration
                                }
                                mainState.navigationState.navigateToBottomBarRoute(route)
                            }
                        )
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = mainState.navigationState.navController,
                    startDestination = BottomBarItem.Guidelines.route,
                    modifier = Modifier.padding(innerPadding),
                    exitTransition = { ExitTransition.None }
                ) {
                    appNavGraph(
                        navController = mainState.navigationState.navController,
                        navigateToElement = mainState.navigationState::navigateToElement,
                        navigateToAboutDemo = {
                            aboutConfiguration = aboutDemoConfiguration
                            mainState.navigationState.navController.navigateToOdsAbout()
                        },
                        aboutConfiguration = {
                            aboutConfiguration
                        }
                    )
                }

                if (changeThemeDialogVisible) {
                    ChangeThemeDialog(
                        themeManager = mainState.themeState,
                        dismissDialog = {
                            changeThemeDialogVisible = false
                        },
                        onThemeSelected = {
                            mainViewModel.storeUserThemeName(mainState.themeState.currentThemeConfiguration.name)
                        }
                    )
                }
            }
        }
    }
}

private fun getCurrentThemeConfiguration(
    storedUserThemeName: String?,
    themeConfigurations: List<OdsThemeConfigurationContract>
): OdsThemeConfigurationContract {
    // Return the stored user theme configuration if it exists. If not, return the Orange theme configuration or the first existing theme configuration
    return themeConfigurations.firstOrNull { it.name == storedUserThemeName }
        .orElse { themeConfigurations.firstOrNull { it.isOrange } }
        .orElse { themeConfigurations.first() }
}

@Composable
private fun SystemBarsColorSideEffect(mainState: MainState) {
    val systemBarsBackground = OdsTheme.colors.components.systemBarsBackground
    val activity = LocalContext.current as? ComponentActivity
    activity?.window?.let { window ->
        val view = LocalView.current
        SideEffect {
            window.statusBarColor = systemBarsBackground.toArgb()
            window.navigationBarColor = systemBarsBackground.toArgb()
            val isAppearanceLightBars = with(mainState.themeState) { if (currentThemeConfiguration.isOrange) !darkModeEnabled else false }
            with(WindowCompat.getInsetsController(window, view)) {
                isAppearanceLightStatusBars = isAppearanceLightBars
                isAppearanceLightNavigationBars = isAppearanceLightBars
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AppBarTabs(appBarTabsState: AppBarTabsState) {
    with(appBarTabsState) {
        pagerState?.let { pagerState ->
            // Do not use tabs directly because this is a SnapshotStateList
            // Thus its value can be modified and can lead to crashes if it becomes empty
            val tabs = tabs.toList()

            if (scrollableTabs.value) {
                OdsScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    tabs = tabs(
                        tabs = tabs,
                        pagerState = pagerState,
                        tabIconEnabled = tabIconEnabled.value,
                        tabTextEnabled = tabTextEnabled.value
                    ),
                    tabIconPosition = tabsIconPosition.value
                )
            } else {
                OdsTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    tabs = tabs(
                        tabs = tabs,
                        pagerState = pagerState,
                        tabIconEnabled = tabIconEnabled.value,
                        tabTextEnabled = tabTextEnabled.value
                    ),
                    tabIconPosition = tabsIconPosition.value
                )
            }
        }
    }
}

@Composable
private fun AppBarActionsHandler(navigate: (String) -> Unit, onChangeThemeActionClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val themeManager = LocalThemeManager.current

    // Manage top app bar actions clicked
    LaunchedEffect(key1 = Unit) {
        Screen.appBarActionClicked.onEach { action ->
            when (action) {
                AppBarAction.Search -> navigate(MainNavigation.SearchRoute)
                AppBarAction.ChangeTheme -> onChangeThemeActionClick()
                AppBarAction.ChangeMode -> themeManager.darkModeEnabled = !configuration.isDarkModeEnabled
            }
        }.launchIn(this)
    }
}

@Composable
private fun ChangeThemeDialog(themeManager: ThemeManager, dismissDialog: () -> Unit, onThemeSelected: () -> Unit) {
    var selectedThemeName by rememberSaveable { mutableStateOf(themeManager.currentThemeConfiguration.name) }

    Dialog(onDismissRequest = dismissDialog) {
        Column(modifier = Modifier.background(OdsTheme.colors.surface)) {
            OdsText(
                text = stringResource(R.string.top_app_bar_action_change_theme_desc),
                modifier = Modifier
                    .padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_m), bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
                    .padding(horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin)),
                style = OdsTextStyle.TitleL
            )
            themeManager.themeConfigurations.forEach { themeConfiguration ->
                OdsListItem(
                    text = themeConfiguration.name,
                    trailing = OdsListItem.TrailingRadioButton(
                        selectedThemeName == themeConfiguration.name,
                        {
                            selectedThemeName = themeConfiguration.name
                            if (themeConfiguration != themeManager.currentThemeConfiguration) {
                                themeManager.currentThemeConfiguration = themeConfiguration
                                onThemeSelected()
                            }
                            dismissDialog()
                        }
                    )
                )
            }
        }
    }
}