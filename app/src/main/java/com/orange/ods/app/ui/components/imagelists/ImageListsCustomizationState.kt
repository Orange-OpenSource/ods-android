/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.imagelists

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberImageListsCustomizationState(
    sideIcons: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    textDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(sideIcons, textDisplayed) {
        ImageListsCustomizationState(sideIcons, textDisplayed)
    }

class ImageListsCustomizationState(
    val sideIcons: MutableState<Boolean>,
    val textDisplayed: MutableState<Boolean>
) {
    val hasSideIcons
        get() = sideIcons.value

    val hasText
        get() = textDisplayed.value

}