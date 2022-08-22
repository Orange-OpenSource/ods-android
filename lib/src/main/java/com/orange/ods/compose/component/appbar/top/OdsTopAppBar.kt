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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/23e0e6-app-bars/b/620966" class="external" target="_blank">Material ODS Top App Bar</a>.
 *
 * The top app bar displays information and actions relating to the current screen.
 *
 * This TopAppBar has slots for a title, navigation icon, and actions. Note that the [title] slot
 * is inset from the start according to spec - for custom use cases such as horizontally
 * centering the title, use the other TopAppBar overload for a generic TopAppBar with no
 * restriction on content.
 *
 * @param modifier The [Modifier] to be applied to this TopAppBar
 * @param title The title to be displayed in the center of the TopAppBar
 * @param navigationIcon Optional navigation icon displayed at the start of the TopAppBar. This should be an [Icon].
 * @param onNavigationIconClick Optional action executed on the navigation icon click.
 * @param actions The actions displayed at the end of the TopAppBar. This should typically be
 * [IconButton]s. The default layout here is a [Row], so icons inside will be placed horizontally.
 * @param elevated True to set an elevation to the top app bar (shadow displayed), false otherwise.
 */
@Composable
fun OdsTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    elevated: Boolean = true
) {
    TopAppBar(
        title = { title?.let { Text(text = title) } },
        modifier = modifier,
        navigationIcon = navigationIcon?.let { navIcon ->
            {
                if (onNavigationIconClick != null) {
                    IconButton(onClick = onNavigationIconClick) {
                        navIcon()
                    }
                } else {
                    navIcon()
                }
            }
        },
        actions = actions,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = if (elevated) AppBarDefaults.TopAppBarElevation else 0.dp
    )
}