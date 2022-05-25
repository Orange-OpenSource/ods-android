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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.rememberMutableStateListOf

/**
 * Destinations used in the [OdsDemoApp].
 */
object MainDestinations {
    const val HOME_ROUTE = "home"

    const val GUIDELINE_TYPOGRAPHY = "guideline/typography"
    const val GUIDELINE_COLORS = "guideline/colors"

    const val COMPONENT_DETAIL_ROUTE = "component"
    const val COMPONENT_ID_KEY = "componentId"
    const val COMPONENT_SUBTYPE_ROUTE = "component/subtype"

    const val ABOUT_ITEM_DETAIL_ROUTE = "aboutItem"
    const val ABOUT_ITEM_ID_KEY = "aboutItemId"
}

@Composable
fun rememberOdsDemoAppState(
    navController: NavHostController = rememberNavController(),
    topAppBarTitleRes: MutableState<Int> = rememberSaveable { mutableStateOf(R.string.navigation_item_guidelines) },
    odsThemes: SnapshotStateList<OdsTheme> = rememberMutableStateListOf(),
    darkModeEnabled: MutableState<Boolean>
) =
    remember(navController, topAppBarTitleRes, odsThemes, darkModeEnabled) {
        OdsDemoAppState(navController, topAppBarTitleRes, odsThemes, darkModeEnabled)
    }

class OdsDemoAppState(
    val navController: NavHostController,
    val topAppBarTitleRes: MutableState<Int>,
    val odsThemes: SnapshotStateList<OdsTheme>,
    val darkModeEnabled: MutableState<Boolean>
) {

    fun updateTheme(isDark: Boolean) {
        darkModeEnabled.value = isDark
    }

    // ----------------------------------------------------------
    // TopAppBar state source of truth
    // ----------------------------------------------------------

    fun updateTopAppBarTitle(titleRes: Int) {
        topAppBarTitleRes.value = titleRes
    }

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    val bottomBarTabs = HomeSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

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