/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.menus

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