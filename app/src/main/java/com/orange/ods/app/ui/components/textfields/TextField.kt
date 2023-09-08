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
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
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
        .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))

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
                    trailing = if (hasTrailingIcon) {
                        OdsIconTrailing(
                            painter = painterResource(id = com.orange.ods.R.drawable.ic_eye),
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
                componentName = OdsComposable.OdsTextField.name,
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
            FunctionCallCode(
                name = componentName,
                exhaustiveParameters = false,
                parameters = {
                    string("value", displayedText)
                    lambda("onValueChange")
                    label(label)
                    placeholder(placeholder)
                    classInstance<KeyboardOptions>("keyboardOptions") {
                        simple("capitalization", capitalizationValue)
                        stringRepresentation("keyboardType", softKeyboardType.value.keyboardType)
                        stringRepresentation("imeAction", softKeyboardAction.value.imeAction)
                    }
                    if (hasLeadingIcon) icon()
                    if (!hasVisualisationIcon) stringRepresentation("visualisationIcon", false)
                    if (!isEnabled) enabled(false)
                    if (isError) {
                        stringRepresentation("isError", true)
                        errorMessage?.let { string("errorMessage", it) }
                    }
                    if (isSingleLine) stringRepresentation("singleLine", true)
                    if (hasTrailing) simple("trailing", "<trailing composable>")
                    if (hasCharacterCounter) {
                        function("characterCounter", OdsComposable.OdsTextFieldCharacterCounter.name) {
                            stringRepresentation("valueLength", displayedText.length)
                            enabled(isEnabled)
                        }
                    }
                })
        }
    }
}
