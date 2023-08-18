/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.imagetile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.imagetile.OdsImageTileCaptionDisplayType

@Composable
fun rememberImageTileCustomizationState(
    type: MutableState<OdsImageTileCaptionDisplayType> = rememberSaveable { mutableStateOf(OdsImageTileCaptionDisplayType.Overlay) },
    iconDisplayed: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(type, iconDisplayed) {
        ImageTileCustomizationState(type, iconDisplayed)
    }

class ImageTileCustomizationState(
    val type: MutableState<OdsImageTileCaptionDisplayType>,
    val iconDisplayed: MutableState<Boolean>
) {

    val isOverlay
        get() = type.value == OdsImageTileCaptionDisplayType.Overlay

    val isBelow
        get() = type.value == OdsImageTileCaptionDisplayType.Below

    val hasIcon
        get() = iconDisplayed.value

    val hasText
        get() = type.value != OdsImageTileCaptionDisplayType.None
}