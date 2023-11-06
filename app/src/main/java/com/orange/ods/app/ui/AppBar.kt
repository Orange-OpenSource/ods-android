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

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.*
import com.orange.ods.compose.component.appbar.top.OdsLargeTopAppBarInternal
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarInternal

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
    with(appBarState) {
        if (isLarge) {
            OdsLargeTopAppBarInternal(
                title = title,
                navigationIcon = getNavigationIcon(upPress),
                actions = actions,
                overflowMenuActions = overflowMenuActions,
                scrollBehavior = if (hasScrollBehavior) scrollBehavior else null
            )
        } else {
            OdsTopAppBarInternal(
                title = title,
                navigationIcon = getNavigationIcon(upPress),
                actions = actions,
                overflowMenuActions = overflowMenuActions,
                elevated = false // elevation is managed in MainScreen cause of tabs
            )
        }
    }
}