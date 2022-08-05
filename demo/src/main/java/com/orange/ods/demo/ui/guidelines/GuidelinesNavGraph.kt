/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.demo.ui.MainDestinations
import com.orange.ods.demo.ui.guidelines.color.GuidelineColorScreen
import com.orange.ods.demo.ui.guidelines.spacing.GuidelineSpacingScreen
import com.orange.ods.demo.ui.guidelines.typography.GuidelineTypographyScreen

@ExperimentalMaterialApi
fun NavGraphBuilder.addGuidelinesGraph(updateTopBarTitle: (Int) -> Unit, clearTopAppBarTabs: () -> Unit) {
    composable(MainDestinations.GUIDELINE_COLOR) {
        clearTopAppBarTabs()
        GuidelineColorScreen(updateTopBarTitle = updateTopBarTitle)
    }
    composable(MainDestinations.GUIDELINE_TYPOGRAPHY) {
        clearTopAppBarTabs()
        GuidelineTypographyScreen(updateTopBarTitle = updateTopBarTitle)
    }
    composable(MainDestinations.GUIDELINE_SPACING) {
        clearTopAppBarTabs()
        GuidelineSpacingScreen(updateTopBarTitle = updateTopBarTitle)
    }
}
