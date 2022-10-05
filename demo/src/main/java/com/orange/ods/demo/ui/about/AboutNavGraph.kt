/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.about

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orange.ods.demo.ui.MainDestinations
import com.orange.ods.demo.ui.components.tabs.LocalTabsManager

fun NavGraphBuilder.addAboutGraph() {
    composable(
        "${MainDestinations.ABOUT_ITEM_DETAIL_ROUTE}/{${MainDestinations.ABOUT_ITEM_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.ABOUT_ITEM_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        LocalTabsManager.current.clearTopAppBarTabs()
        val arguments = requireNotNull(backStackEntry.arguments)
        val aboutItemId = arguments.getLong(MainDestinations.ABOUT_ITEM_ID_KEY)
        AboutFileScreen(aboutItemId)
    }
}