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

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.DisplayType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.InputType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.SoftKeyboardAction
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.SoftKeyboardType
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.TrailingElement

@Composable
fun rememberTextFieldCustomizationState(
    text: MutableState<String> = rememberSaveable { mutableStateOf("") },
    inputType: MutableState<InputType> = rememberSaveable { mutableStateOf(InputType.SingleLine) },
    leadingIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    displayType: MutableState<DisplayType> = rememberSaveable { mutableStateOf(DisplayType.Default) },
    trailingElement: MutableState<TrailingElement> = rememberSaveable { mutableStateOf(TrailingElement.None) },
    visualisationIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    characterCounter: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    softKeyboardType: MutableState<SoftKeyboardType> = rememberSaveable { mutableStateOf(SoftKeyboardType.Text) },
    softKeyboardAction: MutableState<SoftKeyboardAction> = rememberSaveable { mutableStateOf(SoftKeyboardAction.None) },
    softKeyboardCapitalization: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(
        text,
        inputType,
        leadingIcon,
        displayType,
        trailingElement,
        visualisationIcon,
        characterCounter,
        softKeyboardType,
        softKeyboardAction,
        softKeyboardCapitalization
    ) {
        TextFieldCustomizationState(
            text,
            inputType,
            leadingIcon,
            displayType,
            trailingElement,
            visualisationIcon,
            characterCounter,
            softKeyboardType,
            softKeyboardAction,
            softKeyboardCapitalization
        )
    }

class TextFieldCustomizationState(
    val text: MutableState<String>,
    val inputType: MutableState<InputType>,
    val leadingIcon: MutableState<Boolean>,
    val displayType: MutableState<DisplayType>,
    val trailingElement: MutableState<TrailingElement>,
    val visualisationIcon: MutableState<Boolean>,
    val characterCounter: MutableState<Boolean>,
    val softKeyboardType: MutableState<SoftKeyboardType>,
    val softKeyboardAction: MutableState<SoftKeyboardAction>,
    val softKeyboardCapitalization: MutableState<Boolean>
) {
    companion object {
        const val TextFieldMaxChars = 20
    }

    enum class InputType {
        SingleLine, Multiline, TextArea
    }

    enum class DisplayType {
        Default, Error, Disabled
    }

    enum class TrailingElement {
        None, Icon, Text
    }

    enum class SoftKeyboardType(val keyboardType: KeyboardType, @StringRes val labelRes: Int) {
        Text(KeyboardType.Text, R.string.component_text_field_keyboard_type_text),
        Decimal(KeyboardType.Decimal, R.string.component_text_field_keyboard_type_decimal),
        Email(KeyboardType.Email, R.string.component_text_field_keyboard_type_email),
        Number(KeyboardType.Number, R.string.component_text_field_keyboard_type_number),
        Phone(KeyboardType.Phone, R.string.component_text_field_keyboard_type_phone),
        Url(KeyboardType.Uri, R.string.component_text_field_keyboard_type_url);
    }

    enum class SoftKeyboardAction(val imeAction: ImeAction, @StringRes val labelRes: Int) {
        None(ImeAction.None, R.string.component_text_field_keyboard_action_none),
        Default(ImeAction.Default, R.string.component_text_field_keyboard_action_default),
        Done(ImeAction.Done, R.string.component_text_field_keyboard_action_done),
        Go(ImeAction.Go, R.string.component_text_field_keyboard_action_go),
        Search(ImeAction.Search, R.string.component_text_field_keyboard_action_search),
        Send(ImeAction.Send, R.string.component_text_field_keyboard_action_send),
        Previous(ImeAction.Previous, R.string.component_text_field_keyboard_action_previous),
        Next(ImeAction.Next, R.string.component_text_field_keyboard_action_next);
    }

    val displayedText: String
        get() = if (hasCharacterCounter && text.value.length > TextFieldMaxChars) {
            text.value.substring(0, TextFieldMaxChars) // Limit the length of the text field value to the maximum number of characters
        } else text.value

    val isEnabled
        get() = displayType.value != DisplayType.Disabled

    val isError
        get() = displayType.value == DisplayType.Error

    val hasLeadingIcon
        get() = leadingIcon.value

    val hasVisualisationIcon
        get() = visualisationIcon.value

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
            text.value = newText
        }
    }
}