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

package com.orange.ods.app.ui.components.textfields

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.tab.OdsTabRow
import com.orange.ods.compose.utilities.composable.Keyboard
import com.orange.ods.compose.utilities.composable.keyboardAsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentTextField(variant: Variant) {
    val textFieldCustomizationState = rememberTextFieldCustomizationState()

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            when (variant) {
                Variant.TextField -> TextFieldTextCustomization(textFieldCustomizationState = textFieldCustomizationState)
                Variant.TextFieldPassword -> TextFieldPasswordCustomization(textFieldCustomizationState = textFieldCustomizationState)
                else -> {}
            }
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin),
                    vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin)
                )
        ) {
            when (variant) {
                Variant.TextField -> TextField(customizationState = textFieldCustomizationState)
                Variant.TextFieldPassword -> TextFieldPassword(customizationState = textFieldCustomizationState)
                else -> {}
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TextFieldTextCustomization(textFieldCustomizationState: TextFieldCustomizationState) {
    val tabs = CustomizationTab.entries
    val pagerState = rememberPagerState { tabs.size }
    val scope = rememberCoroutineScope()
    val keyboardState by keyboardAsState()

    // Clear text field focus each time the keyboard customization is opened and the soft keyboard is closed
    if (tabs[pagerState.currentPage] == CustomizationTab.Keyboard && keyboardState == Keyboard.Closed) {
        LocalFocusManager.current.clearFocus()
    }

    OdsTabRow(
        selectedTabIndex = pagerState.currentPage,
        tabs = tabs.mapIndexed { index, customizationTab ->
            OdsTabRow.Tab(
                icon = null,
                text = stringResource(id = customizationTab.titleRes)
            ) {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        }
    )

    HorizontalPager(state = pagerState) { page ->
        Column {
            tabs[page].Content(textFieldCustomizationState)
        }
    }
}

@Composable
private fun TextFieldPasswordCustomization(textFieldCustomizationState: TextFieldCustomizationState) {
    with(textFieldCustomizationState) {
        DisplayTypeCustomization(this)
        OdsListItem(
            text = stringResource(id = R.string.component_text_field_visualisation_icon),
            trailing = OdsListItem.TrailingSwitch(visualisationIcon.value, { visualisationIcon.value = it })
        )
        OdsListItem(
            text = stringResource(id = R.string.component_text_field_character_counter),
            trailing = OdsListItem.TrailingSwitch(characterCounter.value, { characterCounter.value = it })
        )
    }
}

@Composable
private fun ComponentCustomizationContent(textFieldCustomizationState: TextFieldCustomizationState) {
    with(textFieldCustomizationState) {
        OdsListItem(
            text = stringResource(id = R.string.component_element_leading_icon),
            trailing = OdsListItem.TrailingSwitch(leadingIcon.value, { leadingIcon.value = it })
        )

        Subtitle(textRes = R.string.component_text_field_input_type, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            selectedChoiceChipIndex = TextFieldCustomizationState.InputType.entries.indexOf(inputType.value),
            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
            choiceChips = TextFieldCustomizationState.InputType.entries.map { inputType ->
                val textResId = when (inputType) {
                    TextFieldCustomizationState.InputType.SingleLine -> R.string.component_text_field_input_type_single_line
                    TextFieldCustomizationState.InputType.Multiline -> R.string.component_text_field_input_type_multiline
                    TextFieldCustomizationState.InputType.TextArea -> R.string.component_text_field_input_type_text_area
                }
                OdsChoiceChipsFlowRow.ChoiceChip(
                    stringResource(id = textResId),
                    { this.inputType.value = inputType },
                    // Note: TextArea chip is disabled cause there is no parameter allowing text area in Jetpack Compose sdk for now
                    // https://issuetracker.google.com/issues/122476634
                    inputType != TextFieldCustomizationState.InputType.TextArea
                )
            }
        )

        DisplayTypeCustomization(this)

        Subtitle(textRes = R.string.component_element_trailing, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            selectedChoiceChipIndex = TextFieldCustomizationState.TrailingElement.entries.indexOf(trailingElement.value),
            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
            choiceChips = TextFieldCustomizationState.TrailingElement.entries.map { trailingElement ->
                val textResId = when (trailingElement) {
                    TextFieldCustomizationState.TrailingElement.None -> R.string.component_element_none
                    TextFieldCustomizationState.TrailingElement.Icon -> R.string.component_element_icon
                    TextFieldCustomizationState.TrailingElement.Text -> R.string.component_element_text
                }
                OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = textResId), { this.trailingElement.value = trailingElement })
            }
        )
        OdsListItem(
            text = stringResource(id = R.string.component_text_field_character_counter),
            trailing = OdsListItem.TrailingSwitch(characterCounter.value, { characterCounter.value = it })
        )
    }
}

@Composable
private fun KeyboardCustomizationContent(textFieldCustomizationState: TextFieldCustomizationState) {
    with(textFieldCustomizationState) {
        Subtitle(textRes = R.string.component_text_field_keyboard_type, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            selectedChoiceChipIndex = TextFieldCustomizationState.SoftKeyboardType.entries.indexOf(softKeyboardType.value),
            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
            choiceChips = TextFieldCustomizationState.SoftKeyboardType.entries.map { softKeyboardType ->
                OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = softKeyboardType.labelRes), { this.softKeyboardType.value = softKeyboardType })
            }
        )

        OdsListItem(
            text = stringResource(id = R.string.component_text_field_keyboard_capitalization),
            trailing = OdsListItem.TrailingSwitch(softKeyboardCapitalization.value, { softKeyboardCapitalization.value = it })
        )

        Subtitle(textRes = R.string.component_text_field_keyboard_action, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            selectedChoiceChipIndex = TextFieldCustomizationState.SoftKeyboardAction.entries.indexOf(softKeyboardAction.value),
            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
            choiceChips = TextFieldCustomizationState.SoftKeyboardAction.entries.map { softKeyboardAction ->
                OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = softKeyboardAction.labelRes), { this.softKeyboardAction.value = softKeyboardAction })
            }
        )
    }
}

@Composable
private fun DisplayTypeCustomization(textFieldCustomizationState: TextFieldCustomizationState) {
    val context = LocalContext.current
    Subtitle(textRes = R.string.component_state, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChoiceChipIndex = TextFieldCustomizationState.DisplayType.entries.indexOf(textFieldCustomizationState.displayType.value),
        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
        choiceChips = TextFieldCustomizationState.DisplayType.entries.map { displayType ->
            val textResId = when (displayType) {
                TextFieldCustomizationState.DisplayType.Default -> R.string.component_state_default
                TextFieldCustomizationState.DisplayType.Error -> R.string.component_state_error
                TextFieldCustomizationState.DisplayType.Disabled -> R.string.component_state_disabled
            }
            OdsChoiceChipsFlowRow.ChoiceChip(
                stringResource(id = textResId),
                { textFieldCustomizationState.displayType.value = displayType },
                semantics = {
                    if (textFieldCustomizationState.displayType.value == TextFieldCustomizationState.DisplayType.Error && displayType == TextFieldCustomizationState.DisplayType.Error) {
                        error(context.getString(R.string.component_text_field_error_message))
                    }
                }
            )
        }
    )
}

private enum class CustomizationTab(@StringRes val titleRes: Int) {
    TextField(R.string.component_text_field),
    Keyboard(R.string.component_text_field_keyboard);

    @Composable
    fun Content(textFieldCustomizationState: TextFieldCustomizationState) {
        when (this) {
            TextField -> ComponentCustomizationContent(textFieldCustomizationState = textFieldCustomizationState)
            Keyboard -> KeyboardCustomizationContent(textFieldCustomizationState = textFieldCustomizationState)
        }
    }
}