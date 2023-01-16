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
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.ButtonTechnicalText
import com.orange.ods.demo.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.Title
import com.orange.ods.utilities.extension.fullName

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonsContained(style: OdsButtonStyle) {
    val buttonCustomizationState = rememberButtonCustomizationState(containedButtonStyle = rememberSaveable { mutableStateOf(style) })

    with(buttonCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                if (containedButtonStyle.value in listOf(OdsButtonStyle.FunctionalPositive, OdsButtonStyle.FunctionalNegative)) {
                    Subtitle(textRes = R.string.component_button_style_functional, horizontalPadding = true)
                    OdsChoiceChipsFlowRow(
                        selectedChip = containedButtonStyle,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        outlinedChips = true
                    ) {
                        OdsChoiceChip(textRes = R.string.component_button_style_functional_positive, value = OdsButtonStyle.FunctionalPositive)
                        OdsChoiceChip(textRes = R.string.component_button_style_functional_negative, value = OdsButtonStyle.FunctionalNegative)
                    }
                }
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsSwitchTrailing(checked = leadingIcon)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_button_variable_width),
                    trailing = OdsSwitchTrailing(checked = variableWidth)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsSwitchTrailing(checked = enabled)
                )
            }) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
            ) {
                with(containedButtonStyle.value) {
                    if (this in listOf(OdsButtonStyle.FunctionalNegative, OdsButtonStyle.FunctionalPositive)) {
                        Title(
                            textRes = if (this == OdsButtonStyle.FunctionalNegative) R.string.component_button_style_functional_negative else R.string.component_button_style_functional_positive,
                            horizontalPadding = true
                        )
                    }
                }

                ContainedButton(style = containedButtonStyle.value, leadingIcon = hasLeadingIcon, enabled = isEnabled, variableWidth = hasVariableWidth)

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                InvertedBackgroundColumn {
                    ContainedButton(
                        style = containedButtonStyle.value,
                        leadingIcon = hasLeadingIcon,
                        enabled = isEnabled,
                        variableWidth = hasVariableWidth,
                        displaySurface = displaySurface
                    )
                }

                CodeImplementationColumn {
                    ButtonTechnicalText(
                        componentName = OdsComponent.OdsButton.name,
                        style = containedButtonStyle.value.fullName,
                        enabled = isEnabled,
                        variableWidth = hasVariableWidth,
                        icon = hasLeadingIcon
                    )
                }
            }
        }
    }
}

@Composable
private fun ContainedButton(
    style: OdsButtonStyle,
    leadingIcon: Boolean,
    enabled: Boolean,
    variableWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin), vertical = dimensionResource(R.dimen.spacing_m))

    OdsButton(
        modifier = if (variableWidth) modifier else modifier.fillMaxWidth(),
        icon = if (leadingIcon) painterResource(id = R.drawable.ic_coffee) else null,
        text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled),
        onClick = {},
        enabled = enabled,
        style = style,
        displaySurface = displaySurface
    )
}