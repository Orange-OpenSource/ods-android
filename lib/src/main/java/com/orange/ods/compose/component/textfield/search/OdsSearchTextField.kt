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

package com.orange.ods.compose.component.textfield.search

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.orange.ods.R
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.textfield.styledTextFieldValue
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.colors.fromToken

/**
 *
 * OdsSearchTextField component allows to display a text field in the top app bar of a search screen.
 * @see androidx.compose.material.TextField
 *
 * @param value Input text to be shown in the text field.
 * @param onValueChange Callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback.
 * @param placeholder Placeholder to be displayed in the text field when the input text is empty.
 * @param modifier [Modifier] applied to this text field.
 */
@Composable
internal fun OdsSearchTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    val textStyle = OdsTheme.typography.titleLarge
    TextField(
        value = styledTextFieldValue(value = value, textStyle = textStyle),
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = OdsTheme.typography.bodyLarge.textStyle,
                fontSize = 20.sp
            )
        },
        modifier = modifier,
        textStyle = textStyle.textStyle,
        trailingIcon = {
            OdsIconButton(
                onClick = { onValueChange(TextFieldValue("")) },
                graphicsObject = rememberVectorPainter(image = Icons.Default.Close),
                contentDescription = stringResource(id = R.string.ods_searchTextField_clear_labelA11y),
                tint = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.trailingIconColor)
            )
        },
        singleLine = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.headlineColor),
            unfocusedTextColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.headlineColor),
            cursorColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.headlineColor),
            focusedLeadingIconColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.leadingIconColor),
            unfocusedLeadingIconColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.leadingIconColor),
            focusedTrailingIconColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.trailingIconColor),
            unfocusedTrailingIconColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.trailingIconColor),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.headlineColor).copy(0.74f), //TODO Use plain color
            unfocusedPlaceholderColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.headlineColor).copy(0.74f), // TODO use plain color
        )
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSearchTextField(@PreviewParameter(OdsSearchTextFieldPreviewParameterProvider::class) parameter: OdsSearchTextFieldPreviewParameter) =
    OdsPreview {
        with(parameter) {
            OdsSearchTextField(
                value = value,
                onValueChange = {},
                placeholder = placeholder
            )
        }
    }

private data class OdsSearchTextFieldPreviewParameter(
    val value: TextFieldValue,
    val placeholder: String
)

private class OdsSearchTextFieldPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsSearchTextFieldPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsSearchTextFieldPreviewParameter>
    get() {
        val placeholder = "Search something"

        return listOf(
            OdsSearchTextFieldPreviewParameter(TextFieldValue(""), placeholder),
            OdsSearchTextFieldPreviewParameter(TextFieldValue("Text"), placeholder),
        )
    }