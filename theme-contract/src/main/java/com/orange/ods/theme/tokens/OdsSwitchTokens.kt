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
    val selectedHandleColor: OdsSemanticColorToken = OdsSemanticColorToken.OnPrimary,
    val selectedTrackColor: OdsSemanticColorToken = OdsSemanticColorToken.Primary,
    val unselectedHandleColor: OdsSemanticColorToken = OdsSemanticColorToken.Outline,
    val unselectedTrackColor: OdsSemanticColorToken = OdsSemanticColorToken.SurfaceVariant,
    val unselectedTrackOutlineColor: OdsSemanticColorToken = OdsSemanticColorToken.Outline
)