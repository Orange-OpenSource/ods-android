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
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import com.orange.ods.theme.guideline.OdsDemoGuideline

const val OrangeThemeName = "Orange"

interface OdsThemeConfigurationContract : Parcelable {

    val name: String

    /**
     * Customization of the colors
     * Colors are different in light and dark
     */
    val colors: ThemeColors

    /**
     * Customization of the typography if needed
     * Note: If not overridden the MaterialTheme typography will be used
     */
    val typography: Typography
        get() = Typography()

    /**
     * Customization of the shapes if needed
     * Note: If not overridden the MaterialTheme shapes will be used
     */
    val shapes: Shapes
        get() = Shapes()

    /**
     * Demo Guideline definition
     * By default a guideline typography is generated based on the theme configuration typography defined.
     */
    val demoGuideline: OdsDemoGuideline
        get() = OdsDemoGuideline(typography)

}

data class ThemeColors(val lightColors: OdsColors, val darkColors: OdsColors)