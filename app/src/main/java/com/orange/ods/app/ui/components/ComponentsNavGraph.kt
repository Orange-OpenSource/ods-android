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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orange.ods.app.ui.AppNavController

object ComponentsNavigation {
    const val ComponentDetailRoute = "component"
    const val ComponentVariantDemoRoute = "component/variant"
    const val ComponentDemoRoute = "component/demo"

    const val ComponentIdKey = "componentId"
    const val ComponentVariantIdKey = "componentVariantId"
}

fun NavGraphBuilder.addComponentsGraph(navController: AppNavController, upPress: () -> Unit) {

    composable(
        "${ComponentsNavigation.ComponentDetailRoute}/{${ComponentsNavigation.ComponentIdKey}}",
        arguments = listOf(navArgument(ComponentsNavigation.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeComponentId = arguments.getLong(ComponentsNavigation.ComponentIdKey)

        val component = remember(routeComponentId) { Component.fromId(routeComponentId) }
        component?.let {
            ComponentDetailScreen(
                component = component,
                onVariantClick = { variantId -> navController.navigateToElement(ComponentsNavigation.ComponentVariantDemoRoute, variantId, from) },
                onDemoClick = { navController.navigateToElement(ComponentsNavigation.ComponentDemoRoute, routeComponentId, from) }
            )
        }
    }

    composable(
        "${ComponentsNavigation.ComponentVariantDemoRoute}/{${ComponentsNavigation.ComponentVariantIdKey}}",
        arguments = listOf(navArgument(ComponentsNavigation.ComponentVariantIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeVariantId = arguments.getLong(ComponentsNavigation.ComponentVariantIdKey)
        val variant = remember(routeVariantId) { Variant.fromId(routeVariantId) }
        val component = components.firstOrNull { it.variants.contains(variant) }

        if (variant != null && component != null) {
            component.demoScreen(variant, upPress)
        }
    }

    composable(
        "${ComponentsNavigation.ComponentDemoRoute}/{${ComponentsNavigation.ComponentIdKey}}",
        arguments = listOf(navArgument(ComponentsNavigation.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(ComponentsNavigation.ComponentIdKey)
        val component = remember { components.firstOrNull { it.id == componentId } }

        component?.let {
            component.demoScreen(null, upPress)
        }

    }
}