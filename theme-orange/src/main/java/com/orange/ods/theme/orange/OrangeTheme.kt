/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.orange

import com.orange.ods.compose.theme.OdsColors
import com.orange.ods.compose.theme.OdsCustomTheme
import kotlinx.parcelize.Parcelize

@Parcelize
class OrangeTheme : OdsCustomTheme() {

    override val lightThemeColors: OdsColors
        get() = orangeLightColors()

    override val darkThemeColors: OdsColors
        get() = orangeDarkColors()

    /*override fun getLightThemeColors() = lightColors(
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

    override fun getDarkThemeColors() = darkColors(
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
    )*/

    override fun getTypography() = OdsTypography
}