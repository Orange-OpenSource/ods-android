/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.theme.orange

import androidx.compose.material.Colors
import com.orange.ods.theme.colors.OdsBottomNavigationColors
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.colors.OdsComponentColors
import com.orange.ods.theme.colors.OdsFloatingActionButtonColors
import com.orange.ods.theme.colors.OdsFunctionalColors
import com.orange.ods.theme.colors.OdsSwitchColors
import com.orange.ods.theme.colors.OdsTabColors
import com.orange.ods.theme.colors.OdsTopAppBarColors

val OrangeLightColors = OdsColors(
    materialColors = Colors(
        primary = Orange200,
        primaryVariant = Orange100,
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
        isLight = true
    ),

    functionalColors = OdsFunctionalColors(
        positive = Positive200,
        onPositive = White100,
        negative = Negative200,
        onNegative = White100,
        info = Info200,
        alert = Alert200
    ),

    componentColors = OdsComponentColors(
        systemBarsBackground = White100,

        bottomNavigation = OdsBottomNavigationColors(
            barBackground = White100,
            barContent = Black900,
            itemSelected = Orange200,
            itemUnselected = Black900
        ),

        floatingActionButton = OdsFloatingActionButtonColors(
            background = Orange200,
            content = Black900
        ),

        topAppBar = OdsTopAppBarColors(
            barBackground = White100,
            barContent = Black900
        ),

        switch = OdsSwitchColors(
            uncheckedThumb = White100
        ),

        tab = OdsTabColors(
            background = White100,
            selectedContent = Orange200,
            unselectedContent = Black900
        )
    )
)

val OrangeDarkColors = OdsColors(
    materialColors = Colors(
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
        isLight = false
    ),

    functionalColors = OdsFunctionalColors(
        positive = Positive100,
        onPositive = Black900,
        negative = Negative100,
        onNegative = White100,
        info = Info100,
        alert = Alert100
    ),

    componentColors = OdsComponentColors(
        systemBarsBackground = Black900,

        bottomNavigation = OdsBottomNavigationColors(
            barBackground = Black900,
            barContent = White100,
            itemSelected = Orange200,
            itemUnselected = White100
        ),

        floatingActionButton = OdsFloatingActionButtonColors(
            background = Orange100,
            content = Black900
        ),

        topAppBar = OdsTopAppBarColors(
            barBackground = DarkSurfaceDefault,
            barContent = White100
        ),

        switch = OdsSwitchColors(
            uncheckedThumb = Grey400
        ),

        tab = OdsTabColors(
            background = DarkSurfaceDefault,
            selectedContent = Orange100,
            unselectedContent = White100
        )
    )
)