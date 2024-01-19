/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
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
    val textStyle = textStyle(style = style)
    OdsText(
        text = styledText(text, textStyle),
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
        text = if (OdsTheme.typography.isAllCapsTextStyle(textStyle)) text.uppercase() else text,
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
