/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
