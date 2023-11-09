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
import com.orange.ods.compose.component.tab.OdsTabRowTabBuilder
import com.orange.ods.compose.component.tab.OdsTabRowTabIconBuilder
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun tabs(
    tabs: List<NavigationItem>,
    pagerState: PagerState,
    tabIconType: MainTabsCustomizationState.TabIconType,
    tabTextEnabled: Boolean,
): List<OdsTabRowTabBuilder> {
    val scope = rememberCoroutineScope()

    return tabs.mapIndexed { index, tab ->
        OdsTabRowTabBuilder(
            icon = if (tabIconType != MainTabsCustomizationState.TabIconType.None) OdsTabRowTabIconBuilder(painter = painterResource(id = tab.iconResId)) else null,
            text = if (tabTextEnabled) stringResource(id = tab.textResId) else null,
        ) {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    }
}
