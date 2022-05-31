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
import com.orange.ods.demo.ui.components.tabs.TabsConfiguration

@ExperimentalMaterialApi
@ExperimentalPagerApi
fun NavGraphBuilder.addComponentsGraph(
    onNavElementClick: (String, Long?, NavBackStackEntry) -> Unit,
    updateTopBarTitle: (Int) -> Unit,
    updateTopAppBarTabs: (TabsConfiguration) -> Unit,
    clearTopAppBarTabs: () -> Unit
) {
    composable(
        "${MainDestinations.COMPONENT_DETAIL_ROUTE}/{${MainDestinations.COMPONENT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.COMPONENT_ID_KEY) { type = NavType.LongType })
    ) { from ->
        clearTopAppBarTabs()
        val arguments = requireNotNull(from.arguments)
        val componentId = arguments.getLong(MainDestinations.COMPONENT_ID_KEY)
        ComponentDetailScreen(
            componentId,
            onSubComponentClick = { id -> onNavElementClick(MainDestinations.COMPONENT_SUBTYPE_ROUTE, id, from) },
            updateTopBarTitle = updateTopBarTitle
        )
    }

    composable(
        "${MainDestinations.COMPONENT_SUBTYPE_ROUTE}/{${MainDestinations.COMPONENT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.COMPONENT_ID_KEY) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val subComponentId = arguments.getLong(MainDestinations.COMPONENT_ID_KEY)
        SubComponentDetailScreen(
            subComponentId = subComponentId,
            updateTopBarTitle = updateTopBarTitle,
            updateTopAppBarTabs = updateTopAppBarTabs
        )
    }
}