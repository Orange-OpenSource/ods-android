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

data class ComponentsItem(
    @DrawableRes var image: Int,
    @StringRes var title: Int,
    var route: String
)

val componentsItems = listOf(
    ComponentsItem(
        image = R.drawable.picture_component_buttons,
        title = R.string.component_buttons,
        route = ComponentsNavigationItem.Buttons.route
    ),
    ComponentsItem(
        image = R.drawable.picture_component_controls,
        title = R.string.component_controls,
        route = ComponentsNavigationItem.Controls.route
    ),
    /*
    ComponentsScreenItem(
        image = R.drawable.picture_component_botton_navigation,
        title = R.string.component_bottom_navigation,
        route = ComponentsNavigationItem.BottomNavigation.route
    ),
    */
    ComponentsItem(
        image = R.drawable.picture_component_cards,
        title = R.string.component_cards,
        route = ComponentsNavigationItem.Cards.route
    )
)