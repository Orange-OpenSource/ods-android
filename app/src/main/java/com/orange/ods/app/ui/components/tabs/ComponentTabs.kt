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

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.TabsConfiguration
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.text.OdsTextBody1

private const val MinFixedTabCount = 2
private const val MaxFixedTabCount = 3
private const val MinScrollableTabCount = 4
private const val MaxScrollableTabCount = 6

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun ComponentTabs(variant: Variant, upPress: () -> Unit) {
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

    val tabsCustomizationState = rememberMainTabsCustomizationState(tabsCount = rememberSaveable { mutableStateOf(tabCountMin) })
    LocalMainTopAppBarManager.current.updateTopAppBarTabs(
        TabsConfiguration(
            scrollableTabs = scrollableTabs,
            tabs = tabsCustomizationState.tabs,
            pagerState = tabsCustomizationState.pagerState,
            tabIconType = tabsCustomizationState.tabIconType.value,
            tabTextEnabled = tabsCustomizationState.tabTextEnabled.value,
        )
    )

    BackHandler {
        upPress()
    }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = tabsCustomizationState.bottomSheetScaffoldState,
        bottomSheetContent = {
            Subtitle(textRes = R.string.component_element_icon, horizontalPadding = true)
            OdsChoiceChipsFlowRow(
                selectedChip = tabsCustomizationState.tabIconType,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                outlinedChips = true
            ) {
                OdsChoiceChip(
                    textRes = R.string.component_tab_icon_leading,
                    value = MainTabsCustomizationState.TabIconType.Leading,
                    enabled = tabsCustomizationState.isTabIconCustomizationEnabled
                )
                OdsChoiceChip(
                    textRes = R.string.component_tab_icon_top,
                    value = MainTabsCustomizationState.TabIconType.Top,
                    enabled = tabsCustomizationState.isTabIconCustomizationEnabled
                )
                OdsChoiceChip(
                    textRes = R.string.component_element_none,
                    value = MainTabsCustomizationState.TabIconType.None,
                    enabled = tabsCustomizationState.isTabIconCustomizationEnabled
                )
            }

            OdsListItem(
                text = stringResource(id = R.string.component_element_text),
                trailing = OdsSwitchTrailing(checked = tabsCustomizationState.tabTextEnabled, enabled = tabsCustomizationState.isTabTextCustomizationEnabled)
            )

            ComponentCountRow(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                title = stringResource(id = R.string.component_tabs_count),
                count = tabsCustomizationState.tabsCount,
                minusIconContentDescription = stringResource(id = R.string.component_tabs_remove_tab),
                plusIconContentDescription = stringResource(id = R.string.component_tabs_add_tab),
                minCount = tabCountMin,
                maxCount = tabCountMax
            )
        }) {

        HorizontalPager(state = tabsCustomizationState.pagerState, count = tabsCustomizationState.tabs.size) { page ->
            val textResId = tabsCustomizationState.tabs[page].textResId
            TabsPagerContentScreen(stringResource(id = textResId))
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
