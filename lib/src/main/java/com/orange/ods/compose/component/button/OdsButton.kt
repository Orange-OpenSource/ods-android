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

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.theme.Black900
import com.orange.ods.compose.theme.Negative200
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.Positive100
import com.orange.ods.compose.theme.White100
import com.orange.ods.compose.theme.odsDarkThemeColors
import com.orange.ods.compose.theme.odsLightThemeColors

/**
 * Specifying an [OdsButtonContainedStyle] allow to display a button with specific colors.
 */
enum class OdsButtonContainedStyle {
    Default, Primary, FunctionalPositive, FunctionalNegative;

    @Composable
    internal fun getColors(displaySurface: OdsDisplaySurface): ButtonColors {
        return when (this) {
            Default -> odsDefaultButtonColors(displaySurface)
            Primary -> odsPrimaryButtonColors(displaySurface)
            FunctionalPositive -> odsPositiveButtonColors(displaySurface)
            FunctionalNegative -> odsNegativeButtonColors(displaySurface)
        }
    }
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
 * @param style Controls the style of the button. Use `OdsButtonStyle.Primary` for an highlighted button style. To get a green/red button
 * style on contained buttons, you can use `OdsButtonStyle.FunctionalPositive` or `OdsButtonStyle.FunctionalNegative` values.
 * @param displaySurface optional allow to force the button display on a dark or light
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
    style: OdsButtonContainedStyle = OdsButtonContainedStyle.Default,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(LocalRippleTheme provides OdsOnPrimaryRippleTheme) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier,
            interactionSource = remember { MutableInteractionSource() },
            elevation = null,
            shape = odsButtonShape,
            colors = style.getColors(displaySurface = displaySurface)
        ) {
            iconRes?.let { ButtonIcon(it) }
            Text(text.uppercase())
        }
    }
}

@Composable
private fun odsDefaultButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = when (displaySurface) {
        OdsDisplaySurface.Default -> MaterialTheme.colors.onSurface
        OdsDisplaySurface.Dark -> odsDarkThemeColors.onSurface
        OdsDisplaySurface.Light -> odsLightThemeColors.onSurface
    },
    contentColor = when (displaySurface) {
        OdsDisplaySurface.Default -> MaterialTheme.colors.surface
        OdsDisplaySurface.Dark -> odsDarkThemeColors.surface
        OdsDisplaySurface.Light -> odsLightThemeColors.surface
    },
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsPrimaryButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = when (displaySurface) {
        OdsDisplaySurface.Default -> MaterialTheme.colors.primary
        OdsDisplaySurface.Dark -> odsDarkThemeColors.primary
        OdsDisplaySurface.Light -> odsLightThemeColors.primary
    },
    contentColor = when (displaySurface) {
        OdsDisplaySurface.Default -> MaterialTheme.colors.onPrimary
        OdsDisplaySurface.Dark -> odsDarkThemeColors.onPrimary
        OdsDisplaySurface.Light -> odsLightThemeColors.onPrimary
    },
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsPositiveButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = Positive100,
    contentColor = Black900,
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsNegativeButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = Negative200,
    contentColor = White100,
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun disabledButtonColors(displaySurface: OdsDisplaySurface) = when (displaySurface) {
    OdsDisplaySurface.Default -> MaterialTheme.colors.onSurface
    OdsDisplaySurface.Dark -> odsDarkThemeColors.onSurface
    OdsDisplaySurface.Light -> odsLightThemeColors.onSurface
}

@Composable
private fun disabledButtonBackgroundColor(displaySurface: OdsDisplaySurface) = disabledButtonColors(displaySurface = displaySurface).copy(alpha = 0.12f)

@Composable
private fun disabledButtonContentColor(displaySurface: OdsDisplaySurface) =
    disabledButtonColors(displaySurface = displaySurface).copy(alpha = ContentAlpha.disabled)

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

@Composable
private fun PreviewOdsButton() = Preview {
    OdsButton(text = "Text", onClick = {}, style = OdsButtonContainedStyle.Default)
}

@Preview(name = "OdsButton - Light")
@Composable
private fun PreviewOdsButtonLight() = PreviewOdsButton()

@Preview(
    name = "OdsButton - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsButtonDark() = PreviewOdsButton()
