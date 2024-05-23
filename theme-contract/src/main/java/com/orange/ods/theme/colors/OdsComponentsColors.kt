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

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.orange.ods.theme.OdsThemeConfigurationItem
import com.orange.ods.theme.OdsToken

private const val ContentUnselectedAlpha = 0.74f

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

        internal fun build(colorScheme: ColorScheme) = OdsComponentsColors(
            systemBarsBackground,
            bottomNavigation ?: colorScheme.DefaultOdsBottomNavigationColors,
            floatingActionButton ?: colorScheme.DefaultOdsFloatingActionButtonColors,
            switch ?: colorScheme.DefaultOdsSwitchColors,
            tab ?: colorScheme.DefaultOdsTabColors,
            topAppBar ?: colorScheme.DefaultOdsTopAppBarColors
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
 * Use [ColorScheme.DefaultOdsBottomNavigationColors] to get the default component's colors based on Material Colors defined in theme
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

//TODO Verify and change default colors if necessary
internal val ColorScheme.DefaultOdsBottomNavigationColors
    get() = OdsBottomNavigationColors(
        barBackground = primaryContainer,
        barContent = onPrimaryContainer,
        itemSelected = onSurface
    )

/**
 * Customizable colors for `OdsFloatingActionButton` component
 *
 * Use [ColorScheme.DefaultOdsFloatingActionButtonColors] to get the default component's colors based on Material Colors defined in theme
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

internal val ColorScheme.DefaultOdsFloatingActionButtonColors
    get() = OdsFloatingActionButtonColors(
        background = secondary,
        content = onSecondary
    )

/**
 * Customizable colors for `OdsTopAppBar` component
 *
 * Use [ColorScheme.DefaultOdsTopAppBarColors] to get the default component's colors based on Material Colors defined in theme
 */
data class OdsTopAppBarColors(
    override val container: Color,
    override val content: Color
) : OdsTopAppBarColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsTopAppBarColorsCatalog<OdsToken<Color>>> {

    override val tokens = object : OdsTopAppBarColorsCatalog<OdsToken<Color>> {
        override val container = OdsToken(OdsToken.Colors.Component.TopAppBar.Container, this@OdsTopAppBarColors.container)
        override val content = OdsToken(OdsToken.Colors.Component.TopAppBar.Content, this@OdsTopAppBarColors.content)
    }
}

interface OdsTopAppBarColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {
    val container: T
    val content: T
}

internal val ColorScheme.DefaultOdsTopAppBarColors
    get() = OdsTopAppBarColors(
        container = surface,
        content = onSurface
    )

/**
 * Customizable colors for `OdsSwitch` component
 *
 * Use [ColorScheme.DefaultOdsSwitchColors] to get the default component's colors based on Material Colors defined in theme
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

internal val ColorScheme.DefaultOdsSwitchColors
    get() = OdsSwitchColors(
        uncheckedThumb = surface
    )

/**
 * Customizable colors for `OdsTab` component
 *
 * Use [ColorScheme.DefaultOdsTabColors] to get the default component's colors based on Material Colors defined in theme
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

//TODO Verify and change default colors if necessary
internal val ColorScheme.DefaultOdsTabColors
    get() = OdsTabColors(
        background = primaryContainer,
        selectedContent = onPrimaryContainer
    )