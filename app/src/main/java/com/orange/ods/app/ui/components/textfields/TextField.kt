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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.textfields.TextFieldCustomizationState.Companion.TextFieldMaxChars
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CodeParameter
import com.orange.ods.app.ui.utilities.composable.ComponentCode
import com.orange.ods.app.ui.utilities.composable.ObjectInstance
import com.orange.ods.app.ui.utilities.composable.ObjectParameter
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

    with(customizationState) {
        val leadingIcon = if (hasLeadingIcon) painterResource(id = R.drawable.ic_heart) else null
        val errorMessage = if (isError) stringResource(id = R.string.component_text_field_error_message) else null
        val onValueChange: (String) -> Unit = { updateText(it) }
        val label = stringResource(id = R.string.component_element_label)
        val placeholder = stringResource(id = R.string.component_text_field_placeholder)
        val characterCounter: (@Composable () -> Unit)? = if (hasCharacterCounter) {
            { TextFieldCharacterCounter(valueLength = displayedText.length, enabled = isEnabled) }
        } else null
        val hasTrailing = hasTrailingText || hasTrailingIcon

        Column {
            if (hasTrailing) {
                OdsTextField(
                    modifier = modifier,
                    leadingIcon = leadingIcon,
                    enabled = isEnabled,
                    isError = isError,
                    errorMessage = errorMessage,
                    value = displayedText,
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
                    singleLine = isSingleLine,
                    keyboardOptions = keyboardOptions,
                    characterCounter = characterCounter
                )
            } else {
                OdsTextField(
                    modifier = modifier,
                    leadingIcon = leadingIcon,
                    enabled = isEnabled,
                    isError = isError,
                    errorMessage = errorMessage,
                    value = displayedText,
                    onValueChange = onValueChange,
                    label = label,
                    placeholder = placeholder,
                    singleLine = isSingleLine,
                    keyboardOptions = keyboardOptions,
                    characterCounter = characterCounter
                )
            }

            TextFieldCodeImplementationColumn(
                componentName = OdsComponent.OdsTextField.name,
                customizationState = customizationState,
                label = label,
                placeholder = placeholder,
                errorMessage = errorMessage,
                hasTrailing = hasTrailing
            )
        }
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

@Composable
fun TextFieldCodeImplementationColumn(
    componentName: String,
    customizationState: TextFieldCustomizationState,
    label: String,
    placeholder: String,
    errorMessage: String?,
    hasTrailing: Boolean
) {
    with(customizationState) {
        val capitalizationValue = if (softKeyboardCapitalization.value) KeyboardCapitalization.Characters.toString() else KeyboardCapitalization.None.toString()
        CodeImplementationColumn {
            ComponentCode(name = componentName, parameters = mutableListOf(
                TextValueParameter.BetweenQuotesParameter("value", displayedText),
                TextValueParameter.LambdaParameter("onValueChange"),
                TextValueParameter.Label(label),
                TextValueParameter.Placeholder(placeholder),
                ObjectParameter(
                    "keyboardOptions", ObjectInstance(
                        KeyboardOptions::class.java.simpleName, listOf<CodeParameter>(
                            TextValueParameter.ValueOnlyParameter("capitalization", capitalizationValue),
                            TextValueParameter.ValueOnlyParameter("keyboardType", softKeyboardType.value.keyboardType.toString()),
                            TextValueParameter.ValueOnlyParameter("imeAction", softKeyboardAction.value.imeAction.toString())
                        )
                    )
                )
            ).apply {
                if (hasLeadingIcon) add(TextValueParameter.Icon)
                if (!hasVisualisationIcon) add(TextValueParameter.StringRepresentationParameter("visualisationIcon", false))
                if (!isEnabled) add(TextValueParameter.Enabled(false))
                if (isError) {
                    add(TextValueParameter.StringRepresentationParameter("isError", true))
                    errorMessage?.let { add(TextValueParameter.BetweenQuotesParameter("errorMessage", it)) }
                }
                if (isSingleLine) add(TextValueParameter.StringRepresentationParameter("singleLine", true))
                if (hasTrailing) add(TextValueParameter.ValueOnlyParameter("trailing", "<trailing composable>"))
                if (hasCharacterCounter) {
                    add(
                        ObjectParameter(
                            "characterCounter", ObjectInstance(
                                OdsComponent.OdsTextFieldCharacterCounter.name, listOf<CodeParameter>(
                                    TextValueParameter.StringRepresentationParameter("valueLength", displayedText.length),
                                    TextValueParameter.Enabled(isEnabled)
                                )
                            )
                        )
                    )
                }
            })
        }
    }
}
