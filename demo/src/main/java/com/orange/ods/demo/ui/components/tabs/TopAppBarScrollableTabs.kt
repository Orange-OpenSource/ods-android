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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.compose.component.tab.OdsLeadingIconTab
import com.orange.ods.compose.component.tab.OdsScrollableTabRow
import com.orange.ods.compose.component.tab.OdsTab
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TopAppBarScrollableTabs(tabs: List<TabItem>, pagerState: PagerState, tabIconType: SubComponentTabsState.TabIconType, tabTextEnabled: Boolean) {

    OdsScrollableTabRow(selectedTabIndex = pagerState.currentPage) {
        TopAppBarTabs(tabs = tabs, pagerState = pagerState, tabIconType = tabIconType, tabTextEnabled = tabTextEnabled)
    }

}
