/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.textfields

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.chip.SelectableChip
import com.orange.ods.compose.component.tab.OdsTab
import com.orange.ods.compose.component.tab.OdsTabRow
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun ComponentTextField(variant: Variant) {
    val textFieldCustomizationState = rememberTextFieldCustomizationState()

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            when (variant) {
                Variant.TextFieldFilledText, Variant.TextFieldOutlinedText -> TextFieldCustomization(textFieldCustomizationState)
                Variant.TextFieldFilledPassword, Variant.TextFieldOutlinedPassword -> TextFieldPasswordCustomization(textFieldCustomizationState)
                else -> {}
            }
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin),
                    vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin)
                )
        ) {
            when (variant) {
                Variant.TextFieldFilledText -> TextFieldFilled(customizationState = textFieldCustomizationState)
                Variant.TextFieldFilledPassword -> TextFieldFilledPassword(customizationState = textFieldCustomizationState)
                Variant.TextFieldOutlinedText -> TextFieldOutlined(customizationState = textFieldCustomizationState)
                Variant.TextFieldOutlinedPassword -> TextFieldOutlinedPassword(customizationState = textFieldCustomizationState)
                else -> {}
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun TextFieldCustomization(textFieldCustomizationState: TextFieldCustomizationState) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val tabs = CustomizationTab.values()

    OdsTabRow(selectedTabIndex = pagerState.currentPage) {
        tabs.forEachIndexed { index, customizationTab ->
            OdsTab(
                text = stringResource(id = customizationTab.titleRes),
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        Column {
            tabs[page].Content(textFieldCustomizationState)
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun TextFieldPasswordCustomization(textFieldCustomizationState: TextFieldCustomizationState) {
    StateCustomization(textFieldCustomizationState.displayType)
    SwitchListItem(labelRes = R.string.component_text_field_visualisation_icon, checked = textFieldCustomizationState.visualisationIcon)
    SwitchListItem(labelRes = R.string.component_text_field_character_counter, checked = textFieldCustomizationState.characterCounter)
}

@ExperimentalMaterialApi
@Composable
private fun ComponentCustomizationContent(textFieldCustomizationState: TextFieldCustomizationState) {
    SwitchListItem(labelRes = R.string.component_element_leading_icon, checked = textFieldCustomizationState.leadingIcon)

    Subtitle(textRes = R.string.component_text_field_input_type, withHorizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = textFieldCustomizationState.inputType,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        SelectableChip(textRes = R.string.component_text_field_input_type_single_line, value = TextFieldCustomizationState.InputType.SingleLine)
        SelectableChip(textRes = R.string.component_text_field_input_type_multi_line, value = TextFieldCustomizationState.InputType.MultiLine)
        // Note: TextArea chip is disabled cause there is no parameter allowing text area in Jetpack Compose sdk for now
        // https://issuetracker.google.com/issues/122476634
        SelectableChip(
            textRes = R.string.component_text_field_input_type_text_area,
            value = TextFieldCustomizationState.InputType.TextArea,
            enabled = false
        )
    }

    StateCustomization(textFieldCustomizationState.displayType)

    Subtitle(textRes = R.string.component_element_trailing, withHorizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = textFieldCustomizationState.trailingElement,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        SelectableChip(textRes = R.string.component_element_none, value = TextFieldCustomizationState.TrailingElement.None)
        SelectableChip(textRes = R.string.component_element_icon, value = TextFieldCustomizationState.TrailingElement.Icon)
        SelectableChip(textRes = R.string.component_element_text, value = TextFieldCustomizationState.TrailingElement.Text)
    }

    SwitchListItem(labelRes = R.string.component_text_field_character_counter, checked = textFieldCustomizationState.characterCounter)
}

@ExperimentalMaterialApi
@Composable
fun KeyboardCustomizationContent(textFieldCustomizationState: TextFieldCustomizationState) {
    Subtitle(textRes = R.string.component_text_field_keyboard_type, withHorizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = textFieldCustomizationState.softKeyboardType,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardType.Text.name, value = TextFieldCustomizationState.SoftKeyboardType.Text)
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardType.Decimal.name, value = TextFieldCustomizationState.SoftKeyboardType.Decimal)
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardType.Email.name, value = TextFieldCustomizationState.SoftKeyboardType.Email)
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardType.Number.name, value = TextFieldCustomizationState.SoftKeyboardType.Number)
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardType.Phone.name, value = TextFieldCustomizationState.SoftKeyboardType.Phone)
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardType.Url.name, value = TextFieldCustomizationState.SoftKeyboardType.Url)
    }

    SwitchListItem(labelRes = R.string.component_text_field_keyboard_capitalization, checked = textFieldCustomizationState.softKeyboardCapitalization)

    Subtitle(textRes = R.string.component_text_field_keyboard_action, withHorizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = textFieldCustomizationState.softKeyboardAction,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardAction.None.name, value = TextFieldCustomizationState.SoftKeyboardAction.None)
        SelectableChip(
            text = TextFieldCustomizationState.SoftKeyboardAction.Default.name,
            value = TextFieldCustomizationState.SoftKeyboardAction.Default
        )
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardAction.Done.name, value = TextFieldCustomizationState.SoftKeyboardAction.Done)
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardAction.Go.name, value = TextFieldCustomizationState.SoftKeyboardAction.Go)
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardAction.Search.name, value = TextFieldCustomizationState.SoftKeyboardAction.Search)
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardAction.Send.name, value = TextFieldCustomizationState.SoftKeyboardAction.Send)
        SelectableChip(
            text = TextFieldCustomizationState.SoftKeyboardAction.Previous.name,
            value = TextFieldCustomizationState.SoftKeyboardAction.Previous
        )
        SelectableChip(text = TextFieldCustomizationState.SoftKeyboardAction.Next.name, value = TextFieldCustomizationState.SoftKeyboardAction.Next)
    }

}

@ExperimentalMaterialApi
@Composable
private fun StateCustomization(displayType: MutableState<TextFieldCustomizationState.DisplayType>) {
    Subtitle(textRes = R.string.component_state, withHorizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = displayType,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        SelectableChip(textRes = R.string.component_state_default, value = TextFieldCustomizationState.DisplayType.Default)
        SelectableChip(textRes = R.string.component_state_error, value = TextFieldCustomizationState.DisplayType.Error)
        SelectableChip(textRes = R.string.component_state_disabled, value = TextFieldCustomizationState.DisplayType.Disabled)
    }
}

enum class CustomizationTab(@StringRes val titleRes: Int) {
    TextField(R.string.component_text_field),
    Keyboard(R.string.component_text_field_keyboard);

    @ExperimentalMaterialApi
    @Composable
    fun Content(textFieldCustomizationState: TextFieldCustomizationState) {
        when (this) {
            TextField -> ComponentCustomizationContent(textFieldCustomizationState = textFieldCustomizationState)
            Keyboard -> KeyboardCustomizationContent(textFieldCustomizationState = textFieldCustomizationState)
        }
    }
}