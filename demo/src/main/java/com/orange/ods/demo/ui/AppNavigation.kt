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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orange.ods.demo.R

sealed class NavigationItem(
    @DrawableRes var icon: Int,
    @StringRes var title: Int,
    var route: String
) {
    object Guidelines : NavigationItem(R.drawable.ic_guide_help, R.string.navigation_item_guidelines,"guidelines")
    object Components : NavigationItem(R.drawable.ic_folder, R.string.navigation_item_components, "components")
    object Modules : NavigationItem(R.drawable.ic_layers, R.string.navigation_item_modules, "modules")
    object About : NavigationItem(R.drawable.ic_info, R.string.navigation_item_about, "about")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Guidelines.route) {
        composable(NavigationItem.Guidelines.route) {
            GuidelinesScreen()
        }
        composable(NavigationItem.Components.route) {
            ComponentsScreen()
        }
        composable(NavigationItem.Modules.route) {
            ModulesScreen()
        }
        composable(NavigationItem.About.route) {
            AboutScreen()
        }
    }
}