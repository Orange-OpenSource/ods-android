/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.colors

import androidx.compose.material.Colors

/**
 * ODS color system.
 *
 * The ODS color system can help you create an ODS color theme that reflects your brand or style.
 * Default components' colors are based on the provided [material] but you can override these colors by providing your colors for each component. As an
 * example, if you need to change the switches' colors you can provide your own [OdsSwitchColors] in the ODS color system.
 */
open class OdsColors(
    val materialColors: Colors,
    private var functionalColors: OdsFunctionalColors,
    private var componentColors: OdsComponentColors
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

    var functionalPositive = functionalColors.functionalPositive
        private set
    var onFunctionalPositive = functionalColors.onFunctionalPositive
        private set
    var functionalNegative = functionalColors.functionalNegative
        private set
    var onFunctionalNegative = functionalColors.onFunctionalNegative
        private set
    var functionalInfo = functionalColors.functionalInfo
        private set
    var functionalAlert = functionalColors.functionalAlert
        private set

    val component = OdsComponentColorsInternal(
        this.componentColors.systemBarsBackground,
        this.componentColors.bottomNavigation ?: materialColors.DefaultOdsBottomNavigationColors,
        this.componentColors.floatingActionButton ?: materialColors.DefaultOdsFloatingActionButtonColors,
        this.componentColors.switch ?: materialColors.DefaultOdsSwitchColors,
        this.componentColors.tab ?: materialColors.DefaultOdsTabColors,
        this.componentColors.topAppBar ?: materialColors.DefaultOdsTopAppBarColors
    )

    /**
     * Returns a copy of this Colors, optionally overriding some of the values.
     */
    fun copy(
        materialColors: Colors = this.materialColors,
        functionalColors: OdsFunctionalColors = this.functionalColors,
        componentColors: OdsComponentColors = this.componentColors
    ): OdsColors = OdsColors(
        materialColors,
        functionalColors,
        componentColors
    )

    /**
     * Updates the internal values of the given OdsColors with values from the other. T
     * his allows efficiently updating a subset of OdsColors, without recomposing every composable that consumes values from LocalColors.
     */
    fun updateColorsFrom(other: OdsColors) {
        primary = other.primary
        primaryVariant = other.primaryVariant
        secondary = other.secondary
        secondaryVariant = other.secondaryVariant
        background = other.background
        surface = other.surface
        error = other.error
        onPrimary = other.onPrimary
        onSecondary = other.onSecondary
        onBackground = other.onBackground
        onSurface = other.onSurface
        onError = other.onError

        functionalPositive = other.functionalPositive
        onFunctionalPositive = other.onFunctionalPositive
        functionalNegative = other.functionalNegative
        onFunctionalNegative = other.onFunctionalNegative
        functionalInfo = other.functionalInfo
        functionalAlert = other.functionalAlert

        component.updateColorsFrom(other.component)
    }

}