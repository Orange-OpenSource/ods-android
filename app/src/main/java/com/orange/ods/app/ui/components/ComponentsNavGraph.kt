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
import com.orange.ods.app.ui.LocalMainTabsManager
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.MainDestinations
import com.orange.ods.app.ui.MainTopAppBarState
import com.orange.ods.app.ui.TopAppBarConfiguration
import com.orange.ods.app.ui.UiFramework

fun NavGraphBuilder.addComponentsGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit) {
    composable(
        "${MainDestinations.ComponentDetailRoute}/{${MainDestinations.ComponentIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        // Restore default values for tabs and top app bar
        LocalMainTabsManager.current.clearTopAppBarTabs()
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)

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
        val topAppBarConfiguration = MainTopAppBarState.DefaultConfiguration.newBuilder()
            .apply {
                val variant = variants.firstOrNull { it.id == variantId }
                if (variant?.uiFrameworks?.contains(UiFramework.Xml) == true) {
                    prependAction(TopAppBarConfiguration.Action.UiFramework)
                }
            }
            .build()
        LocalMainTopAppBarManager.current.updateTopAppBar(topAppBarConfiguration)
        ComponentVariantScreen(variantId = variantId)
    }

    composable(
        "${MainDestinations.ComponentDemoRoute}/{${MainDestinations.ComponentIdKey}}",
        arguments = listOf(navArgument(MainDestinations.ComponentIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.ComponentIdKey)
        val topAppBarConfiguration = MainTopAppBarState.DefaultConfiguration.newBuilder()
            .apply {
                val component = components.firstOrNull { it.id == componentId }
                if (component?.uiFrameworks?.contains(UiFramework.Xml) == true) {
                    prependAction(TopAppBarConfiguration.Action.UiFramework)
                }
            }
            .build()
        LocalMainTopAppBarManager.current.updateTopAppBar(topAppBarConfiguration)
        ComponentDemoScreen(componentId = componentId)
    }
}