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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.orange.ods.compose.component.textfield.OdsOutlinedTextField
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.clickOnElement

@Composable
fun TextFieldOutlined(customizationState: TextFieldCustomizationState) {
    val context = LocalContext.current
    val trailingIconName = stringResource(id = R.string.component_element_trailing)

    var text by rememberSaveable { mutableStateOf("") }
    if (customizationState.hasCharacterCounter && text.length > TextFieldCustomizationState.TextFieldMaxChars) {
        text = text.substring(0, TextFieldCustomizationState.TextFieldMaxChars) // Limit the length of the text field value to the maximum number of characters
    }
    
    Column {
        OdsOutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.spacing_s)),
            leadingIcon = if (customizationState.leadingIcon.value) painterResource(id = R.drawable.ic_heart) else null,
            enabled = customizationState.isEnabled,
            isError = customizationState.isError,
            value = text,
            onValueChange = { text = it },
            label = stringResource(id = R.string.component_element_label),
            placeholder = stringResource(id = R.string.component_text_field_placeholder),
            trailingIcon = if (customizationState.hasTrailingIcon) painterResource(id = R.drawable.ic_eye) else null,
            onTrailingIconClick = if (customizationState.hasTrailingIcon) {
                { clickOnElement(context = context, trailingIconName) }
            } else null,
            trailingText = if (customizationState.hasTrailingText) "units" else null,
            singleLine = customizationState.isSingleLine,
            keyboardOptions = KeyboardOptions(
                capitalization = if (customizationState.softKeyboardCapitalization.value) KeyboardCapitalization.Characters else KeyboardCapitalization.None,
                keyboardType = customizationState.softKeyboardType.value.getKeyboardType(),
                imeAction = customizationState.softKeyboardAction.value.getImeAction()
            )
        )

        if (customizationState.hasCharacterCounter) {
            TextFieldCounter(valueLength = text.length, enabled = customizationState.isEnabled)
        }
    }
}
