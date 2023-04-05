/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable


@Composable
fun rememberButtonIconCustomizationState(
    toggleCount: MutableState<Int> = rememberSaveable { mutableStateOf(ButtonIconCustomizationState.MinToggleCount) },
    enabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(toggleCount, enabled) {
        ButtonIconCustomizationState(toggleCount, enabled)
    }

class ButtonIconCustomizationState(
    val toggleCount: MutableState<Int>,
    val enabled: MutableState<Boolean>
) {
    companion object {
        const val MinToggleCount = 2
        const val MaxToggleCount = 4
    }

    val isEnabled
        get() = enabled.value

}