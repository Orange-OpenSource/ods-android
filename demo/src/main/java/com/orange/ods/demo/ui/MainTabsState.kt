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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.demo.ui.components.tabs.MainTabsCustomizationState
import com.orange.ods.demo.ui.utilities.NavigationItem
import com.orange.ods.demo.ui.utilities.rememberSaveableMutableStateListOf

val LocalMainTabsManager = staticCompositionLocalOf<MainTabsManager> { error("CompositionLocal LocalMainTabsManager not present") }

interface MainTabsManager {

    fun updateTopAppBarTabs(tabsConfiguration: MainTabsConfiguration)

    fun clearTopAppBarTabs()
}

@Composable
fun rememberMainTabsState(
    tabs: SnapshotStateList<NavigationItem> = rememberSaveableMutableStateListOf(),
    tabIconType: MutableState<MainTabsCustomizationState.TabIconType> = rememberSaveable { mutableStateOf(MainTabsCustomizationState.TabIconType.Top) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    scrollableTabs: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
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
) : MainTabsManager {
    var pagerState: PagerState? = null
        private set

    val hasTabs: Boolean
        get() = tabs.isNotEmpty()

    // ----------------------------------------------------------
    // Tabs state source of truth
    // ----------------------------------------------------------

    override fun updateTopAppBarTabs(tabsConfiguration: MainTabsConfiguration) {
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

@OptIn(ExperimentalPagerApi::class)
data class MainTabsConfiguration(
    val scrollableTabs: Boolean,
    val tabs: List<NavigationItem>,
    val pagerState: PagerState,
    val tabIconType: MainTabsCustomizationState.TabIconType,
    val tabTextEnabled: Boolean
)