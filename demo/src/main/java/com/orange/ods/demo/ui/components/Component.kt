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
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsButtonStyle
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
            Buttons, Cards, SheetsBottom, Checkboxes, Chips, Dialogs, FloatingActionButtons, Menus, Progress, RadioButtons, Snackbars, Switches, Tabs -> Alignment.Center
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
            composableName = OdsComponent.OdsBottomNavigation.name
        )

    object Buttons : Component(
        R.string.component_buttons,
        R.drawable.il_buttons,
        R.drawable.il_buttons_small,
        R.string.component_buttons_description,
        listOf(
            Variant.ButtonsPrimary,
            Variant.ButtonsDefault,
            Variant.ButtonsOutlined,
            Variant.ButtonsText,
            Variant.ButtonsFunctional,
            Variant.ButtonsToggle,
            Variant.ButtonsIcon,
        )
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
        composableName = OdsComponent.OdsCheckbox.name
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
        composableName = OdsComponent.OdsAlertDialog.name
    )

    object FloatingActionButtons : Component(
        R.string.component_floating_action_buttons,
        R.drawable.il_fab,
        null,
        R.string.component_floating_action_buttons_description,
        composableName = OdsComponent.OdsFloatingActionButton.name
    )

    object Lists : Component(
        R.string.component_lists,
        R.drawable.il_lists,
        null,
        R.string.component_lists_description,
        composableName = OdsComponent.OdsListItem.name
    )

    object Menus : Component(
        R.string.component_menus,
        R.drawable.il_menus,
        null,
        R.string.component_menus_description,
        listOf(Variant.DropdownMenu, Variant.ExposedDropdownMenu)
    )

    object Progress : Component(
        R.string.component_progress,
        R.drawable.il_progress,
        null,
        R.string.component_progress_description,
        listOf(Variant.ProgressLinear, Variant.ProgressCircular)
    )

    object RadioButtons : Component(
        R.string.component_radio_buttons,
        R.drawable.il_radio_buttons,
        null,
        R.string.component_radio_buttons_description,
        composableName = OdsComponent.OdsRadioButton.name
    )

    object SheetsBottom : Component(
        R.string.component_sheets_bottom,
        R.drawable.il_bottom_sheet,
        null,
        R.string.component_sheet_bottom_description,
        composableName = ""
    )

    object Sliders : Component(
        R.string.component_sliders,
        R.drawable.il_sliders,
        null,
        R.string.component_sliders_description,
        composableName = OdsComponent.OdsSlider.name
    )

    object Snackbars : Component(
        R.string.component_snackbars,
        R.drawable.il_snackbars,
        R.drawable.il_snackbars_small,
        R.string.component_snackbars_description,
        composableName = OdsComponent.OdsSnackbar.name
    )

    object Switches : Component(
        R.string.component_switches,
        R.drawable.il_switches,
        R.drawable.il_switches_small,
        R.string.component_switches_description,
        composableName = OdsComponent.OdsSwitch.name
    )

    object TextFields : Component(
        R.string.component_text_fields,
        R.drawable.il_text_fields,
        R.drawable.il_text_fields_small,
        R.string.component_text_fields_description,
        listOf(Variant.TextField, Variant.TextFieldPassword)
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
    val composableName: String
) {

    val id: Long = Variant::class.sealedSubclasses.indexOf(this::class).toLong()

    object AppBarsTopRegular : Variant(R.string.component_app_bars_top_regular, OdsComponent.OdsTopAppBar.name)

    object ButtonsPrimary : Variant(R.string.component_buttons_highest_emphasis, "${OdsComponent.OdsButton.name} with ${OdsButtonStyle.Primary.name}")
    object ButtonsDefault : Variant(R.string.component_buttons_high_emphasis, "${OdsComponent.OdsButton.name} with ${OdsButtonStyle.Default.name}")
    object ButtonsOutlined : Variant(R.string.component_buttons_medium_emphasis, OdsComponent.OdsOutlinedButton.name)
    object ButtonsText : Variant(R.string.component_buttons_low_emphasis, OdsComponent.OdsTextButton.name)
    object ButtonsFunctional : Variant(R.string.component_buttons_functional, "${OdsComponent.OdsButton.name} with a functional style")
    object ButtonsToggle : Variant(R.string.component_buttons_toggle, OdsComponent.OdsIconToggleButton.name)
    object ButtonsIcon : Variant(R.string.component_buttons_icon, OdsComponent.OdsIconButton.name)

    object CardImageFirst : Variant(R.string.component_card_image_first, OdsComponent.OdsImageFirstCard.name)
    object CardTitleFirst : Variant(R.string.component_card_title_first, OdsComponent.OdsTitleFirstCard.name)
    object CardSmall : Variant(R.string.component_card_small, OdsComponent.OdsSmallCard.name)

    object Chip : Variant(R.string.component_chip, OdsComponent.OdsChip.name)
    object ChipFilter : Variant(R.string.component_chip_type_filter, OdsComponent.OdsFilterChip.name)

    object DropdownMenu : Variant(R.string.component_menu_dropdown, OdsComponent.OdsDropdownMenu.name)
    object ExposedDropdownMenu : Variant(R.string.component_menu_exposed_dropdown, OdsComponent.OdsExposedDropdownMenu.name)

    object ProgressLinear : Variant(R.string.component_progress_linear, OdsComponent.OdsLinearProgressIndicator.name)
    object ProgressCircular : Variant(R.string.component_progress_circular, OdsComponent.OdsCircularProgressIndicator.name)

    object TextField : Variant(R.string.component_text_field_text, OdsComponent.OdsTextField.name)
    object TextFieldPassword : Variant(R.string.component_text_field_password, OdsComponent.OdsPasswordTextField.name)

    object TabsFixed : Variant(R.string.component_tabs_fixed, OdsComponent.OdsTabRow.name)
    object TabsScrollable : Variant(R.string.component_tabs_scrollable, OdsComponent.OdsScrollableTabRow.name)
}
