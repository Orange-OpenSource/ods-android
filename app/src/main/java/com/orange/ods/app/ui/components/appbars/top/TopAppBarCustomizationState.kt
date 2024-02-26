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

package com.orange.ods.app.ui.components.appbars.top

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.app.R
import com.orange.ods.app.ui.CustomAppBarConfiguration

@Composable
fun rememberTopAppBarCustomizationState(
    navigationIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(CustomAppBarConfiguration.Default.isNavigationIconEnabled) },
    actionCount: MutableIntState = rememberSaveable { mutableIntStateOf(CustomAppBarConfiguration.Default.actionCount) },
    overflowMenuEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(CustomAppBarConfiguration.Default.isOverflowMenuEnabled) },
    titleLineCount: MutableState<TopAppBarCustomizationState.Title> = rememberSaveable { mutableStateOf(TopAppBarCustomizationState.Title.Short) },
    scrollBehavior: MutableState<TopAppBarCustomizationState.ScrollBehavior> = rememberSaveable { mutableStateOf(CustomAppBarConfiguration.Default.scrollBehavior) }
) =
    remember(navigationIconEnabled, actionCount, overflowMenuEnabled, titleLineCount, scrollBehavior) {
        TopAppBarCustomizationState(navigationIconEnabled, actionCount, overflowMenuEnabled, titleLineCount, scrollBehavior)
    }

class TopAppBarCustomizationState(
    val navigationIconEnabled: MutableState<Boolean>,
    val actionCount: MutableIntState,
    val overflowMenuEnabled: MutableState<Boolean>,
    val title: MutableState<Title>,
    val scrollBehavior: MutableState<ScrollBehavior>
) {
    enum class Title(val titleRes: Int) {
        Short(R.string.component_app_bars_top_large_title_short_value),
        TwoLines(R.string.component_app_bars_top_large_title_two_lines_value),
        Long(R.string.component_app_bars_top_large_title_long_value)
    }

    enum class ScrollBehavior {
        None, Collapsible
    }

    private val maxActionCount = 3

    val minActionCount = 0

    val isNavigationIconEnabled: Boolean
        get() = navigationIconEnabled.value

    val isOverflowMenuEnabled: Boolean
        get() = overflowMenuEnabled.value

    val isOverflowMenuSwitchEnabled: Boolean
        get() = actionCount.intValue <= maxActionCount - 1

    val maxActionCountSelectable: Int
        get() = if (isOverflowMenuEnabled) maxActionCount - 1 else maxActionCount

}