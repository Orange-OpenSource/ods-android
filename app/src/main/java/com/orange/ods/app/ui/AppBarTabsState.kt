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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.orange.ods.app.ui.components.tabs.MainTabsCustomizationState
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.app.ui.utilities.rememberSaveableMutableStateListOf

@Composable
fun rememberAppBarTabsState(
    tabs: SnapshotStateList<NavigationItem> = rememberSaveableMutableStateListOf(),
    tabIconType: MutableState<MainTabsCustomizationState.TabIconType> = rememberSaveable { mutableStateOf(AppBarTabsState.DefaultConfiguration.tabIconType) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(AppBarTabsState.DefaultConfiguration.tabTextEnabled) },
    scrollableTabs: MutableState<Boolean> = rememberSaveable { mutableStateOf(AppBarTabsState.DefaultConfiguration.scrollableTabs) }
) =
    remember(tabs, tabIconType, tabTextEnabled, scrollableTabs) {
        AppBarTabsState(tabs, tabIconType, tabTextEnabled, scrollableTabs)
    }

@OptIn(ExperimentalFoundationApi::class)
class AppBarTabsState(
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

    fun updateAppBarTabs(tabsConfiguration: TabsConfiguration) {
        with(tabs) {
            clear()
            addAll(tabsConfiguration.tabs)
        }
        pagerState = tabsConfiguration.pagerState
        tabIconType.value = tabsConfiguration.tabIconType
        tabTextEnabled.value = tabsConfiguration.tabTextEnabled
        scrollableTabs.value = tabsConfiguration.scrollableTabs
    }

    fun clearAppBarTabs() {
        tabs.clear()
        pagerState = null
    }
}

@OptIn(ExperimentalFoundationApi::class)
data class TabsConfiguration(
    val scrollableTabs: Boolean,
    val tabs: List<NavigationItem>,
    val pagerState: PagerState?,
    val tabIconType: MainTabsCustomizationState.TabIconType,
    val tabTextEnabled: Boolean
)