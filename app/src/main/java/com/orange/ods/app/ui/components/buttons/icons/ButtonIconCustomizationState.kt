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

package com.orange.ods.app.ui.components.buttons.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable


@Composable
fun rememberButtonIconCustomizationState(
    toggleCount: MutableIntState = rememberSaveable { mutableIntStateOf(ButtonIconCustomizationState.MinToggleCount) },
    enabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(toggleCount, enabled) {
        ButtonIconCustomizationState(toggleCount, enabled)
    }

class ButtonIconCustomizationState(
    val toggleCount: MutableIntState,
    val enabled: MutableState<Boolean>
) {
    companion object {
        const val MinToggleCount = 2
        const val MaxToggleCount = 4
    }

    val isEnabled
        get() = enabled.value

}