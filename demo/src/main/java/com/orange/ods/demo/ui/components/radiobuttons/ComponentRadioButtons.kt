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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.LabelledRadioButton

@Composable
fun ComponentRadioButtonsContent() {
    val radio1 = "radio1"
    val radio2 = "radio2"
    val selectedRadio = remember { mutableStateOf(radio1) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.ods_spacing_s)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LabelledRadioButton(
            selectedRadio = selectedRadio,
            currentRadio = radio1,
            label = stringResource(id = R.string.component_state_enabled)
        )
        LabelledRadioButton(
            selectedRadio = selectedRadio,
            currentRadio = radio2,
            label = stringResource(id = R.string.component_state_enabled)
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        LabelledRadioButton(
            selectedRadio = remember { mutableStateOf(radio1) },
            currentRadio = radio1,
            label = stringResource(id = R.string.component_state_disabled),
            enabled = false
        )
        LabelledRadioButton(
            selectedRadio = remember { mutableStateOf(radio1) },
            currentRadio = radio2,
            label = stringResource(id = R.string.component_state_disabled),
            enabled = false
        )
    }
}