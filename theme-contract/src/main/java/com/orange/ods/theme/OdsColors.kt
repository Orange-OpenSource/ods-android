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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

open class OdsColors(
    isLight: Boolean,
    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    secondaryVariant: Color,
    background: Color,
    surface: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
    onError: Color,
    functionalPositive: Color,
    onFunctionalPositive: Color,
    functionalNegative: Color,
    onFunctionalNegative: Color,
    functionalInfo: Color,
    functionalAlert: Color,
    systemBarsBackground: Color,
    bottomNavigation: OdsBottomNavigationColors? = null,
    topAppBar: OdsTopAppBarColors? = null,
    switch: OdsSwitchColors? = null,
    tab: OdsTabColors? = null
) {
    private val primarySurface = if (isLight) primary else surface
    private val onPrimarySurface = if (isLight) onPrimary else onSurface

    var isLight by mutableStateOf(isLight)
        internal set

    var primary by mutableStateOf(primary)
        private set
    var primaryVariant by mutableStateOf(primaryVariant)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var secondaryVariant by mutableStateOf(secondaryVariant)
        private set
    var background by mutableStateOf(background)
        private set
    var surface by mutableStateOf(surface)
        private set
    var error by mutableStateOf(error)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var onSecondary by mutableStateOf(onSecondary)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set
    var onSurface by mutableStateOf(onSurface)
        private set
    var onError by mutableStateOf(onError)
        private set
    var functionalPositive by mutableStateOf(functionalPositive)
        private set
    var onFunctionalPositive by mutableStateOf(onFunctionalPositive)
        private set
    var functionalNegative by mutableStateOf(functionalNegative)
        private set
    var onFunctionalNegative by mutableStateOf(onFunctionalNegative)
        private set
    var functionalInfo by mutableStateOf(functionalInfo)
        private set
    var functionalAlert by mutableStateOf(functionalAlert)
        private set
    var systemBarsBackground by mutableStateOf(systemBarsBackground)
        private set

    var bottomNavigation by mutableStateOf(
        bottomNavigation ?: OdsBottomNavigationColors(
            barBackground = primarySurface,
            barContent = onPrimarySurface,
            itemSelected = onPrimarySurface,
        )
    )
        private set

    var topAppBar by mutableStateOf(
        topAppBar ?: OdsTopAppBarColors(
            barBackground = primarySurface,
            barContent = onPrimarySurface,
        )
    )
        private set

    var switch by mutableStateOf(
        switch ?: OdsSwitchColors(
            uncheckedThumb = surface,
        )
    )
        private set

    var tab by mutableStateOf(
        tab ?: OdsTabColors(
            background = primarySurface,
            selectedContent = onPrimarySurface
        )
    )
        private set

    /**
     * Returns a copy of this Colors, optionally overriding some of the values.
     */
    fun copy(
        isLight: Boolean = this.isLight,
        primary: Color = this.primary,
        primaryVariant: Color = this.primaryVariant,
        secondary: Color = this.secondary,
        secondaryVariant: Color = this.secondaryVariant,
        background: Color = this.background,
        surface: Color = this.surface,
        error: Color = this.error,
        onPrimary: Color = this.onPrimary,
        onSecondary: Color = this.onSecondary,
        onBackground: Color = this.onBackground,
        onSurface: Color = this.onSurface,
        onError: Color = this.onError,
        functionalPositive: Color = this.functionalPositive,
        onFunctionalPositive: Color = this.onFunctionalPositive,
        functionalNegative: Color = this.functionalNegative,
        onFunctionalNegative: Color = this.onFunctionalNegative,
        functionalInfo: Color = this.functionalInfo,
        functionalAlert: Color = this.functionalAlert,
        systemBarsBackground: Color = this.systemBarsBackground,

        bottomNavigation: OdsBottomNavigationColors = this.bottomNavigation,
        topAppBar: OdsTopAppBarColors = this.topAppBar,
        switch: OdsSwitchColors = this.switch,
        tab: OdsTabColors = this.tab
    ): OdsColors = OdsColors(
        isLight,
        primary,
        primaryVariant,
        secondary,
        secondaryVariant,
        background,
        surface,
        error,
        onPrimary,
        onSecondary,
        onBackground,
        onSurface,
        onError,
        functionalPositive,
        onFunctionalPositive,
        functionalNegative,
        onFunctionalNegative,
        functionalInfo,
        functionalAlert,
        systemBarsBackground,
        bottomNavigation,
        topAppBar,
        switch,
        tab
    )

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
        systemBarsBackground = other.systemBarsBackground

        bottomNavigation = other.bottomNavigation
        topAppBar = other.topAppBar

        switch = other.switch

        tab = other.tab
    }

}
