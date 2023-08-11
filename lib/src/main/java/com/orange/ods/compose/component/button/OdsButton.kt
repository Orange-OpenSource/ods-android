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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.EnumPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable

/**
 * Specifying an [OdsButtonStyle] allow to display a button with specific colors.
 */
enum class OdsButtonStyle {
    Default, Primary, FunctionalPositive, FunctionalNegative;

    companion object

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
 * @param icon Icon displayed in the button before the text. If `null`, no icon will be displayed.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable
 * @param style Controls the style of the button. Use `OdsButtonStyle.Primary` for an highlighted button style. To get a green/red button
 * style on contained buttons, you can use `OdsButtonStyle.FunctionalPositive` or `OdsButtonStyle.FunctionalNegative` values.
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */

@Composable
@OdsComposable
fun OdsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: OdsButtonIcon? = null,
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
            shape = OdsTheme.shapes.small,
            colors = style.getColors(displaySurface = displaySurface)
        ) {
            icon?.Content(modifier = modifier.size(ButtonDefaults.IconSize))
            Text(text = text.uppercase(), style = OdsTheme.typography.button)
        }
    }
}

@Composable
private fun odsDefaultButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = displaySurface.themeColors.onSurface,
    contentColor = displaySurface.themeColors.surface,
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsPrimaryButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = displaySurface.themeColors.primary,
    contentColor = displaySurface.themeColors.onPrimary,
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsPositiveButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = OdsTheme.colors.functional.positive,
    contentColor = OdsTheme.colors.functional.onPositive,
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun odsNegativeButtonColors(displaySurface: OdsDisplaySurface) = ButtonDefaults.buttonColors(
    backgroundColor = OdsTheme.colors.functional.negative,
    contentColor = OdsTheme.colors.functional.onNegative,
    disabledBackgroundColor = disabledButtonBackgroundColor(displaySurface),
    disabledContentColor = disabledButtonContentColor(displaySurface),
)

@Composable
private fun disabledButtonColors(displaySurface: OdsDisplaySurface) = displaySurface.themeColors.onSurface

@Composable
private fun disabledButtonBackgroundColor(displaySurface: OdsDisplaySurface) = disabledButtonColors(displaySurface = displaySurface).copy(alpha = 0.12f)

@Composable
private fun disabledButtonContentColor(displaySurface: OdsDisplaySurface) =
    disabledButtonColors(displaySurface = displaySurface).enable(enabled = false)

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
private fun PreviewOdsButton(@PreviewParameter(OdsButtonPreviewParameterProvider::class) style: OdsButtonStyle) = Preview {
    OdsButton(text = "Text", onClick = {}, style = style)
}

private class OdsButtonPreviewParameterProvider : EnumPreviewParameterProvider(OdsButtonStyle::class)
