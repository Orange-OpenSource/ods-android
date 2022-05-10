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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.SubComponent
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.LabelledCheckbox
import com.orange.ods.demo.ui.utilities.composable.LabelledRadioButton
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@ExperimentalMaterialApi
@Composable
fun SubComponentTextField(subComponent: SubComponent) {
    val customizationState = rememberTextFieldCustomizationState()

    ComponentCustomizationSheetScaffold(sheetContent = {
        LabelledCheckbox(label = stringResource(id = R.string.component_element_leading_icon), checked = customizationState.leadingIconChecked)

        Subtitle(textRes = R.string.component_state, withHorizontalPadding = true)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.ods_spacing_xs))
                .padding(start = dimensionResource(id = R.dimen.ods_spacing_s)),
            horizontalArrangement = Arrangement.Start
        ) {
            LabelledRadioButton(
                selectedRadio = customizationState.selectedState,
                currentRadio = TextFieldCustomizationState.Companion.DisplayType.DEFAULT.name,
                label = stringResource(id = R.string.component_state_default)
            )
            LabelledRadioButton(
                selectedRadio = customizationState.selectedState,
                currentRadio = TextFieldCustomizationState.Companion.DisplayType.ERROR.name,
                label = stringResource(id = R.string.component_state_error)
            )
            LabelledRadioButton(
                selectedRadio = customizationState.selectedState,
                currentRadio = TextFieldCustomizationState.Companion.DisplayType.DISABLED.name,
                label = stringResource(id = R.string.component_state_disabled)
            )
        }
        Subtitle(textRes = R.string.component_element_trailing, withHorizontalPadding = true)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(id = R.dimen.ods_spacing_xs))
                .padding(start = dimensionResource(id = R.dimen.ods_spacing_s)),
            horizontalArrangement = Arrangement.Start
        ) {
            LabelledRadioButton(
                selectedRadio = customizationState.selectedTrailingElement,
                currentRadio = TextFieldCustomizationState.Companion.TrailingElement.NONE.name,
                label = stringResource(id = R.string.component_element_none)
            )
            LabelledRadioButton(
                selectedRadio = customizationState.selectedTrailingElement,
                currentRadio = TextFieldCustomizationState.Companion.TrailingElement.ICON.name,
                label = stringResource(id = R.string.component_element_icon)
            )
            LabelledRadioButton(
                selectedRadio = customizationState.selectedTrailingElement,
                currentRadio = TextFieldCustomizationState.Companion.TrailingElement.TEXT.name,
                label = stringResource(id = R.string.component_element_text)
            )
        }
    }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin), vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin))
        ) {
            when (subComponent) {
                SubComponent.TextFieldsFilled -> TextFieldFilledContent(customizationState)
                SubComponent.TextFieldsOutline -> TextFieldOutlinedContent(customizationState)
                else -> {}
            }
        }
    }
}