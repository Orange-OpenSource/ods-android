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

import androidx.compose.material3.Typography
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.orange.ods.theme.OdsThemeConfigurationItem
import com.orange.ods.theme.OdsToken
import com.orange.ods.theme.tokens.OdsTypographyTokens

/**
 * ODS typography system.
 *
 * The ODS typography system can help you create an ODS typography theme that reflects your brand or style.
 * By default, the ODS typography is built with Orange values but you can override these values in your theme as you want.
 * The [defaultFontFamily] applied to the typography text styles can be changed.
 */
class OdsTypography(
    private val defaultFontFamily: FontFamily = FontFamily.Default,
    headlineLarge: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.25.sp
        )
    ),
    headlineSmall: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        )
    ),
    titleLarge: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        )
    ),
    titleMedium: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        )
    ),
    titleSmall: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.1.sp
        )
    ),
    bodyLarge: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.5.sp
        )
    ),
    bodyMedium: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        )
    ),
    bodySmall: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        )
    ),
    labelLarge: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.25.sp
        ),
        isAllCaps = true
    ),
    labelSmall: OdsTextStyle = OdsTextStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.5.sp
        )
    )
) : OdsTypographyCatalog<OdsTextStyle>, OdsThemeConfigurationItem.TokenProvider<OdsTypographyCatalog<OdsToken<OdsTextStyle>>> {

    override val headlineLarge = headlineLarge.withDefaultFontFamily(defaultFontFamily)
    override val headlineSmall = headlineSmall.withDefaultFontFamily(defaultFontFamily)
    override val titleLarge = titleLarge.withDefaultFontFamily(defaultFontFamily)
    override val titleMedium = titleMedium.withDefaultFontFamily(defaultFontFamily)
    override val titleSmall = titleSmall.withDefaultFontFamily(defaultFontFamily)
    override val bodyLarge = bodyLarge.withDefaultFontFamily(defaultFontFamily)
    override val bodyMedium = bodyMedium.withDefaultFontFamily(defaultFontFamily)
    override val bodySmall = bodySmall.withDefaultFontFamily(defaultFontFamily)
    override val labelLarge = labelLarge.withDefaultFontFamily(defaultFontFamily)
    override val labelSmall = labelSmall.withDefaultFontFamily(defaultFontFamily)

    override val tokens = object : OdsTypographyCatalog<OdsToken<OdsTextStyle>> {
        override val headlineLarge = OdsToken(OdsToken.TextStyle.HeadlineLarge, this@OdsTypography.headlineLarge)
        override val headlineSmall = OdsToken(OdsToken.TextStyle.HeadlineSmall, this@OdsTypography.headlineSmall)
        override val titleLarge = OdsToken(OdsToken.TextStyle.TitleLarge, this@OdsTypography.titleLarge)
        override val titleMedium = OdsToken(OdsToken.TextStyle.TitleMedium, this@OdsTypography.titleMedium)
        override val titleSmall = OdsToken(OdsToken.TextStyle.TitleSmall, this@OdsTypography.titleSmall)
        override val bodyLarge = OdsToken(OdsToken.TextStyle.BodyLarge, this@OdsTypography.bodyLarge)
        override val bodyMedium = OdsToken(OdsToken.TextStyle.BodyMedium, this@OdsTypography.bodyMedium)
        override val bodySmall = OdsToken(OdsToken.TextStyle.BodySmall, this@OdsTypography.bodySmall)
        override val labelLarge = OdsToken(OdsToken.TextStyle.LabelLarge, this@OdsTypography.labelLarge)
        override val labelSmall = OdsToken(OdsToken.TextStyle.LabelSmall, this@OdsTypography.labelSmall)
    }

    /** Corresponding Material 3 Typography */
    val materialTypography
        get() = Typography(
            displayLarge = headlineLarge.textStyle,
            displayMedium = headlineLarge.textStyle,
            displaySmall = headlineLarge.textStyle,
            headlineLarge = headlineLarge.textStyle,
            headlineMedium = headlineSmall.textStyle, //TODO headlineMedium does not exist in the DSM
            headlineSmall = headlineSmall.textStyle,
            titleLarge = titleLarge.textStyle,
            titleMedium = titleMedium.textStyle,
            titleSmall = titleSmall.textStyle,
            bodyLarge = bodyLarge.textStyle,
            bodyMedium = bodyMedium.textStyle,
            bodySmall = bodySmall.textStyle,
            labelLarge = labelLarge.textStyle,
            labelMedium = labelSmall.textStyle, //TODO labelMedium does not exist in the DSM
            labelSmall = labelSmall.textStyle,
        )

    private fun OdsTextStyle.withDefaultFontFamily(default: FontFamily): OdsTextStyle {
        return if (textStyle.fontFamily != null) this else copy(textStyle = textStyle.copy(fontFamily = default))
    }
}

interface OdsTypographyCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val headlineLarge: T
    val headlineSmall: T
    val titleLarge: T
    val titleMedium: T
    val titleSmall: T
    val bodyLarge: T
    val bodyMedium: T
    val bodySmall: T
    val labelLarge: T
    val labelSmall: T
}

@Stable
internal fun OdsTypography.fromToken(value: OdsTypographyTokens): OdsTextStyle {
    return when (value) {
        OdsTypographyTokens.BodyLarge -> bodyLarge
        OdsTypographyTokens.BodyMedium -> bodyMedium
        OdsTypographyTokens.BodySmall -> bodySmall
        OdsTypographyTokens.HeadlineLarge -> headlineLarge
        OdsTypographyTokens.HeadlineSmall -> headlineSmall
        OdsTypographyTokens.LabelLarge -> labelLarge
        OdsTypographyTokens.LabelSmall -> labelSmall
        OdsTypographyTokens.TitleLarge -> titleLarge
        OdsTypographyTokens.TitleMedium -> titleMedium
        OdsTypographyTokens.TitleSmall -> titleSmall
    }
}