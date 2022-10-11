/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines.color

import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.orange.ods.compose.theme.Blue100
import com.orange.ods.compose.theme.Blue200
import com.orange.ods.compose.theme.Blue300
import com.orange.ods.compose.theme.Green100
import com.orange.ods.compose.theme.Green200
import com.orange.ods.compose.theme.Green300
import com.orange.ods.compose.theme.ObsGrey700
import com.orange.ods.compose.theme.Pink100
import com.orange.ods.compose.theme.Pink200
import com.orange.ods.compose.theme.Pink300
import com.orange.ods.compose.theme.Purple100
import com.orange.ods.compose.theme.Purple200
import com.orange.ods.compose.theme.Purple300
import com.orange.ods.compose.theme.Yellow100
import com.orange.ods.compose.theme.Yellow200
import com.orange.ods.compose.theme.Yellow300
import com.orange.ods.compose.theme.functionalAlert
import com.orange.ods.compose.theme.functionalInfo
import com.orange.ods.compose.theme.functionalPositive
import com.orange.ods.demo.R
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.memberProperties

data class GuidelineColor(
    val callable: KCallable<Color>,
    @ColorRes @AttrRes val xmlRes: Int,
    val lightThemeName: String,
    val darkThemeName: String = lightThemeName
) {

    companion object {

        val coreColors = listOf(
            GuidelineColor(Colors::primary, R.attr.colorPrimary, "Orange 100", "Orange 200"),
            GuidelineColor(Colors::background, R.attr.backgroundColor, "Black 900", "White 100"),
            GuidelineColor(Colors::surface, R.attr.colorSurface, "Secondary Background", "White 100"),
            GuidelineColor(::ObsGrey700, R.color.ods_color_core_obsgrey_700, "OBS Grey 700")
        )

        val functionalColors = listOf(
            GuidelineColor(Colors::functionalPositive, R.attr.functionalPositive, "Positive 100", "Positive 200"),
            GuidelineColor(Colors::error, R.attr.colorError, "Negative 100", "Negative 200"),
            GuidelineColor(Colors::functionalInfo, R.attr.functionalInfo, "Info 100", "Info 200"),
            GuidelineColor(Colors::functionalAlert, R.attr.functionalAlert, "Alert 100", "Alert 200")
        )

        val supportingColors = listOf(
            GuidelineColor(::Blue100, R.color.ods_color_supporting_blue_100, "Blue 100"),
            GuidelineColor(::Blue200, R.color.ods_color_supporting_blue_200, "Blue 200"),
            GuidelineColor(::Blue300, R.color.ods_color_supporting_blue_300, "Blue 300"),
            GuidelineColor(::Green100, R.color.ods_color_supporting_green_100, "Green 100"),
            GuidelineColor(::Green200, R.color.ods_color_supporting_green_200, "Green 200"),
            GuidelineColor(::Green300, R.color.ods_color_supporting_green_300, "Green 300"),
            GuidelineColor(::Pink100, R.color.ods_color_supporting_pink_100, "Pink 100"),
            GuidelineColor(::Pink200, R.color.ods_color_supporting_pink_200, "Pink 200"),
            GuidelineColor(::Pink300, R.color.ods_color_supporting_pink_300, "Pink 300"),
            GuidelineColor(::Purple100, R.color.ods_color_supporting_purple_100, "Purple 100"),
            GuidelineColor(::Purple200, R.color.ods_color_supporting_purple_200, "Purple 200"),
            GuidelineColor(::Purple300, R.color.ods_color_supporting_purple_300, "Purple 300"),
            GuidelineColor(::Yellow100, R.color.ods_color_supporting_yellow_100, "Yellow 100"),
            GuidelineColor(::Yellow200, R.color.ods_color_supporting_yellow_200, "Yellow 200"),
            GuidelineColor(::Yellow300, R.color.ods_color_supporting_yellow_300, "Yellow 300")
        )
    }

    @Composable
    fun getName(): String {
        return if (isSystemInDarkTheme()) darkThemeName else lightThemeName
    }

    @Composable
    fun getValue(): Color {
        val isColorsProperty = Colors::class.memberProperties.filterIsInstance<KProperty<Color>>().contains(callable)
        val isColorsExtensionProperty = callable.extensionReceiverParameter?.type?.classifier == Colors::class

        return if (isColorsProperty || isColorsExtensionProperty) callable.call(MaterialTheme.colors) else callable.call()
    }
}

fun Color.toHexString() = "#" + value.toString(16).substring(2, 8).uppercase()

fun Color.toRgbString(): String {
    val (red, green, blue) = listOf(red, green, blue).map { (it * 255).toInt() }
    return "rgb($red, $green, $blue)"
}
