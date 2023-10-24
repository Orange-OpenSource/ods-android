/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.appbars.top

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.app.R
import com.orange.ods.app.ui.AppBarState

@Composable
fun rememberTopAppBarCustomizationState(
    large: MutableState<Boolean>,
    navigationIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(AppBarState.CustomDefaultConfiguration.isNavigationIconEnabled) },
    actionCount: MutableState<Int> = rememberSaveable { mutableStateOf(AppBarState.CustomDefaultConfiguration.actionCount) },
    overflowMenuEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(AppBarState.CustomDefaultConfiguration.isOverflowMenuEnabled) },
    titleLineCount: MutableState<TopAppBarCustomizationState.Title> = rememberSaveable { mutableStateOf(TopAppBarCustomizationState.Title.Short) },
    scrollBehavior: MutableState<TopAppBarCustomizationState.ScrollBehavior> = rememberSaveable { mutableStateOf(AppBarState.CustomDefaultConfiguration.scrollBehavior) }
) =
    remember(large, navigationIconEnabled, actionCount, overflowMenuEnabled, titleLineCount, scrollBehavior) {
        TopAppBarCustomizationState(large, navigationIconEnabled, actionCount, overflowMenuEnabled, titleLineCount, scrollBehavior)
    }

class TopAppBarCustomizationState(
    val large: MutableState<Boolean>,
    val navigationIconEnabled: MutableState<Boolean>,
    val actionCount: MutableState<Int>,
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

    val isLarge: Boolean
        get() = large.value

    val isLargeCollapsible: Boolean
        get() = large.value && scrollBehavior.value == ScrollBehavior.Collapsible

    val isNavigationIconEnabled: Boolean
        get() = navigationIconEnabled.value

    val isOverflowMenuEnabled: Boolean
        get() = overflowMenuEnabled.value

    val isOverflowMenuSwitchEnabled: Boolean
        get() = actionCount.value <= maxActionCount - 1

    val maxActionCountSelectable: Int
        get() = if (isOverflowMenuEnabled) maxActionCount - 1 else maxActionCount

}