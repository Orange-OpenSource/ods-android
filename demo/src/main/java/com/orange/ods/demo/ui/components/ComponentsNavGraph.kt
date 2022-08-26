/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.orange.ods.demo.ui.MainDestinations
import com.orange.ods.demo.ui.OdsDemoTopAppBarState
import com.orange.ods.demo.ui.TopAppBarConfiguration
import com.orange.ods.demo.ui.components.tabs.TabsConfiguration

@ExperimentalMaterialApi
@ExperimentalPagerApi
fun NavGraphBuilder.addComponentsGraph(
    onNavElementClick: (String, Long?, NavBackStackEntry) -> Unit,
    updateTopBarTitle: (Int) -> Unit,
    updateTopAppBar: (TopAppBarConfiguration) -> Unit,
    updateTopAppBarTabs: (TabsConfiguration) -> Unit,
    clearTopAppBarTabs: () -> Unit
) {
    composable(
        "${MainDestinations.COMPONENT_DETAIL_ROUTE}/{${MainDestinations.COMPONENT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.COMPONENT_ID_KEY) { type = NavType.LongType })
    ) { from ->
        // Restore default values for tabs and top app bar
        clearTopAppBarTabs()
        updateTopAppBar(OdsDemoTopAppBarState.defaultConfiguration)

        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.COMPONENT_ID_KEY)
        ComponentDetailScreen(
            componentId,
            onVariantClick = { variantId -> onNavElementClick(MainDestinations.COMPONENT_VARIANT_ROUTE, variantId, from) },
            onDemoClick = { onNavElementClick(MainDestinations.COMPONENT_DEMO_ROUTE, componentId, from) },
            updateTopBarTitle = updateTopBarTitle
        )
    }

    composable(
        "${MainDestinations.COMPONENT_VARIANT_ROUTE}/{${MainDestinations.COMPONENT_VARIANT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.COMPONENT_VARIANT_ID_KEY) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val variantId = arguments.getLong(MainDestinations.COMPONENT_VARIANT_ID_KEY)
        ComponentVariantScreen(
            variantId = variantId,
            updateTopBarTitle = updateTopBarTitle,
            updateTopAppBar = updateTopAppBar,
            updateTopAppBarTabs = updateTopAppBarTabs
        )
    }

    composable(
        "${MainDestinations.COMPONENT_DEMO_ROUTE}/{${MainDestinations.COMPONENT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.COMPONENT_ID_KEY) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.COMPONENT_ID_KEY)
        ComponentDemoScreen(
            componentId = componentId,
            updateTopBarTitle = updateTopBarTitle
        )
    }
}