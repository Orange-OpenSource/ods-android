/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.progress

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberProgressCustomizationState(
    type: MutableState<ProgressCustomizationState.Type> = rememberSaveable { mutableStateOf(ProgressCustomizationState.Type.Determinate) },
    icon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    currentValue: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    label: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(icon, currentValue, label, type) {
        ProgressCustomizationState(type, icon, currentValue, label)
    }

class ProgressCustomizationState(
    val type: MutableState<Type>,
    val icon: MutableState<Boolean>,
    val currentValue: MutableState<Boolean>,
    val label: MutableState<Boolean>
) {
    enum class Type {
        Determinate, Indeterminate
    }

    val hasIcon
        get() = icon.value

    val hasCurrentValue
        get() = currentValue.value

    val hasLabel
        get() = label.value

}