/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.controls

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
import com.orange.ods.demo.ui.utilities.composable.LabelledCheckbox

@Composable
fun ComponentsControlsCheckboxesScreen() {
    ComponentsControlsTemplate(R.string.component_controls_switches_description) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.ods_spacing_s)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LabelledCheckbox(
                checked = remember { mutableStateOf(true) },
                label = stringResource(id = R.string.component_state_enabled)
            )
            LabelledCheckbox(
                checked = remember { mutableStateOf(false) },
                label = stringResource(id = R.string.component_state_enabled)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LabelledCheckbox(
                checked = remember { mutableStateOf(true) },
                label = stringResource(id = R.string.component_state_disabled),
                enabled = false
            )
            LabelledCheckbox(
                checked = remember { mutableStateOf(false) },
                label = stringResource(id = R.string.component_state_disabled),
                enabled = false
            )
        }
    }
}