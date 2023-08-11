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

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Box
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
import com.orange.ods.app.databinding.OdsTextButtonBinding
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Title
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsButtonIcon
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.extension.fullName

@Composable
fun ButtonsText(customizationState: ButtonCustomizationState) {
    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {
            Title(
                textRes = if (textButtonStyle.value == OdsTextButtonStyle.Default) R.string.component_button_style_default else R.string.component_button_style_primary,
                horizontalPadding = true
            )

            TextButton(
                style = textButtonStyle.value,
                leadingIcon = hasLeadingIcon,
                enabled = isEnabled,
                fullScreenWidth = hasFullScreenWidth
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

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
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                xmlAvailable = true
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsTextButton.name,
                    exhaustiveParameters = false,
                    parameters = {
                        simple("style", textButtonStyle.value.fullName)
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
private fun TextButton(
    style: OdsTextButtonStyle,
    leadingIcon: Boolean,
    enabled: Boolean,
    fullScreenWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val context = LocalContext.current
    val text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled)
    val iconId = R.drawable.ic_coffee

    Box(
        modifier = Modifier.padding(
            horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin),
            vertical = dimensionResource(com.orange.ods.R.dimen.spacing_m)
        )
    ) {
        UiFramework<OdsTextButtonBinding>(
            compose = {
                OdsTextButton(
                    modifier = if (fullScreenWidth) Modifier.fillMaxWidth() else Modifier,
                    icon = if (leadingIcon) OdsButtonIcon(painterResource(id = iconId)) else null,
                    text = text,
                    onClick = {},
                    enabled = enabled,
                    style = style,
                    displaySurface = displaySurface
                )
            }, xml = {
                this.text = text
                this.icon = if (leadingIcon) AppCompatResources.getDrawable(context, iconId) else null
                this.enabled = enabled
                this.style = style
                this.displaySurface = displaySurface
                root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val width = if (fullScreenWidth) FrameLayout.LayoutParams.MATCH_PARENT else FrameLayout.LayoutParams.WRAP_CONTENT
                odsTextbutton.layoutParams = FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.WRAP_CONTENT)
            }
        )
    }
}