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
import androidx.compose.material.Typography
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ods.theme.OdsSupportedColors
import com.orange.ods.theme.OdsSupportedTheme

internal val LocalTypography = staticCompositionLocalOf { Typography() }
val LocalColors = staticCompositionLocalOf { odsLightColors() }

val LocalLightThemeColors = compositionLocalOf { odsLightColors() }
val LocalDarkThemeColors = compositionLocalOf { odsDarkColors() }

object OdsTheme {
    val colors: OdsSupportedColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

/**
 * ODS theme is the theme to apply to your screens in an Orange Jetpack Compose application.
 */
@Composable
fun OdsTheme(
    supportedTheme: OdsSupportedTheme,
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkThemeEnabled) supportedTheme.darkThemeColors else supportedTheme.lightThemeColors
    // creating a new object for colors to not mutate the initial colors set when updating the values
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalRippleTheme provides OdsRippleTheme,
        LocalColors provides rememberedColors,
        LocalLightThemeColors provides supportedTheme.lightThemeColors,
        LocalDarkThemeColors provides supportedTheme.darkThemeColors,
        LocalTypography provides supportedTheme.typography,
        content = content
    )
}

