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

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71" class="external" target="_blank">ODS tabs</a>.
 *
 * An OdsScrollableTabRow is a Jetpack Compose [ScrollableTabRow] with the Orange design and theme.
 * @see ScrollableTabRow documentation
 *
 * @param selectedTabIndex the index of the currently selected tab
 * @param modifier optional [Modifier] for this TabRow
 * @param tabs the tabs inside this TabRow. Typically this will be multiple [Tab]s. Each element
 * inside this lambda will be measured and placed evenly across the TabRow, each taking up equal
 * space. Use [OdsTab] to display Orange styled tabs.
 */
@Composable
@OdsComponentApi
fun OdsScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
) {
    ScrollableTabRow(
        modifier = modifier,
        backgroundColor = OdsTheme.colors.surface,
        contentColor = OdsTheme.colors.onSurface,
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = OdsTheme.colors.primary
                )
            }
        },
        divider = {},
        tabs = tabs
    )
}

@Composable
private fun PreviewOdsScrollableTabRow() = Preview {
    data class Tab(@DrawableRes val iconResId: Int, val text: String)

    val tabs = listOf(
        Tab(android.R.drawable.ic_dialog_email, "First tab"),
        Tab(android.R.drawable.ic_dialog_map, "Second tab"),
        Tab(android.R.drawable.ic_dialog_dialer, "Third tab"),
        Tab(android.R.drawable.ic_dialog_info, "Fourth tab")
    )

    val selectedTabIndex = remember { mutableStateOf(0) }
    OdsScrollableTabRow(selectedTabIndex = selectedTabIndex.value) {
        tabs.forEachIndexed { index, tab ->
            OdsTab(
                selected = selectedTabIndex.value == index,
                onClick = { selectedTabIndex.value = index },
                text = tab.text,
                icon = painterResource(id = tab.iconResId)
            )
        }
    }
}

@Preview(name = "OdsScrollableTabRow - Light")
@Composable
private fun PreviewOdsScrollableTabRowLight() = PreviewOdsScrollableTabRow()

@Preview(
    name = "OdsScrollableTabRow - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsScrollableTabRowDark() = PreviewOdsScrollableTabRow()
