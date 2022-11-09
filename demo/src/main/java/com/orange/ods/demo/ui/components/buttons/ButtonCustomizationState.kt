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


@Composable
fun rememberButtonCustomizationState(
    buttonStyle: MutableState<OdsButtonStyle> = rememberSaveable { mutableStateOf(OdsButtonStyle.Default) },
    leadingIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    variableWidth: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    disabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(buttonStyle, leadingIcon, variableWidth, disabled) {
        ButtonCustomizationState(buttonStyle, leadingIcon, variableWidth, disabled)
    }

class ButtonCustomizationState(
    val buttonStyle: MutableState<OdsButtonStyle>,
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