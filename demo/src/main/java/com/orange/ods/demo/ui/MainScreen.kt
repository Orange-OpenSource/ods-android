/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.ui.about.addAboutGraph
import com.orange.ods.demo.ui.components.addComponentsGraph
import com.orange.ods.demo.ui.components.tabs.FixedTabRow
import com.orange.ods.demo.ui.components.tabs.ScrollableTabRow
import com.orange.ods.demo.ui.guidelines.addGuidelinesGraph
import com.orange.ods.demo.ui.utilities.extension.isDarkModeEnabled
import com.orange.ods.demo.ui.utilities.rememberSaveableMutableStateListOf
import com.orange.ods.theme.OdsThemeSettings

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun MainScreen(odsThemeSettings: Set<OdsThemeSettings>) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val mainState = rememberMainState(
        odsThemeSettings = rememberSaveableMutableStateListOf(elements = odsThemeSettings.toList()),
        currentThemeSettings = rememberSaveable { mutableStateOf(odsThemeSettings.first()) },
        darkModeEnabled = rememberSaveable { mutableStateOf(isSystemInDarkTheme) }
    )

    // Change isSystemInDarkTheme() value to make switching theme working with custom color
    val configuration = LocalConfiguration.current.apply {
        isDarkModeEnabled = mainState.darkModeEnabled.value
    }

    CompositionLocalProvider(
        LocalConfiguration provides configuration,
        LocalMainTopAppBarManager provides mainState.topAppBarState,
        LocalMainTabsManager provides mainState.tabsState,
        LocalOdsDemoGuideline provides mainState.currentThemeSettings.value.odsDemoGuideline
    ) {
        OdsTheme(
            odsThemeSettings = mainState.currentThemeSettings.value,
            darkThemeEnabled = configuration.isDarkModeEnabled
        ) {
            Scaffold(
                backgroundColor = OdsTheme.colors.background,
                topBar = {
                    Surface(elevation = AppBarDefaults.TopAppBarElevation) {
                        Column {
                            SystemBarsColorSideEffect(OdsTheme.colors.background)
                            MainTopAppBar(
                                titleRes = mainState.topAppBarState.titleRes.value,
                                shouldShowUpNavigationIcon = !mainState.shouldShowBottomBar,
                                state = mainState.topAppBarState,
                                upPress = mainState::upPress,
                                updateTheme = mainState::updateTheme
                            )
                            // Display tabs in the top bar if needed
                            MainTabs(mainTabsState = mainState.tabsState)
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
                Box(modifier = Modifier.padding(innerPadding)) {
                    NavHost(mainState.navController, startDestination = MainDestinations.HomeRoute) {
                        mainNavGraph(navigateToElement = mainState::navigateToElement)
                    }
                }
            }
        }
    }
}

@Composable
private fun SystemBarsColorSideEffect(backgroundColor: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = backgroundColor
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun MainTabs(mainTabsState: MainTabsState) {
    with(mainTabsState) {
        pagerState?.let { pagerState ->
            if (hasTabs) {
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

@ExperimentalPagerApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.mainNavGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit) {
    navigation(
        route = MainDestinations.HomeRoute,
        startDestination = BottomNavigationSections.Guidelines.route
    ) {
        addBottomNavigationGraph(navigateToElement)
    }

    addGuidelinesGraph()
    addComponentsGraph(navigateToElement)
    addAboutGraph()
}
