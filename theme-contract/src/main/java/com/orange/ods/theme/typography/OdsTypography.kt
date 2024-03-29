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

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * ODS typography system.
 *
 * The ODS typography system can help you create an ODS typography theme that reflects your brand or style.
 * By default, the ODS typography is built with Orange values but you can override these values in your theme as you want.
 * The [fontFamily] applied to the typography text styles can be changed and you can define text styles you want to be in capitals by adding it into
 * the [allCapsTextStyles] list.
 */
class OdsTypography(
    private val fontFamily: FontFamily = FontFamily.Default,
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
        this.headlineL = headlineL.withDefaultFontFamily(fontFamily)
        this.headlineS = headlineS.withDefaultFontFamily(fontFamily)
        this.titleL = titleL.withDefaultFontFamily(fontFamily)
        this.titleM = titleM.withDefaultFontFamily(fontFamily)
        this.titleS = titleS.withDefaultFontFamily(fontFamily)
        this.bodyL = bodyL.withDefaultFontFamily(fontFamily)
        this.bodyM = bodyM.withDefaultFontFamily(fontFamily)
        this.bodyS = bodyS.withDefaultFontFamily(fontFamily)
        this.labelL = labelL.withDefaultFontFamily(fontFamily)
        this.labelS = labelS.withDefaultFontFamily(fontFamily)
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

    /** Corresponding Material 2 Typography */
    val materialTypography
        get() = Typography(
            defaultFontFamily = fontFamily,
            h1 = headlineL,
            h2 = headlineL,
            h3 = headlineL,
            h4 = headlineL,
            h5 = headlineS,
            h6 = titleL,
            subtitle1 = titleM,
            subtitle2 = titleS,
            body1 = bodyL,
            body2 = bodyM,
            button = labelL,
            caption = bodyS,
            overline = labelS
        )
}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}

enum class OdsTextStyle {
    HeadlineL, HeadlineS, TitleL, TitleM, TitleS, BodyL, BodyM, BodyS, LabelL, LabelS
}
