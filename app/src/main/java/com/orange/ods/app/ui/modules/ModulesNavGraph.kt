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

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.app.ui.modules.ModuleDemoDestinations.AboutModuleCustomizationRoute
import com.orange.ods.app.ui.modules.about.AboutCustomizationScreen
import com.orange.ods.module.about.OdsAboutViewModel
import com.orange.ods.module.about.navigation.aboutScreen
import com.orange.ods.module.about.navigation.navigateToOdsAboutDemo

/**
 * Modules demo destinations.
 */
object ModuleDemoDestinations {
    const val AboutModuleCustomizationRoute = "module/about/customization"
}

fun NavGraphBuilder.addModulesGraph(navController: NavController) {

    composable(
        route = AboutModuleCustomizationRoute
    ) { navBackStackEntry ->
/*        LocalMainTopAppBarManager.current.updateTopAppBar(MainTopAppBarState.DefaultConfiguration)
        LocalMainTopAppBarManager.current.updateTopAppBarTitle(Module.About.titleRes)*/

        val viewModel: OdsAboutViewModel = viewModel(navBackStackEntry)
        AboutCustomizationScreen(navigateToAboutModule = navController::navigateToOdsAboutDemo, configureAboutModule = viewModel::configureAboutModule)
    }

    aboutScreen(navController = navController)

}
