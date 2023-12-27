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
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.compose.component.appbar.top.OdsLargeTopAppBarInternal
import com.orange.ods.compose.component.appbar.top.OdsSearchTopAppBar
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
        when (type) {
            Screen.AppBarType.Large -> OdsLargeTopAppBarInternal(
                title = title,
                navigationIcon = getNavigationIcon(upPress),
                actions = actions,
                overflowMenuItems = overflowMenuItems,
                scrollBehavior = if (hasScrollBehavior) scrollBehavior else null
            )
            Screen.AppBarType.Search -> OdsSearchTopAppBar(
                searchHint = stringResource(id = R.string.search_text_field_hint),
                onSearchValueChange = { searchText.value = it },
                searchValue = LocalAppBarManager.current.searchedText,
                navigationIcon = getNavigationIcon(upPress),
                elevated = false // elevation is managed in MainScreen
            )
            Screen.AppBarType.Default -> OdsTopAppBarInternal(
                title = title,
                navigationIcon = getNavigationIcon(upPress),
                actions = actions,
                overflowMenuItems = overflowMenuItems,
                elevated = false // elevation is managed in MainScreen cause of tabs
            )
        }
    }
}