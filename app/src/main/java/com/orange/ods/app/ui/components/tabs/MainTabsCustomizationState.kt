/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.app.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.compose.component.tab.OdsTabRow

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun rememberMainTabsCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    tabsCount: MutableIntState,
    pagerState: PagerState = rememberPagerState { tabsCount.intValue.coerceAtLeast(0) },
    tabIconPosition: MutableState<OdsTabRow.Tab.Icon.Position> = rememberSaveable { mutableStateOf(OdsTabRow.Tab.Icon.Position.Top) },
    tabIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(bottomSheetScaffoldState, pagerState, tabsCount, tabIconPosition, tabIconEnabled, tabTextEnabled) {
        MainTabsCustomizationState(bottomSheetScaffoldState, pagerState, tabsCount, tabIconPosition, tabIconEnabled, tabTextEnabled)
    }

class MainTabsCustomizationState @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class) constructor(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val pagerState: PagerState,
    val tabsCount: MutableIntState,
    val tabIconPosition: MutableState<OdsTabRow.Tab.Icon.Position>,
    val tabIconEnabled: MutableState<Boolean>,
    val tabTextEnabled: MutableState<Boolean>
) {
    val isTabTextCustomizationEnabled: Boolean
        get() = tabIconEnabled.value

    val isTabIconCustomizationEnabled: Boolean
        get() = tabTextEnabled.value

    val isTabIconPositionEnabled: Boolean
        get() = isTabIconCustomizationEnabled && isTabTextCustomizationEnabled

    val tabs: List<NavigationItem>
        get() = NavigationItem.entries.take(tabsCount.intValue.coerceAtLeast(0))
}