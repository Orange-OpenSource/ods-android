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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItem
import com.orange.ods.compose.theme.OdsMaterialTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.ui.about.addAboutGraph
import com.orange.ods.demo.ui.components.addComponentsGraph
import com.orange.ods.demo.ui.guidelines.addGuidelinesGraph
import com.orange.ods.demo.ui.utilities.rememberMutableStateListOf

@ExperimentalMaterialApi
@Composable
fun OdsDemoApp(odsThemes: Set<OdsTheme>) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val appState = rememberOdsDemoAppState(
        odsThemes = rememberMutableStateListOf(elements = odsThemes.toList()),
        darkModeEnabled = rememberSaveable { mutableStateOf(isSystemInDarkTheme) })

    // Change isSystemInDarkTheme() value to make switching theme working with custom color
    if (appState.darkModeEnabled.value) {
        LocalConfiguration.current.uiMode = UI_MODE_NIGHT_YES
    } else {
        LocalConfiguration.current.uiMode = UI_MODE_NIGHT_NO
    }

    OdsMaterialTheme(
        odsTheme = appState.odsThemes.first(),
        darkThemeEnabled = appState.darkModeEnabled.value
    ) {
        Scaffold(
            topBar = {
                OdsDemoTopAppBar(
                    titleRes = appState.topAppBarTitleRes.value,
                    darkModeEnabled = appState.darkModeEnabled.value,
                    shouldShowUpNavigationIcon = !appState.shouldShowBottomBar,
                    navigateUp = appState::upPress,
                    updateTheme = appState::updateTheme
                )
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
                        updateTopBarTitle = appState::updateTopAppBarTitle
                    )
                }
            }
        }
    }
}

@Composable
private fun OdsDemoBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    OdsBottomNavigation {
        tabs.forEach { tab ->
            OdsBottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = tab.iconRes),
                        contentDescription = null
                    )
                },
                label = stringResource(id = tab.titleRes),
                selected = currentRoute == tab.route,
                onClick = { navigateToRoute(tab.route) }
            )
        }
    }
}

@ExperimentalMaterialApi
private fun NavGraphBuilder.odsDemoNavGraph(
    onNavElementClick: (String, Long?, NavBackStackEntry) -> Unit,
    updateTopBarTitle: (Int) -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.GUIDELINES.route
    ) {
        addHomeGraph(onNavElementClick, updateTopBarTitle)
    }

    addGuidelinesGraph(updateTopBarTitle)
    addComponentsGraph(onNavElementClick, updateTopBarTitle)
    addAboutGraph(updateTopBarTitle)
}