/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.component.button.OdsTextButtonStyle


@Composable
fun rememberButtonCustomizationState(
    containedButtonStyle: MutableState<OdsButtonStyle> = rememberSaveable { mutableStateOf(OdsButtonStyle.Default) },
    textButtonStyle: MutableState<OdsTextButtonStyle> = rememberSaveable { mutableStateOf(OdsTextButtonStyle.Default) },
    leadingIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    variableWidth: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    disabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(containedButtonStyle, textButtonStyle, leadingIcon, variableWidth, disabled) {
        ButtonCustomizationState(containedButtonStyle, textButtonStyle, leadingIcon, variableWidth, disabled)
    }

@Composable
fun rememberButtonToggleCustomizationState(
    toggleCount: MutableState<Int> = rememberSaveable { mutableStateOf(ButtonToggleCustomizationState.MinToggleCount) }
) =
    remember(toggleCount) {
        ButtonToggleCustomizationState(toggleCount)
    }

class ButtonCustomizationState(
    val containedButtonStyle: MutableState<OdsButtonStyle>,
    val textButtonStyle: MutableState<OdsTextButtonStyle>,
    val leadingIcon: MutableState<Boolean>,
    val variableWidth: MutableState<Boolean>,
    val disabled: MutableState<Boolean>
) {

    val hasLeadingIcon
        get() = leadingIcon.value

    val isEnabled
        get() = !disabled.value

    val hasVariableWidth
        get() = variableWidth.value
}

class ButtonToggleCustomizationState(
    val toggleCount: MutableState<Int>,
) {
    companion object {
        const val MinToggleCount = 1
        const val MaxToggleCount = 3
    }
}