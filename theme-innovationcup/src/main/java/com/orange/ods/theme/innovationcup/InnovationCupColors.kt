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

import com.orange.ods.theme.OdsColors

val InnovationCupLightColors = OdsColors(
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

    systemBarsBackground = PrimaryDark,

    functionalPositive = Green,
    onFunctionalPositive = White,
    functionalNegative = Red,
    onFunctionalNegative = White,
    functionalInfo = Info,
    functionalAlert = Yellow,

    isLight = true
)

val InnovationCupDarkColors = OdsColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Secondary,
    secondaryVariant = SecondaryDark,
    background = Black,
    surface = Black,
    error = Red,
    onPrimary = White,
    onSecondary = Black,
    onBackground = White,
    onSurface = White,
    onError = Black,

    systemBarsBackground = Black,

    functionalPositive = Green,
    onFunctionalPositive = White,
    functionalNegative = Red,
    onFunctionalNegative = White,
    functionalInfo = Info,
    functionalAlert = Yellow,

    isLight = false
)