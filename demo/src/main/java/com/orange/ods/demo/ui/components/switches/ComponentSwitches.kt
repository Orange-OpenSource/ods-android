/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.switches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.demo.R

@Composable
fun ComponentSwitchesContent() {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.ods_spacing_s)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LabelledSwitch(
                label = stringResource(id = R.string.component_state_enabled),
                selected = remember { mutableStateOf(true) }
            )
            LabelledSwitch(
                label = stringResource(id = R.string.component_state_enabled),
                selected = remember { mutableStateOf(false) }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LabelledSwitch(
                label = stringResource(id = R.string.component_state_disabled),
                selected = remember { mutableStateOf(true) },
                enabled = false
            )
            LabelledSwitch(
                label = stringResource(id = R.string.component_state_disabled),
                selected = remember { mutableStateOf(false) },
                enabled = false
            )
        }
    }
}

@Composable
private fun LabelledSwitch(
    selected: MutableState<Boolean>,
    label: String,
    enabled: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OdsSwitch(
            checked = selected.value,
            onCheckedChange = { selected.value = it },
            enabled = enabled
        )
        OdsTextBody1(text = label)
    }
}