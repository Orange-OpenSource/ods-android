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

@Composable
fun rememberImageItemCustomizationState(
    iconDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    textDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(iconDisplayed, textDisplayed) {
        ImageItemCustomizationState(iconDisplayed, textDisplayed)
    }

class ImageItemCustomizationState(
    val iconDisplayed: MutableState<Boolean>,
    val textDisplayed: MutableState<Boolean>
) {
    val hasIcon
        get() = iconDisplayed.value

    val hasText
        get() = textDisplayed.value

}