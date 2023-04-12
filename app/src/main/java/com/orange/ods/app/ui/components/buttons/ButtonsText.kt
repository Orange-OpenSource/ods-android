/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.ComposableCode
import com.orange.ods.app.ui.utilities.composable.SimpleParameter
import com.orange.ods.app.ui.utilities.composable.Title
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.utilities.extension.fullName

@Composable
fun ButtonsText(customizationState: ButtonCustomizationState) {
    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {

            Title(
                textRes = if (textButtonStyle.value == OdsTextButtonStyle.Default) R.string.component_button_style_default else R.string.component_button_style_primary,
                horizontalPadding = true
            )

            TextButton(style = textButtonStyle.value, leadingIcon = hasLeadingIcon, enabled = isEnabled, fullScreenWidth = hasFullScreenWidth)

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                TextButton(
                    style = textButtonStyle.value,
                    leadingIcon = hasLeadingIcon,
                    enabled = isEnabled,
                    fullScreenWidth = hasFullScreenWidth,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            ) {
                ComposableCode(name = OdsComponent.OdsTextButton.name, parameters = mutableListOf<SimpleParameter>(
                    SimpleParameter.ValueOnlyParameter("style", textButtonStyle.value.fullName)
                ).apply {
                    if (hasFullScreenWidth) add(SimpleParameter.FillMaxWidth)
                    if (hasLeadingIcon) add(SimpleParameter.Icon)
                    if (!isEnabled) add(SimpleParameter.Enabled(false))
                })
            }
        }
    }
}

@Composable
private fun TextButton(
    style: OdsTextButtonStyle,
    leadingIcon: Boolean,
    enabled: Boolean,
    fullScreenWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val modifier = Modifier
        .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
        .padding(top = dimensionResource(R.dimen.spacing_m))

    OdsTextButton(
        modifier = if (fullScreenWidth) modifier.fillMaxWidth() else modifier,
        icon = if (leadingIcon) painterResource(id = R.drawable.ic_coffee) else null,
        text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled),
        onClick = {},
        enabled = enabled,
        style = style,
        displaySurface = displaySurface
    )
}