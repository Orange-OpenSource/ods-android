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
import androidx.compose.ui.Alignment
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.bottomnavigation.ComponentBottomNavigation
import com.orange.ods.demo.ui.components.checkboxes.ComponentCheckboxesContent
import com.orange.ods.demo.ui.components.dialogs.ComponentDialogsContent
import com.orange.ods.demo.ui.components.progress.ComponentProgressContent
import com.orange.ods.demo.ui.components.radiobuttons.ComponentRadioButtonsContent
import com.orange.ods.demo.ui.components.sliders.ComponentSlidersContent
import com.orange.ods.demo.ui.components.switches.ComponentSwitchesContent

sealed class Component(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @DrawableRes val smallImageRes: Int?,
    @StringRes val descriptionRes: Int,
    val variants: List<Variant> = emptyList()
) {
    companion object {

        const val ImageBackgroundColor = 0xff1b1b1b
    }

    val id: Long = Component::class.sealedSubclasses.indexOf(this::class).toLong()

    val imageAlignment: Alignment
        get() = when (this) {
            BottomNavigation -> Alignment.TopCenter
            Lists -> Alignment.BottomCenter
            Sliders, TextFields -> Alignment.CenterEnd
            Buttons, Cards, Checkboxes, Chips, Dialogs, Progress, RadioButtons, Switches, Tabs -> Alignment.Center
        }

    object BottomNavigation :
        Component(R.string.component_bottom_navigation, R.drawable.il_bottom_navigation, null, R.string.component_bottom_navigation_description)

    object Buttons : Component(
        R.string.component_buttons,
        R.drawable.il_buttons,
        R.drawable.il_buttons_small,
        R.string.component_buttons_description,
        listOf(Variant.ButtonsContained, Variant.ButtonsOutlined, Variant.ButtonsText, Variant.ButtonsToggle)
    )

    object Cards : Component(
        R.string.component_cards,
        R.drawable.il_cards,
        null,
        R.string.component_card_description,
        listOf(Variant.CardImageFirst, Variant.CardTitleFirst, Variant.CardSmall)
    )

    object Checkboxes : Component(R.string.component_checkboxes, R.drawable.il_checkboxes, null, R.string.component_checkboxes_description)
    object Chips : Component(
        R.string.component_chips,
        R.drawable.il_chips,
        R.drawable.il_chips_small,
        R.string.component_chips_description,
        listOf(Variant.Chip, Variant.ChipFilter)
    )

    object Dialogs : Component(R.string.component_dialogs, R.drawable.il_dialogs, null, R.string.component_dialogs_description)

    object Lists : Component(
        R.string.component_lists,
        R.drawable.il_lists,
        null,
        R.string.component_lists_description,
        listOf(Variant.ListsSingleLine, Variant.ListsTwoLine, Variant.ListsThreeLine)
    )

    object Progress : Component(R.string.component_progress, R.drawable.il_progress, null, R.string.component_progress_description)
    object RadioButtons : Component(R.string.component_radio_buttons, R.drawable.il_radio_buttons, null, R.string.component_radio_buttons_description)
    object Sliders : Component(R.string.component_sliders, R.drawable.il_sliders, null, R.string.component_sliders_description)
    object Switches : Component(R.string.component_switches, R.drawable.il_switches, R.drawable.il_switches_small, R.string.component_switches_description)
    object TextFields : Component(
        R.string.component_text_fields,
        R.drawable.il_text_fields,
        R.drawable.il_text_fields_small,
        R.string.component_text_fields_description,
        listOf(Variant.TextFieldsFilled, Variant.TextFieldsOutlined)
    )

    object Tabs : Component(
        R.string.component_tabs,
        R.drawable.il_tabs,
        R.drawable.il_tabs_small,
        R.string.component_tabs_description,
        listOf(Variant.TabsFixed, Variant.TabsScrollable)
    )

    @ExperimentalMaterialApi
    @Composable
    fun Detail(onVariantClick: (Long) -> Unit) {
        return when (this) {
            BottomNavigation -> ComponentBottomNavigation()
            Checkboxes -> ComponentDetail(component = this) { ComponentCheckboxesContent() }
            Dialogs -> ComponentDetail(component = this) { ComponentDialogsContent() }
            Progress -> ComponentDetail(component = this) { ComponentProgressContent() }
            RadioButtons -> ComponentDetail(component = this) { ComponentRadioButtonsContent() }
            Sliders -> ComponentDetail(component = this) { ComponentSlidersContent() }
            Switches -> ComponentDetail(component = this) { ComponentSwitchesContent() }
            Buttons, Cards, Chips, Lists, TextFields, Tabs -> ComponentDetailWithVariants(component = this, onVariantClick = onVariantClick)
        }
    }
}

val components = Component::class.sealedSubclasses.mapNotNull { it.objectInstance }

sealed class Variant(
    @StringRes val titleRes: Int,
) {
    val id: Long = Variant::class.sealedSubclasses.indexOf(this::class).toLong()

    object ButtonsContained : Variant(R.string.component_buttons_contained)
    object ButtonsOutlined : Variant(R.string.component_buttons_outlined)
    object ButtonsText : Variant(R.string.component_buttons_text)
    object ButtonsToggle : Variant(R.string.component_buttons_toggle)

    object CardImageFirst : Variant(R.string.component_card_image_first)
    object CardTitleFirst : Variant(R.string.component_card_title_first)
    object CardSmall : Variant(R.string.component_card_small)

    object Chip : Variant(R.string.component_chip)
    object ChipFilter : Variant(R.string.component_chip_filter)

    object ListsSingleLine : Variant(R.string.component_lists_single_line)
    object ListsTwoLine : Variant(R.string.component_lists_two_line)
    object ListsThreeLine : Variant(R.string.component_lists_three_line)

    object TextFieldsFilled : Variant(R.string.component_text_fields_filled)
    object TextFieldsOutlined : Variant(R.string.component_text_fields_outlined)

    object TabsFixed : Variant(R.string.component_tabs_fixed)
    object TabsScrollable : Variant(R.string.component_tabs_scrollable)
}
