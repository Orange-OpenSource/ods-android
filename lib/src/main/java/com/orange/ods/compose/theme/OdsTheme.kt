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

package com.orange.ods.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ods.theme.OdsComponentsConfiguration
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.typography.OdsTypography

private val LocalShapes = staticCompositionLocalOf { Shapes() }

internal val LocalColors = staticCompositionLocalOf<OdsColors> { error("CompositionLocal LocalColors not present") }
private val LocalLightThemeColors = compositionLocalOf<OdsColors> { error("CompositionLocal LocalLightThemeColors not present") }
private val LocalDarkThemeColors = compositionLocalOf<OdsColors> { error("CompositionLocal LocalDarkThemeColors not present") }

private val LocalTypography = staticCompositionLocalOf { OdsTypography() }
private val LocalComponentsConfiguration = staticCompositionLocalOf { OdsComponentsConfiguration() }

private val LocalDarkThemeEnabled = staticCompositionLocalOf<Boolean> { error("CompositionLocal LocalDarkThemeEnabled not present") }

object OdsTheme {

    val colors: OdsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val lightThemeColors: OdsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalLightThemeColors.current

    val darkThemeColors: OdsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDarkThemeColors.current

    val typography: OdsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val componentsConfiguration: OdsComponentsConfiguration
        @Composable
        @ReadOnlyComposable
        get() = LocalComponentsConfiguration.current
}

/**
 * ODS theme is the theme to apply to your screens in an Orange Jetpack Compose application.
 *
 * @param themeConfiguration The configuration of the OdsTheme: colors, typography...
 * @param darkThemeEnabled Indicates whether the dark theme is enabled or not.
 * @param content The content of the theme.
 */
@Composable
fun OdsTheme(
    themeConfiguration: OdsThemeConfigurationContract,
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkThemeEnabled) themeConfiguration.colors.darkColors else themeConfiguration.colors.lightColors

    CompositionLocalProvider(
        LocalDarkThemeEnabled provides darkThemeEnabled,
        LocalRippleTheme provides OdsRippleTheme,
        LocalColors provides colors,
        LocalLightThemeColors provides themeConfiguration.colors.lightColors,
        LocalDarkThemeColors provides themeConfiguration.colors.darkColors,
        LocalTypography provides themeConfiguration.typography,
        LocalShapes provides themeConfiguration.shapes,
        LocalComponentsConfiguration provides themeConfiguration.componentsConfiguration
    ) {
        MaterialTheme(
            colors = colors.materialColors
        ) {
            content()
        }
    }
}

/**
 * Force the given [content] to be displayed in inverse theme (i.e. in dark theme when app is in light or in light theme when app is in dark)
 */
@Composable
fun InverseTheme(content: @Composable () -> Unit) {
    val darkThemeEnabled = LocalDarkThemeEnabled.current
    val colors: OdsColors
    val rippleTheme: RippleTheme
    if (darkThemeEnabled) {
        colors = OdsTheme.lightThemeColors
        rippleTheme = OdsLightRippleTheme
    } else {
        colors = OdsTheme.darkThemeColors
        rippleTheme = OdsDarkRippleTheme
    }

    CompositionLocalProvider(
        LocalRippleTheme provides rippleTheme,
        LocalColors provides colors,
        LocalContentColor provides colors.onSurface
    ) {
        MaterialTheme(
            colors = colors.materialColors
        ) {
            content()
        }
    }
}