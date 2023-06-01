/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.app.ui.components.tabs.MainTabsCustomizationState
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.app.ui.utilities.rememberSaveableMutableStateListOf

@Composable
fun rememberMainTabsState(
    tabs: SnapshotStateList<NavigationItem> = rememberSaveableMutableStateListOf(),
    tabIconType: MutableState<MainTabsCustomizationState.TabIconType> = rememberSaveable { mutableStateOf(MainTabsState.DefaultConfiguration.tabIconType) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(MainTabsState.DefaultConfiguration.tabTextEnabled) },
    scrollableTabs: MutableState<Boolean> = rememberSaveable { mutableStateOf(MainTabsState.DefaultConfiguration.scrollableTabs) }
) =
    remember(tabs, tabIconType, tabTextEnabled, scrollableTabs) {
        MainTabsState(tabs, tabIconType, tabTextEnabled, scrollableTabs)
    }

@OptIn(ExperimentalPagerApi::class)
class MainTabsState(
    val tabs: SnapshotStateList<NavigationItem>,
    val tabIconType: MutableState<MainTabsCustomizationState.TabIconType>,
    val tabTextEnabled: MutableState<Boolean>,
    val scrollableTabs: MutableState<Boolean>
) {

    companion object {
        val DefaultConfiguration = TabsConfiguration(
            scrollableTabs = false,
            tabs = emptyList(),
            pagerState = null,
            tabIconType = MainTabsCustomizationState.TabIconType.Top,
            tabTextEnabled = true,
        )
    }

    var pagerState: PagerState? = null
        private set

    val hasTabs: Boolean
        get() = tabs.isNotEmpty()

    // ----------------------------------------------------------
    // Tabs state source of truth
    // ----------------------------------------------------------

    fun updateTopAppBarTabs(tabsConfiguration: TabsConfiguration) {
        with(tabs) {
            clear()
            addAll(tabsConfiguration.tabs)
        }
        pagerState = tabsConfiguration.pagerState
        tabIconType.value = tabsConfiguration.tabIconType
        tabTextEnabled.value = tabsConfiguration.tabTextEnabled
        scrollableTabs.value = tabsConfiguration.scrollableTabs
    }

    fun clearTopAppBarTabs() {
        tabs.clear()
        pagerState = null
    }
}

@OptIn(ExperimentalPagerApi::class)
data class TabsConfiguration(
    val scrollableTabs: Boolean,
    val tabs: List<NavigationItem>,
    val pagerState: PagerState?,
    val tabIconType: MainTabsCustomizationState.TabIconType,
    val tabTextEnabled: Boolean
)