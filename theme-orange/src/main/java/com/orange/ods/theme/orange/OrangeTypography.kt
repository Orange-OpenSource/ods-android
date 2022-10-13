/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.orange

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val OrangeTypography = Typography(
    defaultFontFamily = Roboto,
    h1 = TextStyle(
        fontSize = 96.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold
    ),
    h4 = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.25).sp
    ),
    h5 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    // title
    h6 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.15).sp
    ),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (0.15).sp
    ),
    subtitle2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.1).sp
    ),
    body1 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.5).sp
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.25).sp
    ),
    button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (1.25).sp
    ),
    caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = (0.4).sp
    ),
    overline = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (1.5).sp
    )
)