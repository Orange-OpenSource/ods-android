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
import com.orange.ods.demo.ui.LocalMainTabsManager
import com.orange.ods.demo.ui.MainDestinations

fun NavGraphBuilder.addAboutGraph() {
    composable(
        "${MainDestinations.AboutItemDetailRoute}/{${MainDestinations.AboutItemIdKey}}",
        arguments = listOf(navArgument(MainDestinations.AboutItemIdKey) { type = NavType.LongType })
    ) { backStackEntry ->
        LocalMainTabsManager.current.clearTopAppBarTabs()
        val arguments = requireNotNull(backStackEntry.arguments)
        val aboutItemId = arguments.getLong(MainDestinations.AboutItemIdKey)
        AboutFileScreen(aboutItemId)
    }
}