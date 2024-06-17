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

import com.orange.ods.theme.colors.OdsSemanticColorValue
import com.orange.ods.theme.colors.OdsSemanticColors

internal val InnovationCupSemanticColors = with(InnovationCupPalette) {
    OdsSemanticColors(
        primary = OdsSemanticColorValue(core.primary40, core.primary80),
        onPrimary = OdsSemanticColorValue(core.white, core.primary20),
        primaryContainer = OdsSemanticColorValue(core.primary90, core.primary30),
        onPrimaryContainer = OdsSemanticColorValue(core.white, core.white),
        inversePrimary = OdsSemanticColorValue(core.primary80, core.primary40),

        secondary = OdsSemanticColorValue(core.secondary40, core.secondary80),
        onSecondary = OdsSemanticColorValue(core.white, core.secondary20),
        secondaryContainer = OdsSemanticColorValue(core.secondary90, core.secondary30),
        onSecondaryContainer = OdsSemanticColorValue(core.secondary10, core.secondary90),

        tertiary = OdsSemanticColorValue(core.tertiary40, core.tertiary80),
        onTertiary = OdsSemanticColorValue(core.white, core.tertiary20),
        tertiaryContainer = OdsSemanticColorValue(core.tertiary90, core.tertiary30),
        onTertiaryContainer = OdsSemanticColorValue(core.tertiary10, core.tertiary90),
        background = OdsSemanticColorValue(core.white, core.black),
        onBackground = OdsSemanticColorValue(core.black, core.white),
        surface = OdsSemanticColorValue(core.neutral98, core.neutral6),
        onSurface = OdsSemanticColorValue(core.black, core.neutral90),
        surfaceVariant = OdsSemanticColorValue(core.neutral94, core.neutral24),
        onSurfaceVariant = OdsSemanticColorValue(core.black, core.neutralVariant90),
        surfaceTint = OdsSemanticColorValue(core.white, core.neutral98),
        inverseSurface = OdsSemanticColorValue(core.neutral20, core.neutral90),
        inverseOnSurface = OdsSemanticColorValue(core.neutral95, core.neutral20),
        error = OdsSemanticColorValue(core.error40, core.error80),
        onError = OdsSemanticColorValue(core.white, core.error20),
        errorContainer = OdsSemanticColorValue(core.error90, core.error30),
        onErrorContainer = OdsSemanticColorValue(core.white, core.error90),
        outline = OdsSemanticColorValue(core.neutralVariant50, core.neutralVariant60),
        outlineVariant = OdsSemanticColorValue(core.neutralVariant80, core.neutralVariant30),
        scrim = OdsSemanticColorValue(core.black, core.black),
        positive = OdsSemanticColorValue(core.green, core.green),
        onPositive = OdsSemanticColorValue(core.white, core.white),
        negative = OdsSemanticColorValue(core.red, core.red),
        onNegative = OdsSemanticColorValue(core.white, core.white),
        info = OdsSemanticColorValue(core.info, core.info),
        alert = OdsSemanticColorValue(core.yellow, core.yellow)
    )
}