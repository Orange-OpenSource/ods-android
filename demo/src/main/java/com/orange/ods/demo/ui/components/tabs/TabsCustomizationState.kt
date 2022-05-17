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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

private const val TabCountMin = 2

@ExperimentalPagerApi
@Composable
fun rememberTabsCustomizationState(
    pagerState: PagerState = rememberPagerState(),
    tabsNumber: MutableState<Int> = rememberSaveable { mutableStateOf(2) },
    iconIsChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(pagerState, tabsNumber, iconIsChecked) {
        TabsCustomizationState(pagerState, tabsNumber, iconIsChecked)
    }

@ExperimentalPagerApi
class TabsCustomizationState(
    val pagerState: PagerState,
    val tabsNumber: MutableState<Int>,
    val iconIsChecked: MutableState<Boolean>
) {
    private val availableTabs = TabItem::class.sealedSubclasses.mapNotNull { it.objectInstance }

    val canRemoveTab: Boolean
        get() = tabsNumber.value > TabCountMin

    val canAddTab: Boolean
        get() = tabsNumber.value < 3

    val tabs: List<TabItem>
        get() = availableTabs.subList(0, tabsNumber.value)
}