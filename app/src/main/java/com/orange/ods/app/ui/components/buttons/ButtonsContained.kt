/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.composable.CodeImplementation
import com.orange.ods.app.ui.utilities.composable.ComponentParameter
import com.orange.ods.app.ui.utilities.composable.Title
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.utilities.extension.fullName

@Composable
fun ButtonsContained(customizationState: ButtonCustomizationState) {

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            with(buttonStyle.value) {
                if (buttonStyle.value in listOf(OdsButtonStyle.FunctionalNegative, OdsButtonStyle.FunctionalPositive)) {
                    Title(
                        textRes = if (this == OdsButtonStyle.FunctionalNegative) R.string.component_button_style_functional_negative else R.string.component_button_style_functional_positive,
                        horizontalPadding = true
                    )
                }
            }

            ContainedButton(style = buttonStyle.value, leadingIcon = hasLeadingIcon, enabled = isEnabled, fullScreenWidth = hasFullScreenWidth)

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                ContainedButton(
                    style = buttonStyle.value,
                    leadingIcon = hasLeadingIcon,
                    enabled = isEnabled,
                    fullScreenWidth = hasFullScreenWidth,
                    displaySurface = displaySurface
                )
            }

            CodeImplementation(OdsComponent.OdsButton.name).CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                componentParameters = mutableListOf<ComponentParameter>(
                    ComponentParameter.SimpleValueParameter("style", buttonStyle.value.fullName)
                ).apply {
                    if (hasFullScreenWidth) add(ComponentParameter.FillMaxWidth)
                    if (hasLeadingIcon) add(ComponentParameter.Icon)
                    if (!isEnabled) add(ComponentParameter.Enabled(false))
                }
            )
        }
    }
}

@Composable
private fun ContainedButton(
    style: OdsButtonStyle,
    leadingIcon: Boolean,
    enabled: Boolean,
    fullScreenWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin), vertical = dimensionResource(R.dimen.spacing_m))

    OdsButton(
        modifier = if (fullScreenWidth) modifier.fillMaxWidth() else modifier,
        icon = if (leadingIcon) painterResource(id = R.drawable.ic_coffee) else null,
        text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled),
        onClick = {},
        enabled = enabled,
        style = style,
        displaySurface = displaySurface
    )
}