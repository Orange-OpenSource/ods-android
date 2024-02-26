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

package com.orange.ods.theme.colors

import androidx.compose.material.Colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private const val ContentUnselectedAlpha = 0.74f

private val Colors.primarySurface
    get() = if (isLight) primary else surface

private val Colors.onPrimarySurface
    get() = if (isLight) onPrimary else onSurface

data class OdsComponentColors(
    val systemBarsBackground: Color,
    val bottomNavigation: OdsBottomNavigationColors? = null,
    val floatingActionButton: OdsFloatingActionButtonColors? = null,
    val switch: OdsSwitchColors? = null,
    val tab: OdsTabColors? = null,
    val topAppBar: OdsTopAppBarColors? = null
)

class OdsComponentColorsInternal(
    systemBarsBackground: Color,
    bottomNavigation: OdsBottomNavigationColors,
    floatingActionButton: OdsFloatingActionButtonColors,
    switch: OdsSwitchColors,
    tab: OdsTabColors,
    topAppBar: OdsTopAppBarColors
) {

    var systemBarsBackground = systemBarsBackground
        private set

    var bottomNavigation by mutableStateOf(bottomNavigation)
        private set

    var floatingActionButton = floatingActionButton
        private set

    var switch = switch
        private set

    var tab = tab
        private set

    var topAppBar = topAppBar
        private set
}

/**
 * Customizable colors for `OdsBottomNavigation` component
 *
 * Use [Colors.DefaultOdsBottomNavigationColors] to get the default component's colors based on Material Colors defined in theme
 */
data class OdsBottomNavigationColors(
    val barBackground: Color,
    val barContent: Color,
    val itemSelected: Color,
    val itemUnselected: Color = itemSelected.copy(alpha = ContentUnselectedAlpha)
)

internal val Colors.DefaultOdsBottomNavigationColors
    get() = OdsBottomNavigationColors(
        barBackground = primarySurface,
        barContent = onPrimarySurface,
        itemSelected = onPrimarySurface
    )

/**
 * Customizable colors for `OdsFloatingActionButton` component
 *
 * Use [Colors.DefaultOdsFloatingActionButtonColors] to get the default component's colors based on Material Colors defined in theme
 */
data class OdsFloatingActionButtonColors(
    val background: Color,
    val content: Color
)

internal val Colors.DefaultOdsFloatingActionButtonColors
    get() = OdsFloatingActionButtonColors(
        background = secondary,
        content = onSecondary
    )

/**
 * Customizable colors for `OdsTopAppBar` component
 *
 * Use [Colors.DefaultOdsTopAppBarColors] to get the default component's colors based on Material Colors defined in theme
 */
data class OdsTopAppBarColors(
    val barBackground: Color,
    val barContent: Color
)

internal val Colors.DefaultOdsTopAppBarColors
    get() = OdsTopAppBarColors(
        barBackground = primarySurface,
        barContent = onPrimarySurface
    )

/**
 * Customizable colors for `OdsSwitch` component
 *
 * Use [Colors.DefaultOdsSwitchColors] to get the default component's colors based on Material Colors defined in theme
 */
data class OdsSwitchColors(
    val uncheckedThumb: Color
)

internal val Colors.DefaultOdsSwitchColors
    get() = OdsSwitchColors(
        uncheckedThumb = surface
    )

/**
 * Customizable colors for `OdsTab` component
 *
 * Use [Colors.DefaultOdsTabColors] to get the default component's colors based on Material Colors defined in theme
 */
data class OdsTabColors(
    val background: Color,
    val selectedContent: Color,
    val unselectedContent: Color = selectedContent.copy(alpha = ContentUnselectedAlpha)
)

internal val Colors.DefaultOdsTabColors
    get() = OdsTabColors(
        background = primarySurface,
        selectedContent = onPrimarySurface
    )