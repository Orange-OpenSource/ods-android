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
import com.orange.ods.compose.component.button.OdsButtonOutlined
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R

@Composable
fun VariantButtonsOutlined() {
    OutlinedButtonsEnabledDisabled(hasIcon = false)
    OutlinedButtonsEnabledDisabled(hasIcon = true)

    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

    LightSurface {
        OutlinedButtonsEnabledDisabled(hasIcon = false, displaySurface = OdsDisplaySurface.LIGHT)
    }
    DarkSurface {
        OutlinedButtonsEnabledDisabled(hasIcon = false, displaySurface = OdsDisplaySurface.DARK)
    }
}

@Composable
private fun OutlinedButtonsEnabledDisabled(hasIcon: Boolean, displaySurface: OdsDisplaySurface = OdsDisplaySurface.DEFAULT) {
    OdsButtonOutlined(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        iconRes = if (hasIcon) R.drawable.ic_search else null,
        displaySurface = displaySurface
    )
    OdsButtonOutlined(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        iconRes = if (hasIcon) R.drawable.ic_search else null,
        enabled = false,
        displaySurface = displaySurface
    )
}