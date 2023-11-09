/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.progress

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.progressindicator.OdsCircularProgressIndicator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProgressCircular() {
    val customizationState = rememberProgressCustomizationState()

    with(customizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                Subtitle(textRes = R.string.component_element_type, horizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    value = type.value,
                    onValueChange = {
                        value -> type.value = value
                        if (value == ProgressCustomizationState.Type.Indeterminate) resetAnimation()
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                    chips = listOf(
                        OdsChoiceChip(text = stringResource(id = R.string.component_progress_determinate), value = ProgressCustomizationState.Type.Determinate),
                        OdsChoiceChip(
                            text = stringResource(id = R.string.component_progress_indeterminate),
                            value = ProgressCustomizationState.Type.Indeterminate
                        )
                    )
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_element_label),
                    trailing = OdsListItem.TrailingSwitch(label.value, { label.value = it })
                )
            }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                val text = stringResource(id = R.string.component_progress_label)
                OdsCircularProgressIndicator(
                    progress = if (type.value == ProgressCustomizationState.Type.Determinate) determinateProgressAnimation.value else null,
                    label = if (hasLabel) text else null,
                    modifier = Modifier
                        .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                        .align(alignment = Alignment.CenterHorizontally)
                )
                if (type.value == ProgressCustomizationState.Type.Determinate) {
                    LaunchedEffect(DeterminateProgressTargetValue) {
                        determinateProgressValue.value = DeterminateProgressTargetValue
                    }
                }

                CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))) {
                    FunctionCallCode(
                        name = OdsComposable.OdsCircularProgressIndicator.name,
                        exhaustiveParameters = false,
                        parameters = {
                            if (type.value == ProgressCustomizationState.Type.Determinate) stringRepresentation("progress", determinateProgressValue)
                            if (hasLabel) label(text)
                        }
                    )
                }
            }
        }
    }
}