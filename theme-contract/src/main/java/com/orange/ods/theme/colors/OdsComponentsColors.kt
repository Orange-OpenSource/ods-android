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
    val statusBar: OdsStatusBarColors,
    val bottomNavigation: OdsBottomNavigationColors,
    val floatingActionButton: OdsFloatingActionButtonColors,
    val switch: OdsSwitchColors,
    val tab: OdsTabColors,
    val topAppBar: OdsTopAppBarColors
) {

    @Deprecated(
        "Please use constructor with statusBar parameter of type OdsStatusBarColors instead.",
        replaceWith = ReplaceWith("OdsComponentsColors(statusBar, bottomNavigation, floatingActionButton, switch, tab, topAppBar)")
    )
    constructor(
        systemBarsBackground: Color,
        bottomNavigation: OdsBottomNavigationColors,
        floatingActionButton: OdsFloatingActionButtonColors,
        switch: OdsSwitchColors,
        tab: OdsTabColors,
        topAppBar: OdsTopAppBarColors
    ) : this(
        OdsStatusBarColors(systemBarsBackground, false),
        bottomNavigation, floatingActionButton, switch, tab, topAppBar
    )

    class Builder internal constructor() {
        var statusBar: OdsStatusBarColors? = null
        var bottomNavigation: OdsBottomNavigationColors? = null
        var floatingActionButton: OdsFloatingActionButtonColors? = null
        var switch: OdsSwitchColors? = null
        var tab: OdsTabColors? = null
        var topAppBar: OdsTopAppBarColors? = null

        internal fun build(materialColors: Colors) = OdsComponentsColors(
            statusBar ?: materialColors.DefaultOdsStatusBarColors,
            bottomNavigation ?: materialColors.DefaultOdsBottomNavigationColors,
            floatingActionButton ?: materialColors.DefaultOdsFloatingActionButtonColors,
            switch ?: materialColors.DefaultOdsSwitchColors,
            tab ?: materialColors.DefaultOdsTabColors,
            topAppBar ?: materialColors.DefaultOdsTopAppBarColors
        )
    }
}

@Suppress("FunctionName")
fun OdsComponentsColors(init: OdsComponentsColors.Builder.() -> Unit = {}): OdsComponentsColors.Builder {
    return OdsComponentsColors.Builder().apply(init)
}

@Suppress("FunctionName")
@Deprecated(
    "Please use OdsComponentColors function with only init parameter instead.",
    replaceWith = ReplaceWith("OdsComponentsColors(init)")
)
fun OdsComponentsColors(systemBarsBackground: Color, init: OdsComponentsColors.Builder.() -> Unit = {}): OdsComponentsColors.Builder {
    return OdsComponentsColors.Builder().apply {
        init()
        statusBar = OdsStatusBarColors(background = systemBarsBackground)
    }
}

/**
 * Customizable colors for status bar
 *
 * @attribute background Background color of the status bar.
 * @attribute isAppearanceLight Controls the items color of the status bar. When `true`, the items on the bar can be read clearly on a light background.
 *
 * Use [Colors.DefaultOdsStatusBarColors] to get the default component's colors based on Material Colors defined in theme
 */
data class OdsStatusBarColors(
    val background: Color,
    val isAppearanceLight: Boolean = false
)

internal val Colors.DefaultOdsStatusBarColors
    get() = OdsStatusBarColors(
        background = primaryVariant,
        isAppearanceLight = false
    )

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