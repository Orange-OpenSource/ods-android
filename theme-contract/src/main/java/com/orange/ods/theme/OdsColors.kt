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

class OdsColors(
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
    bottomNavigationBarBackground: Color = if (isLight) primary else surface,
    bottomNavigationBarContent: Color = if (isLight) onPrimary else onSurface,
    bottomNavigationItemSelected: Color = if (isLight) onPrimary else onSurface,
    bottomNavigationItemUnselected: Color = bottomNavigationItemSelected.copy(alpha = 0.74f),
    topAppBarBackground: Color = if (isLight) primary else surface,
    topAppBarContent: Color = if (isLight) onPrimary else onSurface,
    switchUncheckedThumb: Color = surface,
) {

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

    var bottomNavigationBarBackground by mutableStateOf(bottomNavigationBarBackground)
        private set
    var bottomNavigationBarContent by mutableStateOf(bottomNavigationBarContent)
        private set
    var bottomNavigationItemSelected by mutableStateOf(bottomNavigationItemSelected)
        private set
    var bottomNavigationItemUnselected by mutableStateOf(bottomNavigationItemUnselected)
        private set

    var topAppBarBackground by mutableStateOf(topAppBarBackground)
        private set
    var topAppBarContent by mutableStateOf(topAppBarContent)
        private set

    var switchUncheckedThumb by mutableStateOf(switchUncheckedThumb)
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

        bottomNavigationBarBackground: Color = this.bottomNavigationBarBackground,
        bottomNavigationBarContent: Color = this.bottomNavigationBarContent,
        bottomNavigationItemSelected: Color = this.bottomNavigationItemSelected,
        bottomNavigationItemUnselected: Color = this.bottomNavigationItemUnselected,
        topAppBarBackground: Color = this.topAppBarBackground,
        topAppBarContent: Color = this.topAppBarContent,
        switchUncheckedThumb: Color = this.switchUncheckedThumb
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
        bottomNavigationBarBackground,
        bottomNavigationBarContent,
        bottomNavigationItemSelected,
        bottomNavigationItemUnselected,
        topAppBarBackground,
        topAppBarContent,
        switchUncheckedThumb
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

        bottomNavigationBarBackground = other.bottomNavigationBarBackground
        bottomNavigationBarContent = other.bottomNavigationBarContent
        bottomNavigationItemSelected = other.bottomNavigationItemSelected
        bottomNavigationItemUnselected = other.bottomNavigationItemUnselected

        topAppBarBackground = other.topAppBarBackground
        topAppBarContent = other.topAppBarContent

        switchUncheckedThumb = other.switchUncheckedThumb
    }
}
