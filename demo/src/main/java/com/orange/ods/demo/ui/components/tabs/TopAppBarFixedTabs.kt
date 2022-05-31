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

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.compose.component.tab.OdsTabRow

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TopAppBarFixedTabs(tabs: List<TabItem>, pagerState: PagerState, tabIconType: SubComponentTabsState.TabIconType, tabTextEnabled: Boolean) {

    OdsTabRow(selectedTabIndex = pagerState.currentPage) {
        TopAppBarTabs(tabs = tabs, pagerState = pagerState, tabIconType = tabIconType, tabTextEnabled = tabTextEnabled)
    }

}