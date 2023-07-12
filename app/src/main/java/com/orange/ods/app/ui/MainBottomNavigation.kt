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
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.app.R
import com.orange.ods.app.ui.about.AboutScreen
import com.orange.ods.app.ui.about.UrlAboutItem
import com.orange.ods.app.ui.about.aboutItems
import com.orange.ods.app.ui.about.id
import com.orange.ods.app.ui.components.ComponentsScreen
import com.orange.ods.app.ui.guidelines.GuidelinesScreen
import com.orange.ods.app.ui.modules.ModulesScreen
import com.orange.ods.app.ui.utilities.launchUrl
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItem

@Composable
fun MainBottomNavigation(items: Array<BottomNavigationSections>, currentRoute: String, navigateToRoute: (String) -> Unit) {
    OdsBottomNavigation {
        items.forEach { item ->
            OdsBottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.iconRes), contentDescription = null) },
                label = stringResource(id = item.titleRes),
                selected = currentRoute == item.route,
                onClick = { navigateToRoute(item.route) }
            )
        }
    }
}

fun NavGraphBuilder.addBottomNavigationGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit) {
    composable(BottomNavigationSections.Guidelines.route) { from ->
        val topAppBarConfiguration = MainTopAppBarState.DefaultConfiguration.newBuilder()
            .prependAction(TopAppBarConfiguration.Action.Search)
            .build()
        LocalMainTopAppBarManager.current.updateTopAppBar(topAppBarConfiguration)
        GuidelinesScreen(onGuidelineClick = { route -> navigateToElement(route, null, from) })
    }
    composable(BottomNavigationSections.Components.route) { from ->
        val topAppBarConfiguration = MainTopAppBarState.DefaultConfiguration.newBuilder()
            .prependAction(TopAppBarConfiguration.Action.Search)
            .build()
        LocalMainTopAppBarManager.current.updateTopAppBar(topAppBarConfiguration)
        ComponentsScreen(onComponentClick = { id -> navigateToElement(MainDestinations.ComponentDetailRoute, id, from) })
    }
    composable(BottomNavigationSections.Modules.route) {
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
        ModulesScreen()
    }
    composable(BottomNavigationSections.About.route) { from ->
        val context = LocalContext.current
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
        AboutScreen(onAboutItemClick = { id ->
            val aboutItem = aboutItems.firstOrNull { it.id == id }
            if (aboutItem is UrlAboutItem) {
                context.launchUrl(aboutItem.url)
            } else {
                navigateToElement(MainDestinations.AboutItemDetailRoute, id, from)
            }
        })
    }
}

enum class BottomNavigationSections(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: String
) {
    Guidelines(R.string.navigation_item_guidelines, R.drawable.ic_guideline_dna, "main/guidelines"),
    Components(R.string.navigation_item_components, R.drawable.ic_component_atom, "main/components"),
    Modules(R.string.navigation_item_modules, R.drawable.ic_module_molecule, "main/modules"),
    About(R.string.navigation_item_about, R.drawable.ic_info, "main/about");
}