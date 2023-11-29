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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.orange.ods.module.about.OdsAboutFileScreen
import com.orange.ods.module.about.OdsAboutHomeScreen
import com.orange.ods.module.about.OdsAboutViewModel
import com.orange.ods.module.about.configuration.OdsAboutFileMenuItem
import com.orange.ods.module.about.configuration.OdsAboutMenuItem
import com.orange.ods.module.about.configuration.OdsAboutUrlMenuItem
import com.orange.ods.module.about.utilities.extension.launchUrl

private const val AboutItemIdKey = "aboutItemId"

private object OdsAboutDestinations {
    const val HomeRoute = "ods/module/about/home"
    const val AboutRoute = "ods/module/about/"
    const val FileItemRoute = "ods/module/about/fileItem"
}

fun NavController.navigateToOdsAbout(navOptions: NavOptions? = null) {
    navigate(OdsAboutDestinations.AboutRoute, navOptions)
}

/**
 * Add this graph to your app in order to integrate the ODS About module.
 */
fun NavGraphBuilder.aboutGraph(navController: NavController) {
    navigation(startDestination = OdsAboutDestinations.HomeRoute, route = OdsAboutDestinations.AboutRoute) {
        composable(route = OdsAboutDestinations.HomeRoute) {
            val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
            viewModel<OdsAboutViewModel>(viewModelStoreOwner).configuration?.let { configuration ->
                OdsAboutHomeScreen(
                    configuration = configuration,
                    onAboutMenuItemClick = onAboutMenuItemClick(navController, configuration.menuItemById)
                )
            }
        }

        composable(
            "${OdsAboutDestinations.FileItemRoute}/{${AboutItemIdKey}}",
            arguments = listOf(navArgument(AboutItemIdKey) { type = NavType.LongType })
        ) { backStackEntry -> AboutFileScreen(navBackStackEntry = backStackEntry) }
    }
}

@Composable
internal fun AboutFileScreen(navBackStackEntry: NavBackStackEntry) {
    val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
    val aboutViewModel = viewModel<OdsAboutViewModel>(viewModelStoreOwner)
    val aboutMenuItemId = requireNotNull(navBackStackEntry.arguments).getLong(AboutItemIdKey).toInt()
    val aboutItem = aboutViewModel.configuration?.menuItemById?.get(aboutMenuItemId) as? OdsAboutFileMenuItem

    aboutItem?.let {
        aboutViewModel.configuration?.onScreenChange?.invoke(it.text)
        OdsAboutFileScreen(it, isSystemInDarkTheme())
    }
}

@Composable
internal fun onAboutMenuItemClick(navController: NavController, menuItemById: Map<Int, OdsAboutMenuItem>): (Int) -> Unit {
    val context = LocalContext.current
    return { id ->
        val aboutMenuItem = menuItemById[id]
        if (aboutMenuItem is OdsAboutUrlMenuItem) {
            context.launchUrl(aboutMenuItem.url)
        } else {
            navController.navigate("${OdsAboutDestinations.FileItemRoute}/$id")
        }
    }
}
