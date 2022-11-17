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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
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
fun ButtonsText() {
    val buttonCustomizationState = rememberButtonCustomizationState()

    with(buttonCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                Subtitle(textRes = R.string.component_style, withHorizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    selectedChip = textButtonStyle,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    outlinedChips = true
                ) {
                    OdsChoiceChip(textRes = R.string.component_button_style_primary, value = OdsTextButtonStyle.Primary)
                    OdsChoiceChip(textRes = R.string.component_button_style_default, value = OdsTextButtonStyle.Default)
                }
                SwitchListItem(labelRes = R.string.component_element_icon, checked = leadingIcon)
                SwitchListItem(labelRes = R.string.component_buttons_variable_width, checked = variableWidth)
                SwitchListItem(labelRes = R.string.component_state_disabled, checked = disabled)
            }) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
            ) {
                ButtonStyleTitle(style = textButtonStyle.value)

                TextButton(style = textButtonStyle.value, leadingIcon = hasLeadingIcon, enabled = isEnabled, variableWidth = hasVariableWidth)

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                if (isSystemInDarkTheme()) {
                    LightSurface {
                        TextButton(
                            style = textButtonStyle.value,
                            leadingIcon = hasLeadingIcon,
                            enabled = isEnabled,
                            variableWidth = hasVariableWidth,
                            displaySurface = OdsDisplaySurface.Light
                        )
                    }
                } else {
                    DarkSurface {
                        TextButton(
                            style = textButtonStyle.value,
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
private fun ButtonStyleTitle(style: OdsTextButtonStyle) {
    val titleRes: Int
    val technicalText: String
    when (style) {
        OdsTextButtonStyle.Default -> {
            titleRes = R.string.component_button_style_default
            technicalText = OdsTextButtonStyle.Default.fullName
        }
        OdsTextButtonStyle.Primary -> {
            titleRes = R.string.component_button_style_primary
            technicalText = OdsTextButtonStyle.Primary.fullName
        }
    }

    StyleTitle(titleRes = titleRes, technicalText = technicalText)
}

@Composable
private fun TextButton(
    style: OdsTextButtonStyle,
    leadingIcon: Boolean,
    enabled: Boolean,
    variableWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val modifier = Modifier
        .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
        .padding(top = dimensionResource(R.dimen.spacing_m))

    OdsTextButton(
        modifier = if (variableWidth) modifier else modifier.fillMaxWidth(),
        icon = if (leadingIcon) painterResource(id = R.drawable.ic_search) else null,
        text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled),
        onClick = {},
        enabled = enabled,
        style = style,
        displaySurface = displaySurface
    )
}