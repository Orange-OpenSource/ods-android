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

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.TextFieldDefaults.UnfocusedIndicatorLineOpacity
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsColors

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/483f94-text-fields/b/720e3b" target="_blank">ODS Text fields</a>.
 *
 * Filled text fields have more visual emphasis than outlined text fields, making them stand out
 * when surrounded by other content and components.
 *
 * If you are looking for an outlined version, see [OdsOutlinedTextField].
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = OdsTheme.typography.subtitle1,
        label = label?.let { { Text(label) } },
        placeholder = placeholder?.let { { Text(text = placeholder, style = OdsTheme.typography.subtitle1) } },
        leadingIcon = leadingIcon?.let {
            {
                OdsTextFieldIcon(
                    painter = leadingIcon,
                    contentDescription = leadingIconContentDescription,
                    onClick = if (enabled) onLeadingIconClick else null,
                )
            }
        },
        trailingIcon = when {
            trailingIcon != null -> {
                {
                    OdsTextFieldIcon(
                        painter = trailingIcon,
                        contentDescription = trailingIconContentDescription,
                        onClick = if (enabled) onTrailingIconClick else null,
                    )
                }
            }
            trailingText != null -> {
                {
                    Text(
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)),
                        text = trailingText,
                        style = OdsTheme.typography.subtitle1,
                        color = OdsTheme.colors.trailingTextColor(value.isEmpty(), enabled)
                    )
                }
            }
            else -> null
        },
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = OdsTextFieldDefaults.colors()
    )
}

/**
 * A counter to display below the text field
 *
 * @param valueLength the text field current value length
 * @param maxChars the maximum of characters to display in the counter. Note: the limitation behavior should be managed by yourself
 * in the `onValueChange` method of the text field.
 * @param modifier a [Modifier] for this text field counter
 * @param enabled set to false to display the text with a disabled color
 */
@Composable
@OdsComponentApi
fun OdsTextFieldCounter(valueLength: Int, maxChars: Int, modifier: Modifier = Modifier, enabled: Boolean = true) {
    OdsTextCaption(
        modifier = modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs)), text = "$valueLength/$maxChars", enabled = enabled
    )
}

@Composable
internal fun OdsTextFieldIcon(painter: Painter, contentDescription: String?, onClick: (() -> Unit)?) {
    val interactionSource = if (onClick != null) remember { MutableInteractionSource() } else remember { DisabledInteractionSource() }
    IconButton(onClick = onClick ?: {}, interactionSource = interactionSource) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
        )
    }
}

@Composable
internal fun OdsColors.trailingTextColor(isValueEmpty: Boolean, isTextFieldEnabled: Boolean) =
    if (isValueEmpty || !isTextFieldEnabled) {
        OdsTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
    } else {
        OdsTheme.colors.onSurface
    }

internal object OdsTextFieldDefaults {

    @Composable
    fun colors() = TextFieldDefaults.textFieldColors(
        textColor = OdsTheme.colors.onSurface,
        backgroundColor = OdsTheme.colors.onSurface.copy(alpha = BackgroundOpacity),
        cursorColor = OdsTheme.colors.primary,
        errorCursorColor = OdsTheme.colors.error,
        focusedIndicatorColor = OdsTheme.colors.primary.copy(alpha = ContentAlpha.high),
        unfocusedIndicatorColor = OdsTheme.colors.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity),
        errorIndicatorColor = OdsTheme.colors.error,
        leadingIconColor = OdsTheme.colors.onSurface,
        trailingIconColor = OdsTheme.colors.onSurface,
        errorTrailingIconColor = OdsTheme.colors.onSurface,
        focusedLabelColor = OdsTheme.colors.onSurface,
        unfocusedLabelColor = OdsTheme.colors.onSurface.copy(ContentAlpha.medium),
        errorLabelColor = OdsTheme.colors.error,
        placeholderColor = OdsTheme.colors.onSurface.copy(ContentAlpha.medium)
    )

}

@Composable
private fun PreviewOdsTextField(hasCounter: Boolean) = Preview {
    var text by remember { mutableStateOf("Input text") }
    Column {
        OdsTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = "Placeholder",
            leadingIcon = painterResource(id = android.R.drawable.ic_dialog_info),
            trailingIcon = painterResource(id = android.R.drawable.ic_input_add)
        )

        if (hasCounter) {
            OdsTextFieldCounter(text.length, 30, Modifier.align(Alignment.End))
        }
    }
}

@Preview(name = "OdsTextField - Light")
@Composable
private fun PreviewOdsTextFieldLight(@PreviewParameter(OdsTextFieldPreviewParameterProvider::class) hasCounter: Boolean) {
    PreviewOdsTextField(hasCounter)
}

@Preview(
    name = "OdsTextField - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsTextFieldDark(@PreviewParameter(OdsTextFieldPreviewParameterProvider::class) hasCounter: Boolean) {
    PreviewOdsTextField(hasCounter)
}

internal class OdsTextFieldPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

