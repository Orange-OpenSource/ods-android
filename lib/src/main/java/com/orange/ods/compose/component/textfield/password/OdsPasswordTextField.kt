/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.textfield.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.compose.component.textfield.OdsTextFieldBottomRow
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews

/**
 * Password text field allows to display and use a text field with common password behaviors like a visualisation icon.
 * It relies on [OdsTextField].
 *
 * @param value Input text to be shown in the text field.
 * @param onValueChange Callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback.
 * @param modifier [Modifier] applied to this text field.
 * @param enabled Controls the enabled state of the [OdsTextField]. When `false`, the text field will
 * be neither editable nor focusable, the input of the text field will not be selectable,
 * visually text field will appear in the disabled UI state.
 * @param readOnly Controls the editable state of the [OdsTextField]. When `true`, the text
 * field can not be modified, however, a user can focus it and copy text from it. Read-only text
 * fields are usually used to display pre-filled forms that user can not edit.
 * @param label Label to be displayed inside or outside the text field. The default text style used
 * is [Typography.caption] when the text field is in focus and [Typography.subtitle1] when the text field is not in focus.
 * @param placeholder Placeholder to be displayed when the text field is in focus and
 * the input text is empty. The default text style for internal [Text] is [Typography.subtitle1].
 * @param visualisationIcon Controls the display of the eye icon to allow showing/hiding password.
 * @param isError Indicates if the text field's current value is in error state. If set to
 * `true`, the text field outline and the error message will be displayed in error color.
 * @param errorMessage Message displayed below the text field when it is in error.
 * @param keyboardOptions Software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions When the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 * @param characterCounter [OdsTextField.CharacterCounter] displayed below the text field.
 */
@Composable
@OdsComposable
fun OdsPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    visualisationIcon: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    characterCounter: OdsTextField.CharacterCounter? = null
) {
    val passwordTextFieldState = rememberOdsPasswordTextFieldState().apply {
        this.enabled.value = enabled
        this.visualisationIcon.value = visualisationIcon
    }

    Column(modifier = modifier) {
        OdsTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            readOnly = readOnly,
            label = label,
            placeholder = placeholder,
            trailing = if (visualisationIcon) {
                passwordVisualisationIcon(passwordTextFieldState)
            } else null,
            isError = isError,
            visualTransformation = passwordTextFieldState.visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true
        )

        OdsTextFieldBottomRow(isError = isError, errorMessage = errorMessage, characterCounter = characterCounter)
    }
}

@Composable
private fun passwordVisualisationIcon(odsPasswordTextFieldState: OdsPasswordTextFieldState) = with(odsPasswordTextFieldState) {
    OdsTextField.TrailingIcon(
        painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_crossed_out_eye) else painterResource(id = R.drawable.ic_eye),
        contentDescription = if (isPasswordVisible) stringResource(id = R.string.ods_passwordTextField_hide_labelA11y) else stringResource(id = R.string.ods_passwordTextField_show_labelA11y),
        onClick = { passwordVisible.value = !isPasswordVisible },
    )
}

private class OdsPasswordTextFieldPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

@UiModePreviews.Default
@Composable
private fun PreviewOdsPasswordTextField(@PreviewParameter(OdsPasswordTextFieldPreviewParameterProvider::class) hasErrorMessage: Boolean) = Preview {
    var value by remember { mutableStateOf("Input text") }
    OdsPasswordTextField(
        value = value,
        onValueChange = { value = it },
        placeholder = "Placeholder",
        isError = hasErrorMessage,
        errorMessage = if (hasErrorMessage) "Error message" else null
    )
}

