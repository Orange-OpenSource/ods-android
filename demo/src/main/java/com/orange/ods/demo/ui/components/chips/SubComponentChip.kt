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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.SubComponent
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationCheckboxItem
import com.orange.ods.demo.ui.utilities.composable.LabelledRadioButton
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@ExperimentalMaterialApi
@Composable
fun SubComponentChip(subComponent: SubComponent) {
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
                    selectedRadio = customizationState.selectedLeadingElement,
                    currentRadio = ChipCustomizationState.LeadingElement.NONE,
                    label = stringResource(id = R.string.component_element_none)
                )
                LabelledRadioButton(
                    selectedRadio = customizationState.selectedLeadingElement,
                    currentRadio = ChipCustomizationState.LeadingElement.AVATAR,
                    label = stringResource(id = R.string.component_element_avatar)
                )
                LabelledRadioButton(
                    selectedRadio = customizationState.selectedLeadingElement,
                    currentRadio = ChipCustomizationState.LeadingElement.ICON,
                    label = stringResource(id = R.string.component_element_icon)
                )
            }

            ComponentCustomizationCheckboxItem(R.string.component_state_selected, customizationState.selectedChecked)
            ComponentCustomizationCheckboxItem(R.string.component_element_trailing_cross, customizationState.trailingCrossChecked)
            ComponentCustomizationCheckboxItem(R.string.component_state_disabled, customizationState.disabledChecked)

        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin),
                    vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin)
                )
        ) {
            when (subComponent) {
                SubComponent.ChipsContained -> ChipContainedContent(customizationState)
                SubComponent.ChipsOutlined -> ChipOutlinedContent(customizationState)
                else -> {}
            }
        }
    }

}

