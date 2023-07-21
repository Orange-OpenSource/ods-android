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
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalCategories
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.about.addAboutGraph
import com.orange.ods.app.ui.components.addComponentsGraph
import com.orange.ods.app.ui.components.tabs.FixedTabRow
import com.orange.ods.app.ui.components.tabs.ScrollableTabRow
import com.orange.ods.app.ui.guidelines.addGuidelinesGraph
import com.orange.ods.app.ui.modules.addModulesGraph
import com.orange.ods.app.ui.search.SearchScreen
import com.orange.ods.app.ui.utilities.extension.isDarkModeEnabled
import com.orange.ods.app.ui.utilities.extension.isOrange
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.extension.orElse
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.xml.theme.OdsXml
import com.orange.ods.xml.utilities.extension.xml

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(themeConfigurations: Set<OdsThemeConfigurationContract>, mainViewModel: MainViewModel = viewModel()) {
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
            themeConfigurations = themeConfigurations.toList()
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

    CompositionLocalProvider(
        LocalConfiguration provides configuration,
        LocalMainTopAppBarManager provides mainState.topAppBarState,
        LocalThemeManager provides mainState.themeState,
        LocalOdsGuideline provides mainState.themeState.currentThemeConfiguration.guideline,
        LocalRecipes provides mainViewModel.recipes,
        LocalCategories provides mainViewModel.categories,
        LocalUiFramework provides mainState.uiFramework
    ) {
        OdsTheme(
            themeConfiguration = mainState.themeState.currentThemeConfiguration,
            darkThemeEnabled = configuration.isDarkModeEnabled
        ) {
            val topBarScrollBehavior: TopAppBarScrollBehavior?
            val modifier: Modifier

            val showTabs by remember {
                derivedStateOf { mainState.topAppBarState.tabsState.hasTabs }
            }
            val displayLargeTopAppBar by remember {
                derivedStateOf { mainState.topAppBarState.isLarge }
            }

            if (displayLargeTopAppBar) {
                topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
                val nestedScrollConnection = remember { topBarScrollBehavior.nestedScrollConnection }
                modifier = Modifier.nestedScroll(nestedScrollConnection)
            } else {
                topBarScrollBehavior = null
                modifier = Modifier
            }

            Scaffold(
                modifier = modifier,
                topBar = {
                    if (mainState.shouldShowTopAppBar) {
                        Surface(elevation = if (isSystemInDarkTheme()) 0.dp else AppBarDefaults.TopAppBarElevation) {
                            Column {
                                SystemBarsColorSideEffect()
                                MainTopAppBar(
                                    shouldShowUpNavigationIcon = !mainState.shouldShowBottomBar,
                                    topAppBarState = mainState.topAppBarState,
                                    upPress = mainState::upPress,
                                    onSearchActionClick = {
                                        mainState.navController.navigate(MainDestinations.SearchRoute)
                                    },
                                    scrollBehavior = topBarScrollBehavior
                                )
                                if (showTabs) {
                                    MainTabs(mainTabsState = mainState.topAppBarState.tabsState)
                                }
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
                    navController = mainState.navController, startDestination = MainDestinations.HomeRoute, modifier = Modifier.padding(innerPadding)
                ) {
                    mainNavGraph(
                        navigateToElement = mainState::navigateToElement,
                        upPress = mainState::upPress,
                        searchedText = mainState.topAppBarState.searchedText
                    )
                }
            }
        }
    }
}

private fun getCurrentThemeConfiguration(storedUserThemeName: String?, themeConfigurations: Set<OdsThemeConfigurationContract>): OdsThemeConfigurationContract {
    // Return the stored user theme configuration if it exists. If not, return the Orange theme configuration or the first existing theme configuration
    return themeConfigurations.firstOrNull { it.name == storedUserThemeName }
        .orElse { themeConfigurations.firstOrNull { it.isOrange } }
        .orElse { themeConfigurations.first() }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainTabs(mainTabsState: MainTabsState) {
    with(mainTabsState) {
        pagerState?.let { pagerState ->
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

private fun NavGraphBuilder.mainNavGraph(
    navigateToElement: (String, Long?, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    searchedText: MutableState<TextFieldValue>
) {
    navigation(
        route = MainDestinations.HomeRoute,
        startDestination = BottomNavigationSections.Guidelines.route
    ) {
        addBottomNavigationGraph(navigateToElement)
    }

    addGuidelinesGraph()
    addComponentsGraph(navigateToElement, upPress)
    addModulesGraph(navigateToElement, upPress)
    addAboutGraph()

    composable(
        route = MainDestinations.SearchRoute
    ) { from ->
        LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.navigation_item_search)
        SearchScreen(
            searchedText,
            onResultItemClick = { route, id -> navigateToElement(route, id, from) }
        )
    }
}
