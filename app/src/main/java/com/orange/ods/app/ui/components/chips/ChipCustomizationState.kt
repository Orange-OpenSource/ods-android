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

package com.orange.ods.app.ui.components.chips

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.chips.ChipCustomizationState.ChipType
import com.orange.ods.app.ui.components.chips.ChipCustomizationState.LeadingElement


@Composable
fun rememberChipCustomizationState(
    chipType: MutableState<ChipType>,
    leadingElement: MutableState<LeadingElement> = rememberSaveable { mutableStateOf(LeadingElement.None) },
    enabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    choiceChipIndexSelected: MutableState<Int?> = rememberSaveable { mutableStateOf(null) }
) =
    remember(chipType, leadingElement, enabled, choiceChipIndexSelected) {
        ChipCustomizationState(chipType, leadingElement, enabled, choiceChipIndexSelected)
    }

class ChipCustomizationState(
    val chipType: MutableState<ChipType>,
    val leadingElement: MutableState<LeadingElement>,
    val enabled: MutableState<Boolean>,
    val selectedChoiceChipIndex: MutableState<Int?>
) {

    enum class ChipType {
        Input, Action, Choice, Filter;

        companion object {
            fun fromVariant(variant: Variant) = when (variant) {
                Variant.ChipChoice -> Choice
                Variant.ChipInput -> Input
                Variant.ChipFilter -> Filter
                else -> Action
            }
        }

        val descriptionRes: Int
            get() = when (this) {
                Input -> R.string.component_chip_input_description
                Action -> R.string.component_chip_action_description
                Choice -> R.string.component_chip_choice_description
                Filter -> R.string.component_chip_filter_description
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
        get() = enabled.value

    fun resetLeadingElement() {
        leadingElement.value = LeadingElement.None
    }

}