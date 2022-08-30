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
import com.orange.ods.compose.component.tab.OdsTab
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TopAppBarTabs(tabs: List<TabItem>, pagerState: PagerState, tabIconType: TabsCustomizationState.TabIconType, tabTextEnabled: Boolean) {
    val scope = rememberCoroutineScope()

    tabs.forEachIndexed { index, tab ->
        val selected = pagerState.currentPage == index
        val onClick: () -> Unit = {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }

        if (tabIconType == TabsCustomizationState.TabIconType.Leading && tabTextEnabled) {
            OdsLeadingIconTab(
                icon = painterResource(id = tab.icon),
                text = stringResource(id = tab.titleRes),
                selected = selected,
                onClick = onClick
            )
        } else {
            OdsTab(
                icon = if (tabIconType == TabsCustomizationState.TabIconType.None) null else painterResource(id = tab.icon),
                text = if (tabTextEnabled) stringResource(id = tab.titleRes) else null,
                selected = selected,
                onClick = onClick
            )
        }
    }
}