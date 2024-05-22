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
import com.orange.ods.theme.OdsThemeConfigurationItem
import com.orange.ods.theme.OdsToken

/**
 * ODS color system.
 *
 * The ODS color system can help you create an ODS color theme that reflects your brand or style.
 * Default components' colors are based on the provided [material] but you can override these colors by providing your colors for each component. As an
 * example, if you need to change the switches' colors you can provide your own [OdsSwitchColors] in the ODS color system.
 */
class OdsColors(
    val material: Colors,
    functional: OdsFunctionalColors,
    components: OdsComponentsColors.Builder
) : OdsColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsColorsCatalog<OdsToken<Color>>> {
    override var primary = material.primary
        private set
    override var primaryVariant = material.primaryVariant
        private set
    override var secondary = material.secondary
        private set
    override var secondaryVariant = material.secondaryVariant
        private set
    override var background = material.background
        private set
    override var surface = material.surface
        private set
    override var error = material.error
        private set
    override var onPrimary = material.onPrimary
        private set
    override var onSecondary = material.onSecondary
        private set
    override var onBackground = material.onBackground
        private set
    override var onSurface = material.onSurface
        private set
    override var onError = material.onError
        private set

    override var functional by mutableStateOf(functional)
        private set

    override val components = components.build(material)

    override val tokens = object : OdsColorsCatalog<OdsToken<Color>> {
        override val primary = OdsToken(OdsToken.Colors.Primary, this@OdsColors.primary)
        override val primaryVariant = OdsToken(OdsToken.Colors.PrimaryVariant, this@OdsColors.primaryVariant)
        override val secondary = OdsToken(OdsToken.Colors.Secondary, this@OdsColors.secondary)
        override val secondaryVariant = OdsToken(OdsToken.Colors.SecondaryVariant, this@OdsColors.secondaryVariant)
        override val background = OdsToken(OdsToken.Colors.Background, this@OdsColors.background)
        override val surface = OdsToken(OdsToken.Colors.Surface, this@OdsColors.surface)
        override val error = OdsToken(OdsToken.Colors.Error, this@OdsColors.error)
        override val onPrimary = OdsToken(OdsToken.Colors.OnPrimary, this@OdsColors.onPrimary)
        override val onSecondary = OdsToken(OdsToken.Colors.OnSecondary, this@OdsColors.onSecondary)
        override val onBackground = OdsToken(OdsToken.Colors.OnBackground, this@OdsColors.onBackground)
        override val onSurface = OdsToken(OdsToken.Colors.OnSurface, this@OdsColors.onSurface)
        override val onError = OdsToken(OdsToken.Colors.OnError, this@OdsColors.onError)
        override val functional = this@OdsColors.functional.tokens
        override val components = this@OdsColors.components.tokens
    }
}

interface OdsColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val primary: T
    val primaryVariant: T
    val secondary: T
    val secondaryVariant: T
    val background: T
    val surface: T
    val error: T
    val onPrimary: T
    val onSecondary: T
    val onBackground: T
    val onSurface: T
    val onError: T
    val functional: OdsFunctionalColorsCatalog<T>
    val components: OdsComponentColorsCatalog<T>
}
