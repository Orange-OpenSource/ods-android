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

package com.orange.ods.compose.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * High level element that displays text and provides semantics / accessibility information.
 *
 * @param text The text to be displayed.
 * @param style ODS style configuration for the text such as color, font, line height etc.
 * @param modifier [Modifier] to apply to this layout node.
 * @param displaySurface [OdsDisplaySurface] applied to the text. It allows to force the text display on light or dark surface.
 * By default, the appearance applied is based on the system night mode value.
 * @param enabled Modifies the text color so that the content looks enabled or disabled.
 * @param textAlign The alignment of the text within the lines of the paragraph.
 * See [TextStyle.textAlign].
 * @param overflow How visual overflow should be handled.
 * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the
 * text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
 * [overflow] and TextAlign may have unexpected effects.
 * @param maxLines An optional maximum number of lines for the text to span, wrapping if
 * necessary. If the text exceeds the given number of lines, it will be truncated according to
 * [overflow] and [softWrap]. It is required that 1 <= [minLines] <= [maxLines].
 * @param minLines The minimum height in terms of minimum number of visible lines. It is required
 * that 1 <= [minLines] <= [maxLines].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A
 * [TextLayoutResult] object that callback provides contains paragraph information, size of the
 * text, baselines and other details. The callback can be used to add additional decoration or
 * functionality to the text. For example, to draw selection around the text.
 */
@Composable
fun OdsText(
    text: String,
    style: OdsTextStyle,
    modifier: Modifier = Modifier,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default,
    enabled: Boolean = true,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    OdsText(
        text = text,
        modifier = modifier,
        color = displaySurface.themeColors.onSurface.enable(enabled = enabled),
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

@Composable
internal fun OdsText(
    text: String,
    style: OdsTextStyle,
    color: Color,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    val textStyle = textStyle(style = style)
    Text(
        text = styledText(text = text, textStyle = textStyle),
        modifier = modifier,
        color = color,
        fontWeight = fontWeight,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = textStyle
    )
}

@Composable
internal fun textStyle(style: OdsTextStyle): TextStyle {
    return with(OdsTheme.typography) {
        when (style) {
            OdsTextStyle.HeadlineL -> headlineL
            OdsTextStyle.HeadlineS -> headlineS
            OdsTextStyle.TitleL -> titleL
            OdsTextStyle.TitleM -> titleM
            OdsTextStyle.TitleS -> titleS
            OdsTextStyle.BodyL -> bodyL
            OdsTextStyle.BodyM -> bodyM
            OdsTextStyle.BodyS -> bodyS
            OdsTextStyle.LabelL -> labelL
            OdsTextStyle.LabelS -> labelS
        }
    }
}

@Composable
internal fun styledText(text: String, textStyle: TextStyle): String {
    return if (OdsTheme.typography.isAllCapsTextStyle(textStyle)) text.uppercase() else text
}
