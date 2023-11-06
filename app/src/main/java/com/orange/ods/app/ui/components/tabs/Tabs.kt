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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.compose.component.tab.OdsLeadingIconTab
import com.orange.ods.compose.component.tab.OdsTab
import com.orange.ods.compose.component.tab.OdsTabRowTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun tabs(
    tabs: List<NavigationItem>,
    pagerState: PagerState,
    tabIconType: MainTabsCustomizationState.TabIconType,
    tabTextEnabled: Boolean,
    leadingIcon: Boolean = false
): List<OdsTabRowTab> {
    val scope = rememberCoroutineScope()

    return if (leadingIcon) {
        tabs.mapIndexed { index, tab ->
            OdsLeadingIconTab(painterResource(id = tab.iconResId), stringResource(id = tab.textResId), pagerState.currentPage == index) {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        }
    } else {
        tabs.mapIndexed { index, tab ->
            OdsTab(
                if (tabIconType != MainTabsCustomizationState.TabIconType.None) painterResource(id = tab.iconResId) else null,
                if (tabTextEnabled) stringResource(id = tab.textResId) else null,
                pagerState.currentPage == index
            ) {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        }
    }
}

