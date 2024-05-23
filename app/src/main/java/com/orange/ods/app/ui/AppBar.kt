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

package com.orange.ods.app.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.compose.component.appbar.top.OdsLargeTopAppBar
import com.orange.ods.compose.component.appbar.top.OdsSearchTopAppBar
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar

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
        when (type) {
            Screen.TopAppBarType.Large -> OdsLargeTopAppBar(
                title = title,
                navigationIcon = getNavigationIcon(upPress),
                actions = actions,
                overflowMenuItems = overflowMenuItems,
                scrollBehavior = if (hasScrollBehavior) scrollBehavior else null
            )
            Screen.TopAppBarType.Search -> {
                val appBarManager = LocalAppBarManager.current
                OdsSearchTopAppBar(
                    placeholder = stringResource(id = R.string.search_text_field_hint),
                    onValueChange = { appBarManager.searchedText = it },
                    value = appBarManager.searchedText,
                    navigationIcon = getNavigationIcon(upPress),
                )
            }
            Screen.TopAppBarType.Default -> OdsTopAppBar(
                title = title,
                navigationIcon = getNavigationIcon(upPress),
                actions = actions,
                overflowMenuItems = overflowMenuItems,
            )
        }
    }
}