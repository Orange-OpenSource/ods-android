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
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.DisplayType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.TrailingElement

@Composable
fun rememberTextFieldCustomizationState(
    leadingIconChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    selectedDisplayType: MutableState<DisplayType> = rememberSaveable { mutableStateOf(DisplayType.DEFAULT) },
    selectedTrailingElement: MutableState<TrailingElement> = rememberSaveable { mutableStateOf(TrailingElement.NONE) }
) =
    remember(leadingIconChecked, selectedDisplayType, selectedTrailingElement) {
        TextFieldCustomizationState(leadingIconChecked, selectedDisplayType, selectedTrailingElement)
    }

class TextFieldCustomizationState(
    val leadingIconChecked: MutableState<Boolean>,
    val selectedState: MutableState<DisplayType>,
    val selectedTrailingElement: MutableState<TrailingElement>
) {
    enum class DisplayType {
        DEFAULT, ERROR, DISABLED
    }

    enum class TrailingElement {
        NONE, ICON, TEXT
    }

    val isEnabled
        get() = selectedState.value != DisplayType.DISABLED

    val isError
        get() = selectedState.value == DisplayType.ERROR

    val hasTrailingIcon
        get() = selectedTrailingElement.value == TrailingElement.ICON

    val hasTrailingText
        get() = selectedTrailingElement.value == TrailingElement.TEXT
}