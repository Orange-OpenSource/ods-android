/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
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
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.app.ui.utilities.rememberSaveableMutableStateListOf
import com.orange.ods.compose.component.tab.OdsTabRow

/**
 * Tabs state source of truth.
 */
@OptIn(ExperimentalFoundationApi::class)
class AppBarTabsState(
    val tabs: SnapshotStateList<NavigationItem>,
    val tabsIconPosition: MutableState<OdsTabRow.Tab.Icon.Position>,
    val tabIconEnabled: MutableState<Boolean>,
    val tabTextEnabled: MutableState<Boolean>,
    val scrollableTabs: MutableState<Boolean>
) {

    companion object {
        val DefaultConfiguration = TabsConfiguration(
            scrollableTabs = false,
            tabs = emptyList(),
            pagerState = null,
            tabsIconPosition = OdsTabRow.Tab.Icon.Position.Top,
            tabIconEnabled = true,
            tabTextEnabled = true,
        )
    }

    var pagerState: PagerState? = null
        private set

    val hasTabs: Boolean
        get() = tabs.isNotEmpty()

    fun updateAppBarTabs(tabsConfiguration: TabsConfiguration) {
        with(tabs) {
            clear()
            addAll(tabsConfiguration.tabs)
        }
        pagerState = tabsConfiguration.pagerState
        tabsIconPosition.value = tabsConfiguration.tabsIconPosition
        tabIconEnabled.value = tabsConfiguration.tabIconEnabled
        tabTextEnabled.value = tabsConfiguration.tabTextEnabled
        scrollableTabs.value = tabsConfiguration.scrollableTabs
    }

    fun clearAppBarTabs() {
        tabs.clear()
        pagerState = null
    }
}

@Composable
fun rememberAppBarTabsState(
    tabs: SnapshotStateList<NavigationItem> = rememberSaveableMutableStateListOf(),
    tabsIconPosition: MutableState<OdsTabRow.Tab.Icon.Position> = rememberSaveable { mutableStateOf(AppBarTabsState.DefaultConfiguration.tabsIconPosition) },
    tabIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(AppBarTabsState.DefaultConfiguration.tabIconEnabled) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(AppBarTabsState.DefaultConfiguration.tabTextEnabled) },
    scrollableTabs: MutableState<Boolean> = rememberSaveable { mutableStateOf(AppBarTabsState.DefaultConfiguration.scrollableTabs) }
) = remember(tabs, tabsIconPosition, tabIconEnabled, tabTextEnabled, scrollableTabs) {
    AppBarTabsState(tabs, tabsIconPosition, tabIconEnabled, tabTextEnabled, scrollableTabs)
}

@OptIn(ExperimentalFoundationApi::class)
data class TabsConfiguration(
    val scrollableTabs: Boolean,
    val tabs: List<NavigationItem>,
    val pagerState: PagerState?,
    val tabsIconPosition: OdsTabRow.Tab.Icon.Position,
    val tabIconEnabled: Boolean,
    val tabTextEnabled: Boolean
)