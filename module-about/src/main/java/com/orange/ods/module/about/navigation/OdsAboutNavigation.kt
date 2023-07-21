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

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.orange.ods.module.about.OdsAboutMainScreen
import com.orange.ods.module.about.OdsAboutViewModel
import com.orange.ods.module.about.configuration.LocalOdsAboutModuleConfiguration

fun NavController.navigateToOdsAbout(navOptions: NavOptions? = null) {
    this.navigate(OdsAboutDestinations.AboutRoute, navOptions)
}

/**
 * Add this graph to your app in order to integrate the ODS About module.
 * Don't forget to configure the About module by providing LocalOdsAboutModuleConfiguration in CompositionLocal.
 */
fun NavGraphBuilder.aboutGraph(navController: NavController) {
    navigation(startDestination = OdsAboutDestinations.HomeRoute, route = OdsAboutDestinations.AboutRoute) {
        composable(route = OdsAboutDestinations.HomeRoute) { navBackStackEntry ->
            val configuration = LocalOdsAboutModuleConfiguration.current
            val aboutViewModel = navBackStackEntry.sharedViewModel<OdsAboutViewModel>(navController = navController)
            aboutViewModel.configureAboutModule(configuration)
            OdsAboutMainScreen(configuration = configuration, onAboutMenuItemClick = onAboutMenuItemClick(navController, configuration.menuItemById))
        }

        composable(
            "${OdsAboutDestinations.FileItemRoute}/{${AboutItemIdKey}}",
            arguments = listOf(navArgument(AboutItemIdKey) { type = NavType.LongType })
        ) { backStackEntry -> AboutFileScreen(navController = navController, navBackStackEntry = backStackEntry) }
    }
}