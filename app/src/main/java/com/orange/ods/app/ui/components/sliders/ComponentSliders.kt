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
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.ComponentCode
import com.orange.ods.app.ui.utilities.composable.IconPainterValue
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.app.ui.utilities.composable.TextValueParameter
import com.orange.ods.app.ui.utilities.composable.Title
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.control.OdsSlider
import com.orange.ods.compose.component.control.OdsSliderLockups
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

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
                    trailing = OdsSwitchTrailing(checked = sideIcons)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_slider_value_displayed),
                    trailing = OdsSwitchTrailing(checked = valueDisplayed)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_slider_stepped),
                    trailing = OdsSwitchTrailing(checked = stepped)
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
            ) {
                val technicalText = if (shouldDisplayValue) OdsComponent.OdsSliderLockups.name else OdsComponent.OdsSlider.name
                val steps = if (isStepped) 9 else 0
                val leftIcon = if (hasSideIcons) painterResource(id = R.drawable.ic_volume_status_1) else null
                val leftIconContentDescription = if (hasSideIcons) stringResource(id = R.string.component_slider_low_volume) else null
                val rightIcon = if (hasSideIcons) painterResource(id = R.drawable.ic_volume_status_4) else null
                val rightIconContentDescription = if (hasSideIcons) stringResource(id = R.string.component_slider_high_volume) else null

                var sliderPosition by remember { mutableStateOf(0f) }
                val valueRange = 0f..100f

                Title(textRes = getTitleRes(isStepped, hasSideIcons, shouldDisplayValue))
                TechnicalText(text = technicalText)

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_m)))

                val componentName: String
                if (shouldDisplayValue) {
                    componentName = OdsComponent.OdsSliderLockups.name
                    OdsSliderLockups(
                        value = sliderPosition,
                        steps = steps,
                        valueRange = valueRange,
                        onValueChange = { sliderPosition = it },
                        leftIcon = leftIcon,
                        leftIconContentDescription = leftIconContentDescription,
                        rightIcon = rightIcon,
                        rightIconContentDescription = rightIconContentDescription
                    )
                } else {
                    componentName = OdsComponent.OdsSlider.name
                    OdsSlider(
                        value = sliderPosition,
                        steps = steps,
                        valueRange = valueRange,
                        onValueChange = { sliderPosition = it },
                        leftIcon = leftIcon,
                        leftIconContentDescription = leftIconContentDescription,
                        rightIcon = rightIcon,
                        rightIconContentDescription = rightIconContentDescription
                    )
                }

                CodeImplementationColumn {
                    ComponentCode(name = componentName, parameters = mutableListOf(
                        TextValueParameter.StringRepresentationParameter("value", sliderPosition),
                        TextValueParameter.ValueOnlyParameter("valueRange", "0f..100f"),
                        TextValueParameter.LambdaParameter("onValueChange")
                    ).apply {
                        if (isStepped) add(TextValueParameter.StringRepresentationParameter("steps", steps))
                        if (hasSideIcons) {
                            add(TextValueParameter.ValueOnlyParameter("leftIcon", IconPainterValue))
                            leftIconContentDescription?.let { add(TextValueParameter.BetweenQuotesParameter("leftIconContentDescription", it)) }
                            add(TextValueParameter.ValueOnlyParameter("rightIcon", IconPainterValue))
                            rightIconContentDescription?.let { add(TextValueParameter.BetweenQuotesParameter("rightIconContentDescription", it)) }
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
    isStepped && shouldDisplayValue && !hasSideIcons -> R.string.component_slider_discrete_lockups
    isStepped && shouldDisplayValue && hasSideIcons -> R.string.component_slider_discrete_lockups_with_icons
    !hasSideIcons -> R.string.component_slider_continuous_lockups
    else -> R.string.component_slider_continuous_lockups_with_icons
}