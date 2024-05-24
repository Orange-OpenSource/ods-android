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
import androidx.compose.material.Shapes
import com.orange.ods.theme.annotation.ExperimentalOdsApi
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.guideline.OdsGuideline
import com.orange.ods.theme.spacing.OdsSpacings
import com.orange.ods.theme.typography.OdsTypography

interface OdsThemeConfigurationContract : Parcelable {

    val name: String

    /**
     * Customization of the colors
     * Colors are different in light and dark
     */
    val colors: OdsThemeColors

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
     */
    val componentsConfiguration: OdsComponentsConfiguration
        get() = OdsComponentsConfiguration()

    /**
     * Guideline definition
     * By default a guideline typography is generated based on the theme configuration typography defined.
     */
    @OptIn(ExperimentalOdsApi::class)
    val guideline: OdsGuideline
        get() = OdsGuideline(typography)

}

data class OdsThemeColors(val lightColors: OdsColors, val darkColors: OdsColors)