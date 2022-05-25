/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalLightThemeColors = compositionLocalOf { lightColors() }
val LocalDarkThemeColors = compositionLocalOf { darkColors() }

/**
 * ODS Material theme is the theme to apply to your screens in an Orange Jetpack Compose application.
 */
@Composable
fun OdsMaterialTheme(
    odsTheme: OdsTheme,
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkThemeEnabled) odsTheme.getDarkThemeColors() else odsTheme.getLightThemeColors(),
        typography = odsTheme.getTypography()
    ) {
        CompositionLocalProvider(
            LocalRippleTheme provides OdsRippleTheme,
            LocalLightThemeColors provides odsTheme.getLightThemeColors(),
            LocalDarkThemeColors provides odsTheme.getDarkThemeColors(),
            content = content
        )
    }
}

/**
 * Default ripple theme
 */
internal object OdsRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = MaterialTheme.colors.onSurface,
        lightTheme = !isSystemInDarkTheme()
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        MaterialTheme.colors.surface,
        lightTheme = !isSystemInDarkTheme()
    )
}

/**
 * Ripple theme in primary color. It overrides the default behavior for some components like buttons, checkboxes...
 */
internal object OdsPrimaryRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = MaterialTheme.colors.primary,
        lightTheme = true // allow to force ripple in primary color when in dark mode
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        MaterialTheme.colors.surface,
        lightTheme = !isSystemInDarkTheme()
    )
}