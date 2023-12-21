/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.app.ui.modules

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.app.ui.modules.ModulesNavigation.AboutCustomizationRoute
import com.orange.ods.app.ui.modules.ModulesNavigation.EmptyStateDemoRoute
import com.orange.ods.app.ui.modules.ModulesNavigation.EmptyStateSetupRoute
import com.orange.ods.app.ui.modules.about.AboutCustomizationScreen
import com.orange.ods.app.ui.modules.about.AboutCustomizationViewModel
import com.orange.ods.app.ui.modules.emptystate.EmptyStateDemoScreen
import com.orange.ods.app.ui.modules.emptystate.EmptyStateSetupScreen
import com.orange.ods.app.ui.modules.emptystate.EmptyStateViewModel
import com.orange.ods.app.ui.navigateToElement

/**
 * Modules demo destinations.
 */
object ModulesNavigation {
    const val AboutCustomizationRoute = "module/about/customization"

    const val EmptyStateSetupRoute = "module/emptyState/setup"
    const val EmptyStateDemoRoute = "module/emptyState/demo"
}

fun NavGraphBuilder.addModulesGraph(navController: NavController, navigateToAboutDemo: () -> Unit) {
    composable(route = AboutCustomizationRoute) { _ ->
        val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
        val viewModel = viewModel<AboutCustomizationViewModel>(viewModelStoreOwner)
        AboutCustomizationScreen(navigateToAboutDemo = navigateToAboutDemo, viewModel = viewModel)
    }

    composable(route = EmptyStateSetupRoute) { navBackStackEntry ->
        EmptyStateSetupScreen(onViewDemoButtonClick = { navController.navigateToElement(EmptyStateDemoRoute, null, navBackStackEntry) })
    }

    composable(route = EmptyStateDemoRoute) { _ ->
        navController.previousBackStackEntry?.let { previousBackStackEntry ->
            val viewModel = viewModel<EmptyStateViewModel>(previousBackStackEntry)
            EmptyStateDemoScreen(viewModel = viewModel)
        }
    }
}
