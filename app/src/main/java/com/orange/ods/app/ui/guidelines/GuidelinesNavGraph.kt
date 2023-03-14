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
import com.orange.ods.app.ui.LocalMainTabsManager
import com.orange.ods.app.ui.MainDestinations
import com.orange.ods.app.ui.guidelines.color.GuidelineColorScreen
import com.orange.ods.app.ui.guidelines.spacing.GuidelineSpacingScreen
import com.orange.ods.app.ui.guidelines.typography.GuidelineTypographyScreen

fun NavGraphBuilder.addGuidelinesGraph() {
    composable(MainDestinations.GuidelineColor) {
        LocalMainTabsManager.current.clearTopAppBarTabs()
        GuidelineColorScreen()
    }
    composable(MainDestinations.GuidelineTypography) {
        LocalMainTabsManager.current.clearTopAppBarTabs()
        GuidelineTypographyScreen()
    }
    composable(MainDestinations.GuidelineSpacing) {
        LocalMainTabsManager.current.clearTopAppBarTabs()
        GuidelineSpacingScreen()
    }
}
