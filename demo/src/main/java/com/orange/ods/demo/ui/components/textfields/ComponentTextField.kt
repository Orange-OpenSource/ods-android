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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.tab.OdsTab
import com.orange.ods.compose.component.tab.OdsTabRow
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.utilities.composable.Keyboard
import com.orange.ods.utilities.composable.keyboardAsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentTextField(variant: Variant) {
    val textFieldCustomizationState = rememberTextFieldCustomizationState()

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            when (variant) {
                Variant.TextField -> TextFieldTextCustomization(textFieldCustomizationState)
                Variant.TextFieldPassword -> TextFieldPasswordCustomization(textFieldCustomizationState)
                else -> {}
            }
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin),
                    vertical = dimensionResource(id = R.dimen.screen_vertical_margin)
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

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TextFieldTextCustomization(textFieldCustomizationState: TextFieldCustomizationState) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val tabs = CustomizationTab.values()
    val keyboardState by keyboardAsState()

    // Clear text field focus each time the keyboard customization is opened and the soft keyboard is closed
    if (tabs[pagerState.currentPage] == CustomizationTab.Keyboard && keyboardState == Keyboard.Closed) {
        LocalFocusManager.current.clearFocus()
    }

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

@Composable
private fun TextFieldPasswordCustomization(textFieldCustomizationState: TextFieldCustomizationState) {
    DisplayTypeCustomization(textFieldCustomizationState.displayType)
    OdsListItem(
        text = stringResource(id = R.string.component_text_field_visualisation_icon),
        trailing = OdsSwitchTrailing(checked = textFieldCustomizationState.visualisationIcon)
    )
    OdsListItem(
        text = stringResource(id = R.string.component_text_field_character_counter),
        trailing = OdsSwitchTrailing(checked = textFieldCustomizationState.characterCounter)
    )
}

@Composable
private fun ComponentCustomizationContent(textFieldCustomizationState: TextFieldCustomizationState) {
    OdsListItem(
        text = stringResource(id = R.string.component_element_leading_icon),
        trailing = OdsSwitchTrailing(checked = textFieldCustomizationState.leadingIcon)
    )

    Subtitle(textRes = R.string.component_text_field_input_type, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = textFieldCustomizationState.inputType,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        OdsChoiceChip(textRes = R.string.component_text_field_input_type_single_line, value = TextFieldCustomizationState.InputType.SingleLine)
        OdsChoiceChip(textRes = R.string.component_text_field_input_type_multiline, value = TextFieldCustomizationState.InputType.Multiline)
        // Note: TextArea chip is disabled cause there is no parameter allowing text area in Jetpack Compose sdk for now
        // https://issuetracker.google.com/issues/122476634
        OdsChoiceChip(
            textRes = R.string.component_text_field_input_type_text_area,
            value = TextFieldCustomizationState.InputType.TextArea,
            enabled = false
        )
    }

    DisplayTypeCustomization(textFieldCustomizationState.displayType)

    Subtitle(textRes = R.string.component_element_trailing, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = textFieldCustomizationState.trailingElement,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        OdsChoiceChip(textRes = R.string.component_element_none, value = TextFieldCustomizationState.TrailingElement.None)
        OdsChoiceChip(textRes = R.string.component_element_icon, value = TextFieldCustomizationState.TrailingElement.Icon)
        OdsChoiceChip(textRes = R.string.component_element_text, value = TextFieldCustomizationState.TrailingElement.Text)
    }

    OdsListItem(
        text = stringResource(id = R.string.component_text_field_character_counter),
        trailing = OdsSwitchTrailing(checked = textFieldCustomizationState.characterCounter)
    )
}

@Composable
private fun KeyboardCustomizationContent(textFieldCustomizationState: TextFieldCustomizationState) {
    Subtitle(textRes = R.string.component_text_field_keyboard_type, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = textFieldCustomizationState.softKeyboardType,
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        TextFieldCustomizationState.SoftKeyboardType.values().forEach { softKeyboardType ->
            OdsChoiceChip(text = stringResource(id = softKeyboardType.labelRes), value = softKeyboardType)
        }
    }

    OdsListItem(
        text = stringResource(id = R.string.component_text_field_keyboard_capitalization),
        trailing = OdsSwitchTrailing(checked = textFieldCustomizationState.softKeyboardCapitalization)
    )

    Subtitle(textRes = R.string.component_text_field_keyboard_action, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = textFieldCustomizationState.softKeyboardAction,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        TextFieldCustomizationState.SoftKeyboardAction.values().forEach { softKeyboardAction ->
            OdsChoiceChip(text = stringResource(id = softKeyboardAction.labelRes), value = softKeyboardAction)
        }
    }

}

@Composable
private fun DisplayTypeCustomization(displayType: MutableState<TextFieldCustomizationState.DisplayType>) {
    Subtitle(textRes = R.string.component_state, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = displayType,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
        outlinedChips = true
    ) {
        val context = LocalContext.current
        OdsChoiceChip(textRes = R.string.component_state_default, value = TextFieldCustomizationState.DisplayType.Default)
        OdsChoiceChip(textRes = R.string.component_state_error, value = TextFieldCustomizationState.DisplayType.Error, modifier = Modifier.semantics {
            if (displayType.value == TextFieldCustomizationState.DisplayType.Error) {
                error(context.getString(R.string.component_text_field_error_message))
            }
        })
        OdsChoiceChip(textRes = R.string.component_state_disabled, value = TextFieldCustomizationState.DisplayType.Disabled)
    }
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