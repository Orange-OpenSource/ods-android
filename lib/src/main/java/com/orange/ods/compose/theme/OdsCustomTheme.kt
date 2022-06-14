/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.theme

import android.os.Parcelable
import androidx.compose.material.Typography

abstract class OdsCustomTheme : Parcelable {

    abstract val lightThemeColors: OdsColors
    abstract val darkThemeColors: OdsColors

    open fun getTypography() = Typography()

}