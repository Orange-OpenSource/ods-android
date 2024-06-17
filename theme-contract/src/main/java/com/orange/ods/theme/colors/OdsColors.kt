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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.orange.ods.theme.OdsThemeConfigurationItem
import com.orange.ods.theme.OdsToken
import com.orange.ods.theme.tokens.OdsSemanticColorToken

/**
 * ODS color system.
 *
 * The ODS color system can help you create an ODS color theme that reflects your brand or style.
 */
class OdsColors(
    val colorScheme: ColorScheme,
    functional: OdsFunctionalColors
) : OdsColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsColorsCatalog<OdsToken<Color>>> {
    override var primary = colorScheme.primary
        private set
    override var primaryVariant = colorScheme.tertiary
        private set
    override var secondary = colorScheme.secondary
        private set
    override var secondaryVariant = colorScheme.secondaryContainer
        private set
    override var background = colorScheme.background
        private set
    override var surface = colorScheme.surface
        private set
    override var surfaceVariant = colorScheme.surfaceVariant
        private set
    override var error = colorScheme.error
        private set
    override var onPrimary = colorScheme.onPrimary
        private set
    override var onSecondary = colorScheme.onSecondary
        private set
    override var onBackground = colorScheme.onBackground
        private set
    override var onSurface = colorScheme.onSurface
        private set
    override var onSurfaceVariant = colorScheme.onSurfaceVariant
        private set
    override var onError = colorScheme.onError
        private set

    override var functional by mutableStateOf(functional)
        private set

    override val tokens = object : OdsColorsCatalog<OdsToken<Color>> {
        override val primary = OdsToken(OdsToken.Colors.Primary, this@OdsColors.primary)
        override val primaryVariant = OdsToken(OdsToken.Colors.PrimaryVariant, this@OdsColors.primaryVariant)
        override val secondary = OdsToken(OdsToken.Colors.Secondary, this@OdsColors.secondary)
        override val secondaryVariant = OdsToken(OdsToken.Colors.SecondaryVariant, this@OdsColors.secondaryVariant)
        override val background = OdsToken(OdsToken.Colors.Background, this@OdsColors.background)
        override val surface = OdsToken(OdsToken.Colors.Surface, this@OdsColors.surface)
        override val surfaceVariant = OdsToken(OdsToken.Colors.SurfaceVariant, this@OdsColors.surfaceVariant)
        override val error = OdsToken(OdsToken.Colors.Error, this@OdsColors.error)
        override val onPrimary = OdsToken(OdsToken.Colors.OnPrimary, this@OdsColors.onPrimary)
        override val onSecondary = OdsToken(OdsToken.Colors.OnSecondary, this@OdsColors.onSecondary)
        override val onBackground = OdsToken(OdsToken.Colors.OnBackground, this@OdsColors.onBackground)
        override val onSurface = OdsToken(OdsToken.Colors.OnSurface, this@OdsColors.onSurface)
        override val onSurfaceVariant = OdsToken(OdsToken.Colors.OnSurfaceVariant, this@OdsColors.onSurfaceVariant)
        override val onError = OdsToken(OdsToken.Colors.OnError, this@OdsColors.onError)
        override val functional = this@OdsColors.functional.tokens
    }
}

interface OdsColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {
    val primary: T
    val primaryVariant: T
    val secondary: T
    val secondaryVariant: T
    val background: T
    val surface: T
    val surfaceVariant: T
    val error: T
    val onPrimary: T
    val onSecondary: T
    val onBackground: T
    val onSurface: T
    val onSurfaceVariant: T
    val onError: T
    val functional: OdsFunctionalColorsCatalog<T>
}

@Stable
fun OdsColors.fromToken(value: OdsSemanticColorToken): Color {
    return when (value) {
        OdsSemanticColorToken.Background -> background
        OdsSemanticColorToken.Error -> error
        OdsSemanticColorToken.ErrorContainer -> colorScheme.errorContainer
        OdsSemanticColorToken.InverseOnSurface -> colorScheme.inverseOnSurface
        OdsSemanticColorToken.InversePrimary -> colorScheme.inversePrimary
        OdsSemanticColorToken.InverseSurface -> colorScheme.inverseSurface
        OdsSemanticColorToken.OnBackground -> onBackground
        OdsSemanticColorToken.OnError -> onError
        OdsSemanticColorToken.OnErrorContainer -> colorScheme.onErrorContainer
        OdsSemanticColorToken.OnPrimary -> onPrimary
        OdsSemanticColorToken.OnPrimaryContainer -> colorScheme.onPrimaryContainer
        OdsSemanticColorToken.OnSecondary -> onSecondary
        OdsSemanticColorToken.OnSecondaryContainer -> colorScheme.onSecondaryContainer
        OdsSemanticColorToken.OnSurface -> onSurface
        OdsSemanticColorToken.OnSurfaceVariant -> onSurfaceVariant
        OdsSemanticColorToken.SurfaceTint -> colorScheme.surfaceTint
        OdsSemanticColorToken.OnTertiary -> colorScheme.onTertiary
        OdsSemanticColorToken.OnTertiaryContainer -> colorScheme.onTertiaryContainer
        OdsSemanticColorToken.Outline -> colorScheme.outline
        OdsSemanticColorToken.OutlineVariant -> colorScheme.outlineVariant
        OdsSemanticColorToken.Primary -> primary
        OdsSemanticColorToken.PrimaryContainer -> colorScheme.primaryContainer
        OdsSemanticColorToken.Scrim -> colorScheme.scrim
        OdsSemanticColorToken.Secondary -> secondary
        OdsSemanticColorToken.SecondaryContainer -> colorScheme.secondaryContainer
        OdsSemanticColorToken.Surface -> surface
        OdsSemanticColorToken.SurfaceVariant -> surfaceVariant
        OdsSemanticColorToken.SurfaceBright -> colorScheme.surfaceBright
        OdsSemanticColorToken.SurfaceContainer -> colorScheme.surfaceContainer
        OdsSemanticColorToken.SurfaceContainerHigh -> colorScheme.surfaceContainerHigh
        OdsSemanticColorToken.SurfaceContainerHighest -> colorScheme.surfaceContainerHighest
        OdsSemanticColorToken.SurfaceContainerLow -> colorScheme.surfaceContainerLow
        OdsSemanticColorToken.SurfaceContainerLowest -> colorScheme.surfaceContainerLowest
        OdsSemanticColorToken.SurfaceDim -> colorScheme.surfaceDim
        OdsSemanticColorToken.Tertiary -> colorScheme.tertiary
        OdsSemanticColorToken.TertiaryContainer -> colorScheme.tertiaryContainer
        else -> Color.Unspecified
    }
}
