/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.tabs.TabItem
import com.orange.ods.demo.ui.components.tabs.TabsCustomizationState

@Composable
@ExperimentalPagerApi
fun rememberOdsDemoTopAppBarState(
    titleRes: MutableState<Int> = rememberSaveable { mutableStateOf(R.string.navigation_item_guidelines) },
    tabs: MutableState<List<TabItem>> = rememberSaveable { mutableStateOf(emptyList()) },
    tabIconType: MutableState<TabsCustomizationState.TabIconType> = rememberSaveable { mutableStateOf(TabsCustomizationState.TabIconType.Top) }
) =
    remember(titleRes, tabs, tabIconType) {
        OdsDemoTopAppBarState(titleRes, tabs, tabIconType)
    }

@ExperimentalPagerApi
class OdsDemoTopAppBarState(
    val titleRes: MutableState<Int>,
    val tabs: MutableState<List<TabItem>>,
    val tabIconType: MutableState<TabsCustomizationState.TabIconType>
) {
    var pagerState: PagerState? = null
        private set

    val hasTabs: Boolean
        get() = tabs.value.isNotEmpty()

    // ----------------------------------------------------------
    // TopAppBar state source of truth
    // ----------------------------------------------------------

    fun updateTopAppBarTitle(titleRes: Int) {
        this.titleRes.value = titleRes
    }

    fun updateTopAppBarTabs(tabs: List<TabItem>, pagerState: PagerState?, tabIconType: TabsCustomizationState.TabIconType) {
        this.tabs.value = tabs
        this.pagerState = pagerState
        this.tabIconType.value = tabIconType
    }

    fun clearTopAppBarTabs() {
        tabs.value = emptyList()
        pagerState = null
    }
}