/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
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
import com.orange.ods.module.about.ui.configuration.OdsAboutConfiguration

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
    navigateToAboutDemo: () -> Unit,
    aboutConfiguration: () -> OdsAboutConfiguration
) {
    addBottomBarGraph(navController, aboutConfiguration)

    addGuidelinesGraph()
    addComponentsGraph(navController)
    addModulesGraph(navController, navigateToAboutDemo)

    composable(
        route = MainNavigation.SearchRoute
    ) { from ->
        SearchScreen(onResultItemClick = { route, id -> navigateToElement(route, id, from) })
    }
}
