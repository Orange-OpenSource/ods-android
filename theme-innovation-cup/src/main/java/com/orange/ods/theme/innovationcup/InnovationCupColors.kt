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

internal val InnovationCupLightColors = with(InnovationCupPalette) {
    OdsColors(
        material = Colors(
            primary = core.primary,
            primaryVariant = core.primaryDark,
            secondary = core.secondary,
            secondaryVariant = core.secondaryDark,
            background = core.white,
            surface = core.white,
            error = core.red,
            onPrimary = core.white,
            onSecondary = core.black,
            onBackground = core.black,
            onSurface = core.black,
            onError = core.black,
            isLight = true
        ),

        functional = OdsFunctionalColors(
            positive = core.green,
            onPositive = core.white,
            negative = core.red,
            onNegative = core.white,
            info = core.info,
            alert = core.yellow
        ),

        components = OdsComponentsColors(
            systemBarsBackground = core.primaryDark
        )
    )
}

internal val InnovationCupDarkColors = with(InnovationCupPalette) {
    OdsColors(
        material = Colors(
            primary = core.primary,
            primaryVariant = core.primaryDark,
            secondary = core.secondary,
            secondaryVariant = core.secondaryDark,
            background = core.black,
            surface = core.darkGrey,
            error = core.red,
            onPrimary = core.white,
            onSecondary = core.black,
            onBackground = core.white,
            onSurface = core.white,
            onError = core.black,
            isLight = false
        ),

        functional = OdsFunctionalColors(
            positive = core.green,
            onPositive = core.white,
            negative = core.red,
            onNegative = core.white,
            info = core.info,
            alert = core.yellow
        ),

        components = OdsComponentsColors(
            systemBarsBackground = core.black
        )
    )
}