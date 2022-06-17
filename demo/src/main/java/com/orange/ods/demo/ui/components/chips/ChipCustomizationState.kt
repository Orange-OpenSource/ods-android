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
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.ChipType
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.LeadingElement


@Composable
fun rememberChipCustomizationState(
    selectedChipType: MutableState<ChipType> = rememberSaveable { mutableStateOf(ChipType.Input) },
    selectedLeadingElement: MutableState<LeadingElement> = rememberSaveable { mutableStateOf(LeadingElement.None) },
    disabledChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    hasBorderChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(selectedChipType, selectedLeadingElement, disabledChecked, hasBorderChecked) {
        ChipCustomizationState(selectedChipType, selectedLeadingElement, disabledChecked, hasBorderChecked)
    }

class ChipCustomizationState(
    val selectedChipType: MutableState<ChipType>,
    val selectedLeadingElement: MutableState<LeadingElement> = mutableStateOf(LeadingElement.None),
    val disabledChecked: MutableState<Boolean>,
    val hasBorderChecked: MutableState<Boolean>
) {

    enum class ChipType {
        Input, Action, Choice
    }

    enum class LeadingElement {
        None, Avatar, Icon
    }

    val isInputChip
        get() = selectedChipType.value == ChipType.Input

    val isActionChip
        get() = selectedChipType.value == ChipType.Action

    val isChoiceChip
        get() = selectedChipType.value == ChipType.Choice

    val hasLeadingAvatar
        get() = selectedLeadingElement.value == LeadingElement.Avatar

    val hasLeadingIcon
        get() = selectedLeadingElement.value == LeadingElement.Icon

    fun resetLeadingElement() {
        selectedLeadingElement.value = LeadingElement.None
    }
}