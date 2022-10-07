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

data class GuidelineColorItem(
    val name: String,
    val jetPackName: String,
    val jetPackValue: Color,
    val xmlResourceValue: Int,
    val colorType: ColorType
) {
    val hexValue = "#" + jetPackValue.value.toString(16).substring(2, 8).uppercase()

    val rgbValue: String

    init {
        val (red, green, blue) = with(jetPackValue) { listOf(red, green, blue) }.map { (it * 255).toInt() }
        rgbValue = "rgb($red, $green, $blue)"
    }
}

enum class ColorType {
    CORE,
    FUNCTIONAL,
    SUPPORTING
}

@Composable
internal fun getCoreColors(systemInDarkTheme: Boolean): List<GuidelineColorItem> = listOf(
    //Orange
    if (systemInDarkTheme) {
        GuidelineColorItem(
            name = "Orange 100",
            jetPackName = "primary",
            jetPackValue = MaterialTheme.colors.primary,
            xmlResourceValue = R.attr.colorPrimary,
            colorType = ColorType.CORE
        )
    } else {
        GuidelineColorItem(
            name = "Orange 200",
            jetPackName = "primary",
            jetPackValue = MaterialTheme.colors.primary,
            xmlResourceValue = R.attr.colorPrimary,
            colorType = ColorType.CORE
        )
    },
    //Background
    if (systemInDarkTheme) {
        GuidelineColorItem(
            name = "Black 900",
            jetPackName = "background",
            jetPackValue = MaterialTheme.colors.background,
            xmlResourceValue = R.attr.backgroundColor,
            colorType = ColorType.CORE
        )
    } else {
        GuidelineColorItem(
            name = "White 100",
            jetPackName = "background",
            jetPackValue = MaterialTheme.colors.background,
            xmlResourceValue = R.attr.backgroundColor,
            colorType = ColorType.CORE
        )
    },
    //Secondary Background
    if (systemInDarkTheme) {
        GuidelineColorItem(
            name = "Secondary Background",
            jetPackName = "surface",
            jetPackValue = MaterialTheme.colors.surface,
            xmlResourceValue = R.attr.colorSurface,
            colorType = ColorType.CORE
        )
    } else {
        GuidelineColorItem(
            name = "White 100",
            jetPackName = "surface",
            jetPackValue = MaterialTheme.colors.surface,
            xmlResourceValue = R.attr.colorSurface,
            colorType = ColorType.CORE
        )
    },
    GuidelineColorItem(
        name = "OBS Grey 700",
        jetPackName = "ObsGrey700",
        jetPackValue = ObsGrey700,
        xmlResourceValue = R.color.ods_color_core_obsgrey_700,
        colorType = ColorType.CORE
    )
)

@Composable
internal fun getFunctionalColors(systemInDarkTheme: Boolean): List<GuidelineColorItem> = listOf(
    //Positive
    if (systemInDarkTheme) {
        GuidelineColorItem(
            name = "Positive 100",
            jetPackName = "functionalPositive",
            jetPackValue = MaterialTheme.colors.functionalPositive,
            xmlResourceValue = R.attr.functionalPositive,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        GuidelineColorItem(
            name = "Positive 200",
            jetPackName = "functionalPositive",
            jetPackValue = MaterialTheme.colors.functionalPositive,
            xmlResourceValue = R.attr.functionalPositive,
            colorType = ColorType.FUNCTIONAL
        )
    },
    //Negative
    if (systemInDarkTheme) {
        GuidelineColorItem(
            name = "Negative 100",
            jetPackName = "error",
            jetPackValue = MaterialTheme.colors.error,
            xmlResourceValue = R.attr.colorError,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        GuidelineColorItem(
            name = "Negative 200",
            jetPackName = "error",
            jetPackValue = MaterialTheme.colors.error,
            xmlResourceValue = R.attr.colorError,
            colorType = ColorType.FUNCTIONAL
        )
    },
    //Info
    if (systemInDarkTheme) {
        GuidelineColorItem(
            name = "Info 100",
            jetPackName = "functionalInfo",
            jetPackValue = MaterialTheme.colors.functionalInfo,
            xmlResourceValue = R.attr.functionalInfo,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        GuidelineColorItem(
            name = "Info 200",
            jetPackName = "functionalInfo",
            jetPackValue = MaterialTheme.colors.functionalInfo,
            xmlResourceValue = R.attr.functionalInfo,
            colorType = ColorType.FUNCTIONAL
        )
    },
    //Alert
    if (systemInDarkTheme) {
        GuidelineColorItem(
            name = "Alert 100",
            jetPackName = "functionalAlert",
            jetPackValue = MaterialTheme.colors.functionalAlert,
            xmlResourceValue = R.attr.functionalAlert,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        GuidelineColorItem(
            name = "Alert 200",
            jetPackName = "functionalAlert",
            jetPackValue = MaterialTheme.colors.functionalAlert,
            xmlResourceValue = R.attr.functionalAlert,
            colorType = ColorType.FUNCTIONAL
        )
    }
)

internal fun getSupportingColors(): List<GuidelineColorItem> = listOf(
    GuidelineColorItem(
        name = "Blue 100",
        jetPackName = "Blue100",
        jetPackValue = Blue100,
        xmlResourceValue = R.color.ods_color_supporting_blue_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Blue 200",
        jetPackName = "Blue200",
        jetPackValue = Blue200,
        xmlResourceValue = R.color.ods_color_supporting_blue_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Blue 300",
        jetPackName = "Blue300",
        jetPackValue = Blue300,
        xmlResourceValue = R.color.ods_color_supporting_blue_300,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Green 100",
        jetPackName = "Green100",
        jetPackValue = Green100,
        xmlResourceValue = R.color.ods_color_supporting_green_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Green 200",
        jetPackName = "Green200",
        jetPackValue = Green200,
        xmlResourceValue = R.color.ods_color_supporting_green_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Green 300",
        jetPackName = "Green300",
        jetPackValue = Green300,
        xmlResourceValue = R.color.ods_color_supporting_green_300,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Pink 100",
        jetPackName = "Pink100",
        jetPackValue = Pink100,
        xmlResourceValue = R.color.ods_color_supporting_pink_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Pink 200",
        jetPackName = "Pink200",
        jetPackValue = Pink200,
        xmlResourceValue = R.color.ods_color_supporting_pink_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Pink 300",
        jetPackName = "Pink300",
        jetPackValue = Pink300,
        xmlResourceValue = R.color.ods_color_supporting_pink_300,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Purple 100",
        jetPackName = "Purple100",
        jetPackValue = Purple100,
        xmlResourceValue = R.color.ods_color_supporting_purple_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Purple 200",
        jetPackName = "Purple200",
        jetPackValue = Purple200,
        xmlResourceValue = R.color.ods_color_supporting_purple_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Purple 300",
        jetPackName = "Purple300",
        jetPackValue = Purple300,
        xmlResourceValue = R.color.ods_color_supporting_purple_300,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Yellow 100",
        jetPackName = "Yellow100",
        jetPackValue = Yellow100,
        xmlResourceValue = R.color.ods_color_supporting_yellow_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Yellow 200",
        jetPackName = "Yellow200",
        jetPackValue = Yellow200,
        xmlResourceValue = R.color.ods_color_supporting_yellow_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Yellow 300",
        jetPackName = "Yellow300",
        jetPackValue = Yellow300,
        xmlResourceValue = R.color.ods_color_supporting_yellow_300,
        colorType = ColorType.SUPPORTING
    )
)