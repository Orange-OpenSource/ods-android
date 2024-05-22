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

package com.orange.ods.theme.orange

import androidx.compose.ui.graphics.Color
import com.orange.ods.theme.colors.OdsColorPalette
import com.orange.ods.theme.colors.OdsColorSet

object OrangeColorPalette : OdsColorPalette {

    class Core : OdsColorSet {

        val orange100 = Color(0xffff7900)
        val orange200 = Color(0xfff16e00)
        val white100 = Color(0xffffffff)
        val black900 = Color(0xff000000)
        val obsGrey700 = Color(0xff595959)
    }

    class Supporting : OdsColorSet {

        val blue100 = Color(0xffB5E8F7)
        val blue200 = Color(0xff4BB4E6)
        val blue300 = Color(0xff085EBD)

        val yellow100 = Color(0xffFFF6B6)
        val yellow200 = Color(0xffFFD200)
        val yellow300 = Color(0xffFFB400)

        val green100 = Color(0xffB8EBD6)
        val green200 = Color(0xff50BE87)
        val green300 = Color(0xff0A6E31)

        val purple100 = Color(0xffD9C2F0)
        val purple200 = Color(0xffA885D8)
        val purple300 = Color(0xff492191)

        val pink100 = Color(0xffFFE8F7)
        val pink200 = Color(0xffFFB4E6)
        val pink300 = Color(0xffFF8AD4)
    }

    class Functional : OdsColorSet {

        val info100 = Color(0xff527EDB)
        val info200 = Color(0xff4170D8)
        val alert100 = Color(0xffFFCC00)
        val alert200 = Color(0xff8F7200)
        val positive100 = Color(0xff32C832)
        val positive200 = Color(0xff228722)
        val negative100 = Color(0xffD53F15)
        val negative200 = Color(0xffCD3C14)
    }

    class Grey : OdsColorSet {

        val Grey200 = Color(0xffEEEEEE)
        val Grey300 = Color(0xffDDDDDD)
        val Grey400 = Color(0xffCCCCCC)
        val Grey500 = Color(0xff999999)
        val Grey600 = Color(0xff666666)
        val Grey800 = Color(0xff333333)
    }

    class Custom : OdsColorSet {

        val DarkSurfaceDefault = Color(0xff121212)
        val Transparent = Color(0x00000000)
        val SliderActiveTickColor = Color(0xffEBA66C)
    }

    val core = Core()

    val supporting = Supporting()

    val functional = Functional()

    val grey = Grey()

    val custom = Custom()
}
