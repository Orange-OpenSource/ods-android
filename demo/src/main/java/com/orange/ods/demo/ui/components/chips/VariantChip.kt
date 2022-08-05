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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.chip.OdsChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.chip.SelectableChip
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.ChipType
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.LeadingElement
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.CheckboxListItem
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@ExperimentalMaterialApi
@Composable
fun VariantChip() {
    val chipCustomizationState = rememberChipCustomizationState()

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            Subtitle(textRes = R.string.component_type, withHorizontalPadding = true)
            OdsChoiceChipsFlowRow(
                selectedChip = chipCustomizationState.chipType,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin))
            ) {
                SelectableChip(textRes = R.string.component_chip_type_input, value = ChipType.Input)
                SelectableChip(textRes = R.string.component_chip_type_choice, value = ChipType.Choice)
                SelectableChip(textRes = R.string.component_chip_type_action, value = ChipType.Action)
            }

            if (chipCustomizationState.isInputChip) {
                Subtitle(textRes = R.string.component_element_leading, withHorizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    selectedChip = chipCustomizationState.leadingElement,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin))
                ) {
                    SelectableChip(textRes = R.string.component_element_none, value = LeadingElement.None)
                    SelectableChip(textRes = R.string.component_element_avatar, value = LeadingElement.Avatar)
                    SelectableChip(textRes = R.string.component_element_icon, value = LeadingElement.Icon)
                }
            } else {
                chipCustomizationState.resetLeadingElement()
            }

            CheckboxListItem(labelRes = R.string.component_state_outlined, checked = chipCustomizationState.outlinedChecked)
            CheckboxListItem(labelRes = R.string.component_state_disabled, checked = chipCustomizationState.disabledChecked)

        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin),
                    vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin)
                )
        ) {
            VariantChip(chipCustomizationState = chipCustomizationState)
        }
    }

}

@ExperimentalMaterialApi
@Composable
private fun VariantChip(chipCustomizationState: ChipCustomizationState) {
    val context = LocalContext.current
    val cancelCrossLabel = stringResource(id = R.string.component_element_cancel_cross)
    val chipLabel = stringResource(id = R.string.component_chip)

    if (chipCustomizationState.isChoiceChip) {
        OdsChoiceChipsFlowRow(selectedChip = chipCustomizationState.choiceChipIndexSelected, outlinedChips = chipCustomizationState.isOutlined) {
            for (index in 1..4) {
                SelectableChip(
                    text = "${getChipText(chipType = chipCustomizationState.chipType.value)} $index",
                    value = index,
                    enabled = chipCustomizationState.isEnabled
                )
            }
        }
    } else {
        OdsChip(
            text = getChipText(chipType = chipCustomizationState.chipType.value),
            onClick = { clickOnElement(context, chipLabel) },
            outlined = chipCustomizationState.outlinedChecked.value,
            leadingIcon = if (chipCustomizationState.isActionChip || chipCustomizationState.hasLeadingIcon) painterResource(id = R.drawable.ic_heart) else null,
            leadingAvatar = if (chipCustomizationState.hasLeadingAvatar) painterResource(id = R.drawable.placeholder_small) else null,
            enabled = !chipCustomizationState.disabledChecked.value,
            onCancel = if (chipCustomizationState.isInputChip) {
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