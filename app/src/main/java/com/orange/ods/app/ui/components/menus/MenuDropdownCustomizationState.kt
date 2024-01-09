/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.components.menus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberMenuDropdownCustomizationState(
    icons: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    dividerExample: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    enabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(icons, dividerExample, enabled) {
        MenuDropdownCustomizationState(icons, dividerExample, enabled)
    }

class MenuDropdownCustomizationState(
    val icons: MutableState<Boolean>,
    val dividerExample: MutableState<Boolean>,
    val enabled: MutableState<Boolean>
) {
    companion object {
        const val MenuItemCount = 5
    }

    val hasIcons
        get() = icons.value

    val hasDividerExample
        get() = dividerExample.value

    val isEnabled
        get() = enabled.value
}