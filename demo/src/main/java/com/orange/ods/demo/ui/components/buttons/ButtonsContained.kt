/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.buttons

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem
import com.orange.ods.utilities.extension.fullName

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonsContained(style: OdsButtonStyle) {
    val buttonCustomizationState = rememberButtonCustomizationState(containedButtonStyle = rememberSaveable { mutableStateOf(style) })

    with(buttonCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                Subtitle(textRes = R.string.component_style, withHorizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    selectedChip = containedButtonStyle,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    outlinedChips = true
                ) {
                    OdsChoiceChip(textRes = R.string.component_button_style_primary, value = OdsButtonStyle.Primary)
                    OdsChoiceChip(textRes = R.string.component_button_style_default, value = OdsButtonStyle.Default)
                    OdsChoiceChip(textRes = R.string.component_button_style_functional, value = OdsButtonStyle.FunctionalPositive)
                }
                if (containedButtonStyle.value in listOf(OdsButtonStyle.FunctionalPositive, OdsButtonStyle.FunctionalNegative)) {
                    Subtitle(textRes = R.string.component_button_style_functional, withHorizontalPadding = true)
                    OdsChoiceChipsFlowRow(
                        selectedChip = containedButtonStyle,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        outlinedChips = true
                    ) {
                        OdsChoiceChip(textRes = R.string.component_button_style_functional_positive, value = OdsButtonStyle.FunctionalPositive)
                        OdsChoiceChip(textRes = R.string.component_button_style_functional_negative, value = OdsButtonStyle.FunctionalNegative)
                    }
                }
                SwitchListItem(labelRes = R.string.component_element_icon, checked = leadingIcon)
                SwitchListItem(labelRes = R.string.component_button_variable_width, checked = variableWidth)
                SwitchListItem(labelRes = R.string.component_state_disabled, checked = disabled)
            }) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
            ) {
                ButtonStyleTitle(style = containedButtonStyle.value)

                ContainedButton(style = containedButtonStyle.value, leadingIcon = hasLeadingIcon, enabled = isEnabled, variableWidth = hasVariableWidth)

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                if (isSystemInDarkTheme()) {
                    LightSurface {
                        ContainedButton(
                            style = containedButtonStyle.value,
                            leadingIcon = hasLeadingIcon,
                            enabled = isEnabled,
                            variableWidth = hasVariableWidth,
                            displaySurface = OdsDisplaySurface.Light
                        )
                    }
                } else {
                    DarkSurface {
                        ContainedButton(
                            style = containedButtonStyle.value,
                            leadingIcon = hasLeadingIcon,
                            enabled = isEnabled,
                            variableWidth = hasVariableWidth,
                            displaySurface = OdsDisplaySurface.Dark
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ButtonStyleTitle(style: OdsButtonStyle) {
    val titleRes: Int
    val technicalText: String
    when (style) {
        OdsButtonStyle.Default -> {
            titleRes = R.string.component_button_style_default
            technicalText = OdsButtonStyle.Default.fullName
        }
        OdsButtonStyle.Primary -> {
            titleRes = R.string.component_button_style_primary
            technicalText = OdsButtonStyle.Primary.fullName
        }
        OdsButtonStyle.FunctionalNegative -> {
            titleRes = R.string.component_button_style_functional_negative
            technicalText = OdsButtonStyle.FunctionalNegative.fullName
        }
        OdsButtonStyle.FunctionalPositive -> {
            titleRes = R.string.component_button_style_functional_positive
            technicalText = OdsButtonStyle.FunctionalPositive.fullName
        }
    }

    StyleTitle(titleRes = titleRes, technicalText = technicalText)
}

@Composable
private fun ContainedButton(
    style: OdsButtonStyle,
    leadingIcon: Boolean,
    enabled: Boolean,
    variableWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val modifier = Modifier
        .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
        .padding(top = dimensionResource(R.dimen.spacing_m))

    OdsButton(
        modifier = if (variableWidth) modifier else modifier.fillMaxWidth(),
        icon = if (leadingIcon) painterResource(id = R.drawable.ic_search) else null,
        text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled),
        onClick = {},
        enabled = enabled,
        style = style,
        displaySurface = displaySurface
    )
}