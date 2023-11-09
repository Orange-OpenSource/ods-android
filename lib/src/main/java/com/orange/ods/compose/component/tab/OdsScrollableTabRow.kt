/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.tab

import androidx.annotation.DrawableRes
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71" class="external" target="_blank">ODS tabs</a>.
 *
 * An [OdsScrollableTabRow] is a Jetpack Compose [ScrollableTabRow] with the Orange design and theme.
 * @see ScrollableTabRow documentation
 *
 * @param selectedTabIndex Index of the currently selected tab.
 * @param tabs List of [OdsTabRowTabBuilder] displayed inside the tabs row.
 * @param modifier [Modifier] applied to the scrollable tabs row.
 * @param tabIconPosition Controls the position of the icon in the tabs. By default, the icon is displayed above the text.
 */
@Composable
@OdsComposable
fun OdsScrollableTabRow(
    selectedTabIndex: Int,
    tabs: List<OdsTabRowTabBuilder>,
    modifier: Modifier = Modifier,
    tabIconPosition: OdsTabRowTabIconPosition = OdsTabRowTabIconPosition.Top
) {
    ScrollableTabRow(
        modifier = modifier,
        backgroundColor = OdsTheme.colors.component.tab.background,
        contentColor = OdsTheme.colors.component.tab.unselectedContent,
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = OdsTheme.colors.component.tab.selectedContent
                )
            }
        },
        divider = {},
        tabs = { tabs.forEachIndexed { index, tab -> tab.Content(OdsTabRowTabBuilder.ExtraParameters(selected = index == selectedTabIndex, iconPosition = tabIconPosition)) } }
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsScrollableTabRow(@PreviewParameter(OdsTabRowPreviewParameterProvider::class) parameter: OdsTabPreviewParameter) = Preview {
    data class Tab(@DrawableRes val iconResId: Int, val text: String)

    val tabs = listOf(
        Tab(android.R.drawable.ic_dialog_email, "First"),
        Tab(android.R.drawable.ic_dialog_map, "Second"),
        Tab(android.R.drawable.ic_dialog_dialer, "Third"),
        Tab(android.R.drawable.ic_dialog_info, "Fourth")
    )

    var selectedTabIndex by remember { mutableStateOf(0) }
    with(parameter) {
        OdsScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            tabs = tabs.mapIndexed { index, tab ->
                OdsTabRowTabBuilder(
                    icon = if (hasIcon) OdsTabRowTabIconBuilder(painterResource(id = tab.iconResId), "") else null,
                    text = if (hasText) tab.text else null,
                    enabled = enabled,
                    onClick = { selectedTabIndex = index }
                )
            }
        )
    }
}
