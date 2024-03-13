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

package com.orange.ods.app.ui.components.floatingactionbuttons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberFabCustomizationState(
    size: MutableState<FabCustomizationState.Size> = rememberSaveable { mutableStateOf(FabCustomizationState.Size.Default) },
    text: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    fullScreenWidth: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(
        size,
        text,
        fullScreenWidth
    ) {
        FabCustomizationState(
            size,
            text,
            fullScreenWidth
        )
    }

class FabCustomizationState(
    val size: MutableState<Size>,
    val text: MutableState<Boolean>,
    val fullScreenWidth: MutableState<Boolean>
) {

    enum class Size {
        Default, Mini
    }

    val hasText
        get() = if (size.value == Size.Mini) false else text.value

    val isTextEnabled
        get() = size.value != Size.Mini && !isFullScreenWidth

    val isFullScreenWidth
        get() = fullScreenWidth.value

    val isFullScreenWidthEnabled: Boolean
        get() = hasText

    fun resetTextAndWidth() {
        text.value = false
        fullScreenWidth.value = false
    }
}