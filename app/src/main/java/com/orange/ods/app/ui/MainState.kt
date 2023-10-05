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
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.orange.ods.app.ui.modules.ModuleDemoDestinations

class MainState(
    val themeState: ThemeState,
    val navController: NavHostController,
    val appBarState: AppBarState,
    val uiFramework: MutableState<UiFramework>
) {

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    val bottomBarItems = BottomBarItem.values()
    private val bottomBarRoutes: List<String> = bottomBarItems.map { it.route }


    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes
                && navController.previousBackStackEntry?.destination?.route != ModuleDemoDestinations.AboutModuleCustomizationRoute


    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        appBarState.clearAppBarTabs()
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigateToBottomBarRoute(route)
        }
    }

    fun navigateToElement(route: String, elementId: Long?, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            val fullRoute = if (elementId != null) "$route/$elementId" else route
            navController.navigate(fullRoute)
        }
    }
}

@Composable
fun rememberMainState(
    themeState: ThemeState,
    navController: NavHostController = rememberNavController(),
    appBarState: AppBarState = rememberAppBarState(navController),
    uiFramework: MutableState<UiFramework> = rememberSaveable { mutableStateOf(UiFramework.Compose) }
) = remember(themeState, navController, appBarState, uiFramework) {
    MainState(themeState, navController, appBarState, uiFramework)
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() = this.getLifecycle().currentState == Lifecycle.State.RESUMED
