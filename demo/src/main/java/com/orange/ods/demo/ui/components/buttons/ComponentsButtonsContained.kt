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
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utils.Subtitle

@Composable
fun ContainedButtons() {
    Subtitle(R.string.component_buttons_contained_subtitle_primary)
    OdsButton(
        modifier = Modifier
            .fullWidthButton(),
        text = stringResource(R.string.component_button_enabled),
        onClick = {},
        hasPrimaryColor = true
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_button_disabled),
        onClick = {},
        enabled = false,
        hasPrimaryColor = true
    )

    OdsButton(
        modifier = Modifier.fullWidthButton(),
        iconRes = R.drawable.ic_search,
        text = stringResource(R.string.component_button_enabled),
        onClick = {},
        hasPrimaryColor = true
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        iconRes = R.drawable.ic_search,
        text = stringResource(R.string.component_button_disabled),
        onClick = {},
        enabled = false,
        hasPrimaryColor = true
    )

    Subtitle(R.string.component_buttons_contained_subtitle_standard)
    OdsButton(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(R.string.component_button_enabled),
        onClick = {},
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_button_disabled),
        onClick = {},
        enabled = false,
    )

    OdsButton(
        modifier = Modifier.fullWidthButton(),
        iconRes = R.drawable.ic_search,
        text = stringResource(R.string.component_button_enabled),
        onClick = {},
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        iconRes = R.drawable.ic_search,
        text = stringResource(R.string.component_button_disabled),
        onClick = {},
        enabled = false,
    )

    LightSurface {
        ContainedButtonsFullWidthAppearanceForced(OdsDisplayAppearance.ON_LIGHT)
    }
    DarkSurface {
        ContainedButtonsFullWidthAppearanceForced(OdsDisplayAppearance.ON_DARK)
    }
}

@Composable
private fun ContainedButtonsFullWidthAppearanceForced(displayAppearance: OdsDisplayAppearance) {
    OdsButton(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(R.string.component_button_enabled),
        onClick = {},
        hasPrimaryColor = true,
        displayAppearance = displayAppearance
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_button_disabled),
        onClick = {},
        enabled = false,
        hasPrimaryColor = true,
        displayAppearance = displayAppearance
    )
    OdsButton(modifier = Modifier.fullWidthButton(), text = stringResource(R.string.component_button_enabled), onClick = {}, displayAppearance = displayAppearance)
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        text = stringResource(R.string.component_button_disabled),
        onClick = {},
        enabled = false,
        displayAppearance = displayAppearance
    )
}