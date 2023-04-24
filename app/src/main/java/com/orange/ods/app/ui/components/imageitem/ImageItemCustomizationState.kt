/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.imageitem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.app.ui.components.floatingactionbuttons.FabCustomizationState

@Composable
fun rememberImageItemCustomizationState(
    size: MutableState<ImageItemCustomizationState.Size> = rememberSaveable { mutableStateOf(ImageItemCustomizationState.Size.Small)},
    iconDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    textDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(iconDisplayed, textDisplayed, size) {
        ImageItemCustomizationState(size, iconDisplayed, textDisplayed)
    }

class ImageItemCustomizationState(
    val size: MutableState<Size>,
    val iconDisplayed: MutableState<Boolean>,
    val textDisplayed: MutableState<Boolean>
) {
    enum class Size {
        Small, Medium, Large;
       companion object {
           fun fromSliderValue(sliderValue: Float) =
               when (sliderValue) {
                   0f -> Small
                   1f -> Medium
                   else -> Large
               }
       }
    }

    val hasIcon
        get() = iconDisplayed.value

    val hasText
        get() = textDisplayed.value

}