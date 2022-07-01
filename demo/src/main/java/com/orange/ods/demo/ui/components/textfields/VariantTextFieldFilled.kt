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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
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
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.clickOnElement

@ExperimentalMaterialApi
@Composable
fun VariantTextFieldFilled(customizationState: TextFieldCustomizationState) {
    var text by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val trailingIconName = stringResource(id = R.string.component_element_trailing)

    OdsTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.ods_spacing_xs)),
        leadingIcon = if (customizationState.leadingIconChecked.value) painterResource(id = R.drawable.ic_heart) else null,
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
        trailingText = if (customizationState.hasTrailingText) "units" else null
    )
}