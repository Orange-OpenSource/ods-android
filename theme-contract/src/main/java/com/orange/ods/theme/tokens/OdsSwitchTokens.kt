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

package com.orange.ods.theme.tokens

open class OdsSwitchTokens(
    val selectedHandleColor: OdsSemanticColorsTokens = OdsSemanticColorsTokens.OnPrimary,
    val selectedTrackColor: OdsSemanticColorsTokens = OdsSemanticColorsTokens.Primary,
    val unselectedHandleColor: OdsSemanticColorsTokens = OdsSemanticColorsTokens.Outline,
    val unselectedTrackColor: OdsSemanticColorsTokens = OdsSemanticColorsTokens.SurfaceVariant,
    val unselectedTrackOutlineColor: OdsSemanticColorsTokens = OdsSemanticColorsTokens.Outline
)