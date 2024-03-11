/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsComponentsConfiguration
import com.orange.ods.theme.typography.OdsTypography

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/483f94-text-fields/b/720e3b" target="_blank">ODS Text fields</a>.
 *
 * The OdsTextField is displayed filled or outlined according the theme component configuration provided. In Orange theme,
 * text fields are outlined cause they are more accessible in term of contrast.
 *
 * @see [OdsOutlinedTextField]
 * @see [OdsFilledTextField]
 *
 * @param value Input text to be shown in the text field.
 * @param onValueChange Callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback.
 * @param modifier [Modifier] applied to this text field.
 * @param trailing [OdsTextField.Trailing] element to display at the end of the text field.
 * @param enabled Controls the enabled state of the [OdsTextField]. When `false`, the text field will
 * be neither editable nor focusable, the input of the text field will not be selectable,
 * visually text field will appear in the disabled UI state.
 * @param readOnly Controls the editable state of the [OdsTextField]. When `true`, the text
 * field can not be modified, however, a user can focus it and copy text from it. Read-only text
 * fields are usually used to display pre-filled forms that user can not edit.
 * @param label Label to be displayed inside or outside the text field. The default text style used
 * is [OdsTypography.bodyS] when the text field is in focus and [OdsTypography.titleM] when the text field is not in focus.
 * @param placeholder Placeholder to be displayed when the text field is in focus and
 * the input text is empty. The default text style for internal [Text] is [OdsTypography.titleM].
 * @param leadingIcon [OdsTextField.LeadingIcon] displayed at the beginning of the text field container.
 * @param isError Indicates if the text field's current value is in error state. If set to
 * `true`, the text field outline and the error message will be displayed in error color.
 * @param errorMessage Message displayed below the text field when it is in error.
 * @param visualTransformation Transforms the visual representation of the input [value].
 * For example, you can use [androidx.compose.ui.text.input.PasswordVisualTransformation] to create a password
 * text field. By default no visual transformation is applied.
 * @param keyboardOptions Software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions When the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 * @param singleLine When set to `true`, this text field becomes a single horizontally scrolling
 * text field instead of wrapping onto multiple lines. The keyboard will be informed to not show
 * the return key as the [ImeAction]. Note that [maxLines] parameter will be ignored as the
 * maxLines attribute will be automatically set to 1.
 * @param maxLines Maximum number of visible lines. Should be equal or greater than 1.
 * Note that this parameter will be ignored and instead maxLines will be set to 1 if [singleLine] is set to `true`.
 * @param characterCounter [OdsTextField.CharacterCounter] displayed below the text field.
 */
@Composable
@OdsComposable
fun OdsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    trailing: OdsTextField.Trailing? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: OdsTextField.LeadingIcon? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    characterCounter: OdsTextField.CharacterCounter? = null
) {
    val filledTextField = OdsTheme.componentsConfiguration.textFieldStyle == OdsComponentsConfiguration.ComponentStyle.Filled

    if (filledTextField) {
        OdsFilledTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailing = trailing,
            isError = isError,
            errorMessage = errorMessage,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            characterCounter = characterCounter
        )
    } else {
        OdsOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailing = trailing,
            isError = isError,
            errorMessage = errorMessage,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            characterCounter = characterCounter
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsTextField(@PreviewParameter(OdsTextFieldPreviewParameterProvider::class) parameter: OdsTextFieldPreviewParameter) = OdsPreview {
    var value by remember { mutableStateOf("Input text") }
    OdsTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { value = it },
        placeholder = "Placeholder",
        leadingIcon = OdsTextField.LeadingIcon(painterResource(id = android.R.drawable.ic_dialog_info), ""),
        trailing = trailingPreview(parameter = parameter),
        isError = parameter.hasErrorMessage,
        errorMessage = getPreviewErrorMessage(parameter.hasErrorMessage, parameter.isVeryLongErrorMessage),
        characterCounter = if (parameter.hasCounter) OdsTextField.CharacterCounter(value.length, 30) else null
    )
}

private fun getPreviewErrorMessage(hasErrorMessage: Boolean, isVeryLongErrorMessage: Boolean) = when {
    hasErrorMessage && !isVeryLongErrorMessage -> "Error message"
    hasErrorMessage && isVeryLongErrorMessage -> "Very very very very very very very very very long error message"
    else -> null
}