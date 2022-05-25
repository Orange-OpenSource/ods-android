/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose

import android.os.Parcelable
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors

abstract class OrangeTheme : Parcelable {

    open fun getLightThemeColors() = lightColors()

    open fun getDarkThemeColors() = darkColors()

    open fun getTypography() = Typography()
}