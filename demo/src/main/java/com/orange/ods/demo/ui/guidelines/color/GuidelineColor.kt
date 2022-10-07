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
    val lightThemeName: String,
    val darkThemeName: String = lightThemeName,
    val callable: KCallable<Color>,
    @ColorRes @AttrRes val xmlRes: Int,
    val type: Type
) {

    companion object {

        val coreColors = listOf(
            GuidelineColor(
                lightThemeName = "Orange 100",
                darkThemeName = "Orange 200",
                callable = Colors::primary,
                xmlRes = R.attr.colorPrimary,
                type = Type.Core,
            ),
            GuidelineColor(
                lightThemeName = "Black 900",
                darkThemeName = "White 100",
                callable = Colors::background,
                xmlRes = R.attr.backgroundColor,
                type = Type.Core
            ),
            GuidelineColor(
                lightThemeName = "Secondary Background",
                darkThemeName = "White 100",
                callable = Colors::surface,
                xmlRes = R.attr.colorSurface,
                type = Type.Core
            ),
            GuidelineColor(
                lightThemeName = "OBS Grey 700",
                callable = ::ObsGrey700,
                xmlRes = R.color.ods_color_core_obsgrey_700,
                type = Type.Core
            )
        )

        val functionalColors = listOf(
            GuidelineColor(
                lightThemeName = "Positive 100",
                darkThemeName = "Positive 200",
                callable = Colors::functionalPositive,
                xmlRes = R.attr.functionalPositive,
                type = Type.Functional
            ),
            GuidelineColor(
                lightThemeName = "Negative 100",
                darkThemeName = "Negative 200",
                callable = Colors::error,
                xmlRes = R.attr.colorError,
                type = Type.Functional
            ),
            GuidelineColor(
                lightThemeName = "Info 100",
                darkThemeName = "Info 200",
                callable = Colors::functionalInfo,
                xmlRes = R.attr.functionalInfo,
                type = Type.Functional
            ),
            GuidelineColor(
                lightThemeName = "Alert 100",
                darkThemeName = "Alert 200",
                callable = Colors::functionalAlert,
                xmlRes = R.attr.functionalAlert,
                type = Type.Functional
            )
        )

        val supportingColors = listOf(
            GuidelineColor(
                lightThemeName = "Blue 100",
                callable = ::Blue100,
                xmlRes = R.color.ods_color_supporting_blue_100,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Blue 200",
                callable = ::Blue200,
                xmlRes = R.color.ods_color_supporting_blue_200,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Blue 300",
                callable = ::Blue300,
                xmlRes = R.color.ods_color_supporting_blue_300,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Green 100",
                callable = ::Green100,
                xmlRes = R.color.ods_color_supporting_green_100,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Green 200",
                callable = ::Green200,
                xmlRes = R.color.ods_color_supporting_green_200,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Green 300",
                callable = ::Green300,
                xmlRes = R.color.ods_color_supporting_green_300,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Pink 100",
                callable = ::Pink100,
                xmlRes = R.color.ods_color_supporting_pink_100,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Pink 200",
                callable = ::Pink200,
                xmlRes = R.color.ods_color_supporting_pink_200,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Pink 300",
                callable = ::Pink300,
                xmlRes = R.color.ods_color_supporting_pink_300,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Purple 100",
                callable = ::Purple100,
                xmlRes = R.color.ods_color_supporting_purple_100,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Purple 200",
                callable = ::Purple200,
                xmlRes = R.color.ods_color_supporting_purple_200,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Purple 300",
                callable = ::Purple300,
                xmlRes = R.color.ods_color_supporting_purple_300,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Yellow 100",
                callable = ::Yellow100,
                xmlRes = R.color.ods_color_supporting_yellow_100,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Yellow 200",
                callable = ::Yellow200,
                xmlRes = R.color.ods_color_supporting_yellow_200,
                type = Type.Supporting
            ),
            GuidelineColor(
                lightThemeName = "Yellow 300",
                callable = ::Yellow300,
                xmlRes = R.color.ods_color_supporting_yellow_300,
                type = Type.Supporting
            )
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

    enum class Type {
        Core,
        Functional,
        Supporting
    }
}

fun Color.toHexString() = "#" + value.toString(16).substring(2, 8).uppercase()

fun Color.toRgbString(): String {
    val (red, green, blue) = listOf(red, green, blue).map { (it * 255).toInt() }
    return "rgb($red, $green, $blue)"
}
