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
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppNavigationState(navController: NavHostController = rememberNavController()) = remember(navController) { AppNavigationState(navController) }

class AppNavigationState(val navController: NavHostController) {
    val previousRoute: String?
        get() = navController.previousBackStackEntry?.destination?.route

    val currentRoute: String?
        get() = navController.currentDestination?.route

    val currentScreen: Screen?
        @Composable get() {
            val routeArgs = navController.currentBackStackEntryAsState().value?.arguments
            return currentRoute?.let { getScreen(it, routeArgs) }
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

    fun upPress() {
        navController.navigateUp()
    }
}

fun NavController.navigateToElement(route: String, elementId: Long?, from: NavBackStackEntry) {
    // In order to discard duplicated navigation events, we check the Lifecycle
    if (from.lifecycleIsResumed()) {
        val fullRoute = if (elementId != null) "$route/$elementId" else route
        navigate(fullRoute)
    }
}

fun NavController.navigateToBottomBarRoute(route: String) {
    navigate(route) {
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
        // Pop up backstack to the first destination and save state. This makes going back
        // to the start destination when pressing back in any other bottom tab.
        popUpTo(findStartDestination(graph).id) {
            saveState = true
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.getLifecycle().currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
