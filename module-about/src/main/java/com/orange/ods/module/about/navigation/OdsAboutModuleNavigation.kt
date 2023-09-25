/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.orange.ods.module.about.OdsAboutMainScreen
import com.orange.ods.module.about.OdsAboutViewModel
import com.orange.ods.module.about.configuration.LocalOdsAboutModuleConfiguration

const val OdsAboutRoute = "ods/module/about/"
const val OdsAboutDemoRoute = "ods/module/about/demo"

fun NavController.navigateToOdsAboutDemo(navOptions: NavOptions? = null) {
    this.navigate(OdsAboutDemoRoute, navOptions)
}

fun NavController.navigateToOdsAbout(navOptions: NavOptions? = null) {
    this.navigate(OdsAboutRoute, navOptions)
}

fun NavGraphBuilder.aboutScreen(navController: NavController) {
    composable(route = OdsAboutRoute) {
        OdsAboutMainScreen(LocalOdsAboutModuleConfiguration.current)
    }

    composable(route = OdsAboutDemoRoute) {
        val configuration = navController.previousBackStackEntry?.let { (viewModel(it) as OdsAboutViewModel).configuration }
        OdsAboutMainScreen(configuration)
    }
}