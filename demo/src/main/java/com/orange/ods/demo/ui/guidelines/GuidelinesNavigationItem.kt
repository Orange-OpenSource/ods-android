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
import com.orange.ods.demo.R

sealed class GuidelinesNavigationItem(
    @StringRes var title: Int,
    var route: String,
    @DrawableRes var image: Int
) {
    object Color : GuidelinesNavigationItem(R.string.guideline_colour, "guidelines/color", R.drawable.picture_guideline_colour)
    object Typography : GuidelinesNavigationItem(R.string.guideline_typography, "guidelines/typography", R.drawable.picture_guideline_typography)
    object Imagery : GuidelinesNavigationItem(R.string.guideline_imagery, "guidelines/imagery", R.drawable.picture_guideline_imagery)
    object Iconography : GuidelinesNavigationItem(R.string.guideline_iconography, "guidelines/iconography", R.drawable.picture_guideline_iconography)
}
