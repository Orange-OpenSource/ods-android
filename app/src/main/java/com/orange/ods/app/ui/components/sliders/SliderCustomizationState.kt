/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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