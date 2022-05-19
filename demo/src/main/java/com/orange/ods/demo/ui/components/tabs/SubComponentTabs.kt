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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.SubComponent

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SubComponentTabs(subComponent: SubComponent, updateTopAppBarTabs: (List<TabItem>, PagerState?, SubComponentTabsState.TabIconType, Boolean) -> Unit) {
    when (subComponent) {
        SubComponent.TabsFixed -> TabsFixedContent(updateTopAppBarTabs)
        SubComponent.TabsScrollable -> TabsScrollableContent()
        else -> {}
    }
}

@Composable
fun TabContentScreen(text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}

sealed class TabItem(@DrawableRes val icon: Int, val title: String, val screen: @Composable () -> Unit) {
    object Favourites : TabItem(R.drawable.ic_heart, "Favourites", { TabContentScreen("Favourites") })
    object Calls : TabItem(R.drawable.ic_call, "Calls", { TabContentScreen("Calls") })
    object Alerts : TabItem(R.drawable.ic_alert, "Alerts", { TabContentScreen("Alerts") })
    object Calendar : TabItem(R.drawable.ic_calendar, "Calendar", { TabContentScreen("Calendar") })
    object Account : TabItem(R.drawable.ic_account, "Account", { TabContentScreen("Account") })
    object Settings : TabItem(R.drawable.ic_settings, "Settings", { TabContentScreen("Settings") })
}