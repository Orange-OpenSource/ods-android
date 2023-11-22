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

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
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

fun NavController.navigateToOdsAbout(navOptions: NavOptions? = null) {
    navigate(OdsAboutDestinations.HomeRoute, navOptions)
}

/**
 * Add this graph to your app in order to integrate the ODS About module.
 */
fun NavGraphBuilder.aboutGraph(navController: NavController) {
    navigation(startDestination = OdsAboutDestinations.HomeRoute, route = OdsAboutDestinations.AboutRoute) {
        composable(route = OdsAboutDestinations.HomeRoute) {
            val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
            viewModel<OdsAboutViewModel>(viewModelStoreOwner).configuration?.let { configuration ->
                OdsAboutHomeScreen(
                    configuration = configuration,
                    onAboutMenuItemClick = onAboutMenuItemClick(navController, configuration.menuItemById)
                )
            }
        }

        composable(
            "${OdsAboutDestinations.FileItemRoute}/{${AboutItemIdKey}}",
            arguments = listOf(navArgument(AboutItemIdKey) { type = NavType.LongType })
        ) { backStackEntry -> AboutFileScreen(navBackStackEntry = backStackEntry) }
    }
}