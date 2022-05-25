/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines.colors

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.orange.ods.demo.R
import com.orange.ods.theme.orange.Blue100
import com.orange.ods.theme.orange.Blue200
import com.orange.ods.theme.orange.Blue300
import com.orange.ods.theme.orange.Green100
import com.orange.ods.theme.orange.Green200
import com.orange.ods.theme.orange.Green300
import com.orange.ods.theme.orange.ObsGrey700
import com.orange.ods.theme.orange.Pink100
import com.orange.ods.theme.orange.Pink200
import com.orange.ods.theme.orange.Pink300
import com.orange.ods.theme.orange.Purple100
import com.orange.ods.theme.orange.Purple200
import com.orange.ods.theme.orange.Purple300
import com.orange.ods.theme.orange.Yellow100
import com.orange.ods.theme.orange.Yellow200
import com.orange.ods.theme.orange.Yellow300
import com.orange.ods.theme.orange.functionalAlert
import com.orange.ods.theme.orange.functionalInfo
import com.orange.ods.theme.orange.functionalPositive

data class GuidelineColorItem(
    val name: String,
    val jetPackName: String,
    val jetPackValue: Color,
    val hexValue: String,
    val rgbValue: String,
    val xmlResourceValue: Int,
    val colorType: ColorType
)

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
            hexValue = "#FF7900",
            rgbValue = "rgb(255, 121, 0)",
            xmlResourceValue = R.attr.colorPrimary,
            colorType = ColorType.CORE
        )
    } else {
        GuidelineColorItem(
            name = "Orange 200",
            jetPackName = "primary",
            jetPackValue = MaterialTheme.colors.primary,
            hexValue = "#F16E00",
            rgbValue = "rgb(241, 110, 0)",
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
            hexValue = "#000000",
            rgbValue = "rgb(0, 0, 0)",
            xmlResourceValue = R.attr.backgroundColor,
            colorType = ColorType.CORE
        )
    } else {
        GuidelineColorItem(
            name = "White 100",
            jetPackName = "background",
            jetPackValue = MaterialTheme.colors.background,
            hexValue = "#FFFFFF",
            rgbValue = "rgb(255, 255, 255)",
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
            hexValue = "#121212",
            rgbValue = "rgb(18, 18, 18)",
            xmlResourceValue = R.attr.colorSurface,
            colorType = ColorType.CORE
        )
    } else {
        GuidelineColorItem(
            name = "White 100",
            jetPackName = "surface",
            jetPackValue = MaterialTheme.colors.surface,
            hexValue = "#FFFFFF",
            rgbValue = "rgb(255, 255, 255)",
            xmlResourceValue = R.attr.colorSurface,
            colorType = ColorType.CORE
        )
    },
    GuidelineColorItem(
        name = "OBS Grey 700",
        jetPackName = "ObsGrey700",
        jetPackValue = ObsGrey700,
        hexValue = "#595959",
        rgbValue = "rgb(89, 89, 89)",
        xmlResourceValue = R.color.ods_color_core_obsgrey_700,
        colorType = ColorType.CORE
    ),
)

@Composable
internal fun getFunctionalColors(systemInDarkTheme: Boolean): List<GuidelineColorItem> = listOf(
    //Positive
    if (systemInDarkTheme) {
        GuidelineColorItem(
            name = "Positive 100",
            jetPackName = "functionalPositive",
            jetPackValue = MaterialTheme.colors.functionalPositive,
            hexValue = "#32C832",
            rgbValue = "rgb(50, 200, 50)",
            xmlResourceValue = R.attr.functionalPositive,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        GuidelineColorItem(
            name = "Positive 200",
            jetPackName = "functionalPositive",
            jetPackValue = MaterialTheme.colors.functionalPositive,
            hexValue = "#228722",
            rgbValue = "rgb(34, 135, 34)",
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
            hexValue = "#D53F15",
            rgbValue = "rgb(213, 63, 21)",
            xmlResourceValue = R.attr.colorError,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        GuidelineColorItem(
            name = "Negative 200",
            jetPackName = "error",
            jetPackValue = MaterialTheme.colors.error,
            hexValue = "#CD3C14",
            rgbValue = "rgb(205, 60, 20)",
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
            hexValue = "#527EDB",
            rgbValue = "rgb(82, 126, 219)",
            xmlResourceValue = R.attr.functionalInfo,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        GuidelineColorItem(
            name = "Info 200",
            jetPackName = "functionalInfo",
            jetPackValue = MaterialTheme.colors.functionalInfo,
            hexValue = "#4170D8",
            rgbValue = "rgb(65, 112, 216)",
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
            hexValue = "#FFCC00",
            rgbValue = "rgb(255, 204, 0)",
            xmlResourceValue = R.attr.functionalAlert,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        GuidelineColorItem(
            name = "Alert 200",
            jetPackName = "functionalAlert",
            jetPackValue = MaterialTheme.colors.functionalAlert,
            hexValue = "#8F7200",
            rgbValue = "rgb(143, 114, 0)",
            xmlResourceValue = R.attr.functionalAlert,
            colorType = ColorType.FUNCTIONAL
        )
    },
)

internal fun getSupportingColors(): List<GuidelineColorItem> = listOf(
    GuidelineColorItem(
        name = "Blue 100",
        jetPackName = "Blue100",
        jetPackValue = Blue100,
        hexValue = "#B5E8F7",
        rgbValue = "rgb(181, 232, 247)",
        xmlResourceValue = R.color.ods_color_supporting_blue_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Blue 200",
        jetPackName = "Blue200",
        jetPackValue = Blue200,
        hexValue = "#4BB4E6",
        rgbValue = "rgb(75, 180, 230)",
        xmlResourceValue = R.color.ods_color_supporting_blue_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Blue 300",
        jetPackName = "Blue300",
        jetPackValue = Blue300,
        hexValue = "#085EBD",
        rgbValue = "rgb(8, 94, 189)",
        xmlResourceValue = R.color.ods_color_supporting_blue_300,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Green 100",
        jetPackName = "Green100",
        jetPackValue = Green100,
        hexValue = "#B8EBD6",
        rgbValue = "rgb(184, 235, 214)",
        xmlResourceValue = R.color.ods_color_supporting_green_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Green 200",
        jetPackName = "Green200",
        jetPackValue = Green200,
        hexValue = "#50BE87",
        rgbValue = "rgb(80, 190, 135)",
        xmlResourceValue = R.color.ods_color_supporting_green_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Green 300",
        jetPackName = "Green300",
        jetPackValue = Green300,
        hexValue = "#0A6E31",
        rgbValue = "rgb(10, 110, 49)",
        xmlResourceValue = R.color.ods_color_supporting_green_300,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Pink 100",
        jetPackName = "Pink100",
        jetPackValue = Pink100,
        hexValue = "#FFE8F7",
        rgbValue = "rgb(255, 232, 247)",
        xmlResourceValue = R.color.ods_color_supporting_pink_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Pink 200",
        jetPackName = "Pink200",
        jetPackValue = Pink200,
        hexValue = "#FFB4E6",
        rgbValue = "rgb(255, 180, 230)",
        xmlResourceValue = R.color.ods_color_supporting_pink_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Pink 300",
        jetPackName = "Pink300",
        jetPackValue = Pink300,
        hexValue = "#FF8AD4",
        rgbValue = "rgb(255, 138, 212)",
        xmlResourceValue = R.color.ods_color_supporting_pink_300,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Purple 100",
        jetPackName = "Purple100",
        jetPackValue = Purple100,
        hexValue = "#D9C2F0",
        rgbValue = "rgb(217, 194, 240)",
        xmlResourceValue = R.color.ods_color_supporting_purple_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Purple 200",
        jetPackName = "Purple200",
        jetPackValue = Purple200,
        hexValue = "#A885D8",
        rgbValue = "rgb(168, 133, 216)",
        xmlResourceValue = R.color.ods_color_supporting_purple_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Purple 300",
        jetPackName = "Purple300",
        jetPackValue = Purple300,
        hexValue = "#492191",
        rgbValue = "rgb(73, 33, 145)",
        xmlResourceValue = R.color.ods_color_supporting_purple_300,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Yellow 100",
        jetPackName = "Yellow100",
        jetPackValue = Yellow100,
        hexValue = "#FFF6B6",
        rgbValue = "rgb(255, 246, 182)",
        xmlResourceValue = R.color.ods_color_supporting_yellow_100,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Yellow 200",
        jetPackName = "Yellow200",
        jetPackValue = Yellow200,
        hexValue = "#FFD200",
        rgbValue = "rgb(255, 210, 0)",
        xmlResourceValue = R.color.ods_color_supporting_yellow_200,
        colorType = ColorType.SUPPORTING
    ),
    GuidelineColorItem(
        name = "Yellow 300",
        jetPackName = "Yellow300",
        jetPackValue = Yellow300,
        hexValue = "#FFB400",
        rgbValue = "rgb(255, 180, 0)",
        xmlResourceValue = R.color.ods_color_supporting_yellow_300,
        colorType = ColorType.SUPPORTING
    ),
)