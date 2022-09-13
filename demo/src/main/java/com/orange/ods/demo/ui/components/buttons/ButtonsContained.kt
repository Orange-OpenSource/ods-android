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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.TechnicalText
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun ButtonsHighEmphasis() {
    Title(R.string.component_buttons_high_emphasis_primary, withHorizontalPadding = true)
    TechnicalText(text = "OdsButtonStyle.Primary", withHorizontalPadding = true)
    ContainedButtons(style = OdsButtonStyle.Primary)

    Title(R.string.component_buttons_high_emphasis_standard, withHorizontalPadding = true)
    TechnicalText(text = "OdsButtonStyle.Default", withHorizontalPadding = true)
    ContainedButtons(style = OdsButtonStyle.Default)
}

@Composable
fun ButtonsFunctional() {
    Title(R.string.component_buttons_functional_positive, withHorizontalPadding = true)
    TechnicalText(text = "OdsButtonStyle.FunctionalPositive", withHorizontalPadding = true)
    ContainedButtonsOnDefaultSurface(style = OdsButtonStyle.FunctionalPositive)

    Title(R.string.component_buttons_functional_negative, withHorizontalPadding = true)
    TechnicalText(text = "OdsButtonStyle.FunctionalNegative", withHorizontalPadding = true)
    ContainedButtonsOnDefaultSurface(style = OdsButtonStyle.FunctionalNegative)
}

@Composable
private fun ContainedButtonsOnDefaultSurface(style: OdsButtonStyle) {
    ContainedButtonsEnabledDisabled(style = style, hasIcon = false)
    ContainedButtonsEnabledDisabled(style = style, hasIcon = true)
}

@Composable
private fun ContainedButtons(style: OdsButtonStyle) {
    ContainedButtonsOnDefaultSurface(style)

    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

    LightSurface {
        ContainedButtonsEnabledDisabled(style = style, hasIcon = false, displaySurface = OdsDisplaySurface.Light)
    }
    DarkSurface {
        ContainedButtonsEnabledDisabled(style = style, hasIcon = false, displaySurface = OdsDisplaySurface.Dark)
    }
}

@Composable
private fun ContainedButtonsEnabledDisabled(
    style: OdsButtonStyle,
    hasIcon: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    OdsButton(
        modifier = Modifier.fullWidthButton(),
        icon = if (hasIcon) painterResource(id = R.drawable.ic_search) else null,
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        style = style,
        displaySurface = displaySurface
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        icon = if (hasIcon) painterResource(id = R.drawable.ic_search) else null,
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        enabled = false,
        style = style,
        displaySurface = displaySurface
    )

}