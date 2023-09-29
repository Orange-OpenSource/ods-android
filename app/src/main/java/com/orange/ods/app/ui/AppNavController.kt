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

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator

/**
 * Subclass of [NavHostController] which manage app navigation. It provides some additional functions to navigate in the application.
 */
class AppNavController(context: Context) : NavHostController(context) {

    fun navigateToElement(route: String, elementId: Long?, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            val fullRoute = if (elementId != null) "$route/$elementId" else route
            navigate(fullRoute)
        }
    }

    fun navigateToBottomBarRoute(route: String) {
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


@Composable
fun rememberAppNavController(
    vararg navigators: Navigator<out NavDestination>
): AppNavController {
    val context = LocalContext.current
    return rememberSaveable(inputs = navigators, saver = appNavControllerSaver(context)) {
        createAppNavController(context)
    }.apply {
        for (navigator in navigators) {
            navigatorProvider.addNavigator(navigator)
        }
    }
}

private fun createAppNavController(context: Context) =
    AppNavController(context).apply {
        navigatorProvider.addNavigator(ComposeNavigator())
        navigatorProvider.addNavigator(DialogNavigator())
    }


private fun appNavControllerSaver(
    context: Context
): Saver<AppNavController, *> = Saver(
    save = { it.saveState() },
    restore = { createAppNavController(context).apply { restoreState(it) } }
)