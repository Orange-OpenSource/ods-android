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
import com.orange.ods.demo.ui.ComponentsNavigationItem

sealed class ComponentsCardItem(
    @DrawableRes var image: Int,
    @StringRes var title: Int,
    var route: String
) {
    object Buttons :
        ComponentsCardItem(
            image = R.drawable.picture_component_buttons,
            title = R.string.component_buttons,
            route = ComponentsNavigationItem.Buttons.route
        )
    object Controls :
        ComponentsCardItem(
            image = R.drawable.picture_component_controls,
            title = R.string.component_controls,
            route = ComponentsNavigationItem.Controls.route
        )
    object BottomNavigation :
        ComponentsCardItem(
            image = R.drawable.picture_component_botton_navigation,
            title = R.string.component_bottom_navigation,
            route = ComponentsNavigationItem.BottomNavigation.route
        )
}
