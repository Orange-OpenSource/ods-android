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
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
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
    const val ComponentVariantRoute = "component/variant"
    const val ComponentVariantIdKey = "componentVariantId"
    const val ComponentDemoRoute = "component/demo"

    const val AboutItemDetailRoute = "aboutItem"
    const val AboutItemIdKey = "aboutItemId"

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
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
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

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

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