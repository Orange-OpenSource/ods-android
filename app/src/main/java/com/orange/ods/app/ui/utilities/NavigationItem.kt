/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.utilities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orange.ods.app.R

enum class NavigationItem(@DrawableRes val iconResId: Int, @StringRes val textResId: Int) {
    Coffee(R.drawable.ic_coffee, R.string.navigation_item_coffee),
    CookingPot(R.drawable.ic_cooking_pot, R.string.navigation_item_cooking_pot),
    IceCream(R.drawable.ic_ice_cream, R.string.navigation_item_ice_cream),
    Restaurant(R.drawable.ic_restaurant, R.string.navigation_item_restaurant),
    Favorites(R.drawable.ic_heart, R.string.navigation_item_favorites),
    Information(R.drawable.ic_info, R.string.navigation_item_information)
}
