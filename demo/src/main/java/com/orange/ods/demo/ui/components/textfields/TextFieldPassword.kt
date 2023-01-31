/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.textfield.password.OdsPasswordTextField
import com.orange.ods.demo.R

@Composable
fun TextFieldPassword(customizationState: TextFieldCustomizationState) {

    Column {
        OdsPasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.spacing_s)),
            enabled = customizationState.isEnabled,
            isError = customizationState.isError,
            errorMessage = if (customizationState.isError) stringResource(id = R.string.component_text_field_error_message) else null,
            value = customizationState.displayedText,
            onValueChange = { customizationState.updateText(it) },
            label = stringResource(id = R.string.component_element_label),
            placeholder = stringResource(id = R.string.component_text_field_placeholder),
            visualisationIcon = customizationState.hasVisualisationIcon,
            keyboardOptions = customizationState.keyboardOptions,
            characterCounter = if (customizationState.hasCharacterCounter) {
                {
                    TextFieldCharacterCounter(valueLength = customizationState.displayedText.length, enabled = customizationState.isEnabled)
                }
            } else null
        )
    }
}