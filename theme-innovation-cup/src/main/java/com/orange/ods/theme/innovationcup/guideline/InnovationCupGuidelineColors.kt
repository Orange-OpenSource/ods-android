/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.innovationcup.guideline

import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.colors.OdsFunctionalColors
import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.GuidelineColorType

val InnovationCupGuidelineColors = listOf(
    GuidelineColor(OdsColors::primary, GuidelineColorType.Core, "Orange 100", "Orange 200"),
    GuidelineColor(OdsColors::primaryVariant, GuidelineColorType.Core, "Orange 100", "Orange 200"),
    GuidelineColor(OdsColors::background, GuidelineColorType.Core, "White 100", "Black 900"),
    GuidelineColor(OdsColors::surface, GuidelineColorType.Core, "White 100", "Secondary Background"),

    GuidelineColor(OdsFunctionalColors::positive, GuidelineColorType.Functional, "Green"),
    GuidelineColor(OdsColors::error, GuidelineColorType.Functional, "Red"),
    GuidelineColor(OdsFunctionalColors::info, GuidelineColorType.Functional, "Info"),
    GuidelineColor(OdsFunctionalColors::alert, GuidelineColorType.Functional, "Yellow")
)