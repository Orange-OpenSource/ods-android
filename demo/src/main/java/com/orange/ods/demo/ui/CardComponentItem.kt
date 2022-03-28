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

sealed class CardComponentItem(
    @DrawableRes var image: Int,
    @StringRes var imageDescription: Int,
    @StringRes var title: Int,
) {
    object Buttons :
        CardComponentItem(
            image = R.drawable.picture_component_buttons,
            imageDescription = R.string.buttons_text,
            title = R.string.buttons_text
        )
    object Controls :
        CardComponentItem(
            image = R.drawable.picture_component_controls,
            imageDescription = R.string.controls_text,
            title = R.string.controls_text
        )
    object BottomNavigation :
        CardComponentItem(
            image = R.drawable.picture_component_botton_navigation,
            imageDescription = R.string.bottom_navigation_text,
            title = R.string.bottom_navigation_text
        )
}
