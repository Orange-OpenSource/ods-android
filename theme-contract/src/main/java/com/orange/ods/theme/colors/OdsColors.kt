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

import androidx.compose.material.Colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * ODS color system.
 *
 * The ODS color system can help you create an ODS color theme that reflects your brand or style.
 * Default components' colors are based on the provided [materialColors] but you can override these colors by providing your colors for each component. As an
 * example, if you need to change the switches' colors you can provide your own [OdsSwitchColors] in the ODS color system.
 */
class OdsColors(
    val materialColors: Colors,
    functionalColors: OdsFunctionalColors,
    componentColors: OdsComponentColors.Builder
) {
    var primary = materialColors.primary
        private set
    var primaryVariant = materialColors.primaryVariant
        private set
    var secondary = materialColors.secondary
        private set
    var secondaryVariant = materialColors.secondaryVariant
        private set
    var background = materialColors.background
        private set
    var surface = materialColors.surface
        private set
    var error = materialColors.error
        private set
    var onPrimary = materialColors.onPrimary
        private set
    var onSecondary = materialColors.onSecondary
        private set
    var onBackground = materialColors.onBackground
        private set
    var onSurface = materialColors.onSurface
        private set
    var onError = materialColors.onError
        private set

    var functional by mutableStateOf(functionalColors)
        private set

    val component = componentColors.build(materialColors)
}