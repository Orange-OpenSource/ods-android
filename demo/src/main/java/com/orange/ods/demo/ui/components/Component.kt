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

sealed class Component(
    val id: Long,
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val subComponents: List<SubComponent> = emptyList()
) {
    object BottomNavigation : Component(1L, R.string.component_bottom_navigation, R.drawable.picture_component_bottom_navigation, R.string.component_bottom_navigation_description)
    object Buttons : Component(2L, R.string.component_buttons, R.drawable.picture_component_buttons, R.string.component_buttons_description)
    object Cards : Component(
        3L,
        R.string.component_cards,
        R.drawable.picture_component_cards,
        R.string.component_card_description,
        listOf(SubComponent.CardImageFirst, SubComponent.CardTitleFirst, SubComponent.CardSmall)
    )

    object Controls : Component(
        4L,
        R.string.component_controls,
        R.drawable.picture_component_controls,
        R.string.component_controls_description,
        listOf(SubComponent.ControlsCheckboxes, SubComponent.ControlsRadioButtons, SubComponent.ControlsSliders, SubComponent.ControlsSwitches)
    )

    object Progress : Component(5L, R.string.component_progress, R.drawable.picture_component_progress, R.string.component_progress_description)
    object Lists : Component(
        6L,
        R.string.component_lists,
        R.drawable.picture_component_lists,
        R.string.component_lists_description,
        listOf(SubComponent.ListsOneLine, SubComponent.ListsTwoLines, SubComponent.ListsThreeLines)
    )
}

val components = listOf(Component.BottomNavigation, Component.Buttons, Component.Cards, Component.Controls, Component.Progress, Component.Lists)

sealed class SubComponent(
    val id: Long,
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int? = null
) {
    object CardImageFirst : SubComponent(1L, R.string.component_card_image_first)
    object CardTitleFirst : SubComponent(2L, R.string.component_card_title_first)
    object CardSmall : SubComponent(3L, R.string.component_card_small)

    object ControlsCheckboxes : SubComponent(4L, R.string.component_controls_checkboxes, R.string.component_controls_checkboxes_description)
    object ControlsRadioButtons : SubComponent(5L, R.string.component_controls_radio_buttons, R.string.component_controls_radio_buttons_description)
    object ControlsSwitches : SubComponent(6L, R.string.component_controls_switches, R.string.component_controls_switches_description)
    object ControlsSliders : SubComponent(7L, R.string.component_controls_sliders, R.string.component_controls_sliders_description)

    object ListsOneLine : SubComponent(8L, R.string.component_lists_one_line)
    object ListsTwoLines : SubComponent(9L, R.string.component_lists_two_lines)
    object ListsThreeLines : SubComponent(10L, R.string.component_lists_three_lines)
}
