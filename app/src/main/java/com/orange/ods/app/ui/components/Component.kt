/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.appbars.top.ComponentTopAppBar
import com.orange.ods.app.ui.components.appbars.top.ComponentTopAppBarExtended
import com.orange.ods.app.ui.components.banners.ComponentBanners
import com.orange.ods.app.ui.components.bottomnavigation.ComponentBottomNavigation
import com.orange.ods.app.ui.components.buttons.ComponentButtons
import com.orange.ods.app.ui.components.cards.ComponentCard
import com.orange.ods.app.ui.components.checkboxes.ComponentCheckboxes
import com.orange.ods.app.ui.components.chips.Chip
import com.orange.ods.app.ui.components.chips.ChipFilter
import com.orange.ods.app.ui.components.dialogs.ComponentDialog
import com.orange.ods.app.ui.components.floatingactionbuttons.ComponentFloatingActionButton
import com.orange.ods.app.ui.components.lists.ComponentLists
import com.orange.ods.app.ui.components.menus.ComponentMenu
import com.orange.ods.app.ui.components.progress.ComponentProgress
import com.orange.ods.app.ui.components.radiobuttons.ComponentRadioButtons
import com.orange.ods.app.ui.components.sheets.ComponentSheetsBottom
import com.orange.ods.app.ui.components.sliders.ComponentSliders
import com.orange.ods.app.ui.components.snackbars.ComponentSnackbars
import com.orange.ods.app.ui.components.switches.ComponentSwitches
import com.orange.ods.app.ui.components.tabs.ComponentTabs
import com.orange.ods.app.ui.components.textfields.ComponentTextField
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsButtonStyle


sealed class Component(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @DrawableRes val smallImageRes: Int?,
    @StringRes val descriptionRes: Int,
    val variants: List<Variant> = emptyList(),
    val composableName: String? = null,
    val ScreenContent: @Composable (() -> Unit)? = null
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
            Banners, Buttons, Cards, SheetsBottom, Checkboxes, Chips, Dialogs, FloatingActionButtons, Menus, Progress, RadioButtons, Snackbars, Switches, Tabs -> Alignment.Center
        }

    object AppBarsTop : Component(
        R.string.component_app_bars_top,
        R.drawable.il_app_bars_top,
        R.drawable.il_app_bars_top_small,
        R.string.component_app_bars_top_description,
        listOf(Variant.AppBarsTopRegular, Variant.AppBarsTopExtended)
    )

    object BottomNavigation :
        Component(
            R.string.component_bottom_navigation,
            R.drawable.il_bottom_navigation,
            null,
            R.string.component_bottom_navigation_description,
            composableName = OdsComponent.OdsBottomNavigation.name,
            ScreenContent = { ComponentBottomNavigation() }
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

    object Banners : Component(
        R.string.component_banners,
        R.drawable.il_banners,
        null,
        R.string.component_banners_description,
        composableName = OdsComponent.OdsBanner.name,
        ScreenContent = { ComponentBanners() }
    )

    object Cards : Component(
        R.string.component_cards,
        R.drawable.il_cards,
        null,
        R.string.component_card_description,
        listOf(Variant.CardVerticalImageFirst, Variant.CardVerticalHeaderFirst, Variant.CardSmall, Variant.CardHorizontal)
    )

    object Checkboxes : Component(
        R.string.component_checkboxes,
        R.drawable.il_checkboxes,
        null,
        R.string.component_checkboxes_description,
        composableName = OdsComponent.OdsCheckbox.name,
        ScreenContent = { ComponentCheckboxes() }

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
        R.drawable.il_dialogs_generic,
        null,
        R.string.component_dialogs_description,
        composableName = OdsComponent.OdsAlertDialog.name,
        ScreenContent = { ComponentDialog() }
    )

    object FloatingActionButtons : Component(
        R.string.component_floating_action_buttons,
        R.drawable.il_fab,
        null,
        R.string.component_floating_action_buttons_description,
        composableName = OdsComponent.OdsFloatingActionButton.name,
        ScreenContent = { ComponentFloatingActionButton() }

    )

    object Lists : Component(
        R.string.component_lists,
        R.drawable.il_lists,
        null,
        R.string.component_lists_description,
        composableName = OdsComponent.OdsListItem.name,
        ScreenContent = { ComponentLists() }
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
        composableName = OdsComponent.OdsRadioButton.name,
        ScreenContent = { ComponentRadioButtons() }

    )

    object SheetsBottom : Component(
        R.string.component_sheets_bottom,
        R.drawable.il_bottom_sheet,
        null,
        R.string.component_sheet_bottom_description,
        composableName = "",
        ScreenContent = { ComponentSheetsBottom() }
    )

    object Sliders : Component(
        R.string.component_sliders,
        R.drawable.il_sliders,
        null,
        R.string.component_sliders_description,
        composableName = OdsComponent.OdsSlider.name,
        ScreenContent = { ComponentSliders() }
    )

    object Snackbars : Component(
        R.string.component_snackbars,
        R.drawable.il_snackbars,
        R.drawable.il_snackbars_small,
        R.string.component_snackbars_description,
        composableName = OdsComponent.OdsSnackbar.name,
        ScreenContent = { ComponentSnackbars() }
    )

    object Switches : Component(
        R.string.component_switches,
        R.drawable.il_switches,
        R.drawable.il_switches_small,
        R.string.component_switches_description,
        composableName = OdsComponent.OdsSwitch.name,
        ScreenContent = { ComponentSwitches() }
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
    val composableName: String,
    val ScreenContent: @Composable () -> Unit,
    val extendedTopAppBar: Boolean = false
) {

    val id: Long = Variant::class.sealedSubclasses.indexOf(this::class).toLong()

    object AppBarsTopRegular : Variant(R.string.component_app_bars_top_regular, OdsComponent.OdsTopAppBar.name, { ComponentTopAppBar() })
    object AppBarsTopExtended : Variant(R.string.component_app_bars_top_extended, OdsComponent.OdsTopAppBar.name, { ComponentTopAppBarExtended() }, true)

    object ButtonsPrimary : Variant(
        R.string.component_buttons_highest_emphasis,
        "${OdsComponent.OdsButton.name} with ${OdsButtonStyle.Primary.name}",
        { ComponentButtons(variant = ButtonsPrimary) }
    )

    object ButtonsDefault : Variant(
        R.string.component_buttons_high_emphasis,
        "${OdsComponent.OdsButton.name} with ${OdsButtonStyle.Default.name}",
        { ComponentButtons(variant = ButtonsDefault) }
    )

    object ButtonsOutlined : Variant(
        R.string.component_buttons_medium_emphasis,
        OdsComponent.OdsOutlinedButton.name,
        { ComponentButtons(variant = ButtonsOutlined) }
    )

    object ButtonsText : Variant(R.string.component_buttons_low_emphasis, OdsComponent.OdsTextButton.name, { ComponentButtons(variant = ButtonsText) })
    object ButtonsFunctional : Variant(
        R.string.component_buttons_functional,
        "${OdsComponent.OdsButton.name} with a functional style",
        { ComponentButtons(variant = ButtonsFunctional) }
    )

    object ButtonsToggle : Variant(R.string.component_buttons_toggle, OdsComponent.OdsIconToggleButton.name, { ComponentButtons(variant = ButtonsToggle) })
    object ButtonsIcon : Variant(R.string.component_buttons_icon, OdsComponent.OdsIconButton.name, { ComponentButtons(variant = ButtonsIcon) })

    object CardVerticalImageFirst :
        Variant(R.string.component_card_vertical_image_first, OdsComponent.OdsVerticalImageFirstCard.name, { ComponentCard(variant = CardVerticalImageFirst) })

    object CardVerticalHeaderFirst : Variant(
        R.string.component_card_vertical_header_first,
        OdsComponent.OdsVerticalHeaderFirstCard.name,
        { ComponentCard(variant = CardVerticalHeaderFirst) })

    object CardSmall : Variant(R.string.component_card_small, OdsComponent.OdsSmallCard.name, { ComponentCard(variant = CardSmall) })
    object CardHorizontal : Variant(R.string.component_card_horizontal, OdsComponent.OdsHorizontalCard.name, { ComponentCard(variant = CardHorizontal) })

    object Chip : Variant(R.string.component_chip, OdsComponent.OdsChip.name, { Chip() })
    object ChipFilter : Variant(R.string.component_chip_type_filter, OdsComponent.OdsFilterChip.name, { ChipFilter() })

    object DropdownMenu : Variant(R.string.component_menu_dropdown, OdsComponent.OdsDropdownMenu.name, { ComponentMenu(variant = DropdownMenu) })
    object ExposedDropdownMenu :
        Variant(R.string.component_menu_exposed_dropdown, OdsComponent.OdsExposedDropdownMenu.name, { ComponentMenu(variant = ExposedDropdownMenu) })

    object ProgressLinear :
        Variant(R.string.component_progress_linear, OdsComponent.OdsLinearProgressIndicator.name, { ComponentProgress(variant = ProgressLinear) })

    object ProgressCircular :
        Variant(R.string.component_progress_circular, OdsComponent.OdsCircularProgressIndicator.name, { ComponentProgress(variant = ProgressCircular) })

    object TextField : Variant(R.string.component_text_field_text, OdsComponent.OdsTextField.name, { ComponentTextField(variant = TextField) })
    object TextFieldPassword :
        Variant(R.string.component_text_field_password, OdsComponent.OdsPasswordTextField.name, { ComponentTextField(variant = TextFieldPassword) })

    object TabsFixed : Variant(R.string.component_tabs_fixed, OdsComponent.OdsTabRow.name, { ComponentTabs(variant = TabsFixed) })
    object TabsScrollable : Variant(R.string.component_tabs_scrollable, OdsComponent.OdsScrollableTabRow.name, { ComponentTabs(variant = TabsScrollable) })
}
