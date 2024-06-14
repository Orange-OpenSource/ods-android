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

package com.orange.ods.module.about.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.orange.ods.module.about.ui.OdsAboutFileScreen
import com.orange.ods.module.about.ui.OdsAboutHomeScreen
import com.orange.ods.module.about.ui.OdsAboutViewModel
import com.orange.ods.module.about.ui.accessibilitystatement.OdsAboutAccessibilityStatementScreen
import com.orange.ods.module.about.ui.appnews.OdsAboutAppNewsScreen
import com.orange.ods.module.about.ui.configuration.OdsAboutAccessibilityStatementMenuItem
import com.orange.ods.module.about.ui.configuration.OdsAboutAppNewsMenuItem
import com.orange.ods.module.about.ui.configuration.OdsAboutConfiguration
import com.orange.ods.module.about.ui.configuration.OdsAboutFileMenuItem
import com.orange.ods.module.about.ui.configuration.OdsAboutMenuItem
import com.orange.ods.module.about.ui.configuration.OdsAboutUrlMenuItem
import com.orange.ods.module.about.ui.utilities.extension.launchUrl


object OdsAboutDestinations {
    const val AboutNavRoute = "ods/module/about/"
    const val HomeRoute = "ods/module/about/home"
    const val FileItemRoute = "ods/module/about/fileItem"
    const val AppNewsRoute = "ods/module/about/appNews"
    const val AccessibilityStatementRoute = "ods/module/about/accessibilityStatement"
}

private const val AboutItemIdKey = "aboutItemId"
private const val AppNewsFileResId = "appNewsFileResId"
private const val AccessibilityStatementItemIdKey = "accessibilityStatementItemIdKey"

@Deprecated(
    "Please use navigate method with OdsAboutDestinations.AboutNavRoute instead.",
    ReplaceWith("navigate(OdsAboutDestinations.AboutNavRoute, navOptions)")
)
fun NavController.navigateToOdsAbout(navOptions: NavOptions? = null) {
    navigate(OdsAboutDestinations.AboutNavRoute, navOptions)
}

/**
 * Add this graph to your app in order to integrate the ODS About module.
 */
fun NavGraphBuilder.odsAboutGraph(navController: NavController, configuration: () -> OdsAboutConfiguration) {
    navigation(startDestination = OdsAboutDestinations.HomeRoute, route = OdsAboutDestinations.AboutNavRoute) {
        composable(route = OdsAboutDestinations.HomeRoute) { navBackStackEntry ->
            val aboutViewModel = viewModel<OdsAboutViewModel>(navBackStackEntry)
            with(configuration()) {
                aboutViewModel.configuration = this
                OdsAboutHomeScreen(
                    configuration = this,
                    onAboutMenuItemClick = onAboutMenuItemClick(navController, menuItemById, onScreenChange)
                )
            }
        }

        composable(
            "${OdsAboutDestinations.FileItemRoute}/{$AboutItemIdKey}",
            arguments = listOf(navArgument(AboutItemIdKey) { type = NavType.LongType })
        ) { navBackStackEntry ->
            navController.previousBackStackEntry?.let { previousBackStackEntry ->
                val aboutViewModel = viewModel<OdsAboutViewModel>(previousBackStackEntry)
                val aboutMenuItemId = requireNotNull(navBackStackEntry.arguments).getLong(AboutItemIdKey).toInt()
                val aboutItem = aboutViewModel.configuration?.menuItemById?.get(aboutMenuItemId) as? OdsAboutFileMenuItem
                aboutItem?.let { OdsAboutFileScreen(it, isSystemInDarkTheme()) }
            }
        }

        composable(
            "${OdsAboutDestinations.AppNewsRoute}/{$AppNewsFileResId}",
            arguments = listOf(navArgument(AppNewsFileResId) { type = NavType.LongType })
        ) { navBackStackEntry ->
            val appNewsFileResId = requireNotNull(navBackStackEntry.arguments).getLong(AppNewsFileResId).toInt()
            OdsAboutAppNewsScreen(fileRes = appNewsFileResId)
        }

        composable(
            "${OdsAboutDestinations.AccessibilityStatementRoute}/{$AccessibilityStatementItemIdKey}",
            arguments = listOf(navArgument(AccessibilityStatementItemIdKey) { type = NavType.LongType })
        ) { navBackStackEntry ->
            navController.previousBackStackEntry?.let { previousBackStackEntry ->
                val aboutViewModel = viewModel<OdsAboutViewModel>(previousBackStackEntry)
                val accessibilityStatementMenuItemId = requireNotNull(navBackStackEntry.arguments).getLong(AccessibilityStatementItemIdKey).toInt()
                val accessibilityStatementMenuItem =
                    aboutViewModel.configuration?.menuItemById?.get(accessibilityStatementMenuItemId) as? OdsAboutAccessibilityStatementMenuItem
                accessibilityStatementMenuItem?.let { OdsAboutAccessibilityStatementScreen(accessibilityStatementMenuItem = it) }
            }
        }
    }
}

@Composable
internal fun onAboutMenuItemClick(
    navController: NavController,
    menuItemById: Map<Int, OdsAboutMenuItem>,
    onScreenChange: ((String) -> Unit)?
): (Int) -> Unit {
    val context = LocalContext.current
    return { id ->
        menuItemById[id]?.let { aboutMenuItem ->
            onScreenChange?.invoke(aboutMenuItem.text)
            when (aboutMenuItem) {
                is OdsAboutUrlMenuItem -> context.launchUrl(aboutMenuItem.url)
                is OdsAboutAppNewsMenuItem -> navController.navigate("${OdsAboutDestinations.AppNewsRoute}/${aboutMenuItem.jsonFileRes}")
                is OdsAboutAccessibilityStatementMenuItem -> navController.navigate("${OdsAboutDestinations.AccessibilityStatementRoute}/$id")
                is OdsAboutFileMenuItem -> navController.navigate("${OdsAboutDestinations.FileItemRoute}/$id")
            }
        }
    }
}
