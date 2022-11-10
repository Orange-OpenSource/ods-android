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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.Title

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonsToggle() {
    val buttonToggleCustomizationState = rememberButtonToggleCustomizationState()

    with(buttonToggleCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                ComponentCountRow(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    title = stringResource(id = R.string.component_buttons_toggle_count),
                    count = toggleCount,
                    minusIconContentDescription = stringResource(id = R.string.component_buttons_toggle_remove),
                    plusIconContentDescription = stringResource(id = R.string.component_buttons_toggle_add),
                    minCount = ButtonToggleCustomizationState.ToggleCountMin,
                    maxCount = ButtonToggleCustomizationState.ToggleCountMax
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(
                        vertical = dimensionResource(id = R.dimen.screen_vertical_margin)
                    )
            ) {
                if (toggleCount.value > 1) {
                    Title(textRes = R.string.component_buttons_toggle_subtitle_group, horizontalPadding = true)
                    ToggleGroup(toggleCount = toggleCount.value)
                } else {
                    Title(textRes = R.string.component_buttons_toggle_subtitle_single, horizontalPadding = true)
                    ToggleSingle(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_m)))
                }

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                if (isSystemInDarkTheme()) {
                    LightSurface {
                        ToggleDisplay(toggleCount = toggleCount.value, displaySurface = OdsDisplaySurface.Light)
                    }
                } else {
                    DarkSurface {
                        ToggleDisplay(toggleCount = toggleCount.value, displaySurface = OdsDisplaySurface.Dark)
                    }
                }
            }
        }

    }
}

@Composable
private fun ToggleDisplay(toggleCount: Int, displaySurface: OdsDisplaySurface) {
    if (toggleCount > 1) {
        ToggleGroup(toggleCount = toggleCount, displaySurface = displaySurface)
    } else {
        ToggleSingle(displaySurface = displaySurface)
    }
}

@Composable
private fun ToggleGroup(toggleCount: Int, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default) {
    val iconsResources = listOf(R.drawable.ic_info, R.drawable.ic_search, R.drawable.ic_guideline_dna)
    var checkedIcon by remember { mutableStateOf(R.drawable.ic_info) }

    Row(
        modifier = Modifier.fullWidthButton(),
        horizontalArrangement = Arrangement.Center
    ) {
        iconsResources.subList(0, toggleCount).forEach { iconRes ->
            OdsIconToggleButton(
                checked = checkedIcon == iconRes,
                onCheckedChange = { checkedIcon = iconRes },
                icon = painterResource(id = iconRes),
                contentDescription = "",
                displaySurface = displaySurface,
            )
        }
    }
}


@Composable
private fun ToggleSingle(modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default) {
    var toggleChecked by remember { mutableStateOf(false) }
    OdsIconToggleButton(
        checked = toggleChecked,
        onCheckedChange = { toggleChecked = it },
        icon = painterResource(id = R.drawable.ic_module_molecule),
        contentDescription = "Search",
        modifier = modifier
            .fillMaxWidth(),
        displaySurface = displaySurface
    )
}