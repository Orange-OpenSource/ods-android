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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.DisplayType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.InputType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.SoftKeyboardAction
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.SoftKeyboardType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.TrailingElement

@Composable
fun rememberTextFieldCustomizationState(
    value: MutableState<String> = rememberSaveable { mutableStateOf("") },
    inputType: MutableState<InputType> = rememberSaveable { mutableStateOf(InputType.SingleLine) },
    leadingIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    displayType: MutableState<DisplayType> = rememberSaveable { mutableStateOf(DisplayType.Default) },
    trailingElement: MutableState<TrailingElement> = rememberSaveable { mutableStateOf(TrailingElement.None) },
    characterCounter: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    softKeyboardType: MutableState<SoftKeyboardType> = rememberSaveable { mutableStateOf(SoftKeyboardType.Text) },
    softKeyboardAction: MutableState<SoftKeyboardAction> = rememberSaveable { mutableStateOf(SoftKeyboardAction.None) },
    softKeyboardCapitalization: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(value, inputType, leadingIcon, displayType, trailingElement, characterCounter, softKeyboardType, softKeyboardAction, softKeyboardCapitalization) {
        TextFieldCustomizationState(
            value,
            inputType,
            leadingIcon,
            displayType,
            trailingElement,
            characterCounter,
            softKeyboardType,
            softKeyboardAction,
            softKeyboardCapitalization
        )
    }

class TextFieldCustomizationState(
    val value: MutableState<String>,
    val inputType: MutableState<InputType>,
    val leadingIcon: MutableState<Boolean>,
    val displayType: MutableState<DisplayType>,
    val trailingElement: MutableState<TrailingElement>,
    val characterCounter: MutableState<Boolean>,
    val softKeyboardType: MutableState<SoftKeyboardType>,
    val softKeyboardAction: MutableState<SoftKeyboardAction>,
    val softKeyboardCapitalization: MutableState<Boolean>
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

    enum class SoftKeyboardType {
        Text, Number, Decimal, Phone, Url, Email;

        fun getKeyboardType() = when (this) {
            Text -> KeyboardType.Text
            Number -> KeyboardType.Number
            Decimal -> KeyboardType.Decimal
            Phone -> KeyboardType.Phone
            Url -> KeyboardType.Uri
            Email -> KeyboardType.Email
        }
    }

    enum class SoftKeyboardAction {
        None, Default, Go, Search, Send, Previous, Next, Done;

        fun getImeAction() = when (this) {
            None -> ImeAction.None
            Default -> ImeAction.Default
            Go -> ImeAction.Go
            Search -> ImeAction.Search
            Send -> ImeAction.Send
            Previous -> ImeAction.Previous
            Next -> ImeAction.Next
            Done -> ImeAction.Done
        }
    }

    val text: String
        get() = if (hasCharacterCounter && value.value.length > TextFieldMaxChars) {
            value.value.substring(0, TextFieldMaxChars) // Limit the length of the text field value to the maximum number of characters
        } else value.value

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

    fun updateText(newText: String) {
        if (!hasCharacterCounter || newText.length <= TextFieldMaxChars) {
            value.value = newText
        }
    }
}