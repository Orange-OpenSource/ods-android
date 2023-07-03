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

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.MainDestinations
import com.orange.ods.app.ui.MainTopAppBarState

fun NavGraphBuilder.addComponentsGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit) {

    composable(
        "${MainDestinations.ComponentDetailRoute}/{${MainDestinations.ComponentIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        // Lot of recompositions with this line
        LocalMainTopAppBarManager.current.reset()

        val arguments = requireNotNull(from.arguments)
        var currentComponentId: Long by remember { mutableStateOf(-1) }
        val routeComponentId = arguments.getLong(MainDestinations.ComponentIdKey)
        val shouldUpdateTitle by remember {
            derivedStateOf { currentComponentId != routeComponentId }
        }

        val component = remember(routeComponentId) { components.firstOrNull { component -> component.id == routeComponentId } }
        component?.let {
            if (shouldUpdateTitle) {
                LocalMainTopAppBarManager.current.updateTopAppBarTitle(component.titleRes)
                currentComponentId = routeComponentId
            }
            ComponentDetailScreen(
                component = component,
                onVariantClick = { variantId -> navigateToElement(MainDestinations.ComponentVariantRoute, variantId, from) },
                onDemoClick = { navigateToElement(MainDestinations.ComponentDemoRoute, routeComponentId, from) }
            )
        }
    }

    composable(
        "${MainDestinations.ComponentVariantRoute}/{${MainDestinations.ComponentVariantIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentVariantIdKey) { type = NavType.LongType })
    ) { from ->
        // Lot of recompositions with this line
        LocalMainTopAppBarManager.current.reset()

        val arguments = requireNotNull(from.arguments)
        var currentVariantId: Long by remember { mutableStateOf(-1) }
        val routeVariantId = arguments.getLong(MainDestinations.ComponentVariantIdKey)
        val variant = remember(routeVariantId) { components.flatMap { it.variants }.firstOrNull { it.id == routeVariantId } }
        val shouldUpdateTitle by remember {
            derivedStateOf { currentVariantId != routeVariantId }
        }

        variant?.let {
            if (shouldUpdateTitle) {
                LocalMainTopAppBarManager.current.updateTopAppBarTitle(variant.titleRes)
                currentVariantId = routeVariantId
            }
            if (variant.largeTopAppBar) LocalMainTopAppBarManager.current.setLargeTopAppBar(true)
            variant.screenContent()
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
            component.screenContent?.let { it() }
        }

    }
}