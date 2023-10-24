/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.guidelines

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.orange.ods.app.R

@Immutable
enum class Guideline(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    val route: String
) {
    Color(R.string.guideline_color, R.drawable.il_color, GuidelinesNavigation.GuidelineColor),
    Typography(R.string.guideline_typography, R.drawable.il_typography, GuidelinesNavigation.GuidelineTypography),
    Spacing(R.string.guideline_spacing, R.drawable.il_spacing, GuidelinesNavigation.GuidelineSpacing);

    val imageBackgroundColor = Color(0xff1b1b1b)

    val imageContentScale: ContentScale
        get() = when (this) {
            Color -> ContentScale.FillBounds
            Typography, Spacing -> ContentScale.Fit
        }

    val imageAlignment: Alignment
        get() = when (this) {
            Color, Typography -> Alignment.Center
            Spacing -> Alignment.BottomCenter
        }
}
