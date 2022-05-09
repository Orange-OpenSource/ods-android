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
import com.orange.ods.compose.component.textfield.OdsOutlinedTextField
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@Composable
fun TextFieldOutlinedContent() {
    Subtitle(textRes = R.string.component_text_field_outlined)

    val trailingIcon = painterResource(id = R.drawable.ic_eye)
    OutlinedTextField(trailingIcon = trailingIcon)
    OutlinedTextField(enabled = false, trailingIcon = trailingIcon)
    OutlinedTextField(isError = true, trailingIcon = trailingIcon)

    Subtitle(textRes = R.string.component_text_field_outlined_suffix_text)

    val trailingText = "units"
    OutlinedTextField(trailingText = trailingText)
    OutlinedTextField(enabled = false, trailingText = trailingText)
    OutlinedTextField(isError = true, trailingText = trailingText)
}

@Composable
private fun OutlinedTextField(enabled: Boolean = true, isError: Boolean = false, trailingIcon: Painter? = null, trailingText: String? = null) {
    var text by rememberSaveable { mutableStateOf("") }
    OdsOutlinedTextField(
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
