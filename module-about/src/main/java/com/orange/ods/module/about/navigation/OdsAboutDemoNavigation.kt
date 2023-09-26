/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.navigation

import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.orange.ods.module.about.OdsAboutHomeScreen
import com.orange.ods.module.about.OdsAboutViewModel


fun NavController.navigateToOdsAboutDemo(navOptions: NavOptions? = null) {
    this.navigate(OdsAboutDestinations.AboutDemoRoute, navOptions)
}

/**
 * Graph used only for About module demonstration purpose.
 */
fun NavGraphBuilder.aboutDemoGraph(navController: NavController) {
    navigation(startDestination = OdsAboutDestinations.DemoHomeRoute, route = OdsAboutDestinations.AboutDemoRoute) {
        composable(route = OdsAboutDestinations.DemoHomeRoute) { navBackStackEntry ->
            val callerViewModel = navController.previousBackStackEntry?.let { (viewModel(it) as OdsAboutViewModel) }
            val configuration = remember { callerViewModel?.configuration }
            val aboutViewModel = navBackStackEntry.sharedViewModel<OdsAboutViewModel>(navController = navController)
            configuration?.let { aboutViewModel.configureAboutModule(configuration) }
            OdsAboutHomeScreen(
                configuration = configuration,
                onAboutMenuItemClick = onAboutMenuItemClick(navController, configuration?.menuItemById.orEmpty(), demo = true)
            )
        }

        composable(
            "${OdsAboutDestinations.DemoFileItemRoute}/{${AboutItemIdKey}}",
            arguments = listOf(navArgument(AboutItemIdKey) { type = NavType.LongType })
        ) { backStackEntry ->
            AboutFileScreen(navController = navController, navBackStackEntry = backStackEntry)
        }
    }
}