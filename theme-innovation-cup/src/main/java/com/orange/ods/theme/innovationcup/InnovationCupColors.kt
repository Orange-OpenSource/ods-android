/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.innovationcup

import androidx.compose.material.Colors
import com.orange.ods.theme.OdsColors

val InnovationCupLightColors = OdsColors(
    materialColors = Colors(
        primary = Primary,
        primaryVariant = PrimaryDark,
        secondary = Secondary,
        secondaryVariant = SecondaryDark,
        background = White,
        surface = White,
        error = Red,
        onPrimary = White,
        onSecondary = Black,
        onBackground = Black,
        onSurface = Black,
        onError = Black,
        isLight = true
    ),

    systemBarsBackground = PrimaryDark,

    functionalPositive = Green,
    onFunctionalPositive = White,
    functionalNegative = Red,
    onFunctionalNegative = White,
    functionalInfo = Info,
    functionalAlert = Yellow,
)

val InnovationCupDarkColors = OdsColors(
    materialColors = Colors(
        primary = Primary,
        primaryVariant = PrimaryDark,
        secondary = Secondary,
        secondaryVariant = SecondaryDark,
        background = Black,
        surface = DarkGrey,
        error = Red,
        onPrimary = White,
        onSecondary = Black,
        onBackground = White,
        onSurface = White,
        onError = Black,
        isLight = false
    ),

    systemBarsBackground = Black,

    functionalPositive = Green,
    onFunctionalPositive = White,
    functionalNegative = Red,
    onFunctionalNegative = White,
    functionalInfo = Info,
    functionalAlert = Yellow,
)