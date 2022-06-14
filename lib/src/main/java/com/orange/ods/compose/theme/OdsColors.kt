/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class OdsColors(
    coreSurface: Color,
    coreOnSurface: Color,
    systemStatusBarBackground: Color = coreSurface,
    systemNavigationBarBackground: Color = coreSurface,
    componentBackgroundDisabled: Color,
    componentContentDisabled: Color,
    topBarBackground: Color = coreSurface,
    topBarContent: Color = coreOnSurface,
    bottomNavigationBarBackground: Color = coreSurface,
    bottomNavigationBarContent: Color = coreOnSurface,
    bottomNavigationBarContentSelected: Color,
    cardBackground: Color = coreSurface,
    cardContent: Color = coreOnSurface,
    isLight: Boolean
) {
    var coreSurface by mutableStateOf(coreSurface)
        private set
    var coreOnSurface by mutableStateOf(coreOnSurface)
        private set
    var systemStatusBarBackground by mutableStateOf(systemStatusBarBackground)
        private set
    var systemNavigationBarBackground by mutableStateOf(systemNavigationBarBackground)
        private set
    var componentBackgroundDisabled by mutableStateOf(componentBackgroundDisabled)
        private set
    var componentContentDisabled by mutableStateOf(componentContentDisabled)
        private set
    var topBarBackground by mutableStateOf(topBarBackground)
        private set
    var topBarContent by mutableStateOf(topBarContent)
        private set
    var bottomNavigationBarBackground by mutableStateOf(bottomNavigationBarBackground)
        private set
    var bottomNavigationBarContent by mutableStateOf(bottomNavigationBarContent)
        private set
    var bottomNavigationBarContentSelected by mutableStateOf(bottomNavigationBarContentSelected)
        private set
    var cardBackground by mutableStateOf(cardBackground)
        private set
    var cardContent by mutableStateOf(cardContent)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        coreSurface: Color = this.coreSurface,
        coreOnSurface: Color = this.coreOnSurface,
        systemStatusBarBackground: Color = this.systemStatusBarBackground,
        systemNavigationBarBackground: Color = this.systemNavigationBarBackground,
        componentDisabledBackground: Color = this.componentBackgroundDisabled,
        componentDisabledContent: Color = this.componentContentDisabled,
        topAppBarBackground: Color = this.topBarBackground,
        topAppBarContent: Color = this.topBarContent,
        bottomNavigationBackground: Color = this.bottomNavigationBarBackground,
        bottomNavigationContent: Color = this.bottomNavigationBarContent,
        bottomNavigationSelectedItem: Color = this.bottomNavigationBarContentSelected,
        cardBackgroundColor: Color = this.cardBackground,
        cardContentColor: Color = this.cardContent,
        isLight: Boolean = this.isLight
    ): OdsColors = OdsColors(
        coreSurface,
        coreOnSurface,
        systemStatusBarBackground,
        systemNavigationBarBackground,
        componentDisabledBackground,
        componentDisabledContent,
        topAppBarBackground,
        topAppBarContent,
        bottomNavigationBackground,
        bottomNavigationContent,
        bottomNavigationSelectedItem,
        cardBackgroundColor,
        cardContentColor,
        isLight
    )

    // will be explained later
    fun updateColorsFrom(other: OdsColors) {
        coreSurface = other.coreSurface
        coreOnSurface = other.coreOnSurface
        systemStatusBarBackground = other.systemStatusBarBackground
        systemNavigationBarBackground = other.systemNavigationBarBackground
        componentBackgroundDisabled = other.componentBackgroundDisabled
        componentContentDisabled = other.componentContentDisabled
        topBarBackground = other.topBarBackground
        topBarContent = other.topBarContent
        bottomNavigationBarBackground = other.bottomNavigationBarBackground
        bottomNavigationBarContent = other.bottomNavigationBarContent
        bottomNavigationBarContentSelected = other.bottomNavigationBarContentSelected
        cardBackground = other.cardBackground
        cardContent = other.cardContent
    }
}