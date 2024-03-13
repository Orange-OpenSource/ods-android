/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.orange.ods.app.R
import com.orange.ods.app.ui.Screen
import com.orange.ods.app.ui.components.appbars.top.ComponentTopAppBar
import com.orange.ods.app.ui.components.banners.ComponentBanners
import com.orange.ods.app.ui.components.bottomnavigation.ComponentBottomNavigation
import com.orange.ods.app.ui.components.buttons.ComponentButtons
import com.orange.ods.app.ui.components.buttons.icons.ComponentButtonsIcons
import com.orange.ods.app.ui.components.cards.ComponentCard
import com.orange.ods.app.ui.components.checkboxes.ComponentCheckboxes
import com.orange.ods.app.ui.components.chips.Chip
import com.orange.ods.app.ui.components.chips.ChipFilter
import com.orange.ods.app.ui.components.dialogs.ComponentDialog
import com.orange.ods.app.ui.components.floatingactionbuttons.ComponentFloatingActionButton
import com.orange.ods.app.ui.components.imageitem.ComponentImageItem
import com.orange.ods.app.ui.components.listitem.ComponentListItem
import com.orange.ods.app.ui.components.menus.ComponentMenu
import com.orange.ods.app.ui.components.navigationdrawers.ComponentModalDrawers
import com.orange.ods.app.ui.components.progress.ComponentProgress
import com.orange.ods.app.ui.components.radiobuttons.ComponentRadioButtons
import com.orange.ods.app.ui.components.sheets.ComponentSheetsBottom
import com.orange.ods.app.ui.components.sliders.ComponentSliders
import com.orange.ods.app.ui.components.snackbars.ComponentSnackbars
import com.orange.ods.app.ui.components.switches.ComponentSwitches
import com.orange.ods.app.ui.components.tabs.ComponentTabs
import com.orange.ods.app.ui.components.textfields.ComponentTextField
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsButton

sealed class Component(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @DrawableRes val smallImageRes: Int?,
    @StringRes val descriptionRes: Int,
    val variants: List<Variant> = emptyList(),
    val composableName: String? = null,
    val demoScreen: @Composable (variant: Variant?) -> Unit,
    val imageAlignment: Alignment = Alignment.Center,
) {

    companion object {
        fun fromId(componentId: Long?) = components.firstOrNull { component -> component.id == componentId }
    }

    val id: Long = Component::class.sealedSubclasses.indexOf(this::class).toLong()

    data object AppBarsTop : Component(
        R.string.component_app_bars_top,
        R.drawable.il_app_bars_top,
        R.drawable.il_app_bars_top_small,
        R.string.component_app_bars_top_description,
        variants = listOf(Variant.AppBarsTopRegular, Variant.AppBarsTopLarge, Variant.AppBarsTopSearch),
        demoScreen = { variant -> if (variant != null) ComponentTopAppBar(variant) },
        imageAlignment = Alignment.TopCenter
    )

    data object Banners : Component(
        R.string.component_banners,
        R.drawable.il_banners,
        null,
        R.string.component_banners_description,
        composableName = OdsComposable.OdsBanner.name,
        demoScreen = { _ -> ComponentBanners() }
    )

    data object BottomNavigation :
        Component(
            R.string.component_bottom_navigation,
            R.drawable.il_bottom_navigation,
            null,
            R.string.component_bottom_navigation_description,
            composableName = OdsComposable.OdsBottomNavigation.name,
            demoScreen = { _ -> ComponentBottomNavigation() },
            imageAlignment = Alignment.TopCenter
        )

    data object Buttons : Component(
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
            Variant.ButtonsTextToggleGroup
        ),
        demoScreen = { variant -> if (variant != null) ComponentButtons(variant = variant) }
    )

    data object ButtonsIcons : Component(
        R.string.component_buttons_icons,
        R.drawable.il_buttons_icons,
        null,
        R.string.component_buttons_description,
        listOf(
            Variant.ButtonsIcon,
            Variant.ButtonsIconToggle,
            Variant.ButtonsIconToggleGroup
        ),
        demoScreen = { variant -> if (variant != null) ComponentButtonsIcons(variant = variant) }
    )

    data object Cards : Component(
        R.string.component_cards,
        R.drawable.il_cards,
        null,
        R.string.component_card_description,
        listOf(Variant.CardVerticalImageFirst, Variant.CardVerticalHeaderFirst, Variant.CardSmall, Variant.CardHorizontal),
        demoScreen = { variant -> if (variant != null) ComponentCard(variant = variant) }
    )

    data object Checkboxes : Component(
        R.string.component_checkboxes,
        R.drawable.il_checkboxes,
        null,
        R.string.component_checkboxes_description,
        composableName = OdsComposable.OdsCheckbox.name,
        demoScreen = { _ -> ComponentCheckboxes() }
    )

    data object Chips : Component(
        R.string.component_chips,
        R.drawable.il_chips,
        R.drawable.il_chips_small,
        R.string.component_chips_description,
        listOf(Variant.ChipAction, Variant.ChipChoice, Variant.ChipInput, Variant.ChipFilter),
        demoScreen = { variant ->
            when {
                variant == Variant.ChipFilter -> ChipFilter()
                variant != null -> Chip(variant = variant)
            }
        }
    )

    data object Dialogs : Component(
        R.string.component_dialogs,
        R.drawable.il_dialogs,
        null,
        R.string.component_dialogs_description,
        composableName = OdsComposable.OdsAlertDialog.name,
        demoScreen = { _ -> ComponentDialog() }
    )

    data object FloatingActionButtons : Component(
        R.string.component_floating_action_buttons,
        R.drawable.il_fab,
        null,
        R.string.component_floating_action_buttons_description,
        composableName = OdsComposable.OdsFloatingActionButton.name,
        demoScreen = { _ -> ComponentFloatingActionButton() }
    )

    data object ImageItem : Component(
        R.string.component_image_item,
        R.drawable.il_image_item,
        null,
        R.string.component_image_item_description,
        composableName = OdsComposable.OdsImageItem.name,
        demoScreen = { _ -> ComponentImageItem() }
    )

    data object ListItem : Component(
        R.string.component_list_item,
        R.drawable.il_list_item,
        null,
        R.string.component_list_item_description,
        composableName = OdsComposable.OdsListItem.name,
        demoScreen = { _ -> ComponentListItem() }
    )

    data object Menus : Component(
        R.string.component_menus,
        R.drawable.il_menus,
        null,
        R.string.component_menus_description,
        listOf(Variant.DropdownMenu, Variant.ExposedDropdownMenu),
        demoScreen = { variant -> if (variant != null) ComponentMenu(variant = variant) }
    )

    data object ModalDrawers : Component(
        R.string.component_modal_drawers,
        R.drawable.il_navigation_drawers,
        null,
        R.string.component_modal_drawers_description,
        demoScreen = { _ -> ComponentModalDrawers() }
    )

    data object Progress : Component(
        R.string.component_progress,
        R.drawable.il_progress,
        null,
        R.string.component_progress_description,
        listOf(Variant.ProgressLinear, Variant.ProgressCircular),
        demoScreen = { variant -> if (variant != null) ComponentProgress(variant = variant) }
    )

    data object RadioButtons : Component(
        R.string.component_radio_buttons,
        R.drawable.il_radio_buttons,
        null,
        R.string.component_radio_buttons_description,
        composableName = OdsComposable.OdsRadioButton.name,
        demoScreen = { _ -> ComponentRadioButtons() }
    )

    data object SheetsBottom : Component(
        R.string.component_sheets_bottom,
        R.drawable.il_bottom_sheet,
        null,
        R.string.component_sheet_bottom_description,
        composableName = OdsComposable.OdsBottomSheetScaffold.name,
        demoScreen = { _ -> ComponentSheetsBottom() }
    )

    data object Sliders : Component(
        R.string.component_sliders,
        R.drawable.il_sliders,
        null,
        R.string.component_sliders_description,
        composableName = OdsComposable.OdsSlider.name,
        demoScreen = { _ -> ComponentSliders() }
    )

    data object Snackbars : Component(
        R.string.component_snackbars,
        R.drawable.il_snackbars,
        R.drawable.il_snackbars_small,
        R.string.component_snackbars_description,
        composableName = OdsComposable.OdsSnackbar.name,
        demoScreen = { _ -> ComponentSnackbars() }
    )

    data object Switches : Component(
        R.string.component_switches,
        R.drawable.il_switches,
        R.drawable.il_switches_small,
        R.string.component_switches_description,
        composableName = OdsComposable.OdsSwitch.name,
        demoScreen = { _ -> ComponentSwitches() }
    )

    data object TextFields : Component(
        R.string.component_text_fields,
        R.drawable.il_text_fields,
        R.drawable.il_text_fields_small,
        R.string.component_text_fields_description,
        listOf(Variant.TextField, Variant.TextFieldPassword),
        demoScreen = { variant -> if (variant != null) ComponentTextField(variant = variant) }
    )

    data object Tabs : Component(
        R.string.component_tabs,
        R.drawable.il_tabs,
        R.drawable.il_tabs_small,
        R.string.component_tabs_description,
        listOf(Variant.TabsFixed, Variant.TabsScrollable),
        demoScreen = { variant -> if (variant != null) ComponentTabs(variant = variant) }
    )
}

val components = Component::class.sealedSubclasses.mapNotNull { it.objectInstance }

sealed class Variant(
    @StringRes val titleRes: Int,
    val composableName: String,
    val topAppBarType: Screen.TopAppBarType = Screen.TopAppBarType.Default,
    val customizableTopAppBar: Boolean = false,
    val hasTabs: Boolean = false
) {

    companion object {
        fun fromId(variantId: Long?) = components.flatMap { it.variants }.firstOrNull { it.id == variantId }
    }

    val id: Long = Variant::class.sealedSubclasses.indexOf(this::class).toLong()

    data object AppBarsTopRegular : Variant(R.string.component_app_bars_top_regular, OdsComposable.OdsTopAppBar.name, customizableTopAppBar = true)
    data object AppBarsTopLarge : Variant(
        R.string.component_app_bars_top_large,
        OdsComposable.OdsLargeTopAppBar.name,
        topAppBarType = Screen.TopAppBarType.Large,
        customizableTopAppBar = true
    )

    data object AppBarsTopSearch : Variant(
        R.string.component_app_bars_top_search,
        OdsComposable.OdsSearchTopAppBar.name,
        topAppBarType = Screen.TopAppBarType.Search,
        customizableTopAppBar = true
    )

    data object ButtonsPrimary : Variant(R.string.component_buttons_high_emphasis, "${OdsComposable.OdsButton.name} with ${OdsButton.Style.Primary.name}")
    data object ButtonsDefault : Variant(R.string.component_buttons_medium_emphasis, "${OdsComposable.OdsButton.name} with ${OdsButton.Style.Default.name}")
    data object ButtonsOutlined : Variant(R.string.component_buttons_low_emphasis, OdsComposable.OdsOutlinedButton.name)
    data object ButtonsText : Variant(R.string.component_buttons_lowest_emphasis, OdsComposable.OdsTextButton.name)
    data object ButtonsTextToggleGroup : Variant(R.string.component_button_text_toggle_group, OdsComposable.OdsTextToggleButtonsRow.name)
    data object ButtonsFunctional : Variant(R.string.component_buttons_functional, "${OdsComposable.OdsButton.name} with a functional style")

    data object ButtonsIcon : Variant(R.string.component_buttons_icon, OdsComposable.OdsIconButton.name)
    data object ButtonsIconToggle : Variant(R.string.component_buttons_icon_toggle, OdsComposable.OdsIconToggleButton.name)
    data object ButtonsIconToggleGroup : Variant(R.string.component_buttons_icon_toggle_group, OdsComposable.OdsIconToggleButtonsRow.name)

    data object CardVerticalImageFirst : Variant(R.string.component_card_vertical_image_first, OdsComposable.OdsVerticalImageFirstCard.name)
    data object CardVerticalHeaderFirst : Variant(R.string.component_card_vertical_header_first, OdsComposable.OdsVerticalHeaderFirstCard.name)
    data object CardSmall : Variant(R.string.component_card_small, OdsComposable.OdsSmallCard.name)
    data object CardHorizontal : Variant(R.string.component_card_horizontal, OdsComposable.OdsHorizontalCard.name)

    data object ChipAction : Variant(R.string.component_chip_action, OdsComposable.OdsChip.name)
    data object ChipChoice : Variant(R.string.component_chip_choice, OdsComposable.OdsChip.name)
    data object ChipInput : Variant(R.string.component_chip_input, OdsComposable.OdsChip.name)
    data object ChipFilter : Variant(R.string.component_chip_filter, OdsComposable.OdsFilterChip.name)

    data object DropdownMenu : Variant(R.string.component_menu_dropdown, OdsComposable.OdsDropdownMenu.name)
    data object ExposedDropdownMenu : Variant(R.string.component_menu_exposed_dropdown, OdsComposable.OdsExposedDropdownMenu.name)

    data object ProgressLinear : Variant(R.string.component_progress_linear, OdsComposable.OdsLinearProgressIndicator.name)
    data object ProgressCircular : Variant(R.string.component_progress_circular, OdsComposable.OdsCircularProgressIndicator.name)

    data object TextField : Variant(R.string.component_text_field_text, OdsComposable.OdsTextField.name)
    data object TextFieldPassword : Variant(R.string.component_text_field_password, OdsComposable.OdsPasswordTextField.name)

    data object TabsFixed : Variant(R.string.component_tabs_fixed, OdsComposable.OdsTabRow.name, hasTabs = true)
    data object TabsScrollable : Variant(R.string.component_tabs_scrollable, OdsComposable.OdsScrollableTabRow.name, hasTabs = true)

}
