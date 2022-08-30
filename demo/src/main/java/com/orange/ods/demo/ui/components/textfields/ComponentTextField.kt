/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.chip.SelectableChip
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@ExperimentalMaterialApi
@Composable
fun ComponentTextField(variant: Variant) {
    val textFieldCustomizationState = rememberTextFieldCustomizationState()

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            SwitchListItem(labelRes = R.string.component_element_leading_icon, checked = textFieldCustomizationState.leadingIconChecked)

            Subtitle(textRes = R.string.component_state, withHorizontalPadding = true)
            OdsChoiceChipsFlowRow(
                selectedChip = textFieldCustomizationState.displayType,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin)),
                outlinedChips = true
            ) {
                SelectableChip(textRes = R.string.component_state_default, value = TextFieldCustomizationState.DisplayType.DEFAULT)
                SelectableChip(textRes = R.string.component_state_error, value = TextFieldCustomizationState.DisplayType.ERROR)
                SelectableChip(textRes = R.string.component_state_disabled, value = TextFieldCustomizationState.DisplayType.DISABLED)
            }

            Subtitle(textRes = R.string.component_element_trailing, withHorizontalPadding = true)
            OdsChoiceChipsFlowRow(
                selectedChip = textFieldCustomizationState.trailingElement,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin)),
                outlinedChips = true
            ) {
                SelectableChip(textRes = R.string.component_element_none, value = TextFieldCustomizationState.TrailingElement.NONE)
                SelectableChip(textRes = R.string.component_element_icon, value = TextFieldCustomizationState.TrailingElement.ICON)
                SelectableChip(textRes = R.string.component_element_text, value = TextFieldCustomizationState.TrailingElement.TEXT)
            }
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin),
                    vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin)
                )
        ) {
            when (variant) {
                Variant.TextFieldsFilled -> VariantTextFieldFilled(textFieldCustomizationState)
                Variant.TextFieldsOutlined -> VariantTextFieldOutlined(textFieldCustomizationState)
                else -> {}
            }
        }
    }
}