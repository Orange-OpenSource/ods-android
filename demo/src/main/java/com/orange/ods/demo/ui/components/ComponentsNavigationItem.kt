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

sealed class ComponentsNavigationItem(
    @StringRes var title: Int,
    var route: String,
    @DrawableRes var image: Int
) {
    object BottomNavigation : ComponentsNavigationItem(R.string.component_bottom_navigation, "components/bottom_navigation", R.drawable.picture_component_botton_navigation)
    object Buttons : ComponentsNavigationItem(R.string.component_buttons, "components/buttons", R.drawable.picture_component_buttons)
    object Cards : ComponentsNavigationItem(R.string.component_cards, "components/card", R.drawable.picture_component_cards)
    object Controls : ComponentsNavigationItem(R.string.component_controls, "components/controls", R.drawable.picture_component_controls)
    object Progress : ComponentsNavigationItem(R.string.component_progress, "components/progress", R.drawable.picture_component_progress)
    object Lists : ComponentsNavigationItem(R.string.component_lists, "components/lists", R.drawable.picture_component_lists)
}

sealed class ComponentsSubLevelNavigationItem(
    @StringRes var title: Int,
    var route: String
) {
    object CardImageFirst : ComponentsSubLevelNavigationItem(R.string.component_card_image_first, "components/card/card_image_first")
    object CardTitleFirst : ComponentsSubLevelNavigationItem(R.string.component_card_title_first, "components/card/card_title_first")
    object CardSmall : ComponentsSubLevelNavigationItem(R.string.component_card_small, "components/card/small_card_view")

    object ControlsCheckboxes : ComponentsSubLevelNavigationItem(R.string.component_controls_checkboxes, "components/controls/checkboxes")
    object ControlsRadioButtons : ComponentsSubLevelNavigationItem(R.string.component_controls_radio_buttons, "components/controls/radio_buttons")
    object ControlsSwitches : ComponentsSubLevelNavigationItem(R.string.component_controls_switches, "components/controls/switches")
    object ControlsSliders : ComponentsSubLevelNavigationItem(R.string.component_controls_sliders, "components/controls/sliders")
}