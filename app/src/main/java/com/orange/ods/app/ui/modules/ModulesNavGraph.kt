/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.modules

import android.os.Bundle
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

object ModulesNavigation {
    const val ModuleDetailRoute = "component"
    const val ModuleDemoRoute = "module/demo"

    const val ModuleIdKey = "moduleId"
}

fun NavGraphBuilder.addModulesGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit, upPress: () -> Unit) {

    composable(
        "${ModulesNavigation.ModuleDetailRoute}/{${ModulesNavigation.ModuleIdKey}}",
        arguments = listOf(navArgument(ModulesNavigation.ModuleIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val routeModuleId = arguments.getLong(ModulesNavigation.ModuleIdKey)

        val module = remember(routeModuleId) { arguments.getModule() }
        module?.let {
            ModuleDetailScreen(
                module = module,
                onDemoClick = { navigateToElement(ModulesNavigation.ModuleDemoRoute, routeModuleId, from) }
            )
        }
    }

    composable(
        "${ModulesNavigation.ModuleDemoRoute}/{${ModulesNavigation.ModuleIdKey}}",
        arguments = listOf(navArgument(ModulesNavigation.ModuleIdKey) { type = NavType.LongType })
    ) { from ->
        val arguments = requireNotNull(from.arguments)
        val module = remember { arguments.getModule() }
        module?.configurationScreen?.let { it(upPress) }

    }
}

private fun Bundle.getModule() = modules.firstOrNull { it.id == this.getLong(ModulesNavigation.ModuleIdKey) }