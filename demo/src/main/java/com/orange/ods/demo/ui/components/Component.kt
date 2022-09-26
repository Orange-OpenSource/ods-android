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
import androidx.compose.ui.Alignment
import com.orange.ods.demo.R

sealed class Component(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @DrawableRes val smallImageRes: Int?,
    @StringRes val descriptionRes: Int,
    val variants: List<Variant> = emptyList(),
    val composableName: String? = null
) {
    companion object {
        const val ImageBackgroundColor = 0xff1b1b1b
    }

    val id: Long = Component::class.sealedSubclasses.indexOf(this::class).toLong()

    val imageAlignment: Alignment
        get() = when (this) {
            AppBarsTop, BottomNavigation -> Alignment.TopCenter
            Lists -> Alignment.BottomCenter
            Sliders, TextFields -> Alignment.CenterEnd
            Buttons, Cards, Checkboxes, Chips, Dialogs, Progress, RadioButtons, Switches, Tabs -> Alignment.Center
        }

    object AppBarsTop : Component(
        R.string.component_app_bars_top,
        R.drawable.il_app_bars_top,
        R.drawable.il_app_bars_top_small,
        R.string.component_app_bars_top_description,
        listOf(Variant.AppBarsTopRegular)
    )

    object BottomNavigation :
        Component(
            R.string.component_bottom_navigation,
            R.drawable.il_bottom_navigation,
            null,
            R.string.component_bottom_navigation_description,
            composableName = "OdsBottomNavigation"
        )

    object Buttons : Component(
        R.string.component_buttons,
        R.drawable.il_buttons,
        R.drawable.il_buttons_small,
        R.string.component_buttons_description,
        listOf(Variant.ButtonsPrimary, Variant.ButtonsContained, Variant.ButtonsOutlined, Variant.ButtonsText, Variant.ButtonsFunctional, Variant.ButtonsToggle)
    )

    object Cards : Component(
        R.string.component_cards,
        R.drawable.il_cards,
        null,
        R.string.component_card_description,
        listOf(Variant.CardImageFirst, Variant.CardTitleFirst, Variant.CardSmall)
    )

    object Checkboxes : Component(
        R.string.component_checkboxes,
        R.drawable.il_checkboxes,
        null,
        R.string.component_checkboxes_description,
        composableName = "OdsCheckbox"
    )

    object Chips : Component(
        R.string.component_chips,
        R.drawable.il_chips,
        R.drawable.il_chips_small,
        R.string.component_chips_description,
        listOf(Variant.Chip, Variant.ChipFilter)
    )

    object Dialogs : Component(
        R.string.component_dialogs,
        R.drawable.il_dialogs,
        null,
        R.string.component_dialogs_description,
        composableName = "OdsAlertDialog"
    )

    object Lists : Component(
        R.string.component_lists,
        R.drawable.il_lists,
        null,
        R.string.component_lists_description,
        composableName = "OdsListItem"
    )

    object Progress : Component(
        R.string.component_progress,
        R.drawable.il_progress,
        null,
        R.string.component_progress_description,
        listOf(Variant.ProgressBar, Variant.ProgressActivityIndicator)
    )

    object RadioButtons : Component(
        R.string.component_radio_buttons,
        R.drawable.il_radio_buttons,
        null,
        R.string.component_radio_buttons_description,
        composableName = "OdsRadioButton"
    )

    object Sliders : Component(
        R.string.component_sliders,
        R.drawable.il_sliders,
        null,
        R.string.component_sliders_description,
        composableName = "OdsSlider"
    )

    object Switches : Component(
        R.string.component_switches,
        R.drawable.il_switches,
        R.drawable.il_switches_small,
        R.string.component_switches_description,
        composableName = "OdsSwitch"
    )

    object TextFields : Component(
        R.string.component_text_fields,
        R.drawable.il_text_fields,
        R.drawable.il_text_fields_small,
        R.string.component_text_fields_description,
        listOf(Variant.TextFieldFilledText, Variant.TextFieldFilledPassword, Variant.TextFieldOutlinedText, Variant.TextFieldOutlinedPassword)
    )

    object Tabs : Component(
        R.string.component_tabs,
        R.drawable.il_tabs,
        R.drawable.il_tabs_small,
        R.string.component_tabs_description,
        listOf(Variant.TabsFixed, Variant.TabsScrollable)
    )
}

val components = Component::class.sealedSubclasses.mapNotNull { it.objectInstance }

sealed class Variant(
    @StringRes val titleRes: Int,
    val composableName: String,
    val section: Section? = null
) {

    enum class Section(@StringRes val titleRes: Int) {
        TextFieldOutlined(R.string.component_text_field_outlined),
        TextFieldFilled(R.string.component_text_field_filled)
    }

    val id: Long = Variant::class.sealedSubclasses.indexOf(this::class).toLong()

    object AppBarsTopRegular : Variant(R.string.component_app_bars_top_regular, "OdsTopAppBar")

    object ButtonsPrimary : Variant(R.string.component_buttons_highest_emphasis, "OdsButton with OdsButtonStyle.Primary")
    object ButtonsContained : Variant(R.string.component_buttons_high_emphasis, "OdsButton with OdsButtonStyle.Default")
    object ButtonsOutlined : Variant(R.string.component_buttons_medium_emphasis, "OdsOutlinedButton")
    object ButtonsText : Variant(R.string.component_buttons_low_emphasis, "OdsTextButton")
    object ButtonsFunctional : Variant(R.string.component_buttons_functional, "OdsButton with a functional style")
    object ButtonsToggle : Variant(R.string.component_buttons_toggle, "OdsIconToggleButton")

    object CardImageFirst : Variant(R.string.component_card_image_first, "OdsImageFirstCard")
    object CardTitleFirst : Variant(R.string.component_card_title_first, "OdsTitleFirstCard")
    object CardSmall : Variant(R.string.component_card_small, "OdsSmallCard")

    object Chip : Variant(R.string.component_chip, "OdsChip")
    object ChipFilter : Variant(R.string.component_chip_filter, "OdsFilterChip")

    object ProgressBar : Variant(R.string.component_progress_bar, "LinearProgressIndicator")
    object ProgressActivityIndicator : Variant(R.string.component_progress_activity_indicator, "CircularProgressIndicator")

    object TextFieldFilledText : Variant(R.string.component_text_field_text, "OdsTextField", Section.TextFieldFilled)
    object TextFieldFilledPassword : Variant(R.string.component_text_field_password, "OdsPasswordTextField", Section.TextFieldFilled)
    object TextFieldOutlinedText : Variant(R.string.component_text_field_text, "OdsOutlinedTextField", Section.TextFieldOutlined)
    object TextFieldOutlinedPassword : Variant(R.string.component_text_field_password, "OdsPasswordOutlinedTextField", Section.TextFieldOutlined)

    object TabsFixed : Variant(R.string.component_tabs_fixed, "OdsTabRow")
    object TabsScrollable : Variant(R.string.component_tabs_scrollable, "OdsScrollableTabRow")
}
