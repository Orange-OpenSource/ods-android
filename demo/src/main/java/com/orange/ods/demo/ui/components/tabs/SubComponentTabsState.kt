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

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun rememberSubComponentTabsState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    tabsNumber: MutableState<Int>,
    pagerState: PagerState = rememberPagerState(),
    selectedTabIconType: MutableState<SubComponentTabsState.TabIconType> = rememberSaveable { mutableStateOf(SubComponentTabsState.TabIconType.Top) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(bottomSheetScaffoldState, pagerState, tabsNumber, selectedTabIconType, tabTextEnabled) {
        SubComponentTabsState(bottomSheetScaffoldState, pagerState, tabsNumber, selectedTabIconType, tabTextEnabled)
    }

@ExperimentalMaterialApi
@ExperimentalPagerApi
class SubComponentTabsState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val pagerState: PagerState,
    val tabsNumber: MutableState<Int>,
    val selectedTabIconType: MutableState<TabIconType>,
    val tabTextEnabled: MutableState<Boolean>
) {
    enum class TabIconType {
        Leading, Top, None
    }

    private val availableTabs = TabItem.values().toList()

    val isTabTextCheckboxEnabled: Boolean
        get() = selectedTabIconType.value != TabIconType.None

    val areTabIconRadiosEnabled: Boolean
        get() = tabTextEnabled.value

    val tabs: List<TabItem>
        get() = availableTabs.take(tabsNumber.value)

    fun canRemoveTab(tabCountMin: Int) = tabsNumber.value > tabCountMin

    fun canAddTab(tabCountMax: Int) = tabsNumber.value < tabCountMax
}