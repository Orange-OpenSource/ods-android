/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.theme.innovationcup.guideline

import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.colors.OdsFunctionalColors
import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.GuidelineColorType

val InnovationCupGuidelineColors = listOf(
    GuidelineColor(OdsColors::primary.name, GuidelineColorType.Core, "Orange 100", "Orange 200") { it.primary },
    GuidelineColor(OdsColors::primaryVariant.name, GuidelineColorType.Core, "Orange 100", "Orange 200") { it.primaryVariant },
    GuidelineColor(OdsColors::background.name, GuidelineColorType.Core, "White 100", "Black 900") { it.background },
    GuidelineColor(OdsColors::surface.name, GuidelineColorType.Core, "White 100", "Secondary Background") { it.surface },
    GuidelineColor(OdsFunctionalColors::positive.name, GuidelineColorType.Functional, "Green") { it.functional.positive },
    GuidelineColor(OdsColors::error.name, GuidelineColorType.Functional, "Red") { it.error },
    GuidelineColor(OdsFunctionalColors::info.name, GuidelineColorType.Functional, "Info") { it.functional.info },
    GuidelineColor(OdsFunctionalColors::alert.name, GuidelineColorType.Functional, "Yellow") { it.functional.alert }
)
