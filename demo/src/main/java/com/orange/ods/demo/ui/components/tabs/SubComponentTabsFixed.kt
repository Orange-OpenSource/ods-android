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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.tab.OdsLeadingIconTab
import com.orange.ods.compose.component.tab.OdsTabRow
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.CustomizationBottomSheet
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun TabsFixedContent(updateTopAppBarTabs: (List<TabItem>, PagerState?) -> Unit) {
    val customizationState = rememberTabsCustomizationState()
    updateTopAppBarTabs(customizationState.tabs, customizationState.pagerState)

    CustomizationBottomSheet(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_element_icon),
                trailing = { OdsCheckbox(checked = customizationState.iconIsChecked.value, onCheckedChange = { checked -> customizationState.iconIsChecked.value = checked }) })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s)),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OdsTextSubtitle1(modifier = Modifier.weight(1f), text = stringResource(id = R.string.component_tabs_number))
                IconButton(onClick = { customizationState.tabsNumber.value-- }, enabled = customizationState.canRemoveTab) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "content description"
                    )
                }
                OdsTextSubtitle1(text = customizationState.tabs.size.toString())
                IconButton(onClick = { customizationState.tabsNumber.value++ }, enabled = customizationState.canAddTab) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "content description"
                    )
                }
            }
        }) {
        TabsContent(tabs = customizationState.tabs, pagerState = customizationState.pagerState)
    }
}

@ExperimentalPagerApi
@Composable
fun TopAppBarTabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    OdsTabRow(
        selectedTabIndex = pagerState.currentPage
    ) {
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            OdsLeadingIconTab(
                icon = painterResource(id = tab.icon),
                text = tab.title,
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}