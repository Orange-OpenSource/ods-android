/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orange.ods.demo.R

sealed class CardItem(
    @DrawableRes var image: Int,
    @StringRes var imageDescription: Int,
    @StringRes var title: Int,
    @StringRes var subTitle: Int?,
) {
    object Colour :
        CardItem(
            image = R.drawable.picture_guideline_colour,
            imageDescription = R.string.colour_text,
            title = R.string.colour_text,
            subTitle = null
        )
    object Typography :
        CardItem(
            image = R.drawable.picture_guideline_typography,
            imageDescription = R.string.typography_text,
            title = R.string.typography_text,
            subTitle = null
        )
    object Imagery :
        CardItem(
            image = R.drawable.picture_guideline_imagery,
            imageDescription = R.string.imagery_text,
            title = R.string.imagery_text,
            subTitle = null
        )
    object Iconography :
        CardItem(
            image = R.drawable.picture_guideline_iconography,
            imageDescription = R.string.iconography_text,
            title = R.string.iconography_text,
            subTitle = null
        )
}
