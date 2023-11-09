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
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.compose.component.textfield.OdsTextFieldCharacterCounterBuilder
import com.orange.ods.compose.component.textfield.OdsTextFieldLeadingIconBuilder
import com.orange.ods.compose.component.textfield.OdsTextFieldTrailingIconBuilder
import com.orange.ods.compose.component.textfield.OdsTextFieldTrailingTextBuilder

private const val TrailingText = "units"

@Composable
fun TextField(customizationState: TextFieldCustomizationState) {
    val context = LocalContext.current
    val trailingIconName = stringResource(id = R.string.component_element_trailing)

    val modifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))

    with(customizationState) {
        val leadingIcon = if (hasLeadingIcon) OdsTextFieldLeadingIconBuilder(painterResource(id = R.drawable.ic_heart), "") else null
        val errorMessage = if (isError) stringResource(id = R.string.component_text_field_error_message) else null
        val onValueChange: (String) -> Unit = { updateText(it) }
        val label = stringResource(id = R.string.component_element_label)
        val placeholder = stringResource(id = R.string.component_text_field_placeholder)
        val characterCounter = if (hasCharacterCounter) OdsTextFieldCharacterCounterBuilder(displayedText.length, TextFieldMaxChars, isEnabled) else null
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
                        OdsTextFieldTrailingIconBuilder(
                            painter = painterResource(id = com.orange.ods.R.drawable.ic_eye),
                            contentDescription = "",
                            onClick = { clickOnElement(context = context, trailingIconName) })
                    } else {
                        OdsTextFieldTrailingTextBuilder(text = TrailingText)
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
                hasTrailing = hasTrailing,
                hasTrailingIcon = hasTrailingIcon
            )
        }
    }
}

@Composable
fun TextFieldCodeImplementationColumn(
    componentName: String,
    customizationState: TextFieldCustomizationState,
    label: String,
    placeholder: String,
    errorMessage: String?,
    hasTrailing: Boolean,
    hasTrailingIcon: Boolean
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
                    if (hasLeadingIcon) classInstance<OdsTextFieldLeadingIconBuilder>("leadingIcon") {
                        painter()
                        contentDescription("")
                    }
                    if (!hasVisualisationIcon) stringRepresentation("visualisationIcon", false)
                    if (!isEnabled) enabled(false)
                    if (isError) {
                        stringRepresentation("isError", true)
                        errorMessage?.let { string("errorMessage", it) }
                    }
                    if (isSingleLine) stringRepresentation("singleLine", true)
                    if (hasTrailing) {
                        val trailingParameterName = "trailing"
                        if (hasTrailingIcon) {
                            classInstance<OdsTextFieldTrailingIconBuilder>(trailingParameterName) {
                                painter()
                                onClick()
                            }
                        } else {
                            classInstance<OdsTextFieldTrailingTextBuilder>(trailingParameterName) {
                                text(TrailingText)
                            }
                        }
                    }
                    if (hasCharacterCounter) {
                        classInstance<OdsTextFieldCharacterCounterBuilder>("characterCounter") {
                            stringRepresentation("characterCount", displayedText.length)
                            enabled(isEnabled)
                        }
                    }
                })
        }
    }
}
