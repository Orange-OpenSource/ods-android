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

@ExperimentalPagerApi
@Composable
fun rememberTabsCustomizationState(
    pagerState: PagerState = rememberPagerState(),
    tabsNumber: MutableState<Int> = rememberSaveable { mutableStateOf(2) }
) =
    remember(pagerState, tabsNumber) {
        TabsCustomizationState(pagerState, tabsNumber)
    }

@ExperimentalPagerApi
class TabsCustomizationState(
    val pagerState: PagerState,
    private val tabsNumber: MutableState<Int>
) {
    private val availableTabs = TabItem::class.sealedSubclasses.mapNotNull { it.objectInstance }

    val tabs: List<TabItem>
        get() = availableTabs.subList(0, tabsNumber.value)
}