/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orange.ods.demo.R

enum class NavigationItem(@DrawableRes val iconResId: Int, @StringRes val textResId: Int) {

    Coffee(R.drawable.ic_coffee, R.string.navigation_item_coffee),
    CookingPot(R.drawable.ic_cooking_pot, R.string.navigation_item_cooking_pot),
    IceCream(R.drawable.ic_ice_cream, R.string.navigation_item_ice_cream),
    Restaurant(R.drawable.ic_restaurant, R.string.navigation_item_restaurant),
    Favorites(R.drawable.ic_heart, R.string.navigation_item_favorites),
    Information(R.drawable.ic_info, R.string.navigation_item_information)
}
