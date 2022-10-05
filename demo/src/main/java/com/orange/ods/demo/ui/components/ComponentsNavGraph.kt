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
import com.orange.ods.demo.ui.LocalTopAppBarManager
import com.orange.ods.demo.ui.MainDestinations
import com.orange.ods.demo.ui.OdsDemoTopAppBarState
import com.orange.ods.demo.ui.components.tabs.LocalTabsManager

@ExperimentalMaterialApi
@ExperimentalPagerApi
fun NavGraphBuilder.addComponentsGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit) {
    composable(
        "${MainDestinations.COMPONENT_DETAIL_ROUTE}/{${MainDestinations.COMPONENT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.COMPONENT_ID_KEY) { type = NavType.LongType })
    ) { from ->
        // Restore default values for tabs and top app bar
        LocalTabsManager.current.clearTopAppBarTabs()
        LocalTopAppBarManager.current.updateTopAppBar(OdsDemoTopAppBarState.DefaultConfiguration)

        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.COMPONENT_ID_KEY)
        ComponentDetailScreen(
            componentId = componentId,
            onVariantClick = { variantId -> navigateToElement(MainDestinations.COMPONENT_VARIANT_ROUTE, variantId, from) },
            onDemoClick = { navigateToElement(MainDestinations.COMPONENT_DEMO_ROUTE, componentId, from) }
        )
    }

    composable(
        "${MainDestinations.COMPONENT_VARIANT_ROUTE}/{${MainDestinations.COMPONENT_VARIANT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.COMPONENT_VARIANT_ID_KEY) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val variantId = arguments.getLong(MainDestinations.COMPONENT_VARIANT_ID_KEY)
        ComponentVariantScreen(variantId = variantId)
    }

    composable(
        "${MainDestinations.COMPONENT_DEMO_ROUTE}/{${MainDestinations.COMPONENT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.COMPONENT_ID_KEY) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.COMPONENT_ID_KEY)
        ComponentDemoScreen(componentId = componentId)
    }
}