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

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Core colors

val Orange100 = Color(0xffff7900)
val Orange200 = Color(0xfff16e00)
val White100 = Color(0xffffffff)
val Black900 = Color(0xff000000)
val ObsGrey700 = Color(0xff595959)

// Supporting colors

val Blue100 = Color(0xffB5E8F7)
val Blue200 = Color(0xff4BB4E6)
val Blue300 = Color(0xff085EBD)

val Yellow100 = Color(0xffFFF6B6)
val Yellow200 = Color(0xffFFD200)
val Yellow300 = Color(0xffFFB400)

val Green100 = Color(0xffB8EBD6)
val Green200 = Color(0xff50BE87)
val Green300 = Color(0xff0A6E31)

val Purple100 = Color(0xffD9C2F0)
val Purple200 = Color(0xffA885D8)
val Purple300 = Color(0xff492191)

val Pink100 = Color(0xffFFE8F7)
val Pink200 = Color(0xffFFB4E6)
val Pink300 = Color(0xffFF8AD4)

// Functional colors

val Info100 = Color(0xff527EDB)
val Info200 = Color(0xff4170D8)
val Alert100 = Color(0xffFFCC00)
val Alert200 = Color(0xff8F7200)
val Positive100 = Color(0xff32C832)
val Positive200 = Color(0xff228722)
val Negative100 = Color(0xffD53F15)
val Negative200 = Color(0xffCD3C14)

// Grey colors

val Grey200 = Color(0xffEEEEEE)
val Grey300 = Color(0xffDDDDDD)
val Grey400 = Color(0xffCCCCCC)
val Grey500 = Color(0xff999999)
val Grey600 = Color(0xff666666)
val Grey800 = Color(0xff333333)

// Custom colors

internal val DarkSurfaceDefault = Color(0xff121212)

val Colors.functionalPositive: Color
    @Composable
    get() = if (isLight) Positive200 else Positive100

val Colors.functionalInfo: Color
    @Composable
    get() = if (isLight) Info200 else Info100

val Colors.functionalAlert: Color
    @Composable
    get() = if (isLight) Alert200 else Alert100