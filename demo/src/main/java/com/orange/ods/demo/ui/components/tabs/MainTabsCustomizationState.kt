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

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.orange.ods.demo.ui.utilities.NavigationItem

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun rememberMainTabsCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    tabsCount: MutableState<Int>,
    pagerState: PagerState = rememberPagerState(),
    selectedTabIconType: MutableState<MainTabsCustomizationState.TabIconType> = rememberSaveable { mutableStateOf(MainTabsCustomizationState.TabIconType.Top) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(bottomSheetScaffoldState, pagerState, tabsCount, selectedTabIconType, tabTextEnabled) {
        MainTabsCustomizationState(bottomSheetScaffoldState, pagerState, tabsCount, selectedTabIconType, tabTextEnabled)
    }

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
class MainTabsCustomizationState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val pagerState: PagerState,
    val tabsCount: MutableState<Int>,
    val tabIconType: MutableState<TabIconType>,
    val tabTextEnabled: MutableState<Boolean>
) {
    enum class TabIconType {
        Leading, Top, None
    }

    private val availableTabs = NavigationItem.values().toList()

    val isTabTextCustomizationEnabled: Boolean
        get() = tabIconType.value != TabIconType.None

    val isTabIconCustomizationEnabled: Boolean
        get() = tabTextEnabled.value

    val tabs: List<NavigationItem>
        get() = availableTabs.take(tabsCount.value.coerceAtLeast(0))
}