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

package com.orange.ods.app.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.compose.component.tab.OdsTabRow

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun rememberMainTabsCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    tabsCount: MutableIntState,
    pagerState: PagerState = rememberPagerState { tabsCount.intValue.coerceAtLeast(0) },
    selectedTabsIconPosition: MutableState<OdsTabRow.Tab.Icon.Position> = rememberSaveable { mutableStateOf(OdsTabRow.Tab.Icon.Position.Top) },
    tabIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(bottomSheetScaffoldState, pagerState, tabsCount, selectedTabsIconPosition, tabIconEnabled, tabTextEnabled) {
        MainTabsCustomizationState(bottomSheetScaffoldState, pagerState, tabsCount, selectedTabsIconPosition, tabIconEnabled, tabTextEnabled)
    }

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
class MainTabsCustomizationState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val pagerState: PagerState,
    val tabsCount: MutableIntState,
    val tabsIconPosition: MutableState<OdsTabRow.Tab.Icon.Position>,
    val tabIconEnabled: MutableState<Boolean>,
    val tabTextEnabled: MutableState<Boolean>
) {
    val isTabTextCustomizationEnabled: Boolean
        get() = tabIconEnabled.value

    val isTabIconCustomizationEnabled: Boolean
        get() = tabTextEnabled.value

    val isTabsIconPositionEnabled: Boolean
        get() = isTabIconCustomizationEnabled && isTabTextCustomizationEnabled

    val tabs: List<NavigationItem>
        get() = NavigationItem.entries.take(tabsCount.intValue.coerceAtLeast(0))
}