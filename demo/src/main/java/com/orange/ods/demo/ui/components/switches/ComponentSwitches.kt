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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem
import com.orange.ods.demo.ui.utilities.composable.Title

@ExperimentalMaterialApi
@Composable
fun ComponentSwitches() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = R.dimen.spacing_m))
    ) {
        Title(textRes = R.string.component_switches_enabled, withHorizontalPadding = true)
        SwitchListItem(
            labelRes = R.string.component_element_item1,
            checked = remember { mutableStateOf(true) }
        )

        Title(textRes = R.string.component_switches_disabled, withHorizontalPadding = true)
        SwitchListItem(
            labelRes = R.string.component_element_item1,
            checked = remember { mutableStateOf(true) },
            enabled = false
        )
        SwitchListItem(
            labelRes = R.string.component_element_item2,
            checked = remember { mutableStateOf(false) },
            enabled = false
        )
    }
}
