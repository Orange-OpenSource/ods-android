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
    shortMessage: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    imageChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(buttonsCount, shortMessage, imageChecked) {
        BannerCustomizationState(buttonsCount, shortMessage, imageChecked)
    }

class BannerCustomizationState(
    val buttonsCount: MutableState<Int>,
    val shortMessage: MutableState<Boolean>,
    val imageChecked: MutableState<Boolean>,
) {

    companion object {
        const val MinActionButtonCount = 0
        const val MaxActionButtonCount = 2
    }

    val hasImage
        get() = imageChecked.value

    val hasFirstButton
        get() = buttonsCount.value > 0

    val hasSecondButton
        get() = buttonsCount.value > 1

    val hasShortMessage
        get() = shortMessage.value
}