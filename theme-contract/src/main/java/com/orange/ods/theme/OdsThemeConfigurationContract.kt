/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme

import android.os.Parcelable
import androidx.compose.material.Typography
import com.orange.ods.theme.guideline.OdsDemoGuideline

interface OdsThemeConfigurationContract : Parcelable {

    val name: String

    /**
     * Customization of the colors
     * Colors are different in light and dark
     */
    val colors: ThemeColors

    /**
     * Customization of the typography
     */
    val typography: Typography

    /**
     * Demo Guideline definition
     * If not overridden, no guideline will be displayed in the ODS Demo Application
     */
    val demoGuideline: OdsDemoGuideline
        get() = OdsDemoGuideline(typography)

}

data class ThemeColors(val lightColors: OdsColors, val darkColors: OdsColors)