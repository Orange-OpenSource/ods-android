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
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.ButtonTechnicalText
import com.orange.ods.demo.ui.utilities.composable.CodeImplementationColumn

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonsOutlined() {
    val buttonCustomizationState = rememberButtonCustomizationState()

    with(buttonCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsSwitchTrailing(checked = leadingIcon)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_button_variable_width),
                    trailing = OdsSwitchTrailing(checked = variableWidth)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_state_disabled),
                    trailing = OdsSwitchTrailing(checked = disabled)
                )
            }) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
            ) {
                OutlinedButton(leadingIcon = hasLeadingIcon, enabled = isEnabled, variableWidth = hasVariableWidth)

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                InvertedBackgroundColumn {
                    OutlinedButton(
                        leadingIcon = hasLeadingIcon,
                        enabled = isEnabled,
                        variableWidth = hasVariableWidth,
                        displaySurface = displaySurface
                    )
                }

                CodeImplementationColumn {
                    ButtonTechnicalText(
                        componentName = OdsComponent.OdsOutlinedButton.name,
                        enabled = isEnabled,
                        variableWidth = hasVariableWidth,
                        icon = hasLeadingIcon
                    )
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