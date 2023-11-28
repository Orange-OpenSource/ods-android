/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.banners

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberBannerCustomizationState(
    buttonsCount: MutableState<Int> = rememberSaveable { mutableStateOf(BannerCustomizationState.MinActionButtonCount) },
    textLineCount: MutableState<Int> = rememberSaveable { mutableStateOf(BannerCustomizationState.MaxTextLineCount) },
    imageChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(buttonsCount, textLineCount, imageChecked) {
        BannerCustomizationState(buttonsCount, textLineCount, imageChecked)
    }

class BannerCustomizationState(
    val buttonsCount: MutableState<Int>,
    val textLinesCount: MutableState<Int>,
    val imageChecked: MutableState<Boolean>,
) {

    companion object {
        const val MinActionButtonCount = 0
        const val MaxActionButtonCount = 2
        const val MinTextLineCount = 1
        const val MaxTextLineCount = 2
    }

    val hasImage
        get() = imageChecked.value

    val hasFirstButton
        get() = buttonsCount.value > 0

    val hasSecondButton
        get() = buttonsCount.value > 1

    val hasTwoTextLines
        get() = textLinesCount.value > 1
}