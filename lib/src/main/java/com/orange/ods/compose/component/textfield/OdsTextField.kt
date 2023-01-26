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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsComponentsConfiguration

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/483f94-text-fields/b/720e3b" target="_blank">ODS Text fields</a>.
 *
 * The OdsTextField is displayed filled or outlined according the theme component configuration provided. In Orange theme,
 * text fields are outlined cause they are more accessible in term of contrast.
 *
 * @see [OdsOutlinedTextField]
 * @see [OdsFilledTextField]
 *
 * @param value the input text to be shown in the text field
 * @param onValueChange the callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback
 * @param modifier a [Modifier] for this text field
 * @param enabled controls the enabled state of the [TextField]. When `false`, the text field will
 * be neither editable nor focusable, the input of the text field will not be selectable,
 * visually text field will appear in the disabled UI state
 * @param readOnly controls the editable state of the [TextField]. When `true`, the text
 * field can not be modified, however, a user can focus it and copy text from it. Read-only text
 * fields are usually used to display pre-filled forms that user can not edit
 * @param label the optional label to be displayed inside the text field container. The default
 * text style for internal [Text] is [Typography.caption] when the text field is in focus and
 * [Typography.subtitle1] when the text field is not in focus
 * @param placeholder the optional placeholder to be displayed when the text field is in focus and
 * the input text is empty. The default text style for internal [Text] is [Typography.subtitle1]
 * @param leadingIcon the optional leading icon painter to be displayed at the beginning of the text field
 * container
 * @param leadingIconContentDescription the optional content description for the leading icon
 * @param onLeadingIconClick the optional action executed on leading icon click
 * @param trailingIcon the optional trailing icon painter to be displayed at the end of the text field
 * container
 * @param trailingIconContentDescription the optional content description for the trailing icon
 * @param onTrailingIconClick the optional action executed on trailing icon click
 * @param trailingText the optional trailing text displayed at the end of the text field container
 * @param isError indicates if the text field's current value is in error state. If set to
 * true, the label, bottom indicator and trailing icon by default will be displayed in error color
 * @param errorMessage displayed when the [OdsTextField] is in error
 * @param visualTransformation transforms the visual representation of the input [value].
 * For example, you can use [androidx.compose.ui.text.input.PasswordVisualTransformation] to create a password
 * text field. By default no visual transformation is applied
 * @param keyboardOptions software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 * @param singleLine when set to true, this text field becomes a single horizontally scrolling
 * text field instead of wrapping onto multiple lines. The keyboard will be informed to not show
 * the return key as the [ImeAction]. Note that [maxLines] parameter will be ignored as the
 * maxLines attribute will be automatically set to 1.
 * @param maxLines the maximum height in terms of maximum number of visible lines. Should be
 * equal or greater than 1. Note that this parameter will be ignored and instead maxLines will be
 * set to 1 if [singleLine] is set to true.
 * @param characterCounter displayed below the text field. Please use the appropriate [OdsTextFieldCharacterCounter] composable.
 */
@Composable
@OdsComponentApi
fun OdsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    leadingIconContentDescription: String? = null,
    onLeadingIconClick: (() -> Unit)? = null,
    trailingIcon: Painter? = null,
    trailingIconContentDescription: String? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    trailingText: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    characterCounter: (@Composable () -> Unit)? = null
) {
    val filledTextField = OdsTheme.componentsConfiguration.textFieldStyle == OdsComponentsConfiguration.Companion.ComponentStyle.Filled

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
            leadingIconContentDescription = leadingIconContentDescription,
            onLeadingIconClick = onLeadingIconClick,
            trailingIcon = trailingIcon,
            trailingIconContentDescription = trailingIconContentDescription,
            onTrailingIconClick = onTrailingIconClick,
            trailingText = trailingText,
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
            leadingIconContentDescription = leadingIconContentDescription,
            onLeadingIconClick = onLeadingIconClick,
            trailingIcon = trailingIcon,
            trailingIconContentDescription = trailingIconContentDescription,
            onTrailingIconClick = onTrailingIconClick,
            trailingText = trailingText,
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
private fun PreviewOdsTextField(@PreviewParameter(OdsTextFieldPreviewParameterProvider::class) parameter: OdsTextFieldPreviewParameter) = Preview {
    var value by remember { mutableStateOf("Input text") }
    OdsTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { value = it },
        placeholder = "Placeholder",
        leadingIcon = painterResource(id = android.R.drawable.ic_dialog_info),
        trailingIcon = painterResource(id = android.R.drawable.ic_input_add),
        isError = parameter.hasErrorMessage,
        errorMessage = getPreviewErrorMessage(parameter.hasErrorMessage, parameter.isVeryLongErrorMessage),
        characterCounter = if (parameter.hasCounter) {
            {
                OdsTextFieldCharacterCounter(value.length, 30)
            }
        } else null
    )
}

private fun getPreviewErrorMessage(hasErrorMessage: Boolean, isVeryLongErrorMessage: Boolean) = when {
    hasErrorMessage && !isVeryLongErrorMessage -> "Error message"
    hasErrorMessage && isVeryLongErrorMessage -> "Very very very very very very very very very long error message"
    else -> null
}