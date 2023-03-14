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

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.textfield.OdsTextFieldDefaults
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews

/**
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * @see androidx.compose.material.TextField
 *
 * @param value the input text to be shown in the text field
 * @param onValueChange the callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback
 * @param modifier a [Modifier] for this text field
 * @param placeholder the optional placeholder to be displayed when the text field is in focus and
 * the input text is empty. The default text style for internal [Text] is [Typography.subtitle1]
 * @param trailingIcon the optional trailing icon painter to be displayed at the end of the text field
 * container
 * @param leadingIcon the optional leading icon painter to be displayed at the beginning of the text field
 * container
 */
@Composable
@OdsComponentApi
fun OdsSearchTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = OdsTextFieldDefaults.textFieldColors()
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = modifier,
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        singleLine = false,
        colors = colors
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
                placeholder = {
                    if (placeholder != 0) {
                        Text(text = stringResource(id = placeholder), fontSize = 18.sp)
                    } else Text(text = "", fontSize = 18.sp)
                },
                trailingIcon = {
                    OdsIconButton(
                        onClick = {
                        },
                        imageVector = trailingIcon,
                        contentDescription = stringResource(id = android.R.string.VideoView_error_text_unknown)
                    )
                }
            )
        }
    }

private data class OdsSearchTextFieldPreviewParameter(
    val value: TextFieldValue,
    val placeholder: Int,
    val trailingIcon: ImageVector,
    val singleLine: Boolean = false
)

private class OdsSearchTextFieldPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsSearchTextFieldPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsSearchTextFieldPreviewParameter>
    get() {
        var value = TextFieldValue("")
        val trailingIcon = Icons.Filled.Search
        val trailing = Icons.Default.Close
        val placeholder = android.R.string.VideoView_error_text_unknown

        return listOf(
            OdsSearchTextFieldPreviewParameter(value, trailingIcon = trailingIcon, placeholder = 0),
            OdsSearchTextFieldPreviewParameter(value, trailingIcon = trailing, placeholder = placeholder),
        )
    }