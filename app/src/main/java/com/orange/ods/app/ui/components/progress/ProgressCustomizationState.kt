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

package com.orange.ods.app.ui.components.progress

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

const val DeterminateProgressTargetValue = 0.9f
private const val DeterminateProgressAnimDuration = 5000

@Composable
fun rememberProgressCustomizationState(
    type: MutableState<ProgressCustomizationState.Type> = rememberSaveable { mutableStateOf(ProgressCustomizationState.Type.Determinate) },
    icon: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    currentValue: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    label: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    determinateProgressValue: MutableFloatState = remember { mutableFloatStateOf(0f) }
) =
    remember(icon, currentValue, label, type) {
        ProgressCustomizationState(type, icon, currentValue, label, determinateProgressValue)
    }

class ProgressCustomizationState(
    val type: MutableState<Type>,
    val icon: MutableState<Boolean>,
    val currentValue: MutableState<Boolean>,
    val label: MutableState<Boolean>,
    val determinateProgressValue: MutableFloatState
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

    val isCurrentValueSwitchEnabled
        get() = type.value != Type.Indeterminate

    val determinateProgressAnimation
        @Composable
        get() = animateFloatAsState(
            targetValue = determinateProgressValue.floatValue,
            animationSpec = tween(
                durationMillis = if (determinateProgressValue.floatValue == 0f) 0 else DeterminateProgressAnimDuration,
                easing = FastOutSlowInEasing
            ),
            label = ""
        )

    fun resetAnimation() {
        determinateProgressValue.floatValue = 0f
    }
}