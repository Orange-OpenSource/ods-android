/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.compose.component.tab.OdsTabRow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FixedTabRow(tabs: List<NavigationItem>, pagerState: PagerState, tabIconType: MainTabsCustomizationState.TabIconType, tabTextEnabled: Boolean) {
    OdsTabRow(selectedTabIndex = pagerState.currentPage) {
        Tabs(tabs = tabs, pagerState = pagerState, tabIconType = tabIconType, tabTextEnabled = tabTextEnabled)
    }
}