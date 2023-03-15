/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.textfield.search

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 *
 * @see androidx.compose.material.TextField
 *
 * @param value the input text to be shown in the text field
 * @param onValueChange the callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback
 * @param modifier a [Modifier] for this text field
 * @param placeholder the placeholder to be displayed when the text field is in focus and
 * the input text is empty.
 */
@Composable
@OdsComponentApi
fun OdsSearchTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, style = OdsTheme.typography.body1) },
        modifier = modifier,
        textStyle = OdsTheme.typography.h6,
        trailingIcon = {
            OdsIconButton(
                onClick = { onValueChange(TextFieldValue("")) },
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.search_clear)
            )
        },
        singleLine = false,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = OdsTheme.colors.primary,
            leadingIconColor = OdsTheme.colors.onSurface,
            trailingIconColor = OdsTheme.colors.onSurface,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSearchTextField(@PreviewParameter(OdsSearchTextFieldPreviewParameterProvider::class) parameter: OdsSearchTextFieldPreviewParameter) =
    Preview {
        with(parameter) {
            OdsSearchTextField(
                value = value,
                onValueChange = {
                },
                placeholder = stringResource(id = placeholder),
            )
        }
    }

private data class OdsSearchTextFieldPreviewParameter(
    val value: TextFieldValue,
    val placeholder: Int,
    val singleLine: Boolean = false
)

private class OdsSearchTextFieldPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsSearchTextFieldPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsSearchTextFieldPreviewParameter>
    get() {
        val value = TextFieldValue("")
        val placeholder = android.R.string.VideoView_error_text_unknown

        return listOf(
            OdsSearchTextFieldPreviewParameter(value, placeholder = 0),
            OdsSearchTextFieldPreviewParameter(value, placeholder = placeholder),
        )
    }