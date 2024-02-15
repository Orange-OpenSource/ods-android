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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalAppBarManager
import com.orange.ods.app.ui.TabsConfiguration
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.tab.OdsTabRow
import com.orange.ods.compose.text.OdsText
import com.orange.ods.theme.typography.OdsTextStyle

private const val MinFixedTabCount = 2
private const val MaxFixedTabCount = 3
private const val MinScrollableTabCount = 4
private const val MaxScrollableTabCount = 6

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ComponentTabs(variant: Variant) {
    val scrollableTabs: Boolean
    val tabCountMin: Int
    val tabCountMax: Int
    if (variant == Variant.TabsScrollable) {
        scrollableTabs = true
        tabCountMin = MinScrollableTabCount
        tabCountMax = MaxScrollableTabCount
    } else {
        scrollableTabs = false
        tabCountMin = MinFixedTabCount
        tabCountMax = MaxFixedTabCount
    }

    val tabsCustomizationState = rememberMainTabsCustomizationState(tabsCount = rememberSaveable { mutableIntStateOf(tabCountMin) })

    with(tabsCustomizationState) {
        val appBarManager = LocalAppBarManager.current
        appBarManager.updateAppBarTabs(
            TabsConfiguration(scrollableTabs, tabs, pagerState, tabIconPosition.value, tabIconEnabled.value, tabTextEnabled.value)
        )

        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = bottomSheetScaffoldState,
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_tab_text),
                    trailing = OdsListItem.TrailingSwitch(tabTextEnabled.value, { tabTextEnabled.value = it }, isTabTextCustomizationEnabled)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_tab_icon),
                    trailing = OdsListItem.TrailingSwitch(tabIconEnabled.value, { tabIconEnabled.value = it }, isTabIconCustomizationEnabled)
                )
                Subtitle(textRes = R.string.component_tabs_icon_position, horizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    selectedChoiceChipIndex = OdsTabRow.Tab.Icon.Position.entries.indexOf(tabIconPosition.value),
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                    choiceChips = OdsTabRow.Tab.Icon.Position.entries.map { tabIconPosition ->
                        val textResId = when (tabIconPosition) {
                            OdsTabRow.Tab.Icon.Position.Top -> R.string.component_tabs_icon_position_top
                            OdsTabRow.Tab.Icon.Position.Leading -> R.string.component_tabs_icon_position_leading
                        }
                        OdsChoiceChipsFlowRow.ChoiceChip(
                            stringResource(id = textResId),
                            { this.tabIconPosition.value = tabIconPosition },
                            isTabIconPositionEnabled
                        )
                    }
                )

                ComponentCountRow(
                    modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                    title = stringResource(id = R.string.component_tabs_count),
                    count = tabsCount,
                    minusIconContentDescription = stringResource(id = R.string.component_tabs_remove_tab),
                    plusIconContentDescription = stringResource(id = R.string.component_tabs_add_tab),
                    minCount = tabCountMin,
                    maxCount = tabCountMax
                )
            }
        ) {
            HorizontalPager(state = pagerState) { pageIndex ->
                val modifier = if (pageIndex == 0) {
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(top = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
                } else {
                    Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                }

                Column(modifier = modifier) {
                    if (pageIndex == 0) {
                        // Display code implementation on first page only
                        CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))) {
                            FunctionCallCode(
                                name = if (scrollableTabs) OdsComposable.OdsScrollableTabRow.name else OdsComposable.OdsTabRow.name,
                                parameters = {
                                    int("selectedTabIndex", pageIndex)
                                    list("tabs") {
                                        tabs.forEach { tab ->
                                            classInstance<OdsTabRow.Tab> {
                                                if (tabIconEnabled.value) {
                                                    classInstance<OdsTabRow.Tab.Icon>("icon") {
                                                        painter()
                                                    }
                                                }
                                                if (tabTextEnabled.value) {
                                                    text(tab.name)
                                                }
                                                onClick()
                                            }
                                        }
                                    }
                                    enum("tabIconPosition", tabIconPosition.value)
                                }
                            )
                        }
                    } else {
                        OdsText(text = stringResource(tabs[pageIndex].textResId), style = OdsTextStyle.BodyL)
                    }
                }
            }
        }
    }
}