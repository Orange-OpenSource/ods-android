/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDarkRippleTheme
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsLightRippleTheme
import com.orange.ods.compose.theme.OdsRippleTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsColors
import com.orange.ods.utilities.extension.disabled

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren't
 * the primary action in an app.
 *
 * @param text Text displayed in the button
 * @param onClick Will be called when the user clicks the button
 * @param modifier Modifier to be applied to the button
 * @param icon Painter of the icon. If `null`, no icon will be displayed.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComponentApi
fun OdsOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides when (displaySurface) {
            OdsDisplaySurface.Default -> OdsRippleTheme
            OdsDisplaySurface.Light -> OdsLightRippleTheme
            OdsDisplaySurface.Dark -> OdsDarkRippleTheme
        }
    ) {
        OutlinedButton(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            shape = OdsTheme.shapes.small,
            border = BorderStroke(
                ButtonDefaults.OutlinedBorderSize,
                if (enabled) {
                    OdsTheme.colors.buttonOutlinedColor(displaySurface)
                } else {
                    OdsTheme.colors.buttonOutlinedDisabledColor(displaySurface)
                }
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.Transparent,
                contentColor = OdsTheme.colors.buttonOutlinedColor(displaySurface),
                disabledContentColor = OdsTheme.colors.buttonOutlinedDisabledColor(displaySurface)
            )
        ) {
            icon?.let { ButtonIcon(it) }
            Text(text = text.uppercase(), style = OdsTheme.typography.button)
        }
    }
}

@Composable
private fun OdsColors.buttonOutlinedColor(displaySurface: OdsDisplaySurface) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.onSurface
        OdsDisplaySurface.Dark -> OdsTheme.darkThemeColors.onSurface
        OdsDisplaySurface.Light -> OdsTheme.lightThemeColors.onSurface
    }

@Composable
private fun OdsColors.buttonOutlinedDisabledColor(displaySurface: OdsDisplaySurface) = buttonOutlinedColor(displaySurface).disabled

@UiModePreviews.Button
@Composable
private fun PreviewOdsOutlinedButton() = Preview {
    OdsOutlinedButton(text = "Text", onClick = {})
}
