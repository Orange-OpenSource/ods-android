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

package com.orange.ods.theme.orange

import com.orange.ods.theme.colors.OdsSemanticColorValue
import com.orange.ods.theme.colors.OdsSemanticColors

internal val OrangeSemanticColors = with(OrangeColorPalette) {
    OdsSemanticColors(
        primary = OdsSemanticColorValue(core.orange200, core.orange100),
        onPrimary = OdsSemanticColorValue(core.white100, core.black900),
        primaryContainer = OdsSemanticColorValue(core.tmpOrangeFFA14D, core.tmpOrangeFFA14D),
        onPrimaryContainer = OdsSemanticColorValue(core.black900, core.black900),
        inversePrimary = OdsSemanticColorValue(core.tmpOrangeFFB68E, core.tmpBrown9C4500),

        secondary = OdsSemanticColorValue(core.orange200, core.white100),
        onSecondary = OdsSemanticColorValue(core.black900, core.black900),
        secondaryContainer = OdsSemanticColorValue(core.tmpGrey333333, core.tmpGreyCCCCCC),
        onSecondaryContainer = OdsSemanticColorValue(core.white100, core.black900),

        tertiary = OdsSemanticColorValue(core.tmpGrey666666, core.tmpGreyCCCCCC),
        onTertiary = OdsSemanticColorValue(core.white100, core.black900),
        tertiaryContainer = OdsSemanticColorValue(core.tmpGreyCCCCCC, core.tmpGrey333333),
        onTertiaryContainer = OdsSemanticColorValue(core.black900, core.white100),
        background = OdsSemanticColorValue(core.white100, core.black900),
        onBackground = OdsSemanticColorValue(core.black900, core.white100),
        surface = OdsSemanticColorValue(core.white100, core.black900),
        onSurface = OdsSemanticColorValue(core.black900, core.white100),
        surfaceVariant = OdsSemanticColorValue(core.tmpGreyEEEEEE, core.tmpGrey333333),
        onSurfaceVariant = OdsSemanticColorValue(core.black900, core.tmpGreyEEEEEE),
        surfaceTint = OdsSemanticColorValue(core.tmpGrey999999, core.white100),
        inverseSurface = OdsSemanticColorValue(core.tmpBrown362F2C, core.tmpGreyEEEEEE),
        inverseOnSurface = OdsSemanticColorValue(core.white100, core.black900),
        error = OdsSemanticColorValue(functional.negative200, functional.tmpRedD53F15),
        onError = OdsSemanticColorValue(core.white100, core.black900),
        errorContainer = OdsSemanticColorValue(functional.tmpRedFFDAD6, functional.tmpRed93000A),
        onErrorContainer = OdsSemanticColorValue(functional.tmpRed410002, functional.tmpRedFFDAD6),
        outline = OdsSemanticColorValue(core.black900, core.tmpGreyEEEEEE),
        outlineVariant = OdsSemanticColorValue(core.tmpGreyEBEBEB, core.tmpBrown52443C),
        scrim = OdsSemanticColorValue(core.black900, core.black900),
        positive = OdsSemanticColorValue(functional.positive200, functional.positive100),
        onPositive = OdsSemanticColorValue(core.white100, core.black900),
        negative = OdsSemanticColorValue(functional.negative200, functional.negative100),
        onNegative = OdsSemanticColorValue(core.white100, core.white100),
        info = OdsSemanticColorValue(functional.info200, functional.info100),
        alert = OdsSemanticColorValue(functional.alert200, functional.alert100)
    )
}