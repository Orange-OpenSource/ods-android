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

package com.orange.ods.app.ui.guidelines

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.app.ui.guidelines.color.GuidelineColorScreen
import com.orange.ods.app.ui.guidelines.spacing.GuidelineSpacingScreen
import com.orange.ods.app.ui.guidelines.typography.GuidelineTypographyScreen

object GuidelinesNavigation {
    const val GuidelineTypography = "guideline/typography"
    const val GuidelineColor = "guideline/color"
    const val GuidelineSpacing = "guideline/spacing"
}

fun NavGraphBuilder.addGuidelinesGraph() {
    composable(GuidelinesNavigation.GuidelineColor) {
        GuidelineColorScreen()
    }
    composable(GuidelinesNavigation.GuidelineTypography) {
        GuidelineTypographyScreen()
    }
    composable(GuidelinesNavigation.GuidelineSpacing) {
        GuidelineSpacingScreen()
    }
}
