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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.demo.ui.utilities.rememberSaveableMutableStateListOf

@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun rememberOdsDemoTabsState(
    tabs: SnapshotStateList<TabItem> = rememberSaveableMutableStateListOf(),
    tabIconType: MutableState<TabsCustomizationState.TabIconType> = rememberSaveable { mutableStateOf(TabsCustomizationState.TabIconType.Top) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    scrollableTabs: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(tabs, tabIconType, tabTextEnabled, scrollableTabs) {
        OdsDemoTabsState(tabs, tabIconType, tabTextEnabled, scrollableTabs)
    }

@ExperimentalPagerApi
@ExperimentalMaterialApi
class OdsDemoTabsState(
    val tabs: SnapshotStateList<TabItem>,
    val tabIconType: MutableState<TabsCustomizationState.TabIconType>,
    val tabTextEnabled: MutableState<Boolean>,
    val scrollableTabs: MutableState<Boolean>
) : OdsDemoTabsManager {
    var pagerState: PagerState? = null
        private set

    val hasTabs: Boolean
        get() = tabs.isNotEmpty()

    // ----------------------------------------------------------
    // Tabs state source of truth
    // ----------------------------------------------------------

    override fun updateTopAppBarTabs(tabsConfiguration: TabsConfiguration) {
        with(tabs) {
            clear()
            addAll(tabsConfiguration.tabs)
        }
        pagerState = tabsConfiguration.pagerState
        tabIconType.value = tabsConfiguration.tabIconType
        tabTextEnabled.value = tabsConfiguration.tabTextEnabled
        scrollableTabs.value = tabsConfiguration.scrollableTabs
    }

    override fun clearTopAppBarTabs() {
        tabs.clear()
        pagerState = null
    }
}

data class TabsConfiguration @ExperimentalPagerApi @ExperimentalMaterialApi constructor(
    val scrollableTabs: Boolean,
    val tabs: List<TabItem>,
    val pagerState: PagerState,
    val tabIconType: TabsCustomizationState.TabIconType,
    val tabTextEnabled: Boolean
)