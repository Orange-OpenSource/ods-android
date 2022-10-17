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

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.accompanist.pager.ExperimentalPagerApi
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.guideline.OdsDemoGuideline

val LocalMainThemingManager = staticCompositionLocalOf<MainThemingManager> { error("CompositionLocal LocalMainThemingManager not present") }
val LocalOdsDemoGuideline = staticCompositionLocalOf<OdsDemoGuideline> { error("CompositionLocal LocalOdsDemoGuideline not present") }

interface MainThemingManager {

    fun getAvailableThemeConfigurations(): List<OdsThemeConfigurationContract>

    fun getCurrentThemeConfiguration(): OdsThemeConfigurationContract
    fun setCurrentThemeConfiguration(themeConfiguration: OdsThemeConfigurationContract)

    fun setDarkMode(enabled: Boolean)

}

@Composable
@ExperimentalPagerApi
@ExperimentalMaterialApi
fun rememberMainThemingState(
    themeSettings: List<OdsThemeConfigurationContract>,
    currentThemeSettings: MutableState<OdsThemeConfigurationContract>,
    darkModeEnabled: MutableState<Boolean>,
) =
    remember(themeSettings, currentThemeSettings, darkModeEnabled) {
        MainThemingState(themeSettings, currentThemeSettings, darkModeEnabled)
    }

@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainThemingState(
    private val themeConfigurations: List<OdsThemeConfigurationContract>,
    private val currentThemeConfiguration: MutableState<OdsThemeConfigurationContract>,
    val darkModeEnabled: MutableState<Boolean>
) : MainThemingManager {

    // ----------------------------------------------------------
    // Theme state source of truth
    // ----------------------------------------------------------

    override fun getAvailableThemeConfigurations() = themeConfigurations

    override fun getCurrentThemeConfiguration() = currentThemeConfiguration.value

    override fun setCurrentThemeConfiguration(themeConfiguration: OdsThemeConfigurationContract) {
        currentThemeConfiguration.value = themeConfiguration
    }

    override fun setDarkMode(enabled: Boolean) {
        darkModeEnabled.value = enabled
    }

}