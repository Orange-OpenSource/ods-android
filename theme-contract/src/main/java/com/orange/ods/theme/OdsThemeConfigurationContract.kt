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

    val lightThemeColors: OdsColors

    val darkThemeColors: OdsColors

    val typography: Typography

    val demoGuideline: OdsDemoGuideline
        get() = OdsDemoGuideline(typography)
}