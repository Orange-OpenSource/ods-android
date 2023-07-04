/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.about

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.MainDestinations
import com.orange.ods.app.ui.MainTopAppBarState

fun NavGraphBuilder.addAboutGraph() {
    composable(
        "${MainDestinations.AboutItemDetailRoute}/{${MainDestinations.AboutItemIdKey}}",
        arguments = listOf(navArgument(MainDestinations.AboutItemIdKey) { type = NavType.LongType })
    ) { backStackEntry ->
        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
        val arguments = requireNotNull(backStackEntry.arguments)
        val aboutItemId = arguments.getLong(MainDestinations.AboutItemIdKey)
        AboutFileScreen(aboutItemId)
    }
}