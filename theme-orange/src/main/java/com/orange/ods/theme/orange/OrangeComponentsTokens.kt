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

import com.orange.ods.theme.tokens.OdsComponentsTokens
import com.orange.ods.theme.tokens.OdsNavigationBarTokens
import com.orange.ods.theme.tokens.OdsSemanticColorsTokens
import com.orange.ods.theme.tokens.OdsSystemBarsTokens

internal val OrangeComponentsTokens = OdsComponentsTokens(
    navigationBar = OdsNavigationBarTokens(
        inactiveIconColor = OdsSemanticColorsTokens.OnSurface,
        inactiveLabelTextColor = OdsSemanticColorsTokens.OnSurface,
        activeIconColor = OdsSemanticColorsTokens.Primary,
        activeLabelTextColor = OdsSemanticColorsTokens.Primary,
        activeIndicatorColor = OdsSemanticColorsTokens.SurfaceVariant,
    ),
    systemBar = OdsSystemBarsTokens(
        containerColor = OdsSemanticColorsTokens.Surface,
        appearanceLight = true,
    )
)