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
import androidx.compose.runtime.Composable

private val lightThemeColors = lightColors(
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

private val darkThemeColors = darkColors(
    primary = Orange200,
    primaryVariant = Orange200,
    secondary = Orange200,
    secondaryVariant = Orange200,
    background = DarkSurfaceDefault,
    surface = DarkSurfaceDefault,
    error = Negative200,
    onPrimary = Black900,
    onSecondary = Black900,
    onBackground = White100,
    onSurface = White100,
    onError = Black900
)

@Composable
fun OdsMaterialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) darkThemeColors else lightThemeColors,
        typography = OdsTypography,
        content = content
    )
}