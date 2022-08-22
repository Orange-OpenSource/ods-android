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

import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
fun OdsScrollableTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
) {
    ScrollableTabRow(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        selectedTabIndex = selectedTabIndex,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = MaterialTheme.colors.primary
                )
            }
        },
        divider = {},
        tabs = tabs
    )
}