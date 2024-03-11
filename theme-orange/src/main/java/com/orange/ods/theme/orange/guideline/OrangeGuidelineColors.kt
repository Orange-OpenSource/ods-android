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

package com.orange.ods.theme.orange.guideline

import com.orange.ods.compose.annotation.ExperimentalOdsApi
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.colors.OdsFunctionalColors
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

@ExperimentalOdsApi
val OrangeGuidelineColors = listOf(
    GuidelineColor(
        OdsColors::primary.name,
        GuidelineColorType.Core,
        "Orange 100",
        "Orange 200",
        com.google.android.material.R.attr.colorPrimary
    ) { it.primary },
    GuidelineColor(
        OdsColors::background.name,
        GuidelineColorType.Core,
        "White 100",
        "Black 900",
        com.google.android.material.R.attr.backgroundColor
    ) { it.background },
    GuidelineColor(
        OdsColors::surface.name,
        GuidelineColorType.Core,
        "White 100",
        "Secondary Background",
        com.google.android.material.R.attr.colorSurface
    ) { it.surface },
    GuidelineColor(::ObsGrey700.name, GuidelineColorType.Core, "OBS Grey 700", xmlResource = R.color.core_obsgrey_700) { ObsGrey700 },

    GuidelineColor(
        OdsFunctionalColors::positive.name,
        GuidelineColorType.Functional,
        "Positive 200",
        "Positive 100",
        R.attr.functionalPositive
    ) { it.functional.positive },
    GuidelineColor(
        OdsFunctionalColors::negative.name,
        GuidelineColorType.Functional,
        "Negative 200",
        "Negative 100",
        R.attr.functionalNegative
    ) { it.functional.negative },
    GuidelineColor(OdsFunctionalColors::info.name, GuidelineColorType.Functional, "Info 200", "Info 100", R.attr.functionalInfo) { it.functional.info },
    GuidelineColor(OdsFunctionalColors::alert.name, GuidelineColorType.Functional, "Alert 200", "Alert 100", R.attr.functionalAlert) { it.functional.alert },

    GuidelineColor(::Blue100.name, GuidelineColorType.Supporting, "Blue 100", xmlResource = R.color.supporting_blue_100) { Blue100 },
    GuidelineColor(::Blue200.name, GuidelineColorType.Supporting, "Blue 200", xmlResource = R.color.supporting_blue_200) { Blue200 },
    GuidelineColor(::Blue300.name, GuidelineColorType.Supporting, "Blue 300", xmlResource = R.color.supporting_blue_300) { Blue300 },
    GuidelineColor(::Green100.name, GuidelineColorType.Supporting, "Green 100", xmlResource = R.color.supporting_green_100) { Green100 },
    GuidelineColor(::Green200.name, GuidelineColorType.Supporting, "Green 200", xmlResource = R.color.supporting_green_200) { Green200 },
    GuidelineColor(::Green300.name, GuidelineColorType.Supporting, "Green 300", xmlResource = R.color.supporting_green_300) { Green300 },
    GuidelineColor(::Pink100.name, GuidelineColorType.Supporting, "Pink 100", xmlResource = R.color.supporting_pink_100) { Pink100 },
    GuidelineColor(::Pink200.name, GuidelineColorType.Supporting, "Pink 200", xmlResource = R.color.supporting_pink_200) { Pink200 },
    GuidelineColor(::Pink300.name, GuidelineColorType.Supporting, "Pink 300", xmlResource = R.color.supporting_pink_300) { Pink300 },
    GuidelineColor(::Purple100.name, GuidelineColorType.Supporting, "Purple 100", xmlResource = R.color.supporting_purple_100) { Purple100 },
    GuidelineColor(::Purple200.name, GuidelineColorType.Supporting, "Purple 200", xmlResource = R.color.supporting_purple_200) { Purple200 },
    GuidelineColor(::Purple300.name, GuidelineColorType.Supporting, "Purple 300", xmlResource = R.color.supporting_purple_300) { Purple300 },
    GuidelineColor(::Yellow100.name, GuidelineColorType.Supporting, "Yellow 100", xmlResource = R.color.supporting_yellow_100) { Yellow100 },
    GuidelineColor(::Yellow200.name, GuidelineColorType.Supporting, "Yellow 200", xmlResource = R.color.supporting_yellow_200) { Yellow200 },
    GuidelineColor(::Yellow300.name, GuidelineColorType.Supporting, "Yellow 300", xmlResource = R.color.supporting_yellow_300) { Yellow300 }
)