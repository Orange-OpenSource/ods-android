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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.about.AboutScreen
import com.orange.ods.demo.ui.components.ComponentsScreen
import com.orange.ods.demo.ui.guidelines.GuidelinesColorScreen
import com.orange.ods.demo.ui.guidelines.GuidelinesScreen
import com.orange.ods.demo.ui.modules.ModulesScreen

sealed class NavigationItem(
    @DrawableRes var icon: Int,
    @StringRes var title: Int,
    var route: String
) {
    object Guidelines : NavigationItem(R.drawable.ic_guideline_dna, R.string.navigation_item_guidelines,"guidelines")
    object Components : NavigationItem(R.drawable.ic_component_atom, R.string.navigation_item_components, "components")
    object Modules : NavigationItem(R.drawable.ic_module_molecule, R.string.navigation_item_modules, "modules")
    object About : NavigationItem(R.drawable.ic_info, R.string.navigation_item_about, "about")
}

sealed class GuidelinesNavigationItem(
    @StringRes var title: Int,
    var route: String
){
    object Color : GuidelinesNavigationItem(R.string.guideline_colour, "guidelines/color")
    object Typography : GuidelinesNavigationItem(R.string.guideline_typography, "guidelines/typography")
    object Imagery : GuidelinesNavigationItem(R.string.guideline_imagery, "guidelines/imagery")
    object Iconography : GuidelinesNavigationItem(R.string.guideline_iconography, "guidelines/iconography")
}

@Composable
fun AppNavigation(navController: NavHostController, onSetScreenTitle: (String) -> Unit) {
    NavHost(navController, startDestination = NavigationItem.Guidelines.route) {
        composable(NavigationItem.Guidelines.route) {
            onSetScreenTitle(stringResource(id = NavigationItem.Guidelines.title))
            GuidelinesScreen(navController)
        }
        composable(NavigationItem.Components.route) {
            onSetScreenTitle(stringResource(id = NavigationItem.Components.title))
            ComponentsScreen()
        }
        composable(NavigationItem.Modules.route) {
            onSetScreenTitle(stringResource(id = NavigationItem.Modules.title))
            ModulesScreen()
        }
        composable(NavigationItem.About.route) {
            onSetScreenTitle(stringResource(id = NavigationItem.About.title))
            AboutScreen()
        }
        composable(GuidelinesNavigationItem.Color.route){
            onSetScreenTitle(stringResource(id = GuidelinesNavigationItem.Color.title))
            GuidelinesColorScreen()
        }
    }
}