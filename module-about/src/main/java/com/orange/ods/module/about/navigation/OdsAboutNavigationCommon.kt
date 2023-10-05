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
import com.orange.ods.module.about.OdsAboutFileScreen
import com.orange.ods.module.about.OdsAboutViewModel
import com.orange.ods.module.about.configuration.FileAboutItem
import com.orange.ods.module.about.configuration.OdsAboutMenuItem
import com.orange.ods.module.about.configuration.UrlAboutItem
import com.orange.ods.module.about.utilities.extension.launchUrl

/**
 * Destinations used in the About module.
 */
object OdsAboutDestinations {
    const val HomeRoute = "ods/module/about/home"
    internal const val AboutRoute = "ods/module/about/"
    internal const val FileItemRoute = "ods/module/about/fileItem"
}

internal const val AboutItemIdKey = "aboutItemId"

@Composable
internal fun AboutFileScreen(navBackStackEntry: NavBackStackEntry) {
    val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
    val aboutViewModel = viewModel<OdsAboutViewModel>(viewModelStoreOwner)
    val aboutMenuItemId = requireNotNull(navBackStackEntry.arguments).getLong(AboutItemIdKey).toInt()
    val aboutItem = aboutViewModel.configuration?.menuItemById?.get(aboutMenuItemId) as? FileAboutItem

    OdsAboutFileScreen(aboutItem, isSystemInDarkTheme())
}

@Composable
internal fun onAboutMenuItemClick(navController: NavController, menuItemById: Map<Int, OdsAboutMenuItem>): (Int) -> Unit {
    val context = LocalContext.current
    return { id ->
        val aboutMenuItem = menuItemById[id]
        if (aboutMenuItem is UrlAboutItem) {
            context.launchUrl(aboutMenuItem.url)
        } else {
            navController.navigate("${OdsAboutDestinations.FileItemRoute}/$id")
        }
    }
}
