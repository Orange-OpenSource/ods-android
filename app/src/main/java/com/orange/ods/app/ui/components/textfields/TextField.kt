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
import com.orange.ods.app.ui.utilities.composable.ClassInstance
import com.orange.ods.app.ui.utilities.composable.ClassInstanceParameter
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CodeParameter
import com.orange.ods.app.ui.utilities.composable.ComposableCode
import com.orange.ods.app.ui.utilities.composable.LambdaParameter
import com.orange.ods.app.ui.utilities.composable.PredefinedParameter
import com.orange.ods.app.ui.utilities.composable.SimpleParameter
import com.orange.ods.app.ui.utilities.composable.StringParameter
import com.orange.ods.app.ui.utilities.composable.StringRepresentationParameter
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
            ComposableCode(name = componentName, exhaustiveParameters = false, parameters = mutableListOf(
                StringParameter("value", displayedText),
                LambdaParameter("onValueChange"),
                PredefinedParameter.Label(label),
                PredefinedParameter.Placeholder(placeholder),
                ClassInstanceParameter(
                    "keyboardOptions", ClassInstance(
                        KeyboardOptions::class.java.simpleName, listOf<CodeParameter>(
                            SimpleParameter("capitalization", capitalizationValue),
                            SimpleParameter("keyboardType", softKeyboardType.value.keyboardType.toString()),
                            SimpleParameter("imeAction", softKeyboardAction.value.imeAction.toString())
                        )
                    )
                )
            ).apply {
                if (hasLeadingIcon) add(PredefinedParameter.Icon)
                if (!hasVisualisationIcon) add(StringRepresentationParameter("visualisationIcon", false))
                if (!isEnabled) add(PredefinedParameter.Enabled(false))
                if (isError) {
                    add(StringRepresentationParameter("isError", true))
                    errorMessage?.let { add(StringParameter("errorMessage", it)) }
                }
                if (isSingleLine) add(StringRepresentationParameter("singleLine", true))
                if (hasTrailing) add(SimpleParameter("trailing", "<trailing composable>"))
                if (hasCharacterCounter) {
                    add(
                        ClassInstanceParameter(
                            "characterCounter", ClassInstance(
                                OdsComponent.OdsTextFieldCharacterCounter.name, listOf<CodeParameter>(
                                    StringRepresentationParameter("valueLength", displayedText.length),
                                    PredefinedParameter.Enabled(isEnabled)
                                )
                            )
                        )
                    )
                }
            })
        }
    }
}
