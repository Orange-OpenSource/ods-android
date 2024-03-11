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
import androidx.compose.ui.graphics.Color

private const val ContentUnselectedAlpha = 0.74f

private val Colors.primarySurface
    get() = if (isLight) primary else surface

private val Colors.onPrimarySurface
    get() = if (isLight) onPrimary else onSurface

class OdsComponentsColors(
    val systemBarsBackground: Color,
    val bottomNavigation: OdsBottomNavigationColors,
    val floatingActionButton: OdsFloatingActionButtonColors,
    val switch: OdsSwitchColors,
    val tab: OdsTabColors,
    val topAppBar: OdsTopAppBarColors
) {

    class Builder internal constructor(val systemBarsBackground: Color) {

        var bottomNavigation: OdsBottomNavigationColors? = null
        var floatingActionButton: OdsFloatingActionButtonColors? = null
        var switch: OdsSwitchColors? = null
        var tab: OdsTabColors? = null
        var topAppBar: OdsTopAppBarColors? = null

        internal fun build(materialColors: Colors) = OdsComponentsColors(
            systemBarsBackground,
            bottomNavigation ?: materialColors.DefaultOdsBottomNavigationColors,
            floatingActionButton ?: materialColors.DefaultOdsFloatingActionButtonColors,
            switch ?: materialColors.DefaultOdsSwitchColors,
            tab ?: materialColors.DefaultOdsTabColors,
            topAppBar ?: materialColors.DefaultOdsTopAppBarColors
        )
    }
}

@Suppress("FunctionName")
fun OdsComponentsColors(systemBarsBackground: Color, init: OdsComponentsColors.Builder.() -> Unit = {}): OdsComponentsColors.Builder {
    return OdsComponentsColors.Builder(systemBarsBackground).apply(init)
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