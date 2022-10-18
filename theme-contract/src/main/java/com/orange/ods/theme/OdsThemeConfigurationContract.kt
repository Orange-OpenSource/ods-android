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

    /**
     * The name of the theme settings
     */
    val name: String

    /**
     * Colors definition applied in light mode.
     */
    val lightThemeColors: OdsColors

    /**
     * Colors definition applied in dark mode.
     */
    val darkThemeColors: OdsColors

    /**
     * Typography definition
     */
    val typography: Typography

    /**
     * Demo Guideline definition
     * If not overridden, no guideline will be displayed in the ODS Demo Application
     */
    val demoGuideline: OdsDemoGuideline
        get() = OdsDemoGuideline(typography)
    
}