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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.bottomnavigation.ComponentBottomNavigation
import com.orange.ods.demo.ui.components.buttons.ComponentButtonsContent
import com.orange.ods.demo.ui.components.checkboxes.ComponentCheckboxesContent
import com.orange.ods.demo.ui.components.dialogs.ComponentDialogsContent
import com.orange.ods.demo.ui.components.progress.ComponentProgressContent
import com.orange.ods.demo.ui.components.radiobuttons.ComponentRadioButtonsContent
import com.orange.ods.demo.ui.components.sliders.ComponentSlidersContent
import com.orange.ods.demo.ui.components.switches.ComponentSwitchesContent

sealed class Component(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val subComponents: List<SubComponent> = emptyList()
) {
    val id: Long = Component::class.sealedSubclasses.indexOf(this::class).toLong()

    object BottomNavigation : Component(R.string.component_bottom_navigation, R.drawable.il_bottom_navigation, R.string.component_bottom_navigation_description)
    object Buttons : Component(R.string.component_buttons, R.drawable.il_buttons, R.string.component_buttons_description)
    object Cards : Component(
        R.string.component_cards,
        R.drawable.il_cards,
        R.string.component_card_description,
        listOf(SubComponent.CardImageFirst, SubComponent.CardTitleFirst, SubComponent.CardSmall)
    )

    object Checkboxes : Component(R.string.component_checkboxes, R.drawable.il_checkbox, R.string.component_checkboxes_description)
    object Dialogs : Component(R.string.component_dialogs, R.drawable.il_dialogs, R.string.component_dialogs_description)
    object Lists : Component(
        R.string.component_lists,
        R.drawable.il_lists,
        R.string.component_lists_description,
        listOf(SubComponent.ListsOneLine, SubComponent.ListsTwoLines, SubComponent.ListsThreeLines)
    )

    object Progress : Component(R.string.component_progress, R.drawable.il_progress, R.string.component_progress_description)
    object RadioButtons : Component(R.string.component_radio_buttons, R.drawable.il_radio_buttons, R.string.component_radio_buttons_description)
    object Sliders : Component(R.string.component_sliders, R.drawable.il_slider, R.string.component_sliders_description)
    object Switches : Component(R.string.component_switches, R.drawable.il_switches, R.string.component_switches_description)
    object TextFields : Component(
        R.string.component_text_fields,
        R.drawable.il_text_fields,
        R.string.component_text_fields_description,
        listOf(SubComponent.TextFieldsFilled, SubComponent.TextFieldsOutlined)
    )

    object Tabs : Component(10L, R.string.component_tabs, R.drawable.il_tabs, R.string.component_tabs_description)

    @ExperimentalMaterialApi
    @Composable
    fun Detail(onSubComponentClick: (Long) -> Unit) {
        return when (this) {
            BottomNavigation -> ComponentBottomNavigation()
            Buttons -> ComponentDetail(component = this) { ComponentButtonsContent() }
            Checkboxes -> ComponentDetail(component = this) { ComponentCheckboxesContent() }
            Dialogs -> ComponentDetail(component = this) { ComponentDialogsContent() }
            Progress -> ComponentDetail(component = this) { ComponentProgressContent() }
            RadioButtons -> ComponentDetail(component = this) { ComponentRadioButtonsContent() }
            Sliders -> ComponentDetail(component = this) { ComponentSlidersContent() }
            Switches -> ComponentDetail(component = this) { ComponentSwitchesContent() }
            Cards, Lists, TextFields -> ComponentDetailWithSubComponents(component = this, onSubComponentClick = onSubComponentClick)
            Tabs -> ComponentDetail(component = this) { }
        }
    }
}

val components = Component::class.sealedSubclasses.mapNotNull { it.objectInstance }

sealed class SubComponent(
    @StringRes val titleRes: Int,
) {
    val id: Long = SubComponent::class.sealedSubclasses.indexOf(this::class).toLong()

    object CardImageFirst : SubComponent(R.string.component_card_image_first)
    object CardTitleFirst : SubComponent(R.string.component_card_title_first)
    object CardSmall : SubComponent(R.string.component_card_small)

    object ListsOneLine : SubComponent(R.string.component_lists_one_line)
    object ListsTwoLines : SubComponent(R.string.component_lists_two_lines)
    object ListsThreeLines : SubComponent(R.string.component_lists_three_lines)

    object TextFieldsFilled : SubComponent(R.string.component_text_fields_filled)
    object TextFieldsOutlined : SubComponent(R.string.component_text_fields_outlined)
}
