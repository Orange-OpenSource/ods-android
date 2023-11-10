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
import androidx.compose.material.TabRow
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
 * An [OdsTabRow] is a Jetpack Compose [TabRow] to which we applied the Orange design and theme.
 * @see TabRow documentation
 *
 * @param selectedTabIndex Index of the currently selected tab.
 * @param tabs List of [OdsTabRow.Tab] displayed inside the tabs row.
 * @param modifier [Modifier] applied to the tab row.
 * @param tabIconPosition Controls the position of the icon in the tabs. By default, the icon is displayed above the text.
 */
@Composable
@OdsComposable
fun OdsTabRow(
    selectedTabIndex: Int,
    tabs: List<OdsTabRow.Tab>,
    modifier: Modifier = Modifier,
    tabIconPosition: OdsTabRow.Tab.Icon.Position = OdsTabRow.Tab.Icon.Position.Top
) {
    TabRow(
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
        tabs = {
            tabs.forEachIndexed { index, tab ->
                tab.Content(
                    OdsTabRow.Tab.ExtraParameters(
                        selected = index == selectedTabIndex,
                        iconPosition = tabIconPosition
                    )
                )
            }
        }
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsTabRow(@PreviewParameter(OdsTabRowPreviewParameterProvider::class) parameter: OdsTabPreviewParameter) = Preview {
    data class Tab(@DrawableRes val iconResId: Int, val text: String)

    val tabs = listOf(
        Tab(android.R.drawable.ic_dialog_email, "First tab"),
        Tab(android.R.drawable.ic_dialog_map, "Second tab"),
        Tab(android.R.drawable.ic_dialog_dialer, "Third tab")
    )

    var selectedTabIndex by remember { mutableStateOf(0) }
    with(parameter) {
        OdsTabRow(
            selectedTabIndex = selectedTabIndex,
            tabIconPosition = if (hasLeadingIconTabs) OdsTabRow.Tab.Icon.Position.Leading else OdsTabRow.Tab.Icon.Position.Top,
            tabs = tabs.mapIndexed { index, tab ->
                OdsTabRow.Tab(
                    icon = if (hasIcon) OdsTabRow.Tab.Icon(painterResource(id = tab.iconResId), "") else null,
                    text = if (hasText) tab.text else null,
                    enabled = enabled,
                    onClick = { selectedTabIndex = index }
                )
            }
        )
    }
}
