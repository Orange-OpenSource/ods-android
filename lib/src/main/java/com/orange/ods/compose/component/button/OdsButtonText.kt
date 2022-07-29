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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.theme.OdsDarkRippleTheme
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsLightRippleTheme
import com.orange.ods.compose.theme.OdsMaterialTheme
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsRippleTheme
import com.orange.ods.compose.theme.odsDarkThemeColors
import com.orange.ods.compose.theme.odsLightThemeColors

/**
 * Specifying an [OdsButtonTextStyle] allow to display a button with specific colors.
 */
enum class OdsButtonTextStyle {
    Default, Primary
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Text buttons are typically used for less-pronounced actions, including those located in dialogs
 * and cards. In cards, text buttons help maintain an emphasis on card content.
 *
 * @param text Text displayed in the button
 * @param onClick Will be called when the user clicks the button
 * @param modifier Modifier to be applied to the button
 * @param iconRes Drawable resource of the icon. If `null`, no icon will be displayed.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable.
 * @param style Controls the style of the button. By default the `onSurface` color is used.
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
fun OdsButtonText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int? = null,
    enabled: Boolean = true,
    style: OdsButtonTextStyle = OdsButtonTextStyle.Default,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides when (style) {
            OdsButtonTextStyle.Primary -> OdsPrimaryRippleTheme
            OdsButtonTextStyle.Default -> when (displaySurface) {
                OdsDisplaySurface.Default -> OdsRippleTheme
                OdsDisplaySurface.Light -> OdsLightRippleTheme
                OdsDisplaySurface.Dark -> OdsDarkRippleTheme
            }
        }
    ) {
        TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            shape = odsButtonShape,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.buttonTextColor(displaySurface, style),
                disabledContentColor = MaterialTheme.colors.buttonTextDisabledColor(displaySurface)
            )
        ) {
            iconRes?.let { ButtonIcon(it) }
            Text(text.uppercase())
        }
    }
}

@Composable
private fun Colors.buttonTextColor(displaySurface: OdsDisplaySurface, style: OdsButtonTextStyle) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> when (style) {
            OdsButtonTextStyle.Primary -> MaterialTheme.colors.primary
            OdsButtonTextStyle.Default -> MaterialTheme.colors.onSurface
        }
        OdsDisplaySurface.Dark -> when (style) {
            OdsButtonTextStyle.Primary -> odsDarkThemeColors.primary
            OdsButtonTextStyle.Default -> odsDarkThemeColors.onSurface
        }
        OdsDisplaySurface.Light -> when (style) {
            OdsButtonTextStyle.Primary -> odsLightThemeColors.primary
            OdsButtonTextStyle.Default -> odsLightThemeColors.onSurface
        }
    }

@Composable
private fun Colors.buttonTextDisabledColor(displaySurface: OdsDisplaySurface) =
    buttonTextColor(displaySurface = displaySurface, style = OdsButtonTextStyle.Default).copy(alpha = ContentAlpha.disabled)

@Composable
private fun PreviewOdsButtonText() = OdsMaterialTheme {
    OdsButtonText(text = "Text", onClick = {}, style = OdsButtonTextStyle.Primary)
}

@Preview(name = "OdsButtonText - Light")
@Composable
private fun PreviewOdsButtonTextLight() = PreviewOdsButtonText()

@Preview(
    name = "OdsButtonText - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsButtonTextDark() = PreviewOdsButtonText()
