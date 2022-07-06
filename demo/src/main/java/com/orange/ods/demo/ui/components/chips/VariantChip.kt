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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.compose.component.chip.OdsChip
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.ChipType
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.LeadingElement
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.CheckboxListItem
import com.orange.ods.demo.ui.utilities.composable.LabelledRadioButton
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@ExperimentalMaterialApi
@Composable
fun VariantChip() {
    val customizationState = rememberChipCustomizationState()

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            Subtitle(textRes = R.string.component_type, withHorizontalPadding = true)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.spacing_s))
                    .padding(start = dimensionResource(id = R.dimen.spacing_m)),
                horizontalArrangement = Arrangement.Start
            ) {
                LabelledRadioButton(
                    selectedRadio = customizationState.chipType,
                    currentRadio = ChipType.Input,
                    label = stringResource(id = R.string.component_chip_type_input)
                )
                LabelledRadioButton(
                    selectedRadio = customizationState.chipType,
                    currentRadio = ChipType.Choice,
                    label = stringResource(id = R.string.component_chip_type_choice)
                )
                LabelledRadioButton(
                    selectedRadio = customizationState.chipType,
                    currentRadio = ChipType.Action,
                    label = stringResource(id = R.string.component_chip_type_action)
                )
            }
            if (customizationState.isInputChip) {
                Subtitle(textRes = R.string.component_element_leading, withHorizontalPadding = true)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.spacing_s))
                        .padding(start = dimensionResource(id = R.dimen.spacing_m)),
                    horizontalArrangement = Arrangement.Start
                ) {
                    LabelledRadioButton(
                        selectedRadio = customizationState.leadingElement,
                        currentRadio = LeadingElement.None,
                        label = stringResource(id = R.string.component_element_none)
                    )
                    LabelledRadioButton(
                        selectedRadio = customizationState.leadingElement,
                        currentRadio = LeadingElement.Avatar,
                        label = stringResource(id = R.string.component_element_avatar)
                    )
                    LabelledRadioButton(
                        selectedRadio = customizationState.leadingElement,
                        currentRadio = LeadingElement.Icon,
                        label = stringResource(id = R.string.component_element_icon)
                    )
                }
            } else {
                customizationState.resetLeadingElement()
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
            VariantChip(customizationState = customizationState)
        }
    }

}

@ExperimentalMaterialApi
@Composable
private fun VariantChip(customizationState: ChipCustomizationState) {
    val context = LocalContext.current
    val cancelCrossLabel = stringResource(id = R.string.component_element_cancel_cross)
    val chipLabel = stringResource(id = R.string.component_chip)

    if (customizationState.isChoiceChip) {
        FlowRow(modifier = Modifier.fillMaxWidth()) {
            for (index in 1..4) {
                ChoiceChip(index, customizationState)
            }
        }
    } else {
        OdsChip(
            text = getChipText(chipType = customizationState.chipType.value),
            onClick = { clickOnElement(context, chipLabel) },
            outlined = customizationState.outlinedChecked.value,
            leadingIcon = if (customizationState.isActionChip || customizationState.hasLeadingIcon) painterResource(id = R.drawable.ic_heart) else null,
            leadingAvatar = if (customizationState.hasLeadingAvatar) painterResource(id = R.drawable.placeholder_small) else null,
            enabled = !customizationState.disabledChecked.value,
            onCancel = if (customizationState.isInputChip) {
                { clickOnElement(context, cancelCrossLabel) }
            } else null
        )
    }


}

@Composable
private fun getChipText(chipType: ChipType): String {
    val chipTypeRes = when (chipType) {
        ChipType.Input -> R.string.component_chip_type_input
        ChipType.Action -> R.string.component_chip_type_action
        ChipType.Choice -> R.string.component_chip_type_choice
    }

    return stringResource(id = R.string.component_chip_type, stringResource(id = chipTypeRes))
}

@ExperimentalMaterialApi
@Composable
private fun ChoiceChip(index: Int, customizationState: ChipCustomizationState) {
    val selected = customizationState.choiceChipIndexSelected.value == index
    val onClick: () -> Unit = { customizationState.selectChoiceChip(index) }
    OdsChip(
        modifier = Modifier.toggleable(
            value = selected,
            role = Role.RadioButton,
            onValueChange = { onClick() }
        ),
        text = "${getChipText(chipType = customizationState.chipType.value)} $index",
        onClick = onClick,
        selected = selected,
        outlined = customizationState.outlinedChecked.value,
        enabled = !customizationState.disabledChecked.value,
    )
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.spacing_s)))
}