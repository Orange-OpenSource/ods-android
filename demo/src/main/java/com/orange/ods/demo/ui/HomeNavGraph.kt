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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.about.AboutScreen
import com.orange.ods.demo.ui.components.ComponentsScreen
import com.orange.ods.demo.ui.components.tabs.LocalTabsManager
import com.orange.ods.demo.ui.guidelines.GuidelinesScreen
import com.orange.ods.demo.ui.modules.ModulesScreen

@ExperimentalMaterialApi
fun NavGraphBuilder.addHomeGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit) {
    composable(HomeSections.GUIDELINES.route) { from ->
        LocalTabsManager.current.clearTopAppBarTabs()
        GuidelinesScreen(onGuidelineClick = { route -> navigateToElement(route, null, from) })
    }
    composable(HomeSections.COMPONENTS.route) { from ->
        LocalTabsManager.current.clearTopAppBarTabs()
        ComponentsScreen(onComponentClick = { id -> navigateToElement(MainDestinations.COMPONENT_DETAIL_ROUTE, id, from) })
    }
    composable(HomeSections.MODULES.route) {
        LocalTabsManager.current.clearTopAppBarTabs()
        ModulesScreen()
    }
    composable(HomeSections.ABOUT.route) { from ->
        LocalTabsManager.current.clearTopAppBarTabs()
        AboutScreen(onAboutItemClick = { id -> navigateToElement(MainDestinations.ABOUT_ITEM_DETAIL_ROUTE, id, from) })
    }
}

enum class HomeSections(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: String
) {
    GUIDELINES(R.string.navigation_item_guidelines, R.drawable.ic_guideline_dna, "home/guidelines"),
    COMPONENTS(R.string.navigation_item_components, R.drawable.ic_component_atom, "home/components"),
    MODULES(R.string.navigation_item_modules, R.drawable.ic_module_molecule, "home/modules"),
    ABOUT(R.string.navigation_item_about, R.drawable.ic_info, "home/about");
}