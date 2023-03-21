/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.about.addAboutGraph
import com.orange.ods.app.ui.components.addComponentsGraph
import com.orange.ods.app.ui.components.tabs.FixedTabRow
import com.orange.ods.app.ui.components.tabs.ScrollableTabRow
import com.orange.ods.app.ui.guidelines.addGuidelinesGraph
import com.orange.ods.app.ui.search.SearchScreen
import com.orange.ods.app.ui.utilities.extension.isDarkModeEnabled
import com.orange.ods.app.ui.utilities.extension.isOrange
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsRadioButtonTrailing
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.utilities.extension.orElse

@Composable
fun MainScreen(themeConfigurations: Set<OdsThemeConfigurationContract>, mainViewModel: MainViewModel = viewModel()) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val mainState = rememberMainState(
        themeState = rememberMainThemeState(
            currentThemeConfiguration = rememberSaveable {
                mutableStateOf(
                    getCurrentThemeConfiguration(
                        mainViewModel.getUserThemeName(),
                        themeConfigurations
                    )
                )
            },
            darkModeEnabled = rememberSaveable { mutableStateOf(isSystemInDarkTheme) },
            themeConfigurations = themeConfigurations.toList()
        )
    )
    // Change isSystemInDarkTheme() value to make switching theme working with custom color
    val configuration = LocalConfiguration.current.apply {
        isDarkModeEnabled = mainState.themeState.darkModeEnabled
    }

    CompositionLocalProvider(
        LocalConfiguration provides configuration,
        LocalMainTopAppBarManager provides mainState.topAppBarState,
        LocalMainThemeManager provides mainState.themeState,
        LocalOdsGuideline provides mainState.themeState.currentThemeConfiguration.guideline,
        LocalRecipes provides mainViewModel.recipes
    ) {
        var changeThemeDialogVisible by remember { mutableStateOf(false) }

        OdsTheme(
            themeConfiguration = mainState.themeState.currentThemeConfiguration,
            darkThemeEnabled = configuration.isDarkModeEnabled
        ) {
            Scaffold(
                backgroundColor = OdsTheme.colors.background,
                topBar = {
                    // The extended top app bar is managed by a custom layout instead of a TopAppBar
                    if (!mainState.topAppBarState.isExtended) {
                        Surface(elevation = if (isSystemInDarkTheme()) 0.dp else AppBarDefaults.TopAppBarElevation) {
                            Column {
                                SystemBarsColorSideEffect()
                                MainTopAppBar(
                                    shouldShowUpNavigationIcon = !mainState.shouldShowBottomBar,
                                    state = mainState.topAppBarState,
                                    upPress = mainState::upPress,
                                    onChangeThemeActionClick = { changeThemeDialogVisible = true },
                                    onSearchActionClick = {
                                        mainState.navController.navigate(MainDestinations.SearchRoute)
                                    }
                                )
                                // Display tabs in the top bar if needed
                                MainTabs(mainTabsState = mainState.topAppBarState.tabsState)
                            }
                        }
                    }
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = mainState.shouldShowBottomBar,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        MainBottomNavigation(
                            items = mainState.bottomBarItems,
                            currentRoute = mainState.currentRoute!!,
                            navigateToRoute = mainState::navigateToBottomBarRoute
                        )
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = mainState.navController,
                    startDestination = MainDestinations.HomeRoute,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    mainNavGraph(navigateToElement = mainState::navigateToElement, searchedText = mainState.topAppBarState.searchedText)
                }

                if (changeThemeDialogVisible) {
                    ChangeThemeDialog(
                        themeState = mainState.themeState,
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
    themeConfigurations: Set<OdsThemeConfigurationContract>
): OdsThemeConfigurationContract {
    // Return the stored user theme configuration if it exists. If not, return the Orange theme configuration or the first existing theme configuration
    return themeConfigurations.firstOrNull { it.name == storedUserThemeName }
        .orElse { themeConfigurations.firstOrNull { it.isOrange } }
        .orElse { themeConfigurations.first() }
}

@Composable
private fun ChangeThemeDialog(themeState: MainThemeState, dismissDialog: () -> Unit, onThemeSelected: () -> Unit) {
    val selectedRadio = rememberSaveable { mutableStateOf(themeState.currentThemeConfiguration.name) }

    Dialog(onDismissRequest = dismissDialog) {
        Column(modifier = Modifier.background(OdsTheme.colors.surface)) {
            OdsTextH6(
                text = stringResource(R.string.top_app_bar_action_change_theme_desc),
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.spacing_m), bottom = dimensionResource(id = R.dimen.spacing_s))
                    .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
            )
            themeState.themeConfigurations.forEach { themeConfiguration ->
                OdsListItem(
                    text = themeConfiguration.name,
                    trailing = OdsRadioButtonTrailing(
                        selectedRadio = selectedRadio,
                        currentRadio = themeConfiguration.name,
                        onClick = {
                            if (themeConfiguration != themeState.currentThemeConfiguration) {
                                themeState.currentThemeConfiguration = themeConfiguration
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

@Composable
private fun SystemBarsColorSideEffect() {
    val systemUiController = rememberSystemUiController()
    val systemBarsBackground = OdsTheme.colors.component.systemBarsBackground
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = systemBarsBackground
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun MainTabs(mainTabsState: MainTabsState) {
    with(mainTabsState) {
        pagerState?.let { pagerState ->
            if (hasTabs) {
                // Do not use tabs directly because this is a SnapshotStateList
                // Thus its value can be modified and can lead to crashes if it becomes empty
                val tabs = tabs.toList()
                if (scrollableTabs.value) {
                    ScrollableTabRow(
                        tabs = tabs,
                        pagerState = pagerState,
                        tabIconType = tabIconType.value,
                        tabTextEnabled = tabTextEnabled.value
                    )
                } else {
                    FixedTabRow(
                        tabs = tabs,
                        pagerState = pagerState,
                        tabIconType = tabIconType.value,
                        tabTextEnabled = tabTextEnabled.value
                    )
                }
            }
        }
    }
}

private fun NavGraphBuilder.mainNavGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit, searchedText: MutableState<TextFieldValue>) {
    navigation(
        route = MainDestinations.HomeRoute,
        startDestination = BottomNavigationSections.Guidelines.route
    ) {
        addBottomNavigationGraph(navigateToElement)
    }

    addGuidelinesGraph()
    addComponentsGraph(navigateToElement)
    addAboutGraph()

    composable(
        route = MainDestinations.SearchRoute
    ) { from ->
        with(LocalMainTopAppBarManager.current) {
            reset()
            updateTopAppBarTitle(R.string.navigation_item_search)
        }
        SearchScreen(searchedText, onComponentClick = { id -> navigateToElement(MainDestinations.ComponentDetailRoute, id, from) })
    }
}
