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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@Composable
fun TextFieldFilledContent() {
    Subtitle(textRes = R.string.component_text_field_filled)

    val trailingIcon = painterResource(id = R.drawable.ic_eye)
    FilledTextField(trailingIcon = trailingIcon)
    FilledTextField(enabled = false, trailingIcon = trailingIcon)
    FilledTextField(isError = true, trailingIcon = trailingIcon)

    Subtitle(textRes = R.string.component_text_field_filled_suffix_text)

    val trailingText = "units"
    FilledTextField(trailingText = trailingText)
    FilledTextField(enabled = false, trailingText = trailingText)
    FilledTextField(isError = true, trailingText = trailingText)
}

@Composable
private fun FilledTextField(enabled: Boolean = true, isError: Boolean = false, trailingIcon: Painter? = null, trailingText: String? = null) {
    var text by rememberSaveable { mutableStateOf("") }
    OdsTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.ods_spacing_xs)),
        leadingIcon = painterResource(id = R.drawable.ic_heart),
        enabled = enabled,
        isError = isError,
        value = text,
        onValueChange = { text = it },
        label = "Label",
        placeholder = "Placeholder",
        trailingIcon = trailingIcon,
        trailingText = trailingText
    )
}
