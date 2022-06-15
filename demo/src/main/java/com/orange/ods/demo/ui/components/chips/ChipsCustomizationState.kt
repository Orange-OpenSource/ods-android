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
import com.orange.ods.demo.ui.components.chips.ChipsCustomizationState.LeadingElement

@Composable
fun rememberChipsCustomizationState(
    selectedLeadingElement: MutableState<LeadingElement> = rememberSaveable { mutableStateOf(LeadingElement.NONE) },
    selectedChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    disabledChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    trailingCrossChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
) =
    remember(selectedLeadingElement, selectedChecked, disabledChecked, trailingCrossChecked) {
        ChipsCustomizationState(selectedLeadingElement, selectedChecked, disabledChecked, trailingCrossChecked)
    }

class ChipsCustomizationState(
    val selectedLeadingElement: MutableState<LeadingElement>,
    val selectedChecked: MutableState<Boolean>,
    val disabledChecked: MutableState<Boolean>,
    val trailingCrossChecked: MutableState<Boolean>
) {

    enum class LeadingElement {
        NONE, AVATAR, ICON
    }

    val hasLeadingAvatar
        get() = selectedLeadingElement.value == LeadingElement.AVATAR

    val hasLeadingIcon
        get() = selectedLeadingElement.value == LeadingElement.ICON
}