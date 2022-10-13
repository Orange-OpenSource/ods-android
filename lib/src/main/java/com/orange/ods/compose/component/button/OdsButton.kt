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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.EnumPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme

/**
 * Specifying an [OdsButtonStyle] allow to display a button with specific colors.
 */
enum class OdsButtonStyle {
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
 * @param icon Painter of the icon. If `null`, no icon will be displayed.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable
 * @param style Controls the style of the button. Use `OdsButtonStyle.Primary` for an highlighted button style. To get a green/red button
 * style on contained buttons, you can use `OdsButtonStyle.FunctionalPositive` or `OdsButtonStyle.FunctionalNegative` values.
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */

@Composable
@OdsComponentApi
fun OdsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    enabled: Boolean = true,
    style: OdsButtonStyle = OdsButtonStyle.Default,
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
            icon?.let { ButtonIcon(it) }
            Text(text = text.uppercase(), style = OdsTheme.typography.button)
        }
    }
}

@Composable
private fun odsDefaultButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.onSurface
        OdsDisplaySurface.Dark -> OdsTheme.colors.onSurface
        OdsDisplaySurface.Light -> OdsTheme.colors.onSurface
    },
    contentColor = when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.surface
        OdsDisplaySurface.Dark -> OdsTheme.colors.surface
        OdsDisplaySurface.Light -> OdsTheme.colors.surface
    },
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsPrimaryButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.primary
        OdsDisplaySurface.Dark -> OdsTheme.colors.primary
        OdsDisplaySurface.Light -> OdsTheme.colors.primary
    },
    contentColor = when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.onPrimary
        OdsDisplaySurface.Dark -> OdsTheme.colors.onPrimary
        OdsDisplaySurface.Light -> OdsTheme.colors.onPrimary
    },
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsPositiveButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = OdsTheme.colors.functionalPositive,
    contentColor = OdsTheme.colors.onFunctionalPositive,
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsNegativeButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = OdsTheme.colors.functionalNegative,
    contentColor = OdsTheme.colors.onFunctionalNegative,
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun positiveBackgroundColor(displaySurface: OdsDisplaySurface) = when (displaySurface) {
    OdsDisplaySurface.Default -> OdsTheme.colors.onSurface
    OdsDisplaySurface.Dark -> OdsTheme.colors.onSurface
    OdsDisplaySurface.Light -> OdsTheme.colors.onSurface
}

@Composable
private fun disabledButtonColors(displaySurface: OdsDisplaySurface) = when (displaySurface) {
    OdsDisplaySurface.Default -> OdsTheme.colors.onSurface
    OdsDisplaySurface.Dark -> OdsTheme.colors.onSurface
    OdsDisplaySurface.Light -> OdsTheme.colors.onSurface
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
        contentColor = OdsTheme.colors.onPrimary,
        lightTheme = true // allow to force ripple used on primary color when in dark mode
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        OdsTheme.colors.primary,
        lightTheme = !isSystemInDarkTheme()
    )
}


@Composable
private fun PreviewOdsButton(style: OdsButtonStyle) = Preview {
    OdsButton(text = "Text", onClick = {}, style = style)
}

@Composable
@Preview(
    name = "OdsButton - Light",
    widthDp = 200
)
private fun PreviewOdsButtonLight(@PreviewParameter(OdsButtonPreviewParameterProvider::class) style: OdsButtonStyle) {
    PreviewOdsButton(style)
}

@Composable
@Preview(
    name = "OdsButton - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 200
)
private fun PreviewOdsButtonDark(@PreviewParameter(OdsButtonPreviewParameterProvider::class) style: OdsButtonStyle) {
    PreviewOdsButton(style)
}

internal class OdsButtonPreviewParameterProvider : EnumPreviewParameterProvider(OdsButtonStyle::class)
