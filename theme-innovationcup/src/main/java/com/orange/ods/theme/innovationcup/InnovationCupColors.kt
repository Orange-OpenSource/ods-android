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
    error = Negative,
    onPrimary = White100,
    onSecondary = Black900,
    onBackground = Black900,
    onSurface = Black900,
    onError = Black900,

    systemBarsBackground = PrimaryDark,

    functionalPositive = Positive,
    onFunctionalPositive = White100,
    functionalNegative = Negative,
    onFunctionalNegative = White100,
    functionalInfo = Info,
    functionalAlert = Alert,

    isLight = true
)

val InnovationCupDarkColors = OdsColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    secondary = Secondary,
    secondaryVariant = SecondaryDark,
    background = Black900,
    surface = Black900,
    error = Negative,
    onPrimary = White100,
    onSecondary = Black900,
    onBackground = White100,
    onSurface = White100,
    onError = Black900,

    systemBarsBackground = Black900,

    functionalPositive = Positive,
    onFunctionalPositive = White100,
    functionalNegative = Negative,
    onFunctionalNegative = White100,
    functionalInfo = Info,
    functionalAlert = Alert,

    isLight = false
)