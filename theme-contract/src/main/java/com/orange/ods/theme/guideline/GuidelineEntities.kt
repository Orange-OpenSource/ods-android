/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.guideline

import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import kotlin.reflect.KCallable

/**
 * A color displayed in the guideline color part of the ODS application
 *
 * @property callable The function or property to call to apply this color
 * @property type The [GuidelineColorType] of the color
 * @property lightThemeName The color name when light theme is applied
 * @property darkThemeName The color name when dark theme is applied
 * @property xmlResource Optional resource used to use this color in XML
 */
data class GuidelineColor(
    val callable: KCallable<Color>,
    val type: GuidelineColorType,
    val lightThemeName: String,
    val darkThemeName: String = lightThemeName,
    @ColorRes @AttrRes val xmlResource: Int? = null
) {

    /**
     * Returns the name of the color in light or dark depending on the system theme used.
     */
    @Composable
    fun getName(): String {
        return if (isSystemInDarkTheme()) darkThemeName else lightThemeName
    }
}

enum class GuidelineColorType {
    Core,
    Functional,
    Supporting
}


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

/**
 * A typography displayed in the guideline color part of the ODS application
 *
 * @property name The name of the typography (ex. Headline 1)
 * @property textStyle The [TextStyle] of the typography
 * @property composeStyle The typography style to use for a composable
 * @property xmlResource The typography style to use in XML
 */
data class GuidelineTextStyle(
    val name: String,
    val textStyle: TextStyle,
    val composeStyle: String,
    @AttrRes val xmlResource: Int
)