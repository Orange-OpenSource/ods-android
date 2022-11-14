/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.guideline.OdsDemoGuideline

val LocalMainThemeManager = staticCompositionLocalOf<MainThemeManager> { error("CompositionLocal LocalMainThemeManager not present") }
val LocalOdsDemoGuideline = staticCompositionLocalOf<OdsDemoGuideline> { error("CompositionLocal LocalOdsDemoGuideline not present") }

interface MainThemeManager {

    val themeConfigurations: List<OdsThemeConfigurationContract>

    var currentThemeConfiguration: OdsThemeConfigurationContract

    var darkModeEnabled: Boolean

}

@Composable
fun rememberMainThemeState(
    themeConfigurations: List<OdsThemeConfigurationContract>,
    currentThemeConfiguration: MutableState<OdsThemeConfigurationContract>,
    darkModeEnabled: MutableState<Boolean>,
) =
    remember(themeConfigurations, currentThemeConfiguration, darkModeEnabled) {
        MainThemeState(themeConfigurations, currentThemeConfiguration, darkModeEnabled)
    }

class MainThemeState(
    override val themeConfigurations: List<OdsThemeConfigurationContract>,
    currentThemeConfiguration: MutableState<OdsThemeConfigurationContract>,
    darkModeEnabled: MutableState<Boolean>
) : MainThemeManager {

    // ----------------------------------------------------------
    // Theme state source of truth
    // ----------------------------------------------------------

    override var currentThemeConfiguration by currentThemeConfiguration

    override var darkModeEnabled by darkModeEnabled

}