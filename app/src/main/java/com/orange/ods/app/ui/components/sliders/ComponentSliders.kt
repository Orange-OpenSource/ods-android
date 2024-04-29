/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.orange.ods.compose.component.control.OdsSliderLockups
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.theme.OdsTheme

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
                    trailing = OdsListItem.TrailingSwitch(sideIcons.value, { sideIcons.value = it })
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_slider_value_displayed),
                    trailing = OdsListItem.TrailingSwitch(valueDisplayed.value, { valueDisplayed.value = it })
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_slider_stepped),
                    trailing = OdsListItem.TrailingSwitch(stepped.value, { stepped.value = it })
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = OdsTheme.spacings.medium.dp)
            ) {
                val technicalText = if (shouldDisplayValue) OdsComposable.OdsSliderLockups.name else OdsComposable.OdsSlider.name
                val steps = if (isStepped) 9 else 0
                val startIconContentDescription = stringResource(id = R.string.component_slider_low_volume)
                val startIcon = if (hasSideIcons) OdsSlider.Icon(painterResource(id = R.drawable.ic_volume_status_1), startIconContentDescription) else null
                val endIconContentDescription = stringResource(id = R.string.component_slider_high_volume)
                val endIcon = if (hasSideIcons) OdsSlider.Icon(painterResource(id = R.drawable.ic_volume_status_4), endIconContentDescription) else null

                var sliderPosition by remember { mutableFloatStateOf(0f) }
                val valueRange = 0f..100f

                Title(textRes = getTitleRes(isStepped, hasSideIcons, shouldDisplayValue))
                TechnicalText(text = technicalText)

                Spacer(modifier = Modifier.padding(top = OdsTheme.spacings.medium.dp))

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
                                classInstance<OdsSlider.Icon>("startIcon") {
                                    painter()
                                    contentDescription(startIconContentDescription)
                                }
                                classInstance<OdsSlider.Icon>("endIcon") {
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