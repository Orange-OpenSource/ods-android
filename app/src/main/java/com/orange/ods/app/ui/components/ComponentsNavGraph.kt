/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.MainDestinations
import com.orange.ods.app.ui.MainTopAppBarState
import com.orange.ods.app.ui.navigateToElement

fun NavGraphBuilder.addComponentsGraph(navController: NavController, upPress: () -> Unit) {

    composable(
        "${MainDestinations.ComponentDetailRoute}/{${MainDestinations.ComponentIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeComponentId = arguments.getLong(MainDestinations.ComponentIdKey)

        val component = remember(routeComponentId) { components.firstOrNull { component -> component.id == routeComponentId } }
        component?.let {
            LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
            LocalMainTopAppBarManager.current.updateTopAppBarTitle(component.titleRes)

            ComponentDetailScreen(
                component = component,
                onVariantClick = { variantId -> navController.navigateToElement(MainDestinations.ComponentVariantDemoRoute, variantId, from) },
                onDemoClick = { navController.navigateToElement(MainDestinations.ComponentDemoRoute, routeComponentId, from) }
            )
        }
    }

    composable(
        "${MainDestinations.ComponentVariantDemoRoute}/{${MainDestinations.ComponentVariantIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentVariantIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeVariantId = arguments.getLong(MainDestinations.ComponentVariantIdKey)
        val variant = remember(routeVariantId) { components.flatMap { it.variants }.firstOrNull { it.id == routeVariantId } }
        val component = components.firstOrNull { it.variants.contains(variant) }

        if (variant != null && component != null) {
            LocalMainTopAppBarManager.current.updateTopAppBarTitle(variant.titleRes)
            LocalMainTopAppBarManager.current.setLargeTopAppBar(variant.largeTopAppBar)
            component.demoScreen(variant, upPress)
        }
    }

    composable(
        "${MainDestinations.ComponentDemoRoute}/{${MainDestinations.ComponentIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)

        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.ComponentIdKey)
        val component = remember { components.firstOrNull { it.id == componentId } }

        component?.let {
            LocalMainTopAppBarManager.current.updateTopAppBarTitle(component.titleRes)
            component.demoScreen(null, upPress)
        }

    }
}