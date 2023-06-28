/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.component.button.OdsTextButtonStyle


@Composable
fun rememberButtonCustomizationState(
    toggleCount: MutableState<Int> = rememberSaveable { mutableStateOf(ButtonCustomizationState.MinToggleCount) },
    buttonStyle: MutableState<OdsButtonStyle> = rememberSaveable { mutableStateOf(OdsButtonStyle.Default) },
    textButtonStyle: MutableState<OdsTextButtonStyle> = rememberSaveable { mutableStateOf(OdsTextButtonStyle.Default) },
    leadingIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    fullScreenWidth: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    enabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(buttonStyle, textButtonStyle, leadingIcon, fullScreenWidth, enabled, toggleCount) {
        ButtonCustomizationState(toggleCount, buttonStyle, textButtonStyle, leadingIcon, fullScreenWidth, enabled)
    }

class ButtonCustomizationState(
    val toggleCount: MutableState<Int>,
    val buttonStyle: MutableState<OdsButtonStyle>,
    val textButtonStyle: MutableState<OdsTextButtonStyle>,
    val leadingIcon: MutableState<Boolean>,
    val fullScreenWidth: MutableState<Boolean>,
    val enabled: MutableState<Boolean>
) {
    companion object {
        const val MinToggleCount = 2
        const val MaxToggleCount = 3
    }

    val hasLeadingIcon
        get() = leadingIcon.value

    val isEnabled
        get() = enabled.value

    val hasFullScreenWidth
        get() = fullScreenWidth.value
}