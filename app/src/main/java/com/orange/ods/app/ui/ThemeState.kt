/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.guideline.OdsGuideline

val LocalThemeManager = staticCompositionLocalOf<ThemeManager> { error("CompositionLocal LocalThemeManager not present") }
val LocalOdsGuideline = staticCompositionLocalOf<OdsGuideline> { error("CompositionLocal LocalOdsGuideline not present") }

interface ThemeManager {

    val themeConfigurations: List<OdsThemeConfigurationContract>

    var currentThemeConfiguration: OdsThemeConfigurationContract

    var darkModeEnabled: Boolean

}

@Composable
fun rememberThemeState(
    themeConfigurations: List<OdsThemeConfigurationContract>,
    currentThemeConfiguration: MutableState<OdsThemeConfigurationContract>,
    darkModeEnabled: MutableState<Boolean>,
) =
    remember(themeConfigurations, currentThemeConfiguration, darkModeEnabled) {
        ThemeState(themeConfigurations, currentThemeConfiguration, darkModeEnabled)
    }

class ThemeState(
    override val themeConfigurations: List<OdsThemeConfigurationContract>,
    currentThemeConfiguration: MutableState<OdsThemeConfigurationContract>,
    darkModeEnabled: MutableState<Boolean>
) : ThemeManager {

    // ----------------------------------------------------------
    // Theme state source of truth
    // ----------------------------------------------------------

    override var currentThemeConfiguration by currentThemeConfiguration

    override var darkModeEnabled by darkModeEnabled

}