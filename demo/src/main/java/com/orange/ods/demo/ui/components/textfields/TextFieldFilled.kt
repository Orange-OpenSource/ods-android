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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.compose.component.textfield.OdsTextFieldCounter
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.textfields.TextFieldCustomizationState.Companion.TextFieldMaxChars
import com.orange.ods.demo.ui.components.utilities.clickOnElement

@ExperimentalMaterialApi
@Composable
fun TextFieldFilled(customizationState: TextFieldCustomizationState) {
    val context = LocalContext.current
    val trailingIconName = stringResource(id = R.string.component_element_trailing)
    val focusRequester = remember { FocusRequester() }

    Column {
        OdsTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.spacing_s))
                .focusRequester(focusRequester),
            leadingIcon = if (customizationState.hasLeadingIcon) painterResource(id = R.drawable.ic_heart) else null,
            enabled = customizationState.isEnabled,
            isError = customizationState.isError,
            value = customizationState.displayedText,
            onValueChange = { customizationState.updateText(it) },
            label = stringResource(id = R.string.component_element_label),
            placeholder = stringResource(id = R.string.component_text_field_placeholder),
            trailingIcon = if (customizationState.hasTrailingIcon) painterResource(id = R.drawable.ic_eye) else null,
            onTrailingIconClick = if (customizationState.hasTrailingIcon) {
                { clickOnElement(context = context, trailingIconName) }
            } else null,
            trailingText = if (customizationState.hasTrailingText) "units" else null,
            singleLine = customizationState.isSingleLine,
            keyboardOptions = customizationState.keyboardOptions
        )

        if (customizationState.hasCharacterCounter) {
            TextFieldCounter(valueLength = customizationState.displayedText.length, enabled = customizationState.isEnabled)
        }
    }
}

@Composable
fun ColumnScope.TextFieldCounter(valueLength: Int, enabled: Boolean) {
    OdsTextFieldCounter(
        modifier = Modifier.align(Alignment.End),
        valueLength = valueLength,
        maxChars = TextFieldMaxChars,
        enabled = enabled
    )
}