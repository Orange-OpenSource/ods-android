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

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.app.ui.components.addComponentsGraph
import com.orange.ods.app.ui.guidelines.addGuidelinesGraph
import com.orange.ods.app.ui.modules.addModulesGraph
import com.orange.ods.app.ui.search.SearchScreen

/**
 * Destinations used in the [MainScreen].
 */
object MainNavigation {
    const val SearchRoute = "search"
}

/**
 * Navigation graph of the application.
 */
fun NavGraphBuilder.appNavGraph(
    navController: NavController,
    navigateToElement: (String, Long?, NavBackStackEntry) -> Unit,
    navigateToAboutModule: () -> Unit,
    upPress: () -> Unit
) {
    addBottomBarGraph(navController)

    addGuidelinesGraph()
    addComponentsGraph(navController, upPress)
    addModulesGraph(navigateToAboutModule)

    composable(
        route = MainNavigation.SearchRoute
    ) { from ->
        SearchScreen(onResultItemClick = { route, id -> navigateToElement(route, id, from) })
    }
}
