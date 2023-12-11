/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsTextButton


@Composable
fun rememberButtonCustomizationState(
    toggleCount: MutableIntState = rememberSaveable { mutableIntStateOf(ButtonCustomizationState.MinToggleCount) },
    buttonStyle: MutableState<OdsButton.Style> = rememberSaveable { mutableStateOf(OdsButton.Style.Default) },
    textButtonStyle: MutableState<OdsTextButton.Style> = rememberSaveable { mutableStateOf(OdsTextButton.Style.Default) },
    leadingIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    fullScreenWidth: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    sameItemsWeight: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    enabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(buttonStyle, textButtonStyle, leadingIcon, fullScreenWidth, sameItemsWeight, enabled, toggleCount) {
        ButtonCustomizationState(toggleCount, buttonStyle, textButtonStyle, leadingIcon, fullScreenWidth, sameItemsWeight, enabled)
    }

class ButtonCustomizationState(
    val toggleCount: MutableIntState,
    val buttonStyle: MutableState<OdsButton.Style>,
    val textButtonStyle: MutableState<OdsTextButton.Style>,
    val leadingIcon: MutableState<Boolean>,
    val fullScreenWidth: MutableState<Boolean>,
    val sameItemsWeight: MutableState<Boolean>,
    val enabled: MutableState<Boolean>
) {
    companion object {
        const val MinToggleCount = 2
        const val MaxToggleCount = 3
    }

    val hasLeadingIcon
        get() = leadingIcon.value

    val isEnabled
        get() = enabled.value

    val hasFullScreenWidth
        get() = fullScreenWidth.value

    val hasSameItemsWeight
        get() = sameItemsWeight.value
}