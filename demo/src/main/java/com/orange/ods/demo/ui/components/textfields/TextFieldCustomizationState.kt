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
    displayType: MutableState<DisplayType> = rememberSaveable { mutableStateOf(DisplayType.DEFAULT) },
    trailingElement: MutableState<TrailingElement> = rememberSaveable { mutableStateOf(TrailingElement.NONE) }
) =
    remember(leadingIconChecked, displayType, trailingElement) {
        TextFieldCustomizationState(leadingIconChecked, displayType, trailingElement)
    }

class TextFieldCustomizationState(
    val leadingIconChecked: MutableState<Boolean>,
    val displayType: MutableState<DisplayType>,
    val trailingElement: MutableState<TrailingElement>
) {
    enum class DisplayType {
        DEFAULT, ERROR, DISABLED
    }

    enum class TrailingElement {
        NONE, ICON, TEXT
    }

    val isEnabled
        get() = displayType.value != DisplayType.DISABLED

    val isError
        get() = displayType.value == DisplayType.ERROR

    val hasTrailingIcon
        get() = trailingElement.value == TrailingElement.ICON

    val hasTrailingText
        get() = trailingElement.value == TrailingElement.TEXT
}