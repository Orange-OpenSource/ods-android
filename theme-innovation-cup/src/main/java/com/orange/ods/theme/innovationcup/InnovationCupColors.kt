/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.theme.innovationcup

import androidx.compose.material.Colors
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.colors.OdsComponentsColors
import com.orange.ods.theme.colors.OdsFunctionalColors

internal val InnovationCupLightColors = OdsColors(
    material = Colors(
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

    functional = OdsFunctionalColors(
        positive = Green,
        onPositive = White,
        negative = Red,
        onNegative = White,
        info = Info,
        alert = Yellow
    ),

    components = OdsComponentsColors(
        systemBarsBackground = PrimaryDark
    )
)

internal val InnovationCupDarkColors = OdsColors(
    material = Colors(
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

    functional = OdsFunctionalColors(
        positive = Green,
        onPositive = White,
        negative = Red,
        onNegative = White,
        info = Info,
        alert = Yellow
    ),

    components = OdsComponentsColors(
        systemBarsBackground = Black
    )
)