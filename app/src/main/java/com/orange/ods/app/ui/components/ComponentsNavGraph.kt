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
        LocalMainTopAppBarManager.current.reset()

        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.ComponentIdKey)
        ComponentDetailScreen(
            componentId = componentId,
            onVariantClick = { variantId -> navigateToElement(MainDestinations.ComponentVariantRoute, variantId, from) },
            onDemoClick = { navigateToElement(MainDestinations.ComponentDemoRoute, componentId, from) }
        )
    }

    composable(
        "${MainDestinations.ComponentVariantRoute}/{${MainDestinations.ComponentVariantIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentVariantIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val variantId = arguments.getLong(MainDestinations.ComponentVariantIdKey)
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
        ComponentVariantScreen(variantId = variantId)
    }

    composable(
        "${MainDestinations.ComponentDemoRoute}/{${MainDestinations.ComponentIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.ComponentIdKey)
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
        ComponentDemoScreen(componentId = componentId)
    }
}