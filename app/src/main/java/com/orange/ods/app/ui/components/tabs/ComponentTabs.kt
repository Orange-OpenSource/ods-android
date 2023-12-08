/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.tab.OdsTabRow
import com.orange.ods.compose.text.OdsTextBody1

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
            TabsConfiguration(scrollableTabs, tabs, pagerState, tabsIconPosition.value, tabIconEnabled.value, tabTextEnabled.value)
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
                    value = tabsIconPosition.value,
                    onValueChange = { value -> tabsIconPosition.value = value },
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                    chips = listOf(
                        OdsChoiceChip(
                            text = stringResource(id = R.string.component_tabs_icon_position_leading),
                            value = OdsTabRow.Tab.Icon.Position.Leading,
                            enabled = isTabsIconPositionEnabled
                        ),
                        OdsChoiceChip(
                            text = stringResource(id = R.string.component_tabs_icon_position_top), value = OdsTabRow.Tab.Icon.Position.Top,
                            enabled = isTabsIconPositionEnabled
                        )
                    )
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
            }) {

            HorizontalPager(state = pagerState) { page ->
                val textResId = tabs[page].textResId
                TabsPagerContentScreen(stringResource(id = textResId))
            }
        }
    }
}

@Composable
private fun TabsPagerContentScreen(text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        OdsTextBody1(text = text)
    }
}
