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
import com.orange.ods.compose.component.button.OdsButtonText
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun ButtonsText() {
    Title(R.string.component_buttons_text_subtitle_primary, withHorizontalPadding = true)
    TextButtons(hasPrimaryColor = true)

    Title(R.string.component_buttons_text_subtitle_default, withHorizontalPadding = true)
    TextButtons(hasPrimaryColor = false)
}

@Composable
private fun TextButtons(hasPrimaryColor: Boolean) {
    TextButtonsEnabledDisabled(hasPrimaryColor = hasPrimaryColor, hasIcon = false)
    TextButtonsEnabledDisabled(hasPrimaryColor = hasPrimaryColor, hasIcon = true)

    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.ods_spacing_xs)))

    LightSurface {
        TextButtonsEnabledDisabled(hasPrimaryColor = hasPrimaryColor, hasIcon = false, displayAppearance = OdsDisplayAppearance.ON_LIGHT)
    }
    DarkSurface {
        TextButtonsEnabledDisabled(hasPrimaryColor = hasPrimaryColor, hasIcon = false, displayAppearance = OdsDisplayAppearance.ON_DARK)
    }
}

@Composable
private fun TextButtonsEnabledDisabled(hasPrimaryColor: Boolean, hasIcon: Boolean, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) {
    OdsButtonText(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        hasPrimaryColor = hasPrimaryColor,
        iconRes = if (hasIcon) R.drawable.ic_search else null,
        displayAppearance = displayAppearance
    )
    OdsButtonText(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        hasPrimaryColor = hasPrimaryColor,
        iconRes = if (hasIcon) R.drawable.ic_search else null,
        enabled = false,
        displayAppearance = displayAppearance
    )
}