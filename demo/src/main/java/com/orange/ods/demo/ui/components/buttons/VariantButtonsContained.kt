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
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun VariantButtonsContained() {
    Title(R.string.component_buttons_contained_subtitle_primary, withHorizontalPadding = true)
    ContainedButtons(hasPrimaryColor = true)

    Title(R.string.component_buttons_contained_subtitle_standard, withHorizontalPadding = true)
    ContainedButtons(hasPrimaryColor = false)
}

@Composable
private fun ContainedButtons(hasPrimaryColor: Boolean) {
    ContainedButtonsEnabledDisabled(hasPrimaryColor = hasPrimaryColor, hasIcon = false)
    ContainedButtonsEnabledDisabled(hasPrimaryColor = hasPrimaryColor, hasIcon = true)

    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

    LightSurface {
        ContainedButtonsAppearanceForced(OdsDisplaySurface.LIGHT, hasPrimaryColor = hasPrimaryColor)
    }
    DarkSurface {
        ContainedButtonsAppearanceForced(OdsDisplaySurface.DARK, hasPrimaryColor = hasPrimaryColor)
    }
}

@Composable
private fun ContainedButtonsAppearanceForced(displaySurface: OdsDisplaySurface, hasPrimaryColor: Boolean) {
    OdsButton(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        hasPrimaryColor = hasPrimaryColor,
        displaySurface = displaySurface
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        enabled = false,
        hasPrimaryColor = hasPrimaryColor,
        displaySurface = displaySurface
    )
}

@Composable
private fun ContainedButtonsEnabledDisabled(hasPrimaryColor: Boolean, hasIcon: Boolean) {
    OdsButton(
        modifier = Modifier.fullWidthButton(),
        iconRes = if (hasIcon) R.drawable.ic_search else null,
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        hasPrimaryColor = hasPrimaryColor
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        iconRes = if (hasIcon) R.drawable.ic_search else null,
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        enabled = false,
        hasPrimaryColor = hasPrimaryColor
    )

}