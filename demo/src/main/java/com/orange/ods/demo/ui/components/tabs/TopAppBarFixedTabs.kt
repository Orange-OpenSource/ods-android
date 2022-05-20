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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.compose.component.tab.OdsLeadingIconTab
import com.orange.ods.compose.component.tab.OdsTab
import com.orange.ods.compose.component.tab.OdsTabRow
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TopAppBarFixedTabs(tabs: List<TabItem>, pagerState: PagerState, tabIconType: SubComponentTabsState.TabIconType, tabTextEnabled: Boolean) {
    val scope = rememberCoroutineScope()

    OdsTabRow(
        selectedTabIndex = pagerState.currentPage
    ) {
        tabs.forEachIndexed { index, tab ->
            if (tabIconType == SubComponentTabsState.TabIconType.Leading && tabTextEnabled) {
                OdsLeadingIconTab(
                    icon = painterResource(id = tab.icon),
                    text = tab.title,
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            } else {
                OdsTab(
                    icon = if (tabIconType == SubComponentTabsState.TabIconType.None) null else painterResource(id = tab.icon),
                    text = if (tabTextEnabled) tab.title else null,
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
    }
}