/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.banners

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberBannerCustomizationState(
    buttonsCount: MutableState<Int> = rememberSaveable { mutableStateOf(BannerCustomizationState.MinActionButtonCount) },
    textLinesCount: MutableState<Int> = rememberSaveable { mutableStateOf(BannerCustomizationState.MaxTextCount) },
    dividerChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    iconChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(buttonsCount, textLinesCount, dividerChecked, iconChecked) {
        BannerCustomizationState(buttonsCount, textLinesCount, dividerChecked, iconChecked)
    }

class BannerCustomizationState(
    val buttonsCount: MutableState<Int>,
    val textLinesCount: MutableState<Int>,
    val dividerChecked: MutableState<Boolean>,
    val iconChecked: MutableState<Boolean>,
) {

    companion object {
        const val MinActionButtonCount = 1
        const val MaxActionButtonCount = 2
        const val MinTextCount = 1
        const val MaxTextCount = 2
    }

    val hasDivider
        get() = dividerChecked.value

    val hasIcon
        get() = iconChecked.value

    val hasButton
        get() = buttonsCount.value > 1

    val hasTextLines
        get() = textLinesCount.value > 1
}