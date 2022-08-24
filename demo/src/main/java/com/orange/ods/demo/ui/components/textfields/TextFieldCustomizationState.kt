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
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.InputType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.TrailingElement

@Composable
fun rememberTextFieldCustomizationState(
    inputType: MutableState<InputType> = rememberSaveable { mutableStateOf(InputType.SingleLine) },
    leadingIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    displayType: MutableState<DisplayType> = rememberSaveable { mutableStateOf(DisplayType.Default) },
    trailingElement: MutableState<TrailingElement> = rememberSaveable { mutableStateOf(TrailingElement.None) },
    characterCounter: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(inputType, leadingIcon, displayType, trailingElement) {
        TextFieldCustomizationState(inputType, leadingIcon, displayType, trailingElement, characterCounter)
    }

class TextFieldCustomizationState(
    val inputType: MutableState<InputType>,
    val leadingIcon: MutableState<Boolean>,
    val displayType: MutableState<DisplayType>,
    val trailingElement: MutableState<TrailingElement>,
    val characterCounter: MutableState<Boolean>
) {
    companion object {
        const val TextFieldMaxChars = 20
    }

    enum class InputType {
        SingleLine, MultiLine, TextArea
    }

    enum class DisplayType {
        Default, Error, Disabled
    }

    enum class TrailingElement {
        None, Icon, Text
    }

    val isEnabled
        get() = displayType.value != DisplayType.Disabled

    val isError
        get() = displayType.value == DisplayType.Error

    val hasLeadingIcon
        get() = leadingIcon.value

    val hasTrailingIcon
        get() = trailingElement.value == TrailingElement.Icon

    val hasTrailingText
        get() = trailingElement.value == TrailingElement.Text

    val isSingleLine
        get() = inputType.value == InputType.SingleLine

    val hasCharacterCounter
        get() = characterCounter.value
}