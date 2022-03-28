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

internal sealed class GuidelinesCardItem(
    @DrawableRes var image: Int,
    @StringRes var title: Int
) {
    object Colour :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_colour,
            title = R.string.guideline_colour
        )
    object Typography :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_typography,
            title = R.string.guideline_typography
        )
    object Imagery :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_imagery,
            title = R.string.guideline_imagery
        )
    object Iconography :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_iconography,
            title = R.string.guideline_iconography
        )
}