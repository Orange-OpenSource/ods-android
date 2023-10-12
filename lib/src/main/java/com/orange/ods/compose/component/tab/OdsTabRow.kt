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
import androidx.compose.material.Tab
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
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71" class="external" target="_blank">ODS tabs</a>.
 *
 * An OdsTabRow is a Jetpack Compose [TabRow] to which we applied the Orange design and theme.
 * @see TabRow documentation
 *
 * @param selectedTabIndex the index of the currently selected tab.
 * @param modifier optional [Modifier] for this OdsTabRow.
 * @param tabs the tabs inside this TabRow. Typically this will be multiple [Tab]s. Each element
 * inside this lambda will be measured and placed evenly across the TabRow, each taking up equal
 * space. Use [OdsTab] to display Orange styled tabs.
 */
@Composable
@OdsComposable
fun OdsTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
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
        tabs = tabs
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsTabRow() = Preview {
    data class Tab(@DrawableRes val iconResId: Int, val text: String)

    val tabs = listOf(
        Tab(android.R.drawable.ic_dialog_email, "First tab"),
        Tab(android.R.drawable.ic_dialog_map, "Second tab"),
        Tab(android.R.drawable.ic_dialog_dialer, "Third tab")
    )

    var selectedTabIndex by remember { mutableStateOf(0) }
    OdsTabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, tab ->
            OdsTab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = tab.text,
                icon = painterResource(id = tab.iconResId)
            )
        }
    }
}
