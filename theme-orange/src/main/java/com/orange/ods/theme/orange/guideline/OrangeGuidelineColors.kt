/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.orange.guideline

import com.orange.ods.theme.GuidelineColor
import com.orange.ods.theme.GuidelineColorType
import com.orange.ods.theme.OdsColors
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
import com.orange.ods.theme.orange.R
import com.orange.ods.theme.orange.Yellow100
import com.orange.ods.theme.orange.Yellow200
import com.orange.ods.theme.orange.Yellow300

val OrangeGuidelineColors = listOf(
    GuidelineColor(OdsColors::primary, R.attr.colorPrimary, GuidelineColorType.Core, "Orange 100", "Orange 200"),
    GuidelineColor(OdsColors::background, R.attr.backgroundColor, GuidelineColorType.Core, "White 100", "Black 900"),
    GuidelineColor(OdsColors::surface, R.attr.colorSurface, GuidelineColorType.Core, "White 100", "Secondary Background"),
    GuidelineColor(::ObsGrey700, R.color.ods_color_core_obsgrey_700, GuidelineColorType.Core, "OBS Grey 700"),

    GuidelineColor(OdsColors::functionalPositive, R.attr.functionalPositive, GuidelineColorType.Functional, "Positive 200", "Positive 100"),
    GuidelineColor(OdsColors::error, R.attr.colorError, GuidelineColorType.Functional, "Negative 200", "Negative 100"),
    GuidelineColor(OdsColors::functionalInfo, R.attr.functionalInfo, GuidelineColorType.Functional, "Info 200", "Info 100"),
    GuidelineColor(OdsColors::functionalAlert, R.attr.functionalAlert, GuidelineColorType.Functional, "Alert 200", "Alert 100"),

    GuidelineColor(::Blue100, R.color.ods_color_supporting_blue_100, GuidelineColorType.Supporting, "Blue 100"),
    GuidelineColor(::Blue200, R.color.ods_color_supporting_blue_200, GuidelineColorType.Supporting, "Blue 200"),
    GuidelineColor(::Blue300, R.color.ods_color_supporting_blue_300, GuidelineColorType.Supporting, "Blue 300"),
    GuidelineColor(::Green100, R.color.ods_color_supporting_green_100, GuidelineColorType.Supporting, "Green 100"),
    GuidelineColor(::Green200, R.color.ods_color_supporting_green_200, GuidelineColorType.Supporting, "Green 200"),
    GuidelineColor(::Green300, R.color.ods_color_supporting_green_300, GuidelineColorType.Supporting, "Green 300"),
    GuidelineColor(::Pink100, R.color.ods_color_supporting_pink_100, GuidelineColorType.Supporting, "Pink 100"),
    GuidelineColor(::Pink200, R.color.ods_color_supporting_pink_200, GuidelineColorType.Supporting, "Pink 200"),
    GuidelineColor(::Pink300, R.color.ods_color_supporting_pink_300, GuidelineColorType.Supporting, "Pink 300"),
    GuidelineColor(::Purple100, R.color.ods_color_supporting_purple_100, GuidelineColorType.Supporting, "Purple 100"),
    GuidelineColor(::Purple200, R.color.ods_color_supporting_purple_200, GuidelineColorType.Supporting, "Purple 200"),
    GuidelineColor(::Purple300, R.color.ods_color_supporting_purple_300, GuidelineColorType.Supporting, "Purple 300"),
    GuidelineColor(::Yellow100, R.color.ods_color_supporting_yellow_100, GuidelineColorType.Supporting, "Yellow 100"),
    GuidelineColor(::Yellow200, R.color.ods_color_supporting_yellow_200, GuidelineColorType.Supporting, "Yellow 200"),
    GuidelineColor(::Yellow300, R.color.ods_color_supporting_yellow_300, GuidelineColorType.Supporting, "Yellow 300")
)