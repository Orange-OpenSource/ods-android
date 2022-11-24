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
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainThemeManager
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.ChipType
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.LeadingElement
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.CheckboxListItem
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.theme.OdsComponentCustomizations.Companion.ChipStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip() {
    val chipCustomizationState = rememberChipCustomizationState()

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            Subtitle(textRes = R.string.component_type, horizontalPadding = true)
            OdsChoiceChipsFlowRow(
                selectedChip = chipCustomizationState.chipType,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                outlinedChips = true
            ) {
                OdsChoiceChip(textRes = R.string.component_chip_type_input, value = ChipType.Input)
                OdsChoiceChip(textRes = R.string.component_chip_type_choice, value = ChipType.Choice)
                OdsChoiceChip(textRes = R.string.component_chip_type_action, value = ChipType.Action)
            }

            if (chipCustomizationState.isInputChip) {
                Subtitle(textRes = R.string.component_element_leading, horizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    selectedChip = chipCustomizationState.leadingElement,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    outlinedChips = true
                ) {
                    OdsChoiceChip(textRes = R.string.component_element_none, value = LeadingElement.None)
                    OdsChoiceChip(textRes = R.string.component_element_avatar, value = LeadingElement.Avatar)
                    OdsChoiceChip(textRes = R.string.component_element_icon, value = LeadingElement.Icon)
                }
            } else {
                chipCustomizationState.resetLeadingElement()
            }

            CheckboxListItem(labelRes = R.string.component_state_disabled, checked = chipCustomizationState.disabledChecked)

        }) {
        ChipTypeDemo(chipCustomizationState.chipType.value) {
            Chip(chipCustomizationState = chipCustomizationState)
        }
    }

}

@Composable
fun ChipTypeDemo(chipType: ChipType, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
    ) {
        Subtitle(textRes = chipType.nameRes)
        OdsTextBody2(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs), bottom = dimensionResource(id = R.dimen.spacing_s)),
            text = stringResource(id = chipType.descriptionRes)
        )
        content()
    }
}

@Composable
private fun Chip(chipCustomizationState: ChipCustomizationState) {
    val context = LocalContext.current
    val outlinedChips = LocalMainThemeManager.current.currentThemeConfiguration.components.chipStyle == ChipStyle.Outlined
    val cancelCrossLabel = stringResource(id = R.string.component_element_cancel_cross)
    val chipLabel = stringResource(id = R.string.component_chip)

    with(chipCustomizationState) {
        if (isChoiceChip) {
            OdsChoiceChipsFlowRow(selectedChip = choiceChipIndexSelected, outlinedChips = outlinedChips) {
                for (index in 1..4) {
                    OdsChoiceChip(
                        text = "${stringResource(id = chipType.value.nameRes)} $index",
                        value = index,
                        enabled = isEnabled
                    )
                }
            }
        } else {
            OdsChip(
                text = stringResource(id = R.string.component_chip_type, stringResource(id = chipType.value.nameRes)),
                onClick = { clickOnElement(context, chipLabel) },
                outlined = outlinedChips,
                leadingIcon = if (isActionChip || hasLeadingIcon) painterResource(id = R.drawable.ic_heart) else null,
                leadingAvatar = if (hasLeadingAvatar) painterResource(id = R.drawable.placeholder_small) else null,
                enabled = !disabledChecked.value,
                onCancel = if (isInputChip) {
                    { clickOnElement(context, cancelCrossLabel) }
                } else null
            )
        }
    }
}