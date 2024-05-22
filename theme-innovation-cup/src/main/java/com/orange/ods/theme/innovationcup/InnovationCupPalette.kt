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

package com.orange.ods.theme.innovationcup

import androidx.compose.ui.graphics.Color
import com.orange.ods.theme.colors.OdsColorPalette
import com.orange.ods.theme.colors.OdsColorSet

object InnovationCupPalette : OdsColorPalette {

    class Core : OdsColorSet {

        val primary = Color(0xff1675d1)
        val primaryLight = Color(0xff62a3ff)
        val primaryDark = Color(0xff004a9f)

        val secondary = Color(0xffb0bec5)
        val secondaryLight = Color(0xffe2f1f8)
        val secondaryDark = Color(0xff808e95)

        val white = Color(0xffffffff)
        val black = Color(0xff000000)
        val darkGrey = Color(0xff121212)

        val info = Color(0xff4170D8)
        val yellow = Color(0xffffcc00)
        val green = Color(0xff43A047)
        val red = Color(0xffB00020)
    }

    val core = Core()
}
