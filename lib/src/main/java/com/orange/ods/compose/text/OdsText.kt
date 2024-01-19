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
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable

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
    Text(
        text = if (OdsTheme.typography.isAllCapsTextStyle(style.value)) text.uppercase() else text,
        modifier = modifier,
        color = displaySurface.themeColors.onSurface.enable(enabled = enabled),
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = style.value
    )
}

enum class OdsTextStyle {

    HeadlineL,
    HeadlineS,
    TitleL,
    TitleM,
    TitleS,
    BodyL,
    BodyM,
    BodyS,
    LabelL,
    LabelS;

    internal val value: TextStyle
        @Composable
        get() {
            return when (this) {
                HeadlineL -> OdsTheme.typography.headlineL
                HeadlineS -> OdsTheme.typography.headlineS
                TitleL -> OdsTheme.typography.titleL
                TitleM -> OdsTheme.typography.titleM
                TitleS -> OdsTheme.typography.titleS
                BodyL -> OdsTheme.typography.bodyL
                BodyM -> OdsTheme.typography.bodyM
                BodyS -> OdsTheme.typography.bodyS
                LabelL -> OdsTheme.typography.labelL
                LabelS -> OdsTheme.typography.labelS
            }
        }
}

