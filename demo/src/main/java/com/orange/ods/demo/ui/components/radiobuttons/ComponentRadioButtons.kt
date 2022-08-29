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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.RadioButtonListItem
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@ExperimentalMaterialApi
@Composable
fun ComponentRadioButtons() {
    val enabled = rememberSaveable { mutableStateOf(true) }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            SwitchListItem(labelRes = R.string.component_state_enabled, checked = enabled)
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = dimensionResource(id = R.dimen.spacing_m))
        ) {
            val selectedRadio = remember { mutableStateOf(R.string.component_element_item1) }
            Column(modifier = Modifier.selectableGroup()) {
                RadioButtonListItem(
                    labelRes = R.string.component_element_item1,
                    selectedRadio = selectedRadio,
                    currentRadio = R.string.component_element_item1,
                    enabled = enabled.value
                )

                RadioButtonListItem(
                    labelRes = R.string.component_element_item2,
                    selectedRadio = selectedRadio,
                    currentRadio = R.string.component_element_item2,
                    enabled = enabled.value
                )

                RadioButtonListItem(
                    labelRes = R.string.component_element_item3,
                    selectedRadio = selectedRadio,
                    currentRadio = R.string.component_element_item3,
                    enabled = enabled.value
                )
            }
        }
    }
}