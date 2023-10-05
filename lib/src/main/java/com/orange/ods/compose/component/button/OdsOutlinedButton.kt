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
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable
import com.orange.ods.theme.colors.OdsColors

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren't
 * the primary action in an app.
 *
 * @param text Text to be displayed into the button
 * @param onClick Callback to be called when the button is clicked
 * @param modifier [Modifier] to be applied to the button
 * @param icon Icon to be displayed in the button before the text
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be clickable
 * @param displaySurface [OdsDisplaySurface] to be applied to the button. It allows to force the button display on light or dark surface. By default, the
 * appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: OdsButtonIcon? = null,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides displaySurface.rippleTheme
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
            icon?.Content()
            Text(text = text.uppercase(), style = OdsTheme.typography.button)
        }
    }
}

@Composable
private fun OdsColors.buttonOutlinedColor(displaySurface: OdsDisplaySurface) = displaySurface.themeColors.onSurface

@Composable
private fun OdsColors.buttonOutlinedDisabledColor(displaySurface: OdsDisplaySurface) = buttonOutlinedColor(displaySurface).enable(enabled = false)

@UiModePreviews.Button
@Composable
private fun PreviewOdsOutlinedButton() = Preview {
    OdsOutlinedButton(text = "Text", onClick = {})
}
