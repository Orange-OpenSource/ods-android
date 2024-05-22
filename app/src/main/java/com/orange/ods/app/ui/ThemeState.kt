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

package com.orange.ods.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ods.theme.OdsThemeConfigurationContract

val LocalThemeManager = staticCompositionLocalOf<ThemeManager> { error("CompositionLocal LocalThemeManager not present") }

interface ThemeManager {
    val themeConfigurations: List<OdsThemeConfigurationContract<*>>
    var currentThemeConfiguration: OdsThemeConfigurationContract<*>
    var darkModeEnabled: Boolean
}

/**
 * Theme state source of truth.
 */
class ThemeState(
    override val themeConfigurations: List<OdsThemeConfigurationContract<*>>,
    currentThemeConfiguration: MutableState<OdsThemeConfigurationContract<*>>,
    darkModeEnabled: MutableState<Boolean>
) : ThemeManager {

    override var currentThemeConfiguration by currentThemeConfiguration

    override var darkModeEnabled by darkModeEnabled

}

@Composable
fun rememberThemeState(
    themeConfigurations: List<OdsThemeConfigurationContract<*>>,
    currentThemeConfiguration: MutableState<OdsThemeConfigurationContract<*>>,
    darkModeEnabled: MutableState<Boolean>,
) = remember(themeConfigurations, currentThemeConfiguration, darkModeEnabled) {
    ThemeState(themeConfigurations, currentThemeConfiguration, darkModeEnabled)
}