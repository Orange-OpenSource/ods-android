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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.app.R
import com.orange.ods.app.ui.about.AboutScreen
import com.orange.ods.app.ui.components.ComponentsScreen
import com.orange.ods.app.ui.guidelines.GuidelinesScreen
import com.orange.ods.app.ui.modules.ModulesScreen
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItem
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItemIcon

@Composable
fun MainBottomNavigation(items: Array<BottomNavigationSections>, currentRoute: String, navigateToRoute: (String) -> Unit) {
    OdsBottomNavigation(
        items = items.map { item ->
            OdsBottomNavigationItem(
                icon = OdsBottomNavigationItemIcon(painter = painterResource(id = item.iconRes), contentDescription = ""),
                label = stringResource(id = item.titleRes),
                selected = currentRoute == item.route,
                onClick = { navigateToRoute(item.route) }
            )
        }
    )
}

fun NavGraphBuilder.addBottomNavigationGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit) {
    composable(BottomNavigationSections.Guidelines.route) { from ->
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultWithSearchActionConfiguration)
        GuidelinesScreen(onGuidelineClick = { route -> navigateToElement(route, null, from) })
    }
    composable(BottomNavigationSections.Components.route) { from ->
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultWithSearchActionConfiguration)
        ComponentsScreen(onComponentClick = { id -> navigateToElement(MainDestinations.ComponentDetailRoute, id, from) })
    }
    composable(BottomNavigationSections.Modules.route) { from ->
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultWithSearchActionConfiguration)
        ModulesScreen(onModuleClick = { id -> navigateToElement(MainDestinations.ModuleDetailRoute, id, from) })
    }
    composable(BottomNavigationSections.About.route) { _ ->
        //val context = LocalContext.current
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
        AboutScreen()/*onAboutItemClick = { id ->
            val aboutItem = aboutItems.firstOrNull { it.id == id }
            if (aboutItem is UrlAboutItem) {
                context.launchUrl(aboutItem.url)
            } else {
                navigateToElement(MainDestinations.AboutItemDetailRoute, id, from)
            }
        })*/
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