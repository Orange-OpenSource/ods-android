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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTabsManager
import com.orange.ods.demo.ui.MainTabsConfiguration
import com.orange.ods.demo.ui.components.Variant
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

private const val FixedTabsCountMin = 2
private const val FixedTabsCountMax = 3
private const val ScrollableTabsCountMin = 4
private const val ScrollableTabsCountMax = 6

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun ComponentTabs(variant: Variant) {
    val scrollableTabs: Boolean
    val tabCountMin: Int
    val tabCountMax: Int
    if (variant == Variant.TabsScrollable) {
        scrollableTabs = true
        tabCountMin = ScrollableTabsCountMin
        tabCountMax = ScrollableTabsCountMax
    } else {
        scrollableTabs = false
        tabCountMin = FixedTabsCountMin
        tabCountMax = FixedTabsCountMax
    }

    val tabsCustomizationState = rememberMainTabsCustomizationState(tabsCount = rememberSaveable { mutableStateOf(tabCountMin) })
    LocalMainTabsManager.current.updateTopAppBarTabs(
        MainTabsConfiguration(
            scrollableTabs = scrollableTabs,
            tabs = tabsCustomizationState.tabs,
            pagerState = tabsCustomizationState.pagerState,
            tabIconType = tabsCustomizationState.tabIconType.value,
            tabTextEnabled = tabsCustomizationState.tabTextEnabled.value,
        )
    )

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = tabsCustomizationState.bottomSheetScaffoldState,
        bottomSheetContent = {
            Subtitle(textRes = R.string.component_element_icon, withHorizontalPadding = true)
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

            SwitchListItem(R.string.component_element_text, tabsCustomizationState.tabTextEnabled, tabsCustomizationState.isTabTextCustomizationEnabled)

            ComponentCountRow(
                title = stringResource(id = R.string.component_tabs_count),
                count = tabsCustomizationState.tabsCount,
                minusIconContentDescription = stringResource(id = R.string.component_tabs_remove_tab),
                plusIconContentDescription = stringResource(id = R.string.component_tabs_add_tab),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                minCount = tabCountMin,
                maxCount = tabCountMax
            )
        }) {

        HorizontalPager(state = tabsCustomizationState.pagerState, count = tabsCustomizationState.tabs.size) { page ->
            tabsCustomizationState.tabs[page].Screen()
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
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

enum class TabItem(@DrawableRes val icon: Int, @StringRes val titleRes: Int) {
    Favourites(R.drawable.ic_heart, R.string.component_tab_favourites),
    Calls(R.drawable.ic_call, R.string.component_tab_calls),
    Alerts(R.drawable.ic_alert, R.string.component_tab_alerts),
    Calendar(R.drawable.ic_calendar, R.string.component_tab_calendar),
    Account(R.drawable.ic_account, R.string.component_tab_account),
    Settings(R.drawable.ic_settings, R.string.component_tab_settings);

    @Composable
    fun Screen() {
        TabsPagerContentScreen(stringResource(id = titleRes))
    }
}