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

package com.orange.ods.compose.component.appbar.top

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/23e0e6-app-bars/b/620966" class="external" target="_blank">ODS Top App Bar</a>.
 *
 * The top app bar displays information and actions relating to the current screen.
 *
 * This TopAppBar has slots for a title, navigation icon, and actions. Note that the [title] slot
 * is inset from the start according to spec - for custom use cases such as horizontally
 * centering the title, use the other TopAppBar overload for a generic TopAppBar with no
 * restriction on content.
 *
 * @param title Title displayed in the center of the top app bar.
 * @param modifier [Modifier] applied to the top app bar.
 * @param navigationIcon Icon displayed at the start of the top app bar.
 * @param actions Actions displayed at the end of the top app bar. The default layout here is a [androidx.compose.foundation.layout.Row], so icons inside will be placed horizontally.
 * @param overflowMenuItems List of items displayed in the overflow menu. The top app bar uses `OdsDropdownMenu` to display its overflow menu.
 * @param elevated Controls the elevation of the top app bar: `true` to set an elevation to the top app bar (a shadow is displayed below), `false` otherwise.
 */
@Composable
@OdsComposable
fun OdsTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: OdsTopAppBar.NavigationIcon? = null,
    actions: List<OdsTopAppBar.ActionButton> = emptyList(),
    overflowMenuItems: List<OdsDropdownMenu.Item> = emptyList(),
    elevated: Boolean = true
) {
    TopAppBar(
        title = { OdsText(text = title, style = OdsTextStyle.TitleL, modifier = Modifier.semantics { traversalIndex = -1f }) },
        modifier = modifier.semantics { isTraversalGroup = true },
        navigationIcon = navigationIcon?.let { { it.Content() } },
        actions = { OdsTopAppBarActions(actions = actions, overflowMenuItems = overflowMenuItems) },
        backgroundColor = OdsTheme.colors.component.topAppBar.barBackground,
        contentColor = OdsTheme.colors.component.topAppBar.barContent,
        elevation = if (elevated) AppBarDefaults.TopAppBarElevation else 0.dp
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsTopAppBar() = OdsPreview {
    val actions = listOf(OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_info), "Info") {})
    val overflowMenuItems = listOf(
        OdsDropdownMenu.Item("Settings") {},
        OdsDropdownMenu.Item("Account") {}
    )
    OdsTopAppBar(
        title = "Title",
        navigationIcon = OdsTopAppBar.NavigationIcon(Icons.AutoMirrored.Filled.ArrowBack, "") {},
        actions = actions,
        overflowMenuItems = overflowMenuItems
    )
}