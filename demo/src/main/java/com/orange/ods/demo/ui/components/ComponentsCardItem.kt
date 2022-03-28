/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orange.ods.demo.R

sealed class ComponentsCardItem(
    @DrawableRes var image: Int,
    @StringRes var title: Int,
) {
    object Buttons :
        ComponentsCardItem(
            image = R.drawable.picture_component_buttons,
            title = R.string.component_buttons
        )
    object Controls :
        ComponentsCardItem(
            image = R.drawable.picture_component_controls,
            title = R.string.component_controls
        )
    object BottomNavigation :
        ComponentsCardItem(
            image = R.drawable.picture_component_botton_navigation,
            title = R.string.component_bottom_navigation
        )
}
