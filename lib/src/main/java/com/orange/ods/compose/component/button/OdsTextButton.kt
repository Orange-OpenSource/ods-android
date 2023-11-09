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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.EnumPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable
import com.orange.ods.theme.colors.OdsColors

/**
 * Specifying an [OdsTextButtonStyle] allow to display a button with specific colors.
 */
enum class OdsTextButtonStyle {
    Default, Primary;

    companion object
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Text buttons are typically used for less-pronounced actions, including those located in dialogs
 * and cards. In cards, text buttons help maintain an emphasis on card content.
 *
 * @param text Text displayed into the button.
 * @param onClick Callback invoked on button click.
 * @param modifier [Modifier] applied to the button.
 * @param icon Icon displayed in the button before the text.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be clickable.
 * @param style Style applied to the button. By default `onSurface` color is used for text color. Use [OdsTextButtonStyle.Primary] for an highlighted
 * text color.
 * @param displaySurface [OdsDisplaySurface] applied to the button. It allows to force the button display on light or dark surface. By default,
 * the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: OdsButtonIconBuilder? = null,
    enabled: Boolean = true,
    style: OdsTextButtonStyle = OdsTextButtonStyle.Default,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    OdsTextButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        icon = icon,
        enabled = enabled,
        maxLines = Int.MAX_VALUE,
        overflow = TextOverflow.Clip,
        style = style,
        displaySurface = displaySurface
    )
}

@Composable
internal fun OdsTextButton(
    text: String,
    onClick: () -> Unit,
    maxLines: Int,
    overflow: TextOverflow,
    modifier: Modifier = Modifier,
    icon: OdsButtonIconBuilder? = null,
    enabled: Boolean = true,
    style: OdsTextButtonStyle = OdsTextButtonStyle.Default,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides when (style) {
            OdsTextButtonStyle.Primary -> OdsPrimaryRippleTheme
            OdsTextButtonStyle.Default -> displaySurface.rippleTheme
        }
    ) {
        TextButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            shape = OdsTheme.shapes.small,
            colors = ButtonDefaults.textButtonColors(
                contentColor = OdsTheme.colors.buttonTextColor(displaySurface, style),
                disabledContentColor = OdsTheme.colors.buttonTextDisabledColor(displaySurface)
            )
        ) {
            icon?.Content()
            Text(
                text = text.uppercase(), style = OdsTheme.typography.button, maxLines = maxLines,
                overflow = overflow
            )
        }
    }
}

@Composable
private fun OdsColors.buttonTextColor(displaySurface: OdsDisplaySurface, style: OdsTextButtonStyle) =
    when (style) {
        OdsTextButtonStyle.Primary -> displaySurface.themeColors.primary
        OdsTextButtonStyle.Default -> displaySurface.themeColors.onSurface
    }

@Composable
private fun OdsColors.buttonTextDisabledColor(displaySurface: OdsDisplaySurface) =
    buttonTextColor(displaySurface = displaySurface, style = OdsTextButtonStyle.Default).enable(enabled = false)

@UiModePreviews.Button
@Composable
private fun PreviewOdsTextButton(@PreviewParameter(OdsTextButtonPreviewParameterProvider::class) style: OdsTextButtonStyle) = Preview {
    OdsTextButton(text = "Text", maxLines = 1, overflow = TextOverflow.Clip, onClick = {}, style = style)
}

private class OdsTextButtonPreviewParameterProvider : EnumPreviewParameterProvider(OdsTextButtonStyle::class.java)
