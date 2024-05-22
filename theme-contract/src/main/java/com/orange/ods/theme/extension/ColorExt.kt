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

package com.orange.ods.theme.extension

import androidx.compose.ui.graphics.Color

/**
 * Returns the hexadecimal value of the color
 */
fun Color.toHexString() = "#" + value.toString(16).substring(2, 8).uppercase()

/**
 * Returns the RGB value of the color as a String (ex. "rgb(255,255,255)")
 */
fun Color.toRgbString(): String {
    val (red, green, blue) = listOf(red, green, blue).map { (it * 255).toInt() }
    return "rgb($red, $green, $blue)"
}
