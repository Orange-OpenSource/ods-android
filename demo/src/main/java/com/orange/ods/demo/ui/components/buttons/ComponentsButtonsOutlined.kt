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
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utils.Title

@Composable
fun OutlinedButtons() {
    Title(R.string.component_buttons_outlined_title)
    OdsOutlinedButton(modifier = Modifier.fullWidthButton(), text = "Enabled", onClick = {})
    OdsOutlinedButton(modifier = Modifier.fullWidthButton(false), text = "Disabled", onClick = {}, enabled = false)

    OdsOutlinedButton(modifier = Modifier.fullWidthButton(), text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search)
    OdsOutlinedButton(modifier = Modifier.fullWidthButton(false), text = "Disabled", onClick = {}, enabled = false, iconRes = R.drawable.ic_search)

    LightSurface {
        OutlinedButtonsFullWidthAppearanceForced(OdsDisplayAppearance.ON_LIGHT)
    }
    DarkSurface {
        OutlinedButtonsFullWidthAppearanceForced(OdsDisplayAppearance.ON_DARK)
    }
}

@Composable
private fun OutlinedButtonsFullWidthAppearanceForced(displayAppearance: OdsDisplayAppearance) {
    OdsOutlinedButton(modifier = Modifier.fullWidthButton(), text = "Enabled", onClick = {}, displayAppearance = displayAppearance)
    OdsOutlinedButton(modifier = Modifier.fullWidthButton(false), text = "Disabled", onClick = {}, enabled = false, displayAppearance = displayAppearance)
}