/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.chips

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.ChipType
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.LeadingElement


@Composable
fun rememberChipCustomizationState(
    chipType: MutableState<ChipType> = rememberSaveable { mutableStateOf(ChipType.Input) },
    leadingElement: MutableState<LeadingElement> = rememberSaveable { mutableStateOf(LeadingElement.None) },
    disabledChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    choiceChipIndexSelected: MutableState<Int?> = rememberSaveable { mutableStateOf(null) }
) =
    remember(chipType, leadingElement, disabledChecked, choiceChipIndexSelected) {
        ChipCustomizationState(chipType, leadingElement, disabledChecked, choiceChipIndexSelected)
    }

class ChipCustomizationState(
    val chipType: MutableState<ChipType>,
    val leadingElement: MutableState<LeadingElement>,
    val disabledChecked: MutableState<Boolean>,
    val choiceChipIndexSelected: MutableState<Int?>
) {

    enum class ChipType {
        Input, Action, Choice, Filter;

        val nameRes: Int
            get() = when (this) {
                Input -> R.string.component_chip_type_input
                Action -> R.string.component_chip_type_action
                Choice -> R.string.component_chip_type_choice
                Filter -> R.string.component_chip_type_filter
            }

        val descriptionRes: Int
            get() = when (this) {
                Input -> R.string.component_chip_type_input_description
                Action -> R.string.component_chip_type_action_description
                Choice -> R.string.component_chip_type_choice_description
                Filter -> R.string.component_chip_type_filter_description
            }
    }

    enum class LeadingElement {
        None, Avatar, Icon
    }

    val isInputChip
        get() = chipType.value == ChipType.Input

    val isActionChip
        get() = chipType.value == ChipType.Action

    val isChoiceChip
        get() = chipType.value == ChipType.Choice

    val hasLeadingAvatar
        get() = leadingElement.value == LeadingElement.Avatar

    val hasLeadingIcon
        get() = leadingElement.value == LeadingElement.Icon

    val isEnabled
        get() = !disabledChecked.value

    fun resetLeadingElement() {
        leadingElement.value = LeadingElement.None
    }

}