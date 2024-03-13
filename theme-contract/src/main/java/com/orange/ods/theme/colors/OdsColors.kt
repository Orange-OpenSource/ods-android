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
 * Default components' colors are based on the provided [material] but you can override these colors by providing your colors for each component. As an
 * example, if you need to change the switches' colors you can provide your own [OdsSwitchColors] in the ODS color system.
 */
class OdsColors(
    val material: Colors,
    functional: OdsFunctionalColors,
    components: OdsComponentsColors.Builder
) {
    var primary = material.primary
        private set
    var primaryVariant = material.primaryVariant
        private set
    var secondary = material.secondary
        private set
    var secondaryVariant = material.secondaryVariant
        private set
    var background = material.background
        private set
    var surface = material.surface
        private set
    var error = material.error
        private set
    var onPrimary = material.onPrimary
        private set
    var onSecondary = material.onSecondary
        private set
    var onBackground = material.onBackground
        private set
    var onSurface = material.onSurface
        private set
    var onError = material.onError
        private set

    var functional by mutableStateOf(functional)
        private set

    val components = components.build(material)
}