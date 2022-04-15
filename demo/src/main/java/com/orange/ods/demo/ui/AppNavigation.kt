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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.about.AboutHtmlFileScreen
import com.orange.ods.demo.ui.about.AboutNavigationItem
import com.orange.ods.demo.ui.about.AboutScreen
import com.orange.ods.demo.ui.components.ComponentsNavigationItem
import com.orange.ods.demo.ui.components.ComponentsScreen
import com.orange.ods.demo.ui.components.ComponentsSubLevelNavigationItem
import com.orange.ods.demo.ui.components.bottomnavigation.ComponentsBottomNavigationScreen
import com.orange.ods.demo.ui.components.buttons.ComponentsButtonsScreen
import com.orange.ods.demo.ui.components.cards.ComponentsCardImageFirstScreen
import com.orange.ods.demo.ui.components.cards.ComponentsCardSmallScreen
import com.orange.ods.demo.ui.components.cards.ComponentsCardTitleFirstScreen
import com.orange.ods.demo.ui.components.cards.ComponentsCardsScreen
import com.orange.ods.demo.ui.components.controls.ComponentsControlsCheckboxesScreen
import com.orange.ods.demo.ui.components.controls.ComponentsControlsRadioButtonsScreen
import com.orange.ods.demo.ui.components.controls.ComponentsControlsScreen
import com.orange.ods.demo.ui.components.controls.ComponentsControlsSlidersScreen
import com.orange.ods.demo.ui.components.controls.ComponentsControlsSwitchesScreen
import com.orange.ods.demo.ui.components.lists.ComponentsListsScreen
import com.orange.ods.demo.ui.components.progress.ComponentsProgressScreen
import com.orange.ods.demo.ui.guidelines.GuidelinesNavigationItem
import com.orange.ods.demo.ui.guidelines.GuidelinesScreen
import com.orange.ods.demo.ui.guidelines.colors.GuidelinesColorsScreen
import com.orange.ods.demo.ui.guidelines.typography.GuidelinesTypographyScreen
import com.orange.ods.demo.ui.modules.ModulesScreen

sealed class NavigationItem(
    @DrawableRes var icon: Int,
    @StringRes var title: Int,
    var route: String
) {
    object Guidelines : NavigationItem(R.drawable.ic_guideline_dna, R.string.navigation_item_guidelines, "guidelines")
    object Components : NavigationItem(R.drawable.ic_component_atom, R.string.navigation_item_components, "components")
    object Modules : NavigationItem(R.drawable.ic_module_molecule, R.string.navigation_item_modules, "modules")
    object About : NavigationItem(R.drawable.ic_info, R.string.navigation_item_about, "about")
}

@ExperimentalMaterialApi
@Composable
fun AppNavigation(navController: NavHostController, onSetScreenTitle: (String) -> Unit) {
    NavHost(navController, startDestination = NavigationItem.Guidelines.route) {

        // App bottom navigation
        composable(NavigationItem.Guidelines.route) {
            onSetScreenTitle(stringResource(id = NavigationItem.Guidelines.title))
            GuidelinesScreen(navController)
        }
        composable(NavigationItem.Components.route) {
            onSetScreenTitle(stringResource(id = NavigationItem.Components.title))
            ComponentsScreen(navController)
        }
        composable(NavigationItem.Modules.route) {
            onSetScreenTitle(stringResource(id = NavigationItem.Modules.title))
            ModulesScreen()
        }
        composable(NavigationItem.About.route) {
            onSetScreenTitle(stringResource(id = NavigationItem.About.title))
            AboutScreen(navController)
        }

        // Guidelines
        composable(GuidelinesNavigationItem.Color.route) {
            onSetScreenTitle(stringResource(id = GuidelinesNavigationItem.Color.title))
            GuidelinesColorsScreen()
        }
        composable(GuidelinesNavigationItem.Typography.route) {
            onSetScreenTitle(stringResource(id = GuidelinesNavigationItem.Typography.title))
            GuidelinesTypographyScreen()
        }

        // Components
        composable(ComponentsNavigationItem.BottomNavigation.route) {
            onSetScreenTitle(stringResource(id = ComponentsNavigationItem.BottomNavigation.title))
            ComponentsBottomNavigationScreen()
        }
        composable(ComponentsNavigationItem.Buttons.route) {
            onSetScreenTitle(stringResource(id = ComponentsNavigationItem.Buttons.title))
            ComponentsButtonsScreen()
        }
        composable(ComponentsNavigationItem.Cards.route) {
            onSetScreenTitle(stringResource(id = ComponentsNavigationItem.Cards.title))
            ComponentsCardsScreen(navController)
        }
        composable(ComponentsNavigationItem.Controls.route) {
            onSetScreenTitle(stringResource(id = ComponentsNavigationItem.Controls.title))
            ComponentsControlsScreen(navController)
        }
        composable(ComponentsNavigationItem.Progress.route) {
            onSetScreenTitle(stringResource(id = ComponentsNavigationItem.Progress.title))
            ComponentsProgressScreen()
        }
        composable(ComponentsNavigationItem.Lists.route) {
            onSetScreenTitle(stringResource(id = ComponentsNavigationItem.Lists.title))
            ComponentsListsScreen()
        }

        // Components sub-level
        composable(ComponentsSubLevelNavigationItem.CardImageFirst.route) {
            onSetScreenTitle(stringResource(id = ComponentsSubLevelNavigationItem.CardImageFirst.title))
            ComponentsCardImageFirstScreen()
        }
        composable(ComponentsSubLevelNavigationItem.CardTitleFirst.route) {
            onSetScreenTitle(stringResource(id = ComponentsSubLevelNavigationItem.CardTitleFirst.title))
            ComponentsCardTitleFirstScreen()
        }
        composable(ComponentsSubLevelNavigationItem.CardSmall.route) {
            onSetScreenTitle(stringResource(id = ComponentsSubLevelNavigationItem.CardSmall.title))
            ComponentsCardSmallScreen()
        }
        composable(ComponentsSubLevelNavigationItem.ControlsCheckboxes.route) {
            onSetScreenTitle(stringResource(id = ComponentsSubLevelNavigationItem.ControlsCheckboxes.title))
            ComponentsControlsCheckboxesScreen()
        }
        composable(ComponentsSubLevelNavigationItem.ControlsRadioButtons.route) {
            onSetScreenTitle(stringResource(id = ComponentsSubLevelNavigationItem.ControlsRadioButtons.title))
            ComponentsControlsRadioButtonsScreen()
        }
        composable(ComponentsSubLevelNavigationItem.ControlsSwitches.route) {
            onSetScreenTitle(stringResource(id = ComponentsSubLevelNavigationItem.ControlsSwitches.title))
            ComponentsControlsSwitchesScreen()
        }
        composable(ComponentsSubLevelNavigationItem.ControlsSliders.route) {
            onSetScreenTitle(stringResource(id = ComponentsSubLevelNavigationItem.ControlsSliders.title))
            ComponentsControlsSlidersScreen()
        }

        // About
        composable(
            AboutNavigationItem.HtmlFile.route.plus("/{${AboutNavigationItem.TITLE_RES_KEY}}/{${AboutNavigationItem.FILE_NAME_KEY}}"),
            arguments = listOf(
                navArgument(AboutNavigationItem.TITLE_RES_KEY) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            onSetScreenTitle(stringResource(id = arguments.getInt(AboutNavigationItem.TITLE_RES_KEY)))
            AboutHtmlFileScreen(arguments.getString(AboutNavigationItem.FILE_NAME_KEY))
        }

    }
}