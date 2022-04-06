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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.compose.component.button.OdsButtonToggle
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utils.Subtitle
import com.orange.ods.demo.ui.utils.Title

@Composable
fun ToggleButtons() {
    Title(R.string.component_buttons_toggle_title)

    Subtitle(R.string.component_buttons_toggle_subtitle_group)
    ToggleGroup()

    Subtitle(R.string.component_buttons_toggle_subtitle_single)
    var toggleChecked by remember { mutableStateOf(false) }
    OdsButtonToggle(
        checked = toggleChecked,
        onCheckedChange = { toggleChecked = it },
        iconRes = R.drawable.ic_module_molecule,
        contentDescription = "Search",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.ods_spacing_xs))
    )

    LightSurface {
        ToggleButtonFullWidthAppearanceForced(OdsDisplayAppearance.ON_LIGHT)
    }
    DarkSurface {
        ToggleButtonFullWidthAppearanceForced(OdsDisplayAppearance.ON_DARK)
    }
}

@Composable
private fun ToggleGroup() {
    val iconsRes = listOf(R.drawable.ic_info, R.drawable.ic_search, R.drawable.ic_guideline_dna)
    var checkedIcon by remember { mutableStateOf(R.drawable.ic_info) }

    Row(
        modifier = Modifier.fullWidthButton(),
        horizontalArrangement = Arrangement.Center
    ) {
        iconsRes.forEach { iconRes ->
            OdsButtonToggle(
                checked = checkedIcon == iconRes,
                onCheckedChange = { checkedIcon = iconRes },
                iconRes = iconRes,
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun ToggleButtonFullWidthAppearanceForced(displayAppearance: OdsDisplayAppearance) {
    var toggleChecked by remember { mutableStateOf(false) }
    OdsButtonToggle(
        checked = toggleChecked,
        onCheckedChange = { toggleChecked = it },
        iconRes = R.drawable.ic_module_molecule,
        contentDescription = "Search",
        modifier = Modifier
            .fullWidthButton()
            .padding(top = dimensionResource(R.dimen.ods_spacing_xxs)),
        displayAppearance = displayAppearance
    )
}