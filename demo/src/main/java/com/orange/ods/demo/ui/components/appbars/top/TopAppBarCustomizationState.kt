/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.appbars.top

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.demo.ui.OdsDemoTopAppBarState

@Composable
fun rememberTopAppBarCustomizationState(
    navigationIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(OdsDemoTopAppBarState.defaultConfiguration.isNavigationIconEnabled) },
    actionCount: MutableState<Int> = rememberSaveable { mutableStateOf(OdsDemoTopAppBarState.defaultConfiguration.actionCount) },
    overflowMenuEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(OdsDemoTopAppBarState.defaultConfiguration.isOverflowMenuEnabled) },
) =
    remember(navigationIconEnabled, actionCount, overflowMenuEnabled) {
        TopAppBarCustomizationState(navigationIconEnabled, actionCount, overflowMenuEnabled)
    }

class TopAppBarCustomizationState(
    val navigationIconEnabled: MutableState<Boolean>,
    val actionCount: MutableState<Int>,
    val overflowMenuEnabled: MutableState<Boolean>
) {
    private val maxActionCount = 3

    val minActionCount = 0

    val isNavigationIconEnabled: Boolean
        get() = navigationIconEnabled.value

    val isOverflowMenuEnabled: Boolean
        get() = overflowMenuEnabled.value

    val isOverflowMenuSwitchEnabled: Boolean
        get() = actionCount.value <= maxActionCount - 1

    val maxActionCountSelectable: Int
        get() = if (isOverflowMenuEnabled) maxActionCount - 1 else maxActionCount

}