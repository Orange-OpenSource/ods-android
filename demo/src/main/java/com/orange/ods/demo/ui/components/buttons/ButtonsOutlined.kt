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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonsOutlined() {
    val buttonCustomizationState = rememberButtonCustomizationState()

    with(buttonCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                SwitchListItem(labelRes = R.string.component_element_icon, checked = leadingIcon)
                SwitchListItem(labelRes = R.string.component_buttons_variable_width, checked = variableWidth)
                SwitchListItem(labelRes = R.string.component_state_disabled, checked = disabled)
            }) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
            ) {
                OutlinedButton(leadingIcon = hasLeadingIcon, enabled = isEnabled, variableWidth = hasVariableWidth)

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                if (isSystemInDarkTheme()) {
                    LightSurface {
                        OutlinedButton(
                            leadingIcon = hasLeadingIcon,
                            enabled = isEnabled,
                            variableWidth = hasVariableWidth,
                            displaySurface = OdsDisplaySurface.Light
                        )
                    }
                } else {
                    DarkSurface {
                        OutlinedButton(
                            leadingIcon = hasLeadingIcon,
                            enabled = isEnabled,
                            variableWidth = hasVariableWidth,
                            displaySurface = OdsDisplaySurface.Dark
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun OutlinedButton(
    leadingIcon: Boolean,
    enabled: Boolean,
    variableWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val modifier = Modifier
        .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
        .padding(top = dimensionResource(R.dimen.spacing_m))

    OdsOutlinedButton(
        modifier = if (variableWidth) modifier else modifier.fillMaxWidth(),
        text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled),
        onClick = {},
        icon = if (leadingIcon) painterResource(id = R.drawable.ic_search) else null,
        enabled = enabled,
        displaySurface = displaySurface
    )
}