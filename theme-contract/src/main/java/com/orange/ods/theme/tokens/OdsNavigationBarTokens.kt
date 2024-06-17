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


open class OdsNavigationBarTokens(
    val containerColor: OdsSemanticColorToken = OdsSemanticColorToken.Surface,
    val inactiveIconColor: OdsSemanticColorToken = OdsSemanticColorToken.OnSurfaceVariant,
    val inactiveLabelTextColor: OdsSemanticColorToken = OdsSemanticColorToken.OnSurfaceVariant,
    val activeIconColor: OdsSemanticColorToken = OdsSemanticColorToken.OnSecondaryContainer,
    val activeLabelTextColor: OdsSemanticColorToken = OdsSemanticColorToken.OnSurface,
    val activeIndicatorColor: OdsSemanticColorToken = OdsSemanticColorToken.SecondaryContainer,
)