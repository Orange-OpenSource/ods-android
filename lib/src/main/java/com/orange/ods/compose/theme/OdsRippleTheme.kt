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
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable

/**
 * Default ripple theme
 */
internal object OdsRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = if (isSystemInDarkTheme()) OdsDarkRippleTheme.defaultColor() else OdsLightRippleTheme.defaultColor()

    @Composable
    override fun rippleAlpha() = if (isSystemInDarkTheme()) OdsDarkRippleTheme.rippleAlpha() else OdsLightRippleTheme.rippleAlpha()
}

/**
 * Ripple theme in primary color. It overrides the default behavior for some components like buttons, checkboxes...
 */
internal object OdsPrimaryRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = OdsTheme.colors.primary,
        lightTheme = true // allow to force ripple in primary color when in dark mode
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        OdsTheme.colors.surface,
        lightTheme = !isSystemInDarkTheme()
    )
}

/**
 * Ripple theme when display is forced on light
 */
internal object OdsLightRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = OdsTheme.colors.onSurface,
        lightTheme = true
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        OdsTheme.colors.surface,
        lightTheme = true
    )
}

/**
 * Ripple theme when display is forced on dark
 */
internal object OdsDarkRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = OdsTheme.colors.onSurface,
        lightTheme = false
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        OdsTheme.colors.surface,
        lightTheme = false
    )
}