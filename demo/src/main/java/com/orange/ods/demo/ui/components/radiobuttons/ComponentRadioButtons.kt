/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.radiobuttons

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.RadioButtonListItem
import com.orange.ods.demo.ui.utilities.composable.Title

@ExperimentalMaterialApi
@Composable
fun ComponentRadioButtonsContent() {
    Title(textRes = R.string.component_radio_buttons_enabled, withHorizontalPadding = true)
    RadioButtons(enabled = true)

    Title(textRes = R.string.component_radio_buttons_disabled, withHorizontalPadding = true)
    RadioButtons(enabled = false)
}

@ExperimentalMaterialApi
@Composable
private fun RadioButtons(enabled: Boolean) {
    val selectedRadio = remember { mutableStateOf(R.string.component_element_item1) }

    RadioButtonListItem(
        labelRes = R.string.component_element_item1,
        selectedRadio = selectedRadio,
        currentRadio = R.string.component_element_item1,
        enabled = enabled
    )

    RadioButtonListItem(
        labelRes = R.string.component_element_item2,
        selectedRadio = selectedRadio,
        currentRadio = R.string.component_element_item2,
        enabled = enabled
    )

    RadioButtonListItem(
        labelRes = R.string.component_element_item3,
        selectedRadio = selectedRadio,
        currentRadio = R.string.component_element_item3,
        enabled = enabled
    )
}