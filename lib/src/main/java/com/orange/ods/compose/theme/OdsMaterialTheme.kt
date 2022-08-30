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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalDarkThemeEnabled = compositionLocalOf { false }

/**
 * ODS Material theme is the theme to apply to your screens in an Orange Jetpack Compose application.
 */
@Composable
fun OdsMaterialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) odsDarkThemeColors else odsLightThemeColors,
        typography = OdsTypography
    ) {
        CompositionLocalProvider(
            LocalRippleTheme provides OdsRippleTheme,
            LocalDarkThemeEnabled provides darkTheme,
            content = content
        )
    }
}

val odsLightThemeColors = lightColors(
    primary = Orange200,
    primaryVariant = Orange200,
    secondary = Orange200,
    secondaryVariant = Orange200,
    background = White100,
    surface = White100,
    error = Negative200,
    onPrimary = Black900,
    onSecondary = Black900,
    onBackground = Black900,
    onSurface = Black900,
    onError = Black900
)

val odsDarkThemeColors = darkColors(
    primary = Orange100,
    primaryVariant = Orange200,
    secondary = Orange200,
    secondaryVariant = Orange200,
    background = Black900,
    surface = DarkSurfaceDefault,
    error = Negative100,
    onPrimary = Black900,
    onSecondary = Black900,
    onBackground = White100,
    onSurface = White100,
    onError = Black900
)