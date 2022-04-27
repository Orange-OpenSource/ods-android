/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.MainDestinations

@Immutable
data class Guideline(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    val route: String
)

val guidelines = listOf(
    Guideline(R.string.guideline_colors, R.drawable.il_colors, MainDestinations.GUIDELINE_COLORS),
    Guideline(R.string.guideline_typography, R.drawable.il_typography, MainDestinations.GUIDELINE_TYPOGRAPHY),
    //Guideline(R.string.guideline_imagery, R.drawable.il_imagery),
    //Guideline(R.string.guideline_iconography, R.drawable.il_iconography)
)