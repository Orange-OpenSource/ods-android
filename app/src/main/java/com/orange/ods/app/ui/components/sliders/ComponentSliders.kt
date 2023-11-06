/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.sliders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.app.ui.utilities.composable.Title
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.control.OdsSlider
import com.orange.ods.compose.component.control.OdsSliderIcon
import com.orange.ods.compose.component.control.OdsSliderLockups
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemTrailingSwitch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentSliders() {
    val sliderCustomizationState = rememberSliderCustomizationState()

    with(sliderCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_slider_side_icons),
                    trailing = OdsListItemTrailingSwitch(sideIcons.value, { sideIcons.value = it })
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_slider_value_displayed),
                    trailing = OdsListItemTrailingSwitch(valueDisplayed.value, { valueDisplayed.value = it })
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_slider_stepped),
                    trailing = OdsListItemTrailingSwitch(stepped.value, { stepped.value = it })
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
            ) {
                val technicalText = if (shouldDisplayValue) OdsComposable.OdsSliderLockups.name else OdsComposable.OdsSlider.name
                val steps = if (isStepped) 9 else 0
                val startIconContentDescription = stringResource(id = R.string.component_slider_low_volume)
                val startIcon = if (hasSideIcons) OdsSliderIcon(painterResource(id = R.drawable.ic_volume_status_1), startIconContentDescription) else null
                val endIconContentDescription = stringResource(id = R.string.component_slider_high_volume)
                val endIcon = if (hasSideIcons) OdsSliderIcon(painterResource(id = R.drawable.ic_volume_status_4), endIconContentDescription) else null

                var sliderPosition by remember { mutableStateOf(0f) }
                val valueRange = 0f..100f

                Title(textRes = getTitleRes(isStepped, hasSideIcons, shouldDisplayValue))
                TechnicalText(text = technicalText)

                Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_m)))

                val componentName: String
                if (shouldDisplayValue) {
                    componentName = OdsComposable.OdsSliderLockups.name
                    OdsSliderLockups(
                        value = sliderPosition,
                        steps = steps,
                        valueRange = valueRange,
                        onValueChange = { sliderPosition = it },
                        startIcon = startIcon,
                        endIcon = endIcon
                    )
                } else {
                    componentName = OdsComposable.OdsSlider.name
                    OdsSlider(
                        value = sliderPosition,
                        steps = steps,
                        valueRange = valueRange,
                        onValueChange = { sliderPosition = it },
                        startIcon = startIcon,
                        endIcon = endIcon
                    )
                }

                CodeImplementationColumn {
                    FunctionCallCode(
                        name = componentName,
                        exhaustiveParameters = false,
                        parameters = {
                            float("value", sliderPosition)
                            simple("valueRange", "0f..100f")
                            lambda("onValueChange")
                            if (isStepped) stringRepresentation("steps", steps)
                            if (hasSideIcons) {
                                classInstance<OdsSliderIcon>("startIcon") {
                                    painter()
                                    contentDescription(startIconContentDescription)
                                }
                                classInstance<OdsSliderIcon>("endIcon") {
                                    painter()
                                    contentDescription(endIconContentDescription)
                                }
                            }
                        })
                }
            }
        }
    }
}

private fun getTitleRes(isStepped: Boolean, hasSideIcons: Boolean, shouldDisplayValue: Boolean) = when {
    isStepped && !hasSideIcons && !shouldDisplayValue -> R.string.component_slider_discrete
    isStepped && hasSideIcons && !shouldDisplayValue -> R.string.component_slider_discrete_with_icons
    !isStepped && !hasSideIcons && !shouldDisplayValue -> R.string.component_slider_continuous
    !isStepped && hasSideIcons && !shouldDisplayValue -> R.string.component_slider_continuous_with_icons
    isStepped && !hasSideIcons -> R.string.component_slider_discrete_lockups
    isStepped && hasSideIcons -> R.string.component_slider_discrete_lockups_with_icons
    !hasSideIcons -> R.string.component_slider_continuous_lockups
    else -> R.string.component_slider_continuous_lockups_with_icons
}