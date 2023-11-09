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

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentContent
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
 * @param title Title displayed in the center of the top app bar.
 * @param modifier [Modifier] applied to the top app bar.
 * @param navigationIcon Icon displayed at the start of the top app bar.
 * @param actions Actions displayed at the end of the top app bar. The default layout here is a [androidx.compose.foundation.layout.Row], so icons inside will be placed horizontally.
 * @param overflowMenuActions Actions displayed in the overflow menu.
 * @param elevated Controls the elevation of the top app bar: `true` to set an elevation to the top app bar (a shadow is displayed below), `false` otherwise.
 */
@Composable
@OdsComposable
fun OdsTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: OdsTopAppBar.NavigationIcon? = null,
    actions: List<OdsTopAppBar.ActionButton> = emptyList(),
    overflowMenuActions: List<OdsTopAppBarOverflowMenuActionItem> = emptyList(),
    elevated: Boolean = true
) {
    OdsTopAppBarInternal(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        overflowMenuActions = overflowMenuActions,
        elevated = elevated
    )
}

// TODO: Remove this method once OdsSearchTopAppBar is developed
@Composable
@OdsComposable
fun OdsTopAppBarInternal(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: OdsTopAppBar.NavigationIcon? = null,
    actions: List<OdsComponentContent<*>> = emptyList(),
    overflowMenuActions: List<OdsTopAppBarOverflowMenuActionItem> = emptyList(),
    elevated: Boolean = true
) {
    TopAppBar(
        title = { Text(text = title, style = OdsTheme.typography.h6) },
        modifier = modifier,
        navigationIcon = navigationIcon?.let { { it.Content() } },
        actions = { OdsTopAppBarActions(actions = actions, overflowMenuActions = overflowMenuActions) },
        backgroundColor = OdsTheme.colors.component.topAppBar.barBackground,
        contentColor = OdsTheme.colors.component.topAppBar.barContent,
        elevation = if (elevated) AppBarDefaults.TopAppBarElevation else 0.dp
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsTopAppBar() = Preview {
    val actions = listOf(OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_info), "Info") {})
    val overflowMenuItems = listOf(
        OdsTopAppBarOverflowMenuActionItem("Settings") {},
        OdsTopAppBarOverflowMenuActionItem("Account") {}
    )
    OdsTopAppBar(
        title = "Title",
        navigationIcon = OdsTopAppBar.NavigationIcon(Icons.Filled.ArrowBack, "") {},
        actions = actions,
        overflowMenuActions = overflowMenuItems
    )
}
