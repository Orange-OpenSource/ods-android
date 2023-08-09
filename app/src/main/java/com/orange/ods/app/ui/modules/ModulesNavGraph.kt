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
import com.orange.ods.app.ui.modules.about.AboutModuleConfigurationType
import com.orange.ods.app.ui.utilities.compat.BundleCompat
import com.orange.ods.module.about.AboutModuleConfiguration

object ModulesNavigation {
    const val ModuleDetailRoute = "component"
    const val ModuleDemoRoute = "module/demo"

    const val ModuleIdKey = "moduleId"
}

/**
 * Modules demo destinations.
 */
object ModuleDemoDestinations {
    const val AboutRoute = "module/demo/about"
    const val ConfigurationKey = "configuration"
}

fun NavGraphBuilder.addModulesGraph(navigateToElement: (String, Long?, NavBackStackEntry) -> Unit) {

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
                navigateToModuleDemo = { route: String -> navigateToElement(route, null, from) }
            )
        }
    }

    composable(
        "${ModuleDemoDestinations.AboutRoute}/{${ModuleDemoDestinations.ConfigurationKey}}",
        arguments = listOf(navArgument(ModuleDemoDestinations.ConfigurationKey) { type = AboutModuleConfigurationType })
    ) { from ->
        val configuration = BundleCompat.getParcelable<AboutModuleConfiguration>(from.arguments, ModuleDemoDestinations.ConfigurationKey)
        configuration?.let {
            Module.About.demoScreen(it)
        }
    }
}

private fun Bundle.getModule() = modules.firstOrNull { it.id == this.getLong(ModulesNavigation.ModuleIdKey) }