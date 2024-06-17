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
    override val floatingActionButton: OdsFloatingActionButtonColors,
    override val tab: OdsTabColors,
) : OdsComponentColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsComponentColorsCatalog<OdsToken<Color>>> {

    class Builder internal constructor() {
        var floatingActionButton: OdsFloatingActionButtonColors? = null
        var tab: OdsTabColors? = null

        internal fun build(colorScheme: ColorScheme) = OdsComponentsColors(
            floatingActionButton ?: colorScheme.DefaultOdsFloatingActionButtonColors,
            tab ?: colorScheme.DefaultOdsTabColors,
        )
    }

    override val tokens = object : OdsComponentColorsCatalog<OdsToken<Color>> {
        override val floatingActionButton = this@OdsComponentsColors.floatingActionButton.tokens
        override val tab = this@OdsComponentsColors.tab.tokens
    }
}

interface OdsComponentColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {
    val floatingActionButton: OdsFloatingActionButtonColorsCatalog<T>
    val tab: OdsTabColorsCatalog<T>
}

@Suppress("FunctionName")
fun OdsComponentsColors(init: OdsComponentsColors.Builder.() -> Unit = {}): OdsComponentsColors.Builder {
    return OdsComponentsColors.Builder().apply(init)
}

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
    override val container: Color,
    override val selectedContent: Color,
    override val unselectedContent: Color = selectedContent.copy(alpha = ContentUnselectedAlpha)
) : OdsTabColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsTabColorsCatalog<OdsToken<Color>>> {

    override val tokens = object : OdsTabColorsCatalog<OdsToken<Color>> {
        override val container = OdsToken(OdsToken.Colors.Component.Tab.Background, this@OdsTabColors.container)
        override val selectedContent = OdsToken(OdsToken.Colors.Component.Tab.SelectedContent, this@OdsTabColors.selectedContent)
        override val unselectedContent = OdsToken(OdsToken.Colors.Component.Tab.UnselectedContent, this@OdsTabColors.unselectedContent)
    }
}

interface OdsTabColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val container: T
    val selectedContent: T
    val unselectedContent: T
}

//TODO Verify and change default colors if necessary
internal val ColorScheme.DefaultOdsTabColors
    get() = OdsTabColors(
        container = primaryContainer,
        selectedContent = onPrimaryContainer
    )