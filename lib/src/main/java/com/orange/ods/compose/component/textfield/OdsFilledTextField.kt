/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

@Composable
internal fun OdsFilledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: OdsTextFieldIcon? = null,
    trailing: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    characterCounter: OdsTextFieldCharacterCounter? = null
) {
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = if (singleLine) value.filter { it != '\n' } else value,
            onValueChange = { newValue ->
                if (!singleLine || !newValue.contains('\n')) {
                    onValueChange(newValue)
                }
            },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = OdsTheme.typography.subtitle1,
            label = label?.let { { Text(label) } },
            placeholder = placeholder?.let { { Text(text = placeholder, style = OdsTheme.typography.subtitle1) } },
            leadingIcon = leadingIcon?.let {
                {
                    it.Content(OdsTextFieldIcon.ExtraParameters(enabled))
                }
            },
            trailingIcon = trailing,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            colors = OdsTextFieldDefaults.textFieldColors()
        )

        OdsTextFieldBottomRow(isError = isError, errorMessage = errorMessage, characterCounter = characterCounter)
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsTextField(@PreviewParameter(OdsTextFieldPreviewParameterProvider::class) parameter: OdsTextFieldPreviewParameter) = Preview {
    var value by remember { mutableStateOf("Input text") }
    OdsFilledTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { value = it },
        placeholder = "Placeholder",
        leadingIcon = OdsTextFieldIcon(painterResource(id = android.R.drawable.ic_dialog_info), ""),
        trailing = getTrailingPreview(parameter = parameter, value = value),
        isError = parameter.hasErrorMessage,
        errorMessage = getPreviewErrorMessage(parameter.hasErrorMessage, parameter.isVeryLongErrorMessage),
        characterCounter = if (parameter.hasCounter) OdsTextFieldCharacterCounter(value.length, 30) else null
    )
}

private fun getPreviewErrorMessage(hasErrorMessage: Boolean, isVeryLongErrorMessage: Boolean) = when {
    hasErrorMessage && !isVeryLongErrorMessage -> "Error message"
    hasErrorMessage && isVeryLongErrorMessage -> "Very very very very very very very very very long error message"
    else -> null
}