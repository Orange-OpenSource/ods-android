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
import androidx.compose.material.Typography
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalTypography = staticCompositionLocalOf { Typography() }
val LocalColors = staticCompositionLocalOf { odsLightColors() }

val LocalLightThemeColors = staticCompositionLocalOf { odsLightColors() }
val LocalDarkThemeColors = staticCompositionLocalOf { odsDarkColors() }


object OdsTheme {
    val colors: OdsColors
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
    odsCustomTheme: OdsCustomTheme,
    darkThemeEnabled: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkThemeEnabled) odsCustomTheme.darkThemeColors else odsCustomTheme.lightThemeColors
    // creating a new object for colors to not mutate the initial colors set when updating the values
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
    CompositionLocalProvider(
        LocalRippleTheme provides OdsRippleTheme,
        LocalColors provides rememberedColors,
        LocalLightThemeColors provides odsCustomTheme.lightThemeColors,
        LocalDarkThemeColors provides odsCustomTheme.darkThemeColors,
        LocalTypography provides odsCustomTheme.getTypography(),
        content = content
    )
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


fun odsLightColors(
    coreSurface: Color = Color.Black,
    coreOnSurface: Color = Color.White,
    componentDisabledBackground: Color = Color.Black.copy(alpha = 0.12f),
    componentDisabledContent: Color = Color.Black.copy(alpha = 0.38f),
    bottomNavigationSelectedItem: Color = Color.Blue
): OdsColors = OdsColors(
    coreSurface = coreSurface,
    coreOnSurface = coreOnSurface,
    systemStatusBarBackground = coreSurface,
    systemNavigationBarBackground = coreSurface,
    componentBackgroundDisabled = componentDisabledBackground,
    componentContentDisabled = componentDisabledContent,
    topBarBackground = coreSurface,
    topBarContent = coreOnSurface,
    bottomNavigationBarBackground = coreSurface,
    bottomNavigationBarContent = coreOnSurface,
    bottomNavigationBarContentSelected = bottomNavigationSelectedItem,
    cardBackground = coreSurface,
    cardContent = coreOnSurface,
    isLight = true
)

fun odsDarkColors(
    coreSurface: Color = Color.White,
    coreOnSurface: Color = Color.Black,
    componentDisabledBackground: Color = Color.White.copy(alpha = 0.12f),
    componentDisabledContent: Color = Color.White.copy(alpha = 0.38f),
    bottomNavigationSelectedItem: Color = Color.Blue,
): OdsColors = OdsColors(
    coreSurface = coreSurface,
    coreOnSurface = coreOnSurface,
    systemStatusBarBackground = coreSurface,
    systemNavigationBarBackground = coreSurface,
    componentBackgroundDisabled = componentDisabledBackground,
    componentContentDisabled = componentDisabledContent,
    topBarBackground = coreSurface,
    topBarContent = coreOnSurface,
    bottomNavigationBarBackground = coreSurface,
    bottomNavigationBarContent = coreOnSurface,
    bottomNavigationBarContentSelected = bottomNavigationSelectedItem,
    cardBackground = coreSurface,
    cardContent = coreOnSurface,
    isLight = false
)