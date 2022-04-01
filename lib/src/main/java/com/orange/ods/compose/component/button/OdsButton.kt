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
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.orange.ods.compose.theme.DarkSurfaceDefault
import com.orange.ods.compose.theme.Grey800

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
 * @param interactionSource the [MutableInteractionSource] representing the stream of
 * [Interaction]s for this Button. You can create and pass in your own remembered
 * [MutableInteractionSource] if you want to observe [Interaction]s and customize the
 * appearance / behavior of this Button in different [Interaction]s.
 */
@Composable
fun OdsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    CompositionLocalProvider(LocalRippleTheme provides OdsOnPrimaryRippleTheme) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
            interactionSource = interactionSource,
            elevation = null,
            shape = odsButtonShape,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = MaterialTheme.colors.buttonDisabledBackgroundColor(),
                disabledContentColor = MaterialTheme.colors.buttonDisabledContentColor()
            )
        ) {
            iconRes?.let { ButtonIcon(it) }
            Text(text.uppercase())
        }
    }
}

@Composable
private fun Colors.buttonDisabledBackgroundColor(): Color {
    return if (isLight) MaterialTheme.colors.primary.copy(alpha = ContentAlpha.disabled) else Grey800
}

@Composable
private fun Colors.buttonDisabledContentColor(): Color {
    return if (isLight) MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled) else DarkSurfaceDefault
}