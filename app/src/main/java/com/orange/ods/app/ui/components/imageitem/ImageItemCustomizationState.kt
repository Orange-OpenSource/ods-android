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
    type: MutableState<ImageItemCustomizationState.Type> = rememberSaveable { mutableStateOf(ImageItemCustomizationState.Type.Overlay) },
    iconDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(type, iconDisplayed) {
        ImageItemCustomizationState(type, iconDisplayed)
    }

class ImageItemCustomizationState(
    val type: MutableState<Type>,
    val iconDisplayed: MutableState<Boolean>
) {
    enum class Type {
        Overlay, Below, None
    }

    val isOverlay
        get() = type.value == Type.Overlay

    val isBelow
        get() = type.value == Type.Below

    val hasIcon
        get() = iconDisplayed.value

    val hasText
        get() = type.value != OdsImageItemTitleType.None
}