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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.ComponentsNavigation
import com.orange.ods.app.ui.components.ComponentsScreen
import com.orange.ods.app.ui.guidelines.GuidelinesScreen
import com.orange.ods.app.ui.modules.ModulesScreen
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.module.about.navigation.aboutGraph

@Composable
fun BottomBar(items: Array<BottomBarItem>, currentRoute: String, navigateToRoute: (String) -> Unit) {
    OdsBottomNavigation(
        items = items.map { item ->
            OdsBottomNavigation.Item(
                icon = OdsBottomNavigation.Item.Icon(painter = painterResource(id = item.iconRes), contentDescription = ""),
                label = stringResource(id = item.titleRes),
                selected = currentRoute == item.route || currentRoute in item.additionalRoutes,
                onClick = {
                    navigateToRoute(item.route)
                }
            )
        }
    )
}

fun NavGraphBuilder.addBottomBarGraph(navController: NavController) {
    composable(BottomBarItem.Guidelines.route) { from ->
        GuidelinesScreen(onGuidelineClick = { route -> navController.navigateToElement(route, null, from) })
    }
    composable(BottomBarItem.Components.route) { from ->
        ComponentsScreen(onComponentClick = { id -> navController.navigateToElement(ComponentsNavigation.ComponentDetailRoute, id, from) })
    }
    composable(BottomBarItem.Modules.route) { from ->
        ModulesScreen(onModuleClick = { route -> navController.navigateToElement(route, null, from) })
    }

    aboutGraph(navController)
}

enum class BottomBarItem(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: String,
    val additionalRoutes: List<String> = emptyList()
) {
    Guidelines(R.string.navigation_item_guidelines, R.drawable.ic_guideline_dna, "main/guidelines"),
    Components(R.string.navigation_item_components, R.drawable.ic_component_atom, "main/components"),
    Modules(R.string.navigation_item_modules, R.drawable.ic_module_molecule, "main/modules"),
    About(R.string.navigation_item_about, R.drawable.ic_info, OdsAboutDestinations.HomeRoute)
}