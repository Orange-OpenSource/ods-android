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
    chipType: MutableState<ChipType> = rememberSaveable { mutableStateOf(ChipType.Input) },
    leadingElement: MutableState<LeadingElement> = rememberSaveable { mutableStateOf(LeadingElement.None) },
    disabledChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    outlinedChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    choiceChipIndexSelected: MutableState<Int?> = rememberSaveable { mutableStateOf(null) }
) =
    remember(chipType, leadingElement, disabledChecked, outlinedChecked, choiceChipIndexSelected) {
        ChipCustomizationState(chipType, leadingElement, disabledChecked, outlinedChecked, choiceChipIndexSelected)
    }

class ChipCustomizationState(
    val chipType: MutableState<ChipType>,
    val leadingElement: MutableState<LeadingElement>,
    val disabledChecked: MutableState<Boolean>,
    val outlinedChecked: MutableState<Boolean>,
    val choiceChipIndexSelected: MutableState<Int?>
) {

    enum class ChipType {
        Input, Action, Choice
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

    val isOutlined
        get() = outlinedChecked.value
    
    fun resetLeadingElement() {
        leadingElement.value = LeadingElement.None
    }

}