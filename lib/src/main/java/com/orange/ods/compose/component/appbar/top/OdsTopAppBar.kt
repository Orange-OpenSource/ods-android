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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.component.menu.OdsDropdownMenuItem
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

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
 * @param actions The actions displayed at the end of the TopAppBar. This should be [OdsTopAppBarActionButton]s.
 * The default layout here is a [Row], so icons inside will be placed horizontally.
 * @param elevated True to set an elevation to the top app bar (shadow displayed), false otherwise.
 */
@Composable
@OdsComponentApi
fun OdsTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    elevated: Boolean = true
) {
    TopAppBar(
        title = { title?.let { Text(text = title, style = OdsTheme.typography.h6) } },
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
        backgroundColor = OdsTheme.colors.component.topAppBar.barBackground,
        contentColor = OdsTheme.colors.component.topAppBar.barContent,
        elevation = if (elevated) AppBarDefaults.TopAppBarElevation else 0.dp
    )
}

/**
 * Action icon button displayed in an [OdsTopAppBar].
 *
 * @param onClick Will be called when the user clicks on the action icon button.
 * @param painter Painter of the icon.
 * @param contentDescription The content description associated to this OdsTopAppBarActionButton.
 * @param modifier The [Modifier] to be applied to this OdsTopAppBarActionButton.
 * @param enabled whether or not this OdsTopAppBarActionButton will handle input events and appear enabled for
 * semantics purposes, true by default.
 */
@Composable
@OdsComponentApi
fun OdsTopAppBarActionButton(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OdsIconButton(
        onClick = onClick,
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        enabled = enabled,
        tint = OdsTheme.colors.component.topAppBar.barContent
    )
}

/**
 * Overflow menu displayed in an [OdsTopAppBar]. It displays the overflow icon (3 vertical dots) and the menu appearing on click.
 *
 * @param overflowIconContentDescription The content description of the overflow icon.
 * @param content The content of the overflow dropdown menu
 */
@Composable
fun OdsTopAppBarOverflowMenuBox(
    overflowIconContentDescription: String,
    content: @Composable ColumnScope.() -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Box {
        OdsTopAppBarActionButton(
            onClick = { showMenu = !showMenu },
            painter = rememberVectorPainter(image = Icons.Filled.MoreVert),
            contentDescription = overflowIconContentDescription
        )
        OdsDropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false },
            content = content
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsTopAppBar() = Preview {
    OdsTopAppBar(
        title = "Title",
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = OdsTheme.colors.component.topAppBar.barContent)
            }
        },
        actions = {
            OdsTopAppBarActionButton(
                onClick = {},
                painter = painterResource(id = android.R.drawable.ic_dialog_info),
                contentDescription = "Info"
            )
            OdsTopAppBarOverflowMenuBox(
                overflowIconContentDescription = "more options"
            ) {
                OdsDropdownMenuItem(text = "settings", onClick = { })
                OdsDropdownMenuItem(text = "account", onClick = { })
            }
        }
    )
}
