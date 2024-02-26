/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.compose.component.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.EnumPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.typography.OdsTextStyle

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
 * @param style Style applied to the button. By default `onSurface` color is used for text color. Use [OdsTextButton.Style.Primary] for an highlighted
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
    icon: OdsButton.Icon? = null,
    enabled: Boolean = true,
    style: OdsTextButton.Style = OdsTextButton.Style.Default,
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
    icon: OdsButton.Icon? = null,
    enabled: Boolean = true,
    style: OdsTextButton.Style = OdsTextButton.Style.Default,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides when (style) {
            OdsTextButton.Style.Primary -> OdsPrimaryRippleTheme
            OdsTextButton.Style.Default -> displaySurface.rippleTheme
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
            OdsText(
                text = text,
                style = OdsTextStyle.LabelL,
                maxLines = maxLines,
                // Set color to Unspecified otherwise the colors parameter of TextButton is overridden
                // by the default value of the displaySurface parameter of OdsText
                color = Color.Unspecified,
                overflow = overflow
            )
        }
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.button.OdsTextButton].
 */
object OdsTextButton {
    /**
     * Specifying an [OdsTextButton.Style] allow to display a button with specific colors.
     */
    enum class Style {
        Default, Primary;

        companion object
    }
}

@Composable
private fun OdsColors.buttonTextColor(displaySurface: OdsDisplaySurface, style: OdsTextButton.Style) =
    when (style) {
        OdsTextButton.Style.Primary -> displaySurface.themeColors.primary
        OdsTextButton.Style.Default -> displaySurface.themeColors.onSurface
    }

@Composable
private fun OdsColors.buttonTextDisabledColor(displaySurface: OdsDisplaySurface) =
    buttonTextColor(displaySurface = displaySurface, style = OdsTextButton.Style.Default).enable(enabled = false)

@UiModePreviews.Button
@Composable
private fun PreviewOdsTextButton(@PreviewParameter(OdsTextButtonPreviewParameterProvider::class) style: OdsTextButton.Style) = Preview {
    OdsTextButton(text = "Text", maxLines = 1, overflow = TextOverflow.Clip, onClick = {}, style = style)
}

private class OdsTextButtonPreviewParameterProvider : EnumPreviewParameterProvider(OdsTextButton.Style::class.java)
