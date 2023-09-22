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
import com.orange.ods.module.about.OdsAboutFileScreen

object AboutNavigation {
    const val AboutItemDetailRoute = "aboutItem"

    const val AboutItemIdKey = "aboutItemId"
}

fun NavGraphBuilder.addAboutGraph() {
    composable(
        "${AboutNavigation.AboutItemDetailRoute}/{${AboutNavigation.AboutItemIdKey}}",
        arguments = listOf(navArgument(AboutNavigation.AboutItemIdKey) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val aboutItemId = arguments.getLong(AboutNavigation.AboutItemIdKey)
        OdsAboutFileScreen(aboutItemId, false)
    }
}