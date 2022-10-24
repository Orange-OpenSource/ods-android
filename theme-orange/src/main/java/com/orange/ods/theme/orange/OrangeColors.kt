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

import com.orange.ods.theme.OdsBottomNavigationColors
import com.orange.ods.theme.OdsColors
import com.orange.ods.theme.OdsSwitchColors
import com.orange.ods.theme.OdsTabColors
import com.orange.ods.theme.OdsTopAppBarColors

val OrangeLightColors = OdsColors(
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

    systemBarsBackground = White100,

    bottomNavigation = OdsBottomNavigationColors(
        barBackground = White100,
        barContent = Black900,
        itemSelected = Orange200,
        itemUnselected = Black900,
    ),

    topAppBar = OdsTopAppBarColors(
        barBackground = White100,
        barContent = Black900
    ),

    switch = OdsSwitchColors(uncheckedThumb = White100),

    tab = OdsTabColors(background = White100, selectedContent = Orange200, unselectedContent = Black900),

    isLight = true
)

val OrangeDarkColors = OdsColors(
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

    systemBarsBackground = Black900,

    bottomNavigation = OdsBottomNavigationColors(
        barBackground = Black900,
        barContent = White100,
        itemSelected = Orange200,
        itemUnselected = White100
    ),

    topAppBar = OdsTopAppBarColors(
        barBackground = DarkSurfaceDefault,
        barContent = White100
    ),

    switch = OdsSwitchColors(uncheckedThumb = Grey400),

    tab = OdsTabColors(background = DarkSurfaceDefault, selectedContent = Orange100, unselectedContent = White100),

    isLight = false
)