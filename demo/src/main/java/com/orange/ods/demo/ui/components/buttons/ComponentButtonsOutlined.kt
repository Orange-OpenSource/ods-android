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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButtonOutlined
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun ButtonsOutlined() {
    Title(R.string.component_buttons_outlined_title, withHorizontalPadding = true)
    OdsButtonOutlined(modifier = Modifier.fullWidthButton(), text = stringResource(R.string.component_state_enabled), onClick = {})
    OdsButtonOutlined(modifier = Modifier.fullWidthButton(false), text = stringResource(R.string.component_state_disabled), onClick = {}, enabled = false)

    OdsButtonOutlined(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        iconRes = R.drawable.ic_search
    )
    OdsButtonOutlined(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        enabled = false,
        iconRes = R.drawable.ic_search
    )

    LightSurface {
        OutlinedButtonsFullWidthAppearanceForced(OdsDisplayAppearance.ON_LIGHT)
    }
    DarkSurface {
        OutlinedButtonsFullWidthAppearanceForced(OdsDisplayAppearance.ON_DARK)
    }
}

@Composable
private fun OutlinedButtonsFullWidthAppearanceForced(displayAppearance: OdsDisplayAppearance) {
    OdsButtonOutlined(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        displayAppearance = displayAppearance
    )
    OdsButtonOutlined(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        enabled = false,
        displayAppearance = displayAppearance
    )
}