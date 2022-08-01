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
fun rememberVariantTabsState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    tabsCount: MutableState<Int>,
    pagerState: PagerState = rememberPagerState(),
    selectedTabIconType: MutableState<VariantTabsState.TabIconType> = rememberSaveable { mutableStateOf(VariantTabsState.TabIconType.Top) },
    tabTextEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(bottomSheetScaffoldState, pagerState, tabsCount, selectedTabIconType, tabTextEnabled) {
        VariantTabsState(bottomSheetScaffoldState, pagerState, tabsCount, selectedTabIconType, tabTextEnabled)
    }

@ExperimentalMaterialApi
@ExperimentalPagerApi
class VariantTabsState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val pagerState: PagerState,
    val tabsCount: MutableState<Int>,
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
        get() = availableTabs.take(tabsCount.value.coerceAtLeast(0))
}