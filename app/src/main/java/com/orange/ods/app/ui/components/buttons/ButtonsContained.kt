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

import android.app.ActionBar
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.databinding.OdsButtonBinding
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Title
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.utilities.extension.fullName

@Composable
fun ButtonsContained(customizationState: ButtonCustomizationState) {

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            val modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
                .padding(top = dimensionResource(R.dimen.spacing_m))
                .let { if (hasFullScreenWidth) it.fillMaxWidth() else it }

            with(buttonStyle.value) {
                if (buttonStyle.value in listOf(OdsButtonStyle.FunctionalNegative, OdsButtonStyle.FunctionalPositive)) {
                    Title(
                        textRes = if (this == OdsButtonStyle.FunctionalNegative) R.string.component_button_style_functional_negative else R.string.component_button_style_functional_positive,
                        horizontalPadding = true
                    )
                }
            }
            ContainedButton(
                style = buttonStyle.value,
                modifier = modifier,
                leadingIcon = hasLeadingIcon,
                enabled = isEnabled,
                fullScreenWidth = hasFullScreenWidth
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                ContainedButton(
                    style = buttonStyle.value,
                    modifier = modifier,
                    leadingIcon = hasLeadingIcon,
                    enabled = isEnabled,
                    fullScreenWidth = hasFullScreenWidth,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                xmlAvailable = true
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsButton.name,
                    exhaustiveParameters = false,
                    parameters = {
                        simple("style", buttonStyle.value.fullName)
                        if (hasFullScreenWidth) fillMaxWidth()
                        if (hasLeadingIcon) icon()
                        if (!isEnabled) enabled(false)
                    }
                )
            }
        }
    }
}

@Composable
private fun ContainedButton(
    style: OdsButtonStyle,
    modifier: Modifier,
    leadingIcon: Boolean,
    enabled: Boolean,
    fullScreenWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val context = LocalContext.current
    val text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled)
    val iconId = R.drawable.ic_coffee

    UiFramework<OdsButtonBinding>(
        modifier = modifier,
        compose = {
            OdsButton(
                modifier = if (fullScreenWidth) Modifier.fillMaxWidth() else Modifier,
                icon = if (leadingIcon) painterResource(id = iconId) else null,
                text = text,
                onClick = {},
                enabled = enabled,
                style = style,
                displaySurface = displaySurface
            )
        }, xml = {
            this.text = text
            button.style = style
            button.icon = if (leadingIcon) AppCompatResources.getDrawable(context, iconId) else null
            button.isEnabled = enabled
            val width = if (fullScreenWidth) ActionBar.LayoutParams.MATCH_PARENT else ActionBar.LayoutParams.WRAP_CONTENT
            button.layoutParams = ViewGroup.LayoutParams(width, ActionBar.LayoutParams.WRAP_CONTENT)
            button.displaySurface = displaySurface
        }
    )
}