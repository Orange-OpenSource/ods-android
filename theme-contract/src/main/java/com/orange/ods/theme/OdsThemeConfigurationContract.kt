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

package com.orange.ods.theme

import android.os.Parcelable
import androidx.compose.material3.Shapes
import com.orange.ods.theme.colors.OdsColorPalette
import com.orange.ods.theme.colors.OdsSemanticColors
import com.orange.ods.theme.spacing.OdsSpacings
import com.orange.ods.theme.tokens.OdsComponentsTokens
import com.orange.ods.theme.typography.OdsTypography

interface OdsThemeConfigurationContract<T> : Parcelable where T : OdsColorPalette {

    val name: String

    /**
     * Customization of the colors
     */
    val colors: OdsThemeColors<T>

    /**
     * Customization of the typography if needed
     */
    val typography: OdsTypography
        get() = OdsTypography()

    /**
     * Customization of the spacings if needed
     */
    val spacings: OdsSpacings
        get() = OdsSpacings()

    /**
     * Customization of the shapes if needed
     * Note: If not overridden the MaterialTheme shapes will be used
     */
    val shapes: Shapes
        get() = Shapes()

    /**
     * Customization of the ODS components if needed
     * TODO will be progressively replaced by [OdsComponentsTokens]
     */
    val componentsConfiguration: OdsComponentsConfiguration
        get() = OdsComponentsConfiguration()

    /**
     * Customization of the ODS components if needed
     */
    val componentsTokens: OdsComponentsTokens
        get() = OdsComponentsTokens()
}

data class OdsThemeColors<T>(val palette: T, val semanticColors: OdsSemanticColors) where T : OdsColorPalette

