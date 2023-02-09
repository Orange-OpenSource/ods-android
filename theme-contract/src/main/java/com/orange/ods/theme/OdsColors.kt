/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
 * ODS color system.
 *
 * The ODS color system can help you create an ODS color theme that reflects your brand or style.
 * Default components' colors are based on the provided [materialColors] but you can override these colors by providing your colors for each component. As an
 * example, if you need to change the switches' colors you can provide your own [OdsSwitchColors] in the ODS color system.
 */
open class OdsColors(
    private var materialColors: Colors,
    functionalPositive: Color,
    onFunctionalPositive: Color,
    functionalNegative: Color,
    onFunctionalNegative: Color,
    functionalInfo: Color,
    functionalAlert: Color,
    systemBarsBackground: Color,
    bottomNavigation: OdsBottomNavigationColors? = null,
    floatingActionButton: OdsFloatingActionButtonColors? = null,
    topAppBar: OdsTopAppBarColors? = null,
    switch: OdsSwitchColors? = null,
    tab: OdsTabColors? = null
) {
    var primary = materialColors.primary
        private set
    var primaryVariant = materialColors.primaryVariant
        private set
    var secondary = materialColors.secondary
        private set
    var secondaryVariant = materialColors.secondaryVariant
        private set
    var background = materialColors.background
        private set
    var surface = materialColors.surface
        private set
    var error = materialColors.error
        private set
    var onPrimary = materialColors.onPrimary
        private set
    var onSecondary = materialColors.onSecondary
        private set
    var onBackground = materialColors.onBackground
        private set
    var onSurface = materialColors.onSurface
        private set
    var onError = materialColors.onError

    var functionalPositive = functionalPositive
        private set
    var onFunctionalPositive = onFunctionalPositive
        private set
    var functionalNegative = functionalNegative
        private set
    var onFunctionalNegative = onFunctionalNegative
        private set
    var functionalInfo = functionalInfo
        private set
    var functionalAlert = functionalAlert
        private set
    var systemBarsBackground = systemBarsBackground
        private set

    var bottomNavigation by mutableStateOf(
        bottomNavigation ?: materialColors.DefaultOdsBottomNavigationColors
    )
        private set

    var floatingActionButton = floatingActionButton ?: materialColors.DefaultOdsFloatingActionButtonColors
        private set

    var topAppBar = topAppBar ?: materialColors.DefaultOdsTopAppBarColors
        private set

    var switch = switch ?: materialColors.DefaultOdsSwitchColors
        private set

    var tab = tab ?: materialColors.DefaultOdsTabColors
        private set

    /**
     * Returns a copy of this Colors, optionally overriding some of the values.
     */
    fun copy(
        materialColors: Colors = this.materialColors,

        functionalPositive: Color = this.functionalPositive,
        onFunctionalPositive: Color = this.onFunctionalPositive,
        functionalNegative: Color = this.functionalNegative,
        onFunctionalNegative: Color = this.onFunctionalNegative,
        functionalInfo: Color = this.functionalInfo,
        functionalAlert: Color = this.functionalAlert,
        systemBarsBackground: Color = this.systemBarsBackground,

        bottomNavigation: OdsBottomNavigationColors = this.bottomNavigation,
        floatingActionButton: OdsFloatingActionButtonColors = this.floatingActionButton,
        topAppBar: OdsTopAppBarColors = this.topAppBar,
        switch: OdsSwitchColors = this.switch,
        tab: OdsTabColors = this.tab
    ): OdsColors = OdsColors(
        materialColors,
        functionalPositive,
        onFunctionalPositive,
        functionalNegative,
        onFunctionalNegative,
        functionalInfo,
        functionalAlert,
        systemBarsBackground,
        bottomNavigation,
        floatingActionButton,
        topAppBar,
        switch,
        tab
    )

    /**
     * Updates the internal values of the given OdsColors with values from the other. T
     * his allows efficiently updating a subset of OdsColors, without recomposing every composable that consumes values from LocalColors.
     */
    fun updateColorsFrom(other: OdsColors) {
        materialColors = other.materialColors
        primary = other.materialColors.primary
        primaryVariant = other.materialColors.primaryVariant
        secondary = other.materialColors.secondary
        secondaryVariant = other.materialColors.secondaryVariant
        background = other.materialColors.background
        surface = other.materialColors.surface
        error = other.materialColors.error
        onPrimary = other.materialColors.onPrimary
        onSecondary = other.materialColors.onSecondary
        onBackground = other.materialColors.onBackground
        onSurface = other.materialColors.onSurface
        onError = other.materialColors.onError

        functionalPositive = other.functionalPositive
        onFunctionalPositive = other.onFunctionalPositive
        functionalNegative = other.functionalNegative
        onFunctionalNegative = other.onFunctionalNegative
        functionalInfo = other.functionalInfo
        functionalAlert = other.functionalAlert
        systemBarsBackground = other.systemBarsBackground

        bottomNavigation = other.bottomNavigation
        floatingActionButton = other.floatingActionButton
        topAppBar = other.topAppBar

        switch = other.switch

        tab = other.tab
    }

}