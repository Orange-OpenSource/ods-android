/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme

import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import kotlin.reflect.KCallable

data class GuidelineColor(
    val callable: KCallable<Color>,
    @ColorRes @AttrRes val xmlRes: Int,
    val type: GuidelineColorType,
    val lightThemeName: String,
    val darkThemeName: String = lightThemeName
)

enum class GuidelineColorType {
    Core,
    Functional,
    Supporting
}


fun Color.toHexString() = "#" + value.toString(16).substring(2, 8).uppercase()

fun Color.toRgbString(): String {
    val (red, green, blue) = listOf(red, green, blue).map { (it * 255).toInt() }
    return "rgb($red, $green, $blue)"
}
