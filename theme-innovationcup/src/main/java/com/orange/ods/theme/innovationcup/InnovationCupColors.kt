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
    background = White100,
    surface = White100,
    error = Negative200,
    onPrimary = PrimaryTextColor,
    onSecondary = SecondaryTextColor,
    onBackground = SecondaryTextColor,
    onSurface = SecondaryTextColor,
    onError = SecondaryTextColor,

    systemBarsBackground = PrimaryDark,

    functionalPositive = Positive200,
    onFunctionalPositive = White100,
    functionalNegative = Negative200,
    onFunctionalNegative = White100,
    functionalInfo = Info200,
    functionalAlert = Alert200,

    isLight = true
)

val InnovationCupDarkColors = OdsColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Secondary,
    secondaryVariant = SecondaryDark,
    background = Black900,
    surface = Black900,
    error = Negative200,
    onPrimary = PrimaryTextColor,
    onSecondary = SecondaryTextColor,
    onBackground = White100,
    onSurface = White100,
    onError = SecondaryTextColor,

    systemBarsBackground = Black900,

    functionalPositive = Positive200,
    onFunctionalPositive = White100,
    functionalNegative = Negative200,
    onFunctionalNegative = White100,
    functionalInfo = Info200,
    functionalAlert = Alert200,

    isLight = false
)