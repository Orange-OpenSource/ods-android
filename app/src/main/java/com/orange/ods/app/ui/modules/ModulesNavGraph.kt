/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.app.ui.modules

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.app.R
import com.orange.ods.app.ui.CustomAppBarConfiguration
import com.orange.ods.app.ui.LocalAppBarManager
import com.orange.ods.app.ui.modules.ModulesNavigation.AboutCustomizationRoute
import com.orange.ods.app.ui.modules.ModulesNavigation.EmptyStateCustomizationRoute
import com.orange.ods.app.ui.modules.ModulesNavigation.EmptyStateDemoRoute
import com.orange.ods.app.ui.modules.ModulesNavigation.MoreAppsCustomizationRoute
import com.orange.ods.app.ui.modules.about.AboutCustomizationScreen
import com.orange.ods.app.ui.modules.about.AboutCustomizationViewModel
import com.orange.ods.app.ui.modules.emptystate.EmptyStateCustomizationScreen
import com.orange.ods.app.ui.modules.emptystate.EmptyStateCustomizationViewModel
import com.orange.ods.app.ui.modules.emptystate.EmptyStateDemoScreen
import com.orange.ods.app.ui.modules.moreapps.MoreAppsCustomizationScreen
import com.orange.ods.app.ui.modules.moreapps.MoreAppsCustomizationViewModel
import com.orange.ods.app.ui.navigateToElement
import com.orange.ods.module.moreapps.ui.configuration.OdsMoreAppsConfiguration
import com.orange.ods.module.moreapps.ui.navigation.odsMoreAppsGraph

/**
 * Modules demo destinations.
 */
object ModulesNavigation {
    const val AboutCustomizationRoute = "module/about/customization"

    const val MoreAppsCustomizationRoute = "module/moreApps/customization"

    const val EmptyStateCustomizationRoute = "module/emptyState/customization"
    const val EmptyStateDemoRoute = "module/emptyState/demo"
}

fun NavGraphBuilder.addModulesGraph(
    navController: NavController,
    navigateToAboutDemo: () -> Unit,
    navigateToMoreAppsDemo: () -> Unit,
    moreAppsConfiguration: () -> OdsMoreAppsConfiguration
) {
    composable(route = AboutCustomizationRoute) { _ ->
        val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
        val viewModel = viewModel<AboutCustomizationViewModel>(viewModelStoreOwner)
        AboutCustomizationScreen(navigateToAboutDemo = navigateToAboutDemo, viewModel = viewModel)
    }

    composable(route = EmptyStateCustomizationRoute) { navBackStackEntry ->
        EmptyStateCustomizationScreen(onViewDemoButtonClick = { navController.navigateToElement(EmptyStateDemoRoute, null, navBackStackEntry) })
    }

    composable(route = EmptyStateDemoRoute) { _ ->
        navController.previousBackStackEntry?.let { previousBackStackEntry ->
            val viewModel = viewModel<EmptyStateCustomizationViewModel>(previousBackStackEntry)
            EmptyStateDemoScreen(viewModel = viewModel)
        }
    }

    composable(route = MoreAppsCustomizationRoute) { _ ->
        val appBarManager = LocalAppBarManager.current
        val appBarTitle = stringResource(id = R.string.module_moreApps_title)
        val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
        val viewModel = viewModel<MoreAppsCustomizationViewModel>(viewModelStoreOwner)
        MoreAppsCustomizationScreen(
            navigateToMoreAppsDemo = {
                navigateToMoreAppsDemo()
                appBarManager.setCustomAppBar(CustomAppBarConfiguration(title = appBarTitle, actionCount = 0))
            },
            viewModel = viewModel
        )
    }

    odsMoreAppsGraph(moreAppsConfiguration)
}
