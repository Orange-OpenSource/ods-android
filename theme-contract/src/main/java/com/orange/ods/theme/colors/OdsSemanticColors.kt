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

package com.orange.ods.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


class OdsSemanticColors(
    val primary: OdsSemanticColorValue,
    val onPrimary: OdsSemanticColorValue,
    val primaryContainer: OdsSemanticColorValue,
    val onPrimaryContainer: OdsSemanticColorValue,
    val inversePrimary: OdsSemanticColorValue,
    val secondary: OdsSemanticColorValue,
    val onSecondary: OdsSemanticColorValue,
    val secondaryContainer: OdsSemanticColorValue,
    val onSecondaryContainer: OdsSemanticColorValue,
    val tertiary: OdsSemanticColorValue,
    val onTertiary: OdsSemanticColorValue,
    val tertiaryContainer: OdsSemanticColorValue,
    val onTertiaryContainer: OdsSemanticColorValue,
    val background: OdsSemanticColorValue,
    val onBackground: OdsSemanticColorValue,
    val surface: OdsSemanticColorValue,
    val onSurface: OdsSemanticColorValue,
    val surfaceVariant: OdsSemanticColorValue,
    val onSurfaceVariant: OdsSemanticColorValue,
    val surfaceTint: OdsSemanticColorValue,
    val inverseSurface: OdsSemanticColorValue,
    val inverseOnSurface: OdsSemanticColorValue,
    val error: OdsSemanticColorValue,
    val onError: OdsSemanticColorValue,
    val errorContainer: OdsSemanticColorValue,
    val onErrorContainer: OdsSemanticColorValue,
    val outline: OdsSemanticColorValue,
    val outlineVariant: OdsSemanticColorValue,
    val scrim: OdsSemanticColorValue,
    val positive: OdsSemanticColorValue,
    val onPositive: OdsSemanticColorValue,
    val negative: OdsSemanticColorValue,
    val onNegative: OdsSemanticColorValue,
    val info: OdsSemanticColorValue,
    val alert: OdsSemanticColorValue,
) {

    val light = OdsColors(
        colorScheme = lightColorScheme(
            primary = primary.light,
            onPrimary = onPrimary.light,
            primaryContainer = primaryContainer.light,
            onPrimaryContainer = onPrimaryContainer.light,
            inversePrimary = inversePrimary.light,
            secondary = secondary.light,
            onSecondary = onSecondary.light,
            secondaryContainer = secondaryContainer.light,
            onSecondaryContainer = onSecondaryContainer.light,
            tertiary = tertiary.light,
            onTertiary = onTertiary.light,
            tertiaryContainer = tertiaryContainer.light,
            onTertiaryContainer = onTertiaryContainer.light,
            background = background.light,
            onBackground = onBackground.light,
            surface = surface.light,
            onSurface = onSurface.light,
            surfaceVariant = surfaceVariant.light,
            onSurfaceVariant = onSurfaceVariant.light,
            surfaceTint = surfaceTint.light,
            inverseSurface = inverseSurface.light,
            inverseOnSurface = inverseOnSurface.light,
            error = error.light,
            onError = onError.light,
            errorContainer = errorContainer.light,
            onErrorContainer = onErrorContainer.light,
            outline = outline.light,
            outlineVariant = outlineVariant.light,
            scrim = scrim.light,

            ),
        functional = OdsFunctionalColors(
            positive = positive.light,
            onPositive = onPositive.light,
            negative = negative.light,
            onNegative = onNegative.light,
            info = info.light,
            alert = alert.light
        ),
        components = OdsComponentsColors() // TODO remove
    )

    val dark = OdsColors(
        colorScheme = darkColorScheme(
            primary = primary.dark,
            onPrimary = onPrimary.dark,
            primaryContainer = primaryContainer.dark,
            onPrimaryContainer = onPrimaryContainer.dark,
            inversePrimary = inversePrimary.dark,
            secondary = secondary.dark,
            onSecondary = onSecondary.dark,
            secondaryContainer = secondaryContainer.dark,
            onSecondaryContainer = onSecondaryContainer.dark,
            tertiary = tertiary.dark,
            onTertiary = onTertiary.dark,
            tertiaryContainer = tertiaryContainer.dark,
            onTertiaryContainer = onTertiaryContainer.dark,
            background = background.dark,
            onBackground = onBackground.dark,
            surface = surface.dark,
            onSurface = onSurface.dark,
            surfaceVariant = surfaceVariant.dark,
            onSurfaceVariant = onSurfaceVariant.dark,
            surfaceTint = surfaceTint.dark,
            inverseSurface = inverseSurface.dark,
            inverseOnSurface = inverseOnSurface.dark,
            error = error.dark,
            onError = onError.dark,
            errorContainer = errorContainer.dark,
            onErrorContainer = onErrorContainer.dark,
            outline = outline.dark,
            outlineVariant = outlineVariant.dark,
            scrim = scrim.dark,
        ),

        functional = OdsFunctionalColors(
            positive = positive.dark,
            onPositive = onPositive.dark,
            negative = negative.dark,
            onNegative = onNegative.dark,
            info = info.dark,
            alert = alert.dark
        ),
        components = OdsComponentsColors() // TODO remove
    )
}

data class OdsSemanticColorValue(val light: Color, val dark: Color)