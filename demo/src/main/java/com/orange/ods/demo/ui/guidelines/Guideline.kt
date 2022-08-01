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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.MainDestinations

@Immutable
enum class Guideline(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    val route: String
) {
    Color(R.string.guideline_colors, R.drawable.il_colors, MainDestinations.GUIDELINE_COLORS),
    Typography(R.string.guideline_typography, R.drawable.il_typography, MainDestinations.GUIDELINE_TYPOGRAPHY);
    //Imagery(R.string.guideline_imagery, R.drawable.il_imagery),
    //Iconography(R.string.guideline_iconography, R.drawable.il_iconography);

    val imageBackgroundColor = Color(0xff1b1b1b)

    val imageContentScale: ContentScale
        get() = when (this) {
            Color -> ContentScale.FillBounds
            Typography -> ContentScale.Fit
        }
}
