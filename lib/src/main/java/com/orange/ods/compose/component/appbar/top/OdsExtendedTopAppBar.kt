/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.appbar.top

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@OdsComposable
fun OdsExtendedTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    onNavigationIconClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val contentColor = OdsTheme.colors.component.topAppBar.barContent
    LargeTopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                navigationIcon()
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = OdsTheme.colors.component.topAppBar.barBackground,
            navigationIconContentColor = contentColor,
            titleContentColor = contentColor,
            actionIconContentColor = contentColor
        ),
        scrollBehavior = scrollBehavior
    )
}