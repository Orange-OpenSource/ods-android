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
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.TechnicalText
import com.orange.ods.demo.ui.utilities.composable.Title
import com.orange.ods.utilities.extension.fullName

@Composable
fun ButtonsText() {
    Title(R.string.component_buttons_low_emphasis_primary, withHorizontalPadding = true)
    TechnicalText(text = OdsButtonStyle.Primary.fullName, withHorizontalPadding = true)
    TextButtons(style = OdsTextButtonStyle.Primary)

    Title(R.string.component_buttons_low_emphasis_default, withHorizontalPadding = true)
    TechnicalText(text = OdsButtonStyle.Default.fullName, withHorizontalPadding = true)
    TextButtons(style = OdsTextButtonStyle.Default)
}

@Composable
private fun TextButtons(style: OdsTextButtonStyle) {
    TextButtonsEnabledDisabled(style = style, hasIcon = false)
    TextButtonsEnabledDisabled(style = style, hasIcon = true)

    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

    LightSurface {
        TextButtonsEnabledDisabled(style = style, hasIcon = false, displaySurface = OdsDisplaySurface.Light)
    }
    DarkSurface {
        TextButtonsEnabledDisabled(style = style, hasIcon = false, displaySurface = OdsDisplaySurface.Dark)
    }
}

@Composable
private fun TextButtonsEnabledDisabled(style: OdsTextButtonStyle, hasIcon: Boolean, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default) {
    OdsTextButton(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        style = style,
        icon = if (hasIcon) painterResource(id = R.drawable.ic_search) else null,
        displaySurface = displaySurface
    )
    OdsTextButton(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        style = style,
        icon = if (hasIcon) painterResource(id = R.drawable.ic_search) else null,
        enabled = false,
        displaySurface = displaySurface
    )
}