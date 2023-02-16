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

import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.GuidelineColorType
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
    GuidelineColor(OdsColors::primary, GuidelineColorType.Core, "Orange 100", "Orange 200", R.attr.colorPrimary),
    GuidelineColor(OdsColors::background, GuidelineColorType.Core, "White 100", "Black 900", R.attr.backgroundColor),
    GuidelineColor(OdsColors::surface, GuidelineColorType.Core, "White 100", "Secondary Background", R.attr.colorSurface),
    GuidelineColor(::ObsGrey700, GuidelineColorType.Core, "OBS Grey 700", xmlResource = R.color.core_obsgrey_700),

    GuidelineColor(OdsColors::functionalPositive, GuidelineColorType.Functional, "Positive 200", "Positive 100", R.attr.functionalPositive),
    GuidelineColor(OdsColors::error, GuidelineColorType.Functional, "Negative 200", "Negative 100", R.attr.colorError),
    GuidelineColor(OdsColors::functionalInfo, GuidelineColorType.Functional, "Info 200", "Info 100", R.attr.functionalInfo),
    GuidelineColor(OdsColors::functionalAlert, GuidelineColorType.Functional, "Alert 200", "Alert 100", R.attr.functionalAlert),

    GuidelineColor(::Blue100, GuidelineColorType.Supporting, "Blue 100", xmlResource = R.color.supporting_blue_100),
    GuidelineColor(::Blue200, GuidelineColorType.Supporting, "Blue 200", xmlResource = R.color.supporting_blue_200),
    GuidelineColor(::Blue300, GuidelineColorType.Supporting, "Blue 300", xmlResource = R.color.supporting_blue_300),
    GuidelineColor(::Green100, GuidelineColorType.Supporting, "Green 100", xmlResource = R.color.supporting_green_100),
    GuidelineColor(::Green200, GuidelineColorType.Supporting, "Green 200", xmlResource = R.color.supporting_green_200),
    GuidelineColor(::Green300, GuidelineColorType.Supporting, "Green 300", xmlResource = R.color.supporting_green_300),
    GuidelineColor(::Pink100, GuidelineColorType.Supporting, "Pink 100", xmlResource = R.color.supporting_pink_100),
    GuidelineColor(::Pink200, GuidelineColorType.Supporting, "Pink 200", xmlResource = R.color.supporting_pink_200),
    GuidelineColor(::Pink300, GuidelineColorType.Supporting, "Pink 300", xmlResource = R.color.supporting_pink_300),
    GuidelineColor(::Purple100, GuidelineColorType.Supporting, "Purple 100", xmlResource = R.color.supporting_purple_100),
    GuidelineColor(::Purple200, GuidelineColorType.Supporting, "Purple 200", xmlResource = R.color.supporting_purple_200),
    GuidelineColor(::Purple300, GuidelineColorType.Supporting, "Purple 300", xmlResource = R.color.supporting_purple_300),
    GuidelineColor(::Yellow100, GuidelineColorType.Supporting, "Yellow 100", xmlResource = R.color.supporting_yellow_100),
    GuidelineColor(::Yellow200, GuidelineColorType.Supporting, "Yellow 200", xmlResource = R.color.supporting_yellow_200),
    GuidelineColor(::Yellow300, GuidelineColorType.Supporting, "Yellow 300", xmlResource = R.color.supporting_yellow_300)
)