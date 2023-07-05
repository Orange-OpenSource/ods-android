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
import com.orange.ods.app.ui.MainTopAppBarState
import com.orange.ods.app.ui.TopAppBarConfiguration

@Composable
fun rememberTopAppBarCustomizationState(
    navigationIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.isNavigationIconEnabled) },
    actionCount: MutableState<Int> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.actions.count()) },
    overflowMenuEnabled: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(
            MainTopAppBarState.DefaultConfiguration.actions.contains(
                TopAppBarConfiguration.Action.OverflowMenu
            )
        )
    },
    titleLineNum: MutableState<TopAppBarCustomizationState.TitleLineNum> = rememberSaveable { mutableStateOf(TopAppBarCustomizationState.TitleLineNum.OneLine) },
    scrollBehavior: MutableState<TopAppBarCustomizationState.ScrollBehavior> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.scrollBehavior) }
) =
    remember(navigationIconEnabled, actionCount, overflowMenuEnabled, titleLineNum, scrollBehavior) {
        TopAppBarCustomizationState(navigationIconEnabled, actionCount, overflowMenuEnabled, titleLineNum, scrollBehavior)
    }

class TopAppBarCustomizationState(
    val navigationIconEnabled: MutableState<Boolean>,
    val actionCount: MutableState<Int>,
    val overflowMenuEnabled: MutableState<Boolean>,
    val titleLineNum: MutableState<TitleLineNum>,
    val scrollBehavior: MutableState<ScrollBehavior>
) {
    enum class TitleLineNum(val titleResId: Int) {
        OneLine(R.string.component_app_bars_top_large_title_one_line_value),
        TwoLines(R.string.component_app_bars_top_large_title_two_lines_value),
        ThreeLinesAndMore(R.string.component_app_bars_top_large_title_truncated_value)
    }

    enum class ScrollBehavior {
        None, Collapsible
    }

    private val maxActionCount = 3

    val minActionCount = 0

    val isCollapsible: Boolean
        get() = scrollBehavior.value == ScrollBehavior.Collapsible

    val isNavigationIconEnabled: Boolean
        get() = navigationIconEnabled.value

    val isOverflowMenuEnabled: Boolean
        get() = overflowMenuEnabled.value

    val isOverflowMenuSwitchEnabled: Boolean
        get() = actionCount.value <= maxActionCount - 1

    val maxActionCountSelectable: Int
        get() = if (isOverflowMenuEnabled) maxActionCount - 1 else maxActionCount

}