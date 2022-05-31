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

import android.content.res.Configuration.UI_MODE_NIGHT_NO
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
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItem
import com.orange.ods.compose.theme.OdsMaterialTheme
import com.orange.ods.demo.ui.about.addAboutGraph
import com.orange.ods.demo.ui.components.addComponentsGraph
import com.orange.ods.demo.ui.components.tabs.TabsConfiguration
import com.orange.ods.demo.ui.components.tabs.TopAppBarFixedTabs
import com.orange.ods.demo.ui.components.tabs.TopAppBarScrollableTabs
import com.orange.ods.demo.ui.guidelines.addGuidelinesGraph

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun OdsDemoApp() {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val appState = rememberOdsDemoAppState(darkModeEnabled = rememberSaveable { mutableStateOf(isSystemInDarkTheme) })

    // Change isSystemInDarkTheme() value to make switching theme working with custom color
    if (appState.darkModeEnabled.value) {
        LocalConfiguration.current.uiMode = UI_MODE_NIGHT_YES
    } else {
        LocalConfiguration.current.uiMode = UI_MODE_NIGHT_NO
    }

    OdsMaterialTheme(appState.darkModeEnabled.value) {
        Scaffold(
            topBar = {
                Surface(elevation = AppBarDefaults.TopAppBarElevation) {
                    Column {
                        OdsDemoTopAppBar(
                            titleRes = appState.topAppBarTitleRes.value,
                            darkModeEnabled = appState.darkModeEnabled.value,
                            shouldShowUpNavigationIcon = !appState.shouldShowBottomBar,
                            navigateUp = appState::upPress,
                            updateTheme = appState::updateTheme
                        )
                        // Display tabs in the top bar if needed
                        with(appState.tabsState) {
                            pagerState?.let { pagerState ->
                                if (hasTabs) {
                                    if (scrollableTabs.value) {
                                        TopAppBarScrollableTabs(
                                            tabs = tabs,
                                            pagerState = pagerState,
                                            tabIconType = tabIconType.value,
                                            tabTextEnabled = tabTextEnabled.value
                                        )
                                    } else {
                                        TopAppBarFixedTabs(
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
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = appState.shouldShowBottomBar,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    OdsDemoBottomBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute
                    )
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(appState.navController, startDestination = MainDestinations.HOME_ROUTE) {
                    odsDemoNavGraph(
                        onNavElementClick = appState::navigateToElement,
                        updateTopBarTitle = appState::updateTopAppBarTitle,
                        updateTopAppBarTabs = appState.tabsState::updateTopAppBarTabs,
                        clearTopAppBarTabs = appState.tabsState::clearTopAppBarTabs
                    )
                }
            }
        }
    }
}

@Composable
private fun OdsDemoBottomBar(tabs: Array<HomeSections>, currentRoute: String, navigateToRoute: (String) -> Unit) {
    OdsBottomNavigation {
        tabs.forEach { tab ->
            OdsBottomNavigationItem(
                icon = { Icon(painter = painterResource(id = tab.iconRes), contentDescription = null) },
                label = stringResource(id = tab.titleRes),
                selected = currentRoute == tab.route,
                onClick = { navigateToRoute(tab.route) }
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.odsDemoNavGraph(
    onNavElementClick: (String, Long?, NavBackStackEntry) -> Unit,
    updateTopBarTitle: (Int) -> Unit,
    updateTopAppBarTabs: (TabsConfiguration) -> Unit,
    clearTopAppBarTabs: () -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.GUIDELINES.route
    ) {
        addHomeGraph(onNavElementClick, updateTopBarTitle, clearTopAppBarTabs)
    }

    addGuidelinesGraph(updateTopBarTitle, clearTopAppBarTabs)
    addComponentsGraph(onNavElementClick, updateTopBarTitle, updateTopAppBarTabs, clearTopAppBarTabs)
    addAboutGraph(updateTopBarTitle, clearTopAppBarTabs)
}