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

class OdsComponentsColors(
    override val floatingActionButton: OdsFloatingActionButtonColors,
) : OdsComponentColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsComponentColorsCatalog<OdsToken<Color>>> {

    class Builder internal constructor() {
        var floatingActionButton: OdsFloatingActionButtonColors? = null

        internal fun build(colorScheme: ColorScheme) = OdsComponentsColors(
            floatingActionButton ?: colorScheme.DefaultOdsFloatingActionButtonColors,
        )
    }

    override val tokens = object : OdsComponentColorsCatalog<OdsToken<Color>> {
        override val floatingActionButton = this@OdsComponentsColors.floatingActionButton.tokens
    }
}

interface OdsComponentColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {
    val floatingActionButton: OdsFloatingActionButtonColorsCatalog<T>
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