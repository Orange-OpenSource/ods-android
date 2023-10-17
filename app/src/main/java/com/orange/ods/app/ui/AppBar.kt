/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.compose.component.appbar.top.OdsLargeTopAppBarInternal
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarInternal
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarNavigationIcon

/**
 * Displays the unique top app bar of the application which can be regular or large.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    appBarState: AppBarState,
    upPress: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior?
) {
    val navigationIcon = if (appBarState.showNavigationIcon) {
        OdsTopAppBarNavigationIcon(Icons.Filled.ArrowBack, stringResource(id = R.string.top_app_bar_back_icon_desc), upPress)
    } else {
        null
    }

    if (appBarState.isLarge) {
        OdsLargeTopAppBarInternal(
            title = appBarState.title,
            navigationIcon = navigationIcon,
            actions = appBarState.actions,
            overflowMenuActions = appBarState.overflowMenuActions,
            scrollBehavior = if (appBarState.hasScrollBehavior) scrollBehavior else null
        )
    } else {
        OdsTopAppBarInternal(
            title = appBarState.title,
            navigationIcon = navigationIcon,
            actions = appBarState.actions,
            overflowMenuActions = appBarState.overflowMenuActions,
            elevated = false // elevation is managed in MainScreen cause of tabs
        )
    }
}