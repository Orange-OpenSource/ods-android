/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.tabs

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.compose.component.tab.OdsTabRow

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FixedTabRow(tabs: List<TabItem>, pagerState: PagerState, tabIconType: MainTabsCustomizationState.TabIconType, tabTextEnabled: Boolean) {

    OdsTabRow(selectedTabIndex = pagerState.currentPage) {
        Tabs(tabs = tabs, pagerState = pagerState, tabIconType = tabIconType, tabTextEnabled = tabTextEnabled)
    }

}