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

import androidx.compose.material3.ColorScheme
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.colors.OdsComponentsColors
import com.orange.ods.theme.colors.OdsFunctionalColors

internal val InnovationCupLightColors = with(InnovationCupPalette) {
    OdsColors(
        colorScheme = ColorScheme(
            primary = core.primary40,
            onPrimary = core.white,
            primaryContainer = core.primary90,
            onPrimaryContainer = core.white,
            inversePrimary = core.primary80,

            secondary = core.secondary40,
            onSecondary = core.white,
            secondaryContainer = core.secondary90,
            onSecondaryContainer = core.secondary10,

            tertiary = core.tertiary40,
            onTertiary = core.white,
            tertiaryContainer = core.tertiary90,
            onTertiaryContainer = core.tertiary10,

            background = core.white,
            onBackground = core.black,
            surface = core.neutral98,
            onSurface = core.black,
            surfaceVariant = core.neutral94,
            onSurfaceVariant = core.black,
            surfaceTint = core.white,
            inverseSurface = core.neutral20,
            inverseOnSurface = core.neutral95,
            error = core.error40,
            onError = core.white,
            errorContainer = core.error90,
            onErrorContainer = core.white,
            outline = core.neutralVariant50,
            outlineVariant = core.neutralVariant80,
            scrim = core.black,
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
            systemBarsBackground = core.primary40
        )
    )
}

internal val InnovationCupDarkColors = with(InnovationCupPalette) {
    OdsColors(
        colorScheme = ColorScheme(
            primary = core.primary80,
            onPrimary = core.primary20,
            primaryContainer = core.primary30,
            onPrimaryContainer = core.white,
            inversePrimary = core.primary40,

            secondary = core.secondary80,
            onSecondary = core.secondary20,
            secondaryContainer = core.secondary30,
            onSecondaryContainer = core.secondary90,

            tertiary = core.tertiary80,
            onTertiary = core.tertiary20,
            tertiaryContainer = core.tertiary30,
            onTertiaryContainer = core.tertiary90,

            background = core.black,
            onBackground = core.white,
            surface = core.neutral98,
            onSurface = core.neutral6,
            surfaceVariant = core.neutral24,
            onSurfaceVariant = core.neutralVariant90,
            surfaceTint = core.neutral98,
            inverseSurface = core.neutral90,
            inverseOnSurface = core.neutral20,
            error = core.error80,
            onError = core.error20,
            errorContainer = core.error30,
            onErrorContainer = core.error90,
            outline = core.neutralVariant60,
            outlineVariant = core.neutralVariant30,
            scrim = core.black,
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