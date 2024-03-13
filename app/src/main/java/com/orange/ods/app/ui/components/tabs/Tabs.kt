/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.app.ui.components.tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.compose.component.tab.OdsTabRow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun tabs(
    tabs: List<NavigationItem>,
    pagerState: PagerState,
    tabIconEnabled: Boolean,
    tabTextEnabled: Boolean,
): List<OdsTabRow.Tab> {
    val scope = rememberCoroutineScope()

    return tabs.mapIndexed { index, tab ->
        OdsTabRow.Tab(
            icon = if (tabIconEnabled) OdsTabRow.Tab.Icon(painter = painterResource(id = tab.iconResId)) else null,
            text = if (tabTextEnabled) stringResource(id = tab.textResId) else null,
        ) {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    }
}
