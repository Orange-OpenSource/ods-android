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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 * Destinations used in the [MainScreen].
 */
object MainDestinations {
    const val HomeRoute = "home"

    const val GuidelineTypography = "guideline/typography"
    const val GuidelineColor = "guideline/color"
    const val GuidelineSpacing = "guideline/spacing"

    const val ComponentDetailRoute = "component"
    const val ComponentIdKey = "componentId"
    const val ComponentVariantDemoRoute = "component/variant"
    const val ComponentVariantIdKey = "componentVariantId"
    const val ComponentDemoRoute = "component/demo"

    const val SearchRoute = "search"
}

@Composable
fun rememberMainState(
    themeState: ThemeState,
    navController: NavHostController = rememberNavController(),
    topAppBarState: MainTopAppBarState = rememberMainTopAppBarState(),
    uiFramework: MutableState<UiFramework> = rememberSaveable { mutableStateOf(UiFramework.Compose) }
) =
    remember(themeState, navController, topAppBarState, uiFramework) {
        MainState(themeState, navController, topAppBarState, uiFramework)
    }

class MainState(
    val themeState: ThemeState,
    val navController: NavHostController,
    val topAppBarState: MainTopAppBarState,
    val uiFramework: MutableState<UiFramework>
) {

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    val bottomBarItems = BottomNavigationSections.values()
    private val bottomBarRoutes = bottomBarItems.map { it.route }

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        with(topAppBarState) {
            updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
            clearTopAppBarTabs()
        }
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigateToBottomBarRoute(route)
        }
    }
}