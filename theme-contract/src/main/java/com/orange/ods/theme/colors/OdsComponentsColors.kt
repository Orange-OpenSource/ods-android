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
import com.orange.ods.theme.OdsThemeConfigurationItem
import com.orange.ods.theme.OdsToken

private const val ContentUnselectedAlpha = 0.74f

private val Colors.primarySurface
    get() = if (isLight) primary else surface

private val Colors.onPrimarySurface
    get() = if (isLight) onPrimary else onSurface

class OdsComponentsColors(
    override val systemBarsBackground: Color,
    override val bottomNavigation: OdsBottomNavigationColors,
    override val floatingActionButton: OdsFloatingActionButtonColors,
    override val switch: OdsSwitchColors,
    override val tab: OdsTabColors,
    override val topAppBar: OdsTopAppBarColors
) : OdsComponentColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsComponentColorsCatalog<OdsToken<Color>>> {

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

    override val tokens = object : OdsComponentColorsCatalog<OdsToken<Color>> {
        override val systemBarsBackground = OdsToken(OdsToken.Colors.Component.SystemBarsBackground, this@OdsComponentsColors.systemBarsBackground)
        override val bottomNavigation = this@OdsComponentsColors.bottomNavigation.tokens
        override val floatingActionButton = this@OdsComponentsColors.floatingActionButton.tokens
        override val switch = this@OdsComponentsColors.switch.tokens
        override val tab = this@OdsComponentsColors.tab.tokens
        override val topAppBar = this@OdsComponentsColors.topAppBar.tokens
    }
}

interface OdsComponentColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val systemBarsBackground: T
    val bottomNavigation: OdsBottomNavigationColorsCatalog<T>
    val floatingActionButton: OdsFloatingActionButtonColorsCatalog<T>
    val switch: OdsSwitchColorsCatalog<T>
    val tab: OdsTabColorsCatalog<T>
    val topAppBar: OdsTopAppBarColorsCatalog<T>
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
    override val barBackground: Color,
    override val barContent: Color,
    override val itemSelected: Color,
    override val itemUnselected: Color = itemSelected.copy(alpha = ContentUnselectedAlpha)
) : OdsBottomNavigationColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsBottomNavigationColorsCatalog<OdsToken<Color>>> {

    override val tokens = object : OdsBottomNavigationColorsCatalog<OdsToken<Color>> {
        override val barBackground = OdsToken(OdsToken.Colors.Component.BottomNavigation.BarBackground, this@OdsBottomNavigationColors.barBackground)
        override val barContent = OdsToken(OdsToken.Colors.Component.BottomNavigation.BarContent, this@OdsBottomNavigationColors.barContent)
        override val itemSelected = OdsToken(OdsToken.Colors.Component.BottomNavigation.ItemSelected, this@OdsBottomNavigationColors.itemSelected)
        override val itemUnselected = OdsToken(OdsToken.Colors.Component.BottomNavigation.ItemUnselected, this@OdsBottomNavigationColors.itemUnselected)
    }
}

interface OdsBottomNavigationColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val barBackground: T
    val barContent: T
    val itemSelected: T
    val itemUnselected: T
}

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
    override val background: Color,
    override val content: Color
) : OdsFloatingActionButtonColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsFloatingActionButtonColorsCatalog<OdsToken<Color>>> {

    override val tokens = object : OdsFloatingActionButtonColorsCatalog<OdsToken<Color>> {
        override val background = OdsToken(OdsToken.Colors.Component.FloatingActionButton.Background, this@OdsFloatingActionButtonColors.background)
        override val content = OdsToken(OdsToken.Colors.Component.FloatingActionButton.Content, this@OdsFloatingActionButtonColors.content)
    }
}

interface OdsFloatingActionButtonColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val background: T
    val content: T
}

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
    override val barBackground: Color,
    override val barContent: Color
) : OdsTopAppBarColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsTopAppBarColorsCatalog<OdsToken<Color>>> {

    override val tokens = object : OdsTopAppBarColorsCatalog<OdsToken<Color>> {
        override val barBackground = OdsToken(OdsToken.Colors.Component.TopAppBar.BarBackground, this@OdsTopAppBarColors.barBackground)
        override val barContent = OdsToken(OdsToken.Colors.Component.TopAppBar.BarContent, this@OdsTopAppBarColors.barContent)
    }
}

interface OdsTopAppBarColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val barBackground: T
    val barContent: T
}

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
    override val uncheckedThumb: Color
) : OdsSwitchColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsSwitchColorsCatalog<OdsToken<Color>>> {

    override val tokens = object : OdsSwitchColorsCatalog<OdsToken<Color>> {
        override val uncheckedThumb = OdsToken(OdsToken.Colors.Component.Switch.UncheckedThumb, this@OdsSwitchColors.uncheckedThumb)
    }
}

interface OdsSwitchColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val uncheckedThumb: T
}

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
    override val background: Color,
    override val selectedContent: Color,
    override val unselectedContent: Color = selectedContent.copy(alpha = ContentUnselectedAlpha)
) : OdsTabColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsTabColorsCatalog<OdsToken<Color>>> {

    override val tokens = object : OdsTabColorsCatalog<OdsToken<Color>> {
        override val background = OdsToken(OdsToken.Colors.Component.Tab.Background, this@OdsTabColors.background)
        override val selectedContent = OdsToken(OdsToken.Colors.Component.Tab.SelectedContent, this@OdsTabColors.selectedContent)
        override val unselectedContent = OdsToken(OdsToken.Colors.Component.Tab.UnselectedContent, this@OdsTabColors.unselectedContent)
    }
}

interface OdsTabColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val background: T
    val selectedContent: T
    val unselectedContent: T
}

internal val Colors.DefaultOdsTabColors
    get() = OdsTabColors(
        background = primarySurface,
        selectedContent = onPrimarySurface
    )