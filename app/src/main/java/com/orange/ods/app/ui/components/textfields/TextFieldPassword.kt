/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.app.ui.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.textfields.TextFieldCustomizationState.Companion.TextFieldMaxChars
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.compose.component.textfield.password.OdsPasswordTextField

@Composable
fun TextFieldPassword(customizationState: TextFieldCustomizationState) {
    val label = stringResource(id = R.string.component_element_label)
    val placeholder = stringResource(id = R.string.component_text_field_placeholder)
    val errorMessage = stringResource(id = R.string.component_text_field_error_message)

    with(customizationState) {
        Column {
            OdsPasswordTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                enabled = isEnabled,
                isError = isError,
                errorMessage = if (isError) errorMessage else null,
                value = displayedText,
                onValueChange = { updateText(it) },
                label = label,
                placeholder = placeholder,
                visualisationIcon = hasVisualisationIcon,
                keyboardOptions = keyboardOptions,
                characterCounter = if (hasCharacterCounter) OdsTextField.CharacterCounter(displayedText.length, TextFieldMaxChars, isEnabled) else null
            )

            TextFieldCodeImplementationColumn(
                componentName = OdsComposable.OdsPasswordTextField.name,
                customizationState = customizationState,
                label = label,
                placeholder = placeholder,
                errorMessage = errorMessage,
                hasTrailing = false,
                hasTrailingIcon = false
            )
        }
    }
}