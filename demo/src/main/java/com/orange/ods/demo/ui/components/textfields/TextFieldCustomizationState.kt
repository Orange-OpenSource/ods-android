/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.textfields

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberTextFieldCustomizationState(
    leadingIconChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    selectedState: MutableState<String> = rememberSaveable { mutableStateOf(TextFieldCustomizationState.Companion.DisplayType.DEFAULT.name) },
    selectedTrailingElement: MutableState<String> = rememberSaveable { mutableStateOf(TextFieldCustomizationState.Companion.TrailingElement.NONE.name) }
) =
    remember(leadingIconChecked, selectedState, selectedTrailingElement) {
        TextFieldCustomizationState(leadingIconChecked, selectedState, selectedTrailingElement)
    }

class TextFieldCustomizationState(
    val leadingIconChecked: MutableState<Boolean>,
    val selectedState: MutableState<String>,
    val selectedTrailingElement: MutableState<String>
) {
    companion object {
        enum class DisplayType {
            DEFAULT, ERROR, DISABLED
        }

        enum class TrailingElement {
            NONE, ICON, TEXT
        }
    }

    val enabled
        get() = selectedState.value != DisplayType.DISABLED.name

    val isError
        get() = selectedState.value == DisplayType.ERROR.name

    val hasTrailingIcon
        get() = selectedTrailingElement.value == TrailingElement.ICON.name

    val hasTrailingText
        get() = selectedTrailingElement.value == TrailingElement.TEXT.name
}