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

package com.orange.ods.app.ui.components.sliders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable


@Composable
fun rememberSliderCustomizationState(
    sideIcons: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    valueDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    stepped: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(sideIcons, valueDisplayed, stepped) {
        SliderCustomizationState(sideIcons, valueDisplayed, stepped)
    }

class SliderCustomizationState(
    val sideIcons: MutableState<Boolean>,
    val valueDisplayed: MutableState<Boolean>,
    val stepped: MutableState<Boolean>
) {
    val hasSideIcons
        get() = sideIcons.value

    val shouldDisplayValue
        get() = valueDisplayed.value

    val isStepped
        get() = stepped.value
}