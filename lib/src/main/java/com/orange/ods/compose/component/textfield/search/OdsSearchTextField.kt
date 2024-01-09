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

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

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
@OdsComposable
internal fun OdsSearchTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = OdsTheme.typography.body1,
                fontSize = 20.sp
            )
        },
        modifier = modifier,
        textStyle = OdsTheme.typography.h6,
        trailingIcon = {
            OdsIconButton(
                onClick = { onValueChange(TextFieldValue("")) },
                graphicsObject = rememberVectorPainter(image = Icons.Default.Close),
                contentDescription = stringResource(id = R.string.ods_searchTextField_clear_labelA11y),
                tint = OdsTheme.colors.component.topAppBar.barContent
            )
        },
        singleLine = false,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = OdsTheme.colors.component.topAppBar.barContent,
            cursorColor = OdsTheme.colors.component.topAppBar.barContent,
            leadingIconColor = OdsTheme.colors.component.topAppBar.barContent,
            trailingIconColor = OdsTheme.colors.component.topAppBar.barContent,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            placeholderColor = OdsTheme.colors.component.topAppBar.barContent.copy(ContentAlpha.medium),
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