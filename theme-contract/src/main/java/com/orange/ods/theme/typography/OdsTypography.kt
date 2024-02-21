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

package com.orange.ods.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class OdsTypography(
    defaultFontFamily: FontFamily = FontFamily.Default,
    headlineL: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.25.sp
    ),
    headlineS: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleL: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleM: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleS: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.1.sp
    ),
    bodyL: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    bodyM: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodyS: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelL: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.25.sp,
    ),
    labelS: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.5.sp
    ),
    allCapsTextStyles: List<OdsTextStyle> = listOf(OdsTextStyle.LabelL)
) {

    val headlineL: TextStyle
    val headlineS: TextStyle
    val titleL: TextStyle
    val titleM: TextStyle
    val titleS: TextStyle
    val bodyL: TextStyle
    val bodyM: TextStyle
    val bodyS: TextStyle
    val labelL: TextStyle
    val labelS: TextStyle
    private val allCapsTextStyles: List<TextStyle>

    init {
        this.headlineL = headlineL.withDefaultFontFamily(defaultFontFamily)
        this.headlineS = headlineS.withDefaultFontFamily(defaultFontFamily)
        this.titleL = titleL.withDefaultFontFamily(defaultFontFamily)
        this.titleM = titleM.withDefaultFontFamily(defaultFontFamily)
        this.titleS = titleS.withDefaultFontFamily(defaultFontFamily)
        this.bodyL = bodyL.withDefaultFontFamily(defaultFontFamily)
        this.bodyM = bodyM.withDefaultFontFamily(defaultFontFamily)
        this.bodyS = bodyS.withDefaultFontFamily(defaultFontFamily)
        this.labelL = labelL.withDefaultFontFamily(defaultFontFamily)
        this.labelS = labelS.withDefaultFontFamily(defaultFontFamily)
        this.allCapsTextStyles = allCapsTextStyles.map { textStyle ->
            when (textStyle) {
                OdsTextStyle.HeadlineL -> this.headlineL
                OdsTextStyle.HeadlineS -> this.headlineS
                OdsTextStyle.TitleL -> this.titleL
                OdsTextStyle.TitleM -> this.titleM
                OdsTextStyle.TitleS -> this.titleS
                OdsTextStyle.BodyL -> this.bodyL
                OdsTextStyle.BodyM -> this.bodyM
                OdsTextStyle.BodyS -> this.bodyS
                OdsTextStyle.LabelL -> this.labelL
                OdsTextStyle.LabelS -> this.labelS
            }
        }
    }

    fun isAllCapsTextStyle(textStyle: TextStyle) = allCapsTextStyles.contains(textStyle)
}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}

enum class OdsTextStyle {
    HeadlineL, HeadlineS, TitleL, TitleM, TitleS, BodyL, BodyM, BodyS, LabelL, LabelS
}
