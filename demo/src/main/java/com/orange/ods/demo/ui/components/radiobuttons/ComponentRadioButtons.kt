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
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsRadioButtonTrailing
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentRadioButtons() {
    val enabled = rememberSaveable { mutableStateOf(true) }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_state_enabled),
                trailing = OdsSwitchTrailing(checked = enabled)
            )
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = dimensionResource(id = R.dimen.spacing_m))
        ) {
            val selectedRadio = remember { mutableStateOf(R.string.component_element_item1) }
            Column(modifier = Modifier.selectableGroup()) {
                OdsListItem(
                    text = stringResource(id = R.string.component_element_item1),
                    trailing = OdsRadioButtonTrailing(
                        selectedRadio = selectedRadio,
                        currentRadio = R.string.component_element_item1,
                        enabled = enabled.value
                    )
                )

                OdsListItem(
                    text = stringResource(id = R.string.component_element_item2),
                    trailing = OdsRadioButtonTrailing(
                        selectedRadio = selectedRadio,
                        currentRadio = R.string.component_element_item2,
                        enabled = enabled.value
                    )
                )

                OdsListItem(
                    text = stringResource(id = R.string.component_element_item3),
                    trailing = OdsRadioButtonTrailing(
                        selectedRadio = selectedRadio,
                        currentRadio = R.string.component_element_item3,
                        enabled = enabled.value
                    )
                )
            }
        }
    }
}