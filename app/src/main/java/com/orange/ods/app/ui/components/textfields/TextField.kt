/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.textfields.TextFieldCustomizationState.Companion.TextFieldMaxChars
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.composable.CodeImplementation
import com.orange.ods.app.ui.utilities.composable.TextValueParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.textfield.OdsIconTrailing
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.compose.component.textfield.OdsTextFieldCharacterCounter
import com.orange.ods.compose.component.textfield.OdsTextTrailing

@Composable
fun TextField(customizationState: TextFieldCustomizationState) {
    val context = LocalContext.current
    val trailingIconName = stringResource(id = R.string.component_element_trailing)

    val modifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen.spacing_s))
    val leadingIcon = if (customizationState.hasLeadingIcon) painterResource(id = R.drawable.ic_heart) else null
    val enabled = customizationState.isEnabled
    val isError = customizationState.isError
    val errorMessage = if (customizationState.isError) stringResource(id = R.string.component_text_field_error_message) else null
    val value = customizationState.displayedText
    val onValueChange: (String) -> Unit = { customizationState.updateText(it) }
    val label = stringResource(id = R.string.component_element_label)
    val placeholder = stringResource(id = R.string.component_text_field_placeholder)
    val singleLine = customizationState.isSingleLine
    val keyboardOptions = customizationState.keyboardOptions
    val characterCounter: (@Composable () -> Unit)? = if (customizationState.hasCharacterCounter) {
        { TextFieldCharacterCounter(valueLength = customizationState.displayedText.length, enabled = customizationState.isEnabled) }
    } else null
    val hasTrailing = customizationState.hasTrailingText || customizationState.hasTrailingIcon

    Column {
        if (hasTrailing) {
            OdsTextField(
                modifier = modifier,
                leadingIcon = leadingIcon,
                enabled = enabled,
                isError = isError,
                errorMessage = errorMessage,
                value = value,
                onValueChange = onValueChange,
                label = label,
                placeholder = placeholder,
                trailing = if (customizationState.hasTrailingIcon) {
                    OdsIconTrailing(
                        painter = painterResource(id = R.drawable.ic_eye),
                        onClick = { clickOnElement(context = context, trailingIconName) })
                } else {
                    OdsTextTrailing(text = "units")
                },
                singleLine = singleLine,
                keyboardOptions = keyboardOptions,
                characterCounter = characterCounter
            )
        } else {
            OdsTextField(
                modifier = modifier,
                leadingIcon = leadingIcon,
                enabled = enabled,
                isError = isError,
                errorMessage = errorMessage,
                value = value,
                onValueChange = onValueChange,
                label = label,
                placeholder = placeholder,
                singleLine = singleLine,
                keyboardOptions = keyboardOptions,
                characterCounter = characterCounter
            )
        }

        CodeImplementation(OdsComponent.OdsTextField.name).CodeImplementationColumn(
            codeParameters = mutableListOf(
                TextValueParameter.BetweenQuotesParameter("value", value),
                TextValueParameter.LambdaParameter("onValueChange"),
                TextValueParameter.Label(label),
                TextValueParameter.Placeholder(placeholder),
                TextValueParameter.ValueOnlyParameter("keyboardOptions", "<KeyboardOptions>") // TODO
            ).apply {
                if (leadingIcon != null) add(TextValueParameter.Icon)
                if (!enabled) add(TextValueParameter.Enabled(false))
                if (isError) {
                    add(TextValueParameter.StringRepresentationParameter("isError", true))
                    errorMessage?.let { add(TextValueParameter.BetweenQuotesParameter("errorMessage", it)) }
                }
                if (singleLine) add(TextValueParameter.StringRepresentationParameter("singleLine", true))
                if (hasTrailing) add(TextValueParameter.ValueOnlyParameter("trailing", "<trailing composable>"))
                if (characterCounter != null) add(TextValueParameter.ValueOnlyParameter("characterCounter", "<OdsTextFieldCharacterCounter>")) // TODO
            }
        )
    }
}

@Composable
fun TextFieldCharacterCounter(valueLength: Int, enabled: Boolean) {
    OdsTextFieldCharacterCounter(
        valueLength = valueLength,
        maxChars = TextFieldMaxChars,
        enabled = enabled
    )
}

