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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.compose.theme.odsDarkThemeColors
import com.orange.ods.compose.theme.odsLightThemeColors

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Contained buttons are high-emphasis, distinguished by their use of elevation and fill. They
 * contain actions that are primary to your app.
 *
 * @param text Text displayed in the button
 * @param onClick Will be called when the user clicks the button
 * @param modifier Modifier to be applied to the button
 * @param iconRes Drawable resource of the icon. If `null`, no icon will be displayed.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable
 * @param hasPrimaryColor Controls the style of the button. When `true`, the button has primary background color, otherwise it has onSurface background color.
 * @param displayAppearance optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
fun OdsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int? = null,
    enabled: Boolean = true,
    hasPrimaryColor: Boolean = false,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) {
    CompositionLocalProvider(LocalRippleTheme provides OdsOnPrimaryRippleTheme) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            elevation = null,
            shape = odsButtonShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.buttonBackgroundColor(displayAppearance, hasPrimaryColor),
                contentColor = MaterialTheme.colors.buttonContentColor(displayAppearance, hasPrimaryColor),
                disabledBackgroundColor = MaterialTheme.colors.buttonDisabledColor(displayAppearance).copy(alpha = 0.12f),
                disabledContentColor = MaterialTheme.colors.buttonDisabledColor(displayAppearance).copy(alpha = ContentAlpha.disabled)
            )
        ) {
            iconRes?.let { ButtonIcon(it) }
            Text(text.uppercase())
        }
    }
}

@Composable
private fun Colors.buttonBackgroundColor(displayAppearance: OdsDisplayAppearance, hasPrimaryColor: Boolean) =
    when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> if (hasPrimaryColor) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
        OdsDisplayAppearance.ON_DARK -> if (hasPrimaryColor) odsDarkThemeColors.primary else odsDarkThemeColors.onSurface
        OdsDisplayAppearance.ON_LIGHT -> if (hasPrimaryColor) odsLightThemeColors.primary else odsLightThemeColors.onSurface
    }

@Composable
private fun Colors.buttonContentColor(displayAppearance: OdsDisplayAppearance, hasPrimaryColor: Boolean) =
    when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> if (hasPrimaryColor) MaterialTheme.colors.onPrimary else MaterialTheme.colors.surface
        OdsDisplayAppearance.ON_DARK -> if (hasPrimaryColor) odsDarkThemeColors.onPrimary else odsDarkThemeColors.surface
        OdsDisplayAppearance.ON_LIGHT -> if (hasPrimaryColor) odsLightThemeColors.onPrimary else odsLightThemeColors.surface
    }

@Composable
private fun Colors.buttonDisabledColor(displayAppearance: OdsDisplayAppearance) =
    when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> MaterialTheme.colors.onSurface
        OdsDisplayAppearance.ON_DARK -> odsDarkThemeColors.onSurface
        OdsDisplayAppearance.ON_LIGHT -> odsLightThemeColors.onSurface
    }

/**
 * Ripple theme used on primary color.
 * Note: It is only used on contained buttons.
 */
private object OdsOnPrimaryRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = MaterialTheme.colors.onPrimary,
        lightTheme = true // allow to force ripple used on primary color when in dark mode
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        MaterialTheme.colors.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}
