/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Button
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.EnumPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Contained buttons are high-emphasis, distinguished by their use of elevation and fill. They
 * contain actions that are primary to your app.
 *
 * @param text Text displayed into the button.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier [Modifier] applied to the button.
 * @param icon Icon displayed in the button before the text.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be clickable.
 * @param style Style applied to the button. Set it to [OdsButton.Style.Primary] for an highlighted button style or use.
 * [OdsButton.Style.FunctionalPositive]/[OdsButton.Style.FunctionalNegative] for a functional green/red button style.
 * @param displaySurface [OdsDisplaySurface] applied to the button. It allows to force the button display on light or dark surface.
 * By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: OdsButton.Icon? = null,
    enabled: Boolean = true,
    style: OdsButton.Style = OdsButton.Style.Default,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(LocalRippleTheme provides OdsOnPrimaryRippleTheme) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            elevation = null,
            shape = OdsTheme.shapes.small,
            colors = style.getColors(displaySurface = displaySurface)
        ) {
            icon?.Content()
            OdsText(
                text = text,
                style = OdsTextStyle.LabelL,
                // Set color to Unspecified otherwise the colors parameter of Button is overridden
                // by the default value of the displaySurface parameter of OdsText
                color = Color.Unspecified
            )
        }
    }
}

/**
 * Ripple theme used on primary color.
 * Note: It is only used on contained buttons.
 */
private object OdsOnPrimaryRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = OdsTheme.colors.onPrimary,
        lightTheme = true // allow to force ripple used on primary color when in dark mode
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        OdsTheme.colors.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}

@UiModePreviews.Button
@Composable
private fun PreviewOdsButton(@PreviewParameter(OdsButtonPreviewParameterProvider::class) style: OdsButton.Style) = Preview {
    OdsButton(text = "Text", onClick = {}, style = style)
}

private class OdsButtonPreviewParameterProvider : EnumPreviewParameterProvider(OdsButton.Style::class.java)
