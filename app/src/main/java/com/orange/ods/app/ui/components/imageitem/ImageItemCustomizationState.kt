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
import com.orange.ods.compose.component.imageitem.OdsImageItemTitleType

@Composable
fun rememberImageItemCustomizationState(
    type: MutableState<OdsImageItemTitleType> = rememberSaveable { mutableStateOf(OdsImageItemTitleType.Overlay) },
    iconDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(type, iconDisplayed) {
        ImageItemCustomizationState(type, iconDisplayed)
    }

class ImageItemCustomizationState(
    val type: MutableState<OdsImageItemTitleType>,
    val iconDisplayed: MutableState<Boolean>
) {

    val isOverlay
        get() = type.value == OdsImageItemTitleType.Overlay

    val isBelow
        get() = type.value == OdsImageItemTitleType.Below

    val hasIcon
        get() = iconDisplayed.value

    val hasText
        get() = type.value != OdsImageItemTitleType.None
}