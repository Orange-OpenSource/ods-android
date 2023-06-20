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

import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import com.orange.ods.theme.colors.OdsColors

/**
 * Allow to force elements appearance to be displayed on light or dark surface.
 *
 * Possible values of [OdsDisplaySurface].
 */
enum class OdsDisplaySurface {
    /**
     * The element is displayed on a surface corresponding to the Android system theme of the device.
     */
    Default,

    /**
     * The element is displayed on a dark background even if the device system is set in light theme.
     */
    Dark,

    /**
     * The element is displayed on a light background even if the device system is set in dark theme.
     */
    Light;
    
    companion object

    val themeColors: OdsColors
        @Composable
        get() = when (this) {
            Default -> OdsTheme.colors
            Dark -> OdsTheme.darkThemeColors
            Light -> OdsTheme.lightThemeColors
        }

    val rippleTheme: RippleTheme
        get() = when (this) {
            Default -> OdsRippleTheme
            Light -> OdsLightRippleTheme
            Dark -> OdsDarkRippleTheme
        }
}
