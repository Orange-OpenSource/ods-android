/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
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
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.code.XmlViewTag
import com.orange.ods.app.ui.utilities.composable.Title
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.xml.utilities.extension.xmlEnumValue

@Composable
fun ButtonsText(customizationState: ButtonCustomizationState) {
    with(customizationState) {
        val buttonText = stringResource(if (isEnabled) R.string.component_state_enabled else R.string.component_state_disabled)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {
            Title(
                textRes = if (textButtonStyle.value == OdsTextButton.Style.Default) R.string.component_button_style_default else R.string.component_button_style_primary,
                horizontalPadding = true
            )

            TextButton(
                text = buttonText,
                style = textButtonStyle.value,
                leadingIcon = hasLeadingIcon,
                enabled = isEnabled,
                fullScreenWidth = hasFullScreenWidth
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                TextButton(
                    text = buttonText,
                    style = textButtonStyle.value,
                    leadingIcon = hasLeadingIcon,
                    enabled = isEnabled,
                    fullScreenWidth = hasFullScreenWidth,
                    invertedTheme = true
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                composeContent = {
                    FunctionCallCode(
                        name = OdsComposable.OdsTextButton.name,
                        exhaustiveParameters = false,
                        parameters = {
                            text(buttonText)
                            enum("style", textButtonStyle.value)
                            if (hasFullScreenWidth) fillMaxWidth()
                            if (hasLeadingIcon) {
                                classInstance<OdsButton.Icon>("icon") {
                                    painter()
                                }
                            }
                            if (!isEnabled) enabled(false)
                        }
                    )
                },
                xmlContent = {
                    CodeBackgroundColumn {
                        XmlViewTag(
                            clazz = com.orange.ods.xml.component.button.OdsTextButton::class.java,
                            xmlAttributes = {
                                id("ods_text_button")
                                layoutWidth(hasFullScreenWidth)
                                layoutHeight()
                                appAttr("text", buttonText)
                                if (hasLeadingIcon) drawable("icon", "icon")
                                if (!isEnabled) disabled()
                                if (textButtonStyle.value != OdsTextButton.Style.Default) appAttr("odsTextButtonStyle", textButtonStyle.value.xmlEnumValue)
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun TextButton(
    text: String,
    style: OdsTextButton.Style,
    leadingIcon: Boolean,
    enabled: Boolean,
    fullScreenWidth: Boolean,
    invertedTheme: Boolean = false
) {
    val context = LocalContext.current
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
                    icon = if (leadingIcon) OdsButton.Icon(painterResource(id = iconId)) else null,
                    text = text,
                    onClick = {},
                    enabled = enabled,
                    style = style,
                )
            }, xml = {
                this.text = text
                this.icon = if (leadingIcon) AppCompatResources.getDrawable(context, iconId) else null
                this.enabled = enabled
                this.style = style
                this.invertedTheme = invertedTheme
                root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val width = if (fullScreenWidth) FrameLayout.LayoutParams.MATCH_PARENT else FrameLayout.LayoutParams.WRAP_CONTENT
                odsTextButton.layoutParams = FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.WRAP_CONTENT)
            }
        )
    }
}