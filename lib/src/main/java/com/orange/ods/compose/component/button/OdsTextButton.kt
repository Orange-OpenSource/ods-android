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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.LocalRippleTheme
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
import com.orange.ods.compose.theme.OdsDarkRippleTheme
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsLightRippleTheme
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsRippleTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsColors

/**
 * Specifying an [OdsTextButtonStyle] allow to display a button with specific colors.
 */
enum class OdsTextButtonStyle {
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
 * @param icon Painter of the icon. If `null`, no icon will be displayed.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable.
 * @param style Controls the style of the button. By default the `onSurface` color is used.
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComponentApi
fun OdsTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    enabled: Boolean = true,
    style: OdsTextButtonStyle = OdsTextButtonStyle.Default,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides when (style) {
            OdsTextButtonStyle.Primary -> OdsPrimaryRippleTheme
            OdsTextButtonStyle.Default -> when (displaySurface) {
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
                contentColor = OdsTheme.colors.buttonTextColor(displaySurface, style),
                disabledContentColor = OdsTheme.colors.buttonTextDisabledColor(displaySurface)
            )
        ) {
            icon?.let { ButtonIcon(it) }
            Text(text = text.uppercase(), style = OdsTheme.typography.button)
        }
    }
}

@Composable
private fun OdsColors.buttonTextColor(displaySurface: OdsDisplaySurface, style: OdsTextButtonStyle) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> when (style) {
            OdsTextButtonStyle.Primary -> OdsTheme.colors.primary
            OdsTextButtonStyle.Default -> OdsTheme.colors.onSurface
        }
        OdsDisplaySurface.Dark -> when (style) {
            OdsTextButtonStyle.Primary -> OdsTheme.colors.primary
            OdsTextButtonStyle.Default -> OdsTheme.colors.onSurface
        }
        OdsDisplaySurface.Light -> when (style) {
            OdsTextButtonStyle.Primary -> OdsTheme.colors.primary
            OdsTextButtonStyle.Default -> OdsTheme.colors.onSurface
        }
    }

@Composable
private fun OdsColors.buttonTextDisabledColor(displaySurface: OdsDisplaySurface) =
    buttonTextColor(displaySurface = displaySurface, style = OdsTextButtonStyle.Default).copy(alpha = ContentAlpha.disabled)

@Composable
private fun PreviewOdsTextButton(style: OdsTextButtonStyle) = Preview {
    OdsTextButton(text = "Text", onClick = {}, style = style)
}

@Preview(
    name = "OdsTextButton - Light",
    widthDp = 200
)
@Composable
private fun PreviewOdsTextButtonLight(@PreviewParameter(OdsTextButtonPreviewParameterProvider::class) style: OdsTextButtonStyle) {
    PreviewOdsTextButton(style)
}

@Preview(
    name = "OdsTextButton - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 200
)
@Composable
private fun PreviewOdsTextButtonDark(@PreviewParameter(OdsTextButtonPreviewParameterProvider::class) style: OdsTextButtonStyle) {
    PreviewOdsTextButton(style)
}

internal class OdsTextButtonPreviewParameterProvider : EnumPreviewParameterProvider(OdsTextButtonStyle::class)
