/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.chips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.compose.component.chip.OdsFilterChip
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.CheckboxListItem
import com.orange.ods.demo.ui.utilities.composable.LabelledRadioButton
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@ExperimentalMaterialApi
@Composable
fun SubComponentChipFilter() {
    val customizationState = rememberChipCustomizationState()

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {

            Subtitle(textRes = R.string.component_element_leading, withHorizontalPadding = true)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.ods_spacing_xs))
                    .padding(start = dimensionResource(id = R.dimen.ods_spacing_s)),
                horizontalArrangement = Arrangement.Start
            ) {
                LabelledRadioButton(
                    selectedRadio = customizationState.leadingElement,
                    currentRadio = ChipCustomizationState.LeadingElement.None,
                    label = stringResource(id = R.string.component_element_none)
                )
                LabelledRadioButton(
                    selectedRadio = customizationState.leadingElement,
                    currentRadio = ChipCustomizationState.LeadingElement.Avatar,
                    label = stringResource(id = R.string.component_element_avatar)
                )
            }

            CheckboxListItem(labelRes = R.string.component_state_outlined, checked = customizationState.outlinedChecked)
            CheckboxListItem(labelRes = R.string.component_state_disabled, checked = customizationState.disabledChecked)

        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin),
                    vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin)
                )
        ) {
            FlowRow(modifier = Modifier.fillMaxWidth()) {
                for (index in 1..4) {
                    FilterChip(index, customizationState)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun FilterChip(index: Int, customizationState: ChipCustomizationState) {
    val selected = rememberSaveable { mutableStateOf(false) }
    OdsFilterChip(
        text = "${stringResource(id = R.string.component_chip_filter)} $index",
        leadingAvatar = if (customizationState.hasLeadingAvatar) painterResource(id = R.drawable.placeholder_small) else null,
        onClick = { selected.value = !selected.value },
        selected = selected.value,
        outlined = customizationState.outlinedChecked.value,
        enabled = !customizationState.disabledChecked.value,
    )
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.ods_spacing_xs)))
}