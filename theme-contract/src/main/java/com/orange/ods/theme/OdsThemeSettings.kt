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

interface OdsThemeSettings : Parcelable {

    val lightThemeColors: OdsSupportedColors

    val darkThemeColors: OdsSupportedColors

    val typography: Typography

    /**
     * The colors of the supported theme guideline
     * Note: These colors are only used to be displayed in the theme demo application.
     */
    val guidelineColors: List<GuidelineColor>
}