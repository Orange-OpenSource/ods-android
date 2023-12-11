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
import com.orange.ods.compose.component.imageitem.OdsImageItem

@Composable
fun rememberImageItemCustomizationState(
    type: MutableState<OdsImageItem.LegendAreaDisplayType> = rememberSaveable { mutableStateOf(OdsImageItem.LegendAreaDisplayType.Overlay) },
    iconDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(type, iconDisplayed) {
        ImageItemCustomizationState(type, iconDisplayed)
    }

class ImageItemCustomizationState(
    val type: MutableState<OdsImageItem.LegendAreaDisplayType>,
    val iconDisplayed: MutableState<Boolean>
) {
    val hasIcon
        get() = iconDisplayed.value

    val hasText
        get() = type.value != OdsImageItem.LegendAreaDisplayType.None
}