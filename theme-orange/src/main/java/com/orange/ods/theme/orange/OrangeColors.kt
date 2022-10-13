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

import com.orange.ods.theme.OdsSupportedColors

val OrangeLightColors = OdsSupportedColors(
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
    onError = Black900,

    functionalPositive = Positive200,
    onFunctionalPositive = White100,
    functionalNegative = Negative200,
    onFunctionalNegative = White100,
    functionalInfo = Info200,
    functionalAlert = Alert200,

    switchUncheckedThumb = White100,

    isLight = true
)

val OrangeDarkColors = OdsSupportedColors(
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
    onError = Black900,

    functionalPositive = Positive100,
    onFunctionalPositive = Black900,
    functionalNegative = Negative100,
    onFunctionalNegative = White100,
    functionalInfo = Info100,
    functionalAlert = Alert100,

    switchUncheckedThumb = Grey400,

    isLight = false
)