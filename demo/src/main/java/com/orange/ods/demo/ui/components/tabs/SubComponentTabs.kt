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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.SubComponent
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationCheckboxItem
import com.orange.ods.demo.ui.utilities.composable.LabelledRadioButton

private const val FixedTabsCountMin = 2
private const val FixedTabsCountMax = 3
private const val ScrollableTabsCountMin = 4
private const val ScrollableTabsCountMax = 6

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SubComponentTabs(subComponent: SubComponent, updateTopAppBarTabs: (TabsConfiguration) -> Unit) {
    val scrollableTabs: Boolean
    val tabCountMin: Int
    val tabCountMax: Int
    if (subComponent == SubComponent.TabsScrollable) {
        scrollableTabs = true
        tabCountMin = ScrollableTabsCountMin
        tabCountMax = ScrollableTabsCountMax
    } else {
        scrollableTabs = false
        tabCountMin = FixedTabsCountMin
        tabCountMax = FixedTabsCountMax
    }

    val subComponentTabsState = rememberSubComponentTabsState(tabsNumber = rememberSaveable { mutableStateOf(tabCountMin) })
    updateTopAppBarTabs(
        TabsConfiguration(
            scrollableTabs = scrollableTabs,
            tabs = subComponentTabsState.tabs,
            pagerState = subComponentTabsState.pagerState,
            tabIconType = subComponentTabsState.selectedTabIconType.value,
            tabTextEnabled = subComponentTabsState.tabTextEnabled.value,
        )
    )

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = subComponentTabsState.bottomSheetScaffoldState,
        bottomSheetContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.ods_spacing_xs))
                    .padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s)),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OdsTextSubtitle1(modifier = Modifier.weight(1f), text = stringResource(id = R.string.component_element_icon))
                LabelledRadioButton(
                    selectedRadio = subComponentTabsState.selectedTabIconType,
                    currentRadio = SubComponentTabsState.TabIconType.Leading,
                    label = stringResource(id = R.string.component_tab_icon_leading),
                    enabled = subComponentTabsState.areTabIconRadiosEnabled
                )
                LabelledRadioButton(
                    selectedRadio = subComponentTabsState.selectedTabIconType,
                    currentRadio = SubComponentTabsState.TabIconType.Top,
                    label = stringResource(id = R.string.component_tab_icon_top),
                    enabled = subComponentTabsState.areTabIconRadiosEnabled
                )
                LabelledRadioButton(
                    selectedRadio = subComponentTabsState.selectedTabIconType,
                    currentRadio = SubComponentTabsState.TabIconType.None,
                    label = stringResource(id = R.string.component_element_none),
                    enabled = subComponentTabsState.areTabIconRadiosEnabled
                )
            }

            ComponentCustomizationCheckboxItem(R.string.component_element_text, subComponentTabsState.tabTextEnabled, subComponentTabsState.isTabTextCheckboxEnabled)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s)),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OdsTextSubtitle1(modifier = Modifier.weight(1f), text = stringResource(id = R.string.component_tabs_count))
                IconButton(onClick = { subComponentTabsState.tabsNumber.value-- }, enabled = subComponentTabsState.canRemoveTab(tabCountMin)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "content description"
                    )
                }
                OdsTextSubtitle1(text = subComponentTabsState.tabs.size.toString())
                IconButton(onClick = { subComponentTabsState.tabsNumber.value++ }, enabled = subComponentTabsState.canAddTab(tabCountMax)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "content description"
                    )
                }
            }
        }) {

        HorizontalPager(state = subComponentTabsState.pagerState, count = subComponentTabsState.tabs.size) { page ->
            subComponentTabsState.tabs[page].Screen()
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