/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.card.OdsHorizontalCardImagePosition

@Composable
fun rememberCardCustomizationState(
    clickable: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    thumbnailChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    textChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    subtitleChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    actionButtonCount: MutableState<Int> = rememberSaveable { mutableStateOf(CardCustomizationState.MinActionButtonCount) },
    dividerEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    imagePosition: MutableState<OdsHorizontalCardImagePosition> = rememberSaveable { mutableStateOf(OdsHorizontalCardImagePosition.Start) }
) =
    remember(clickable, thumbnailChecked, textChecked, subtitleChecked, actionButtonCount, dividerEnabled, imagePosition) {
        CardCustomizationState(clickable, thumbnailChecked, textChecked, subtitleChecked, actionButtonCount, dividerEnabled, imagePosition)
    }

class CardCustomizationState(
    val clickable: MutableState<Boolean>,
    val thumbnailChecked: MutableState<Boolean>,
    val textChecked: MutableState<Boolean>,
    val subtitleChecked: MutableState<Boolean>,
    val actionButtonCount: MutableState<Int>,
    val dividerEnabled: MutableState<Boolean>,
    val imagePosition: MutableState<OdsHorizontalCardImagePosition>
) {

    companion object {
        const val MinActionButtonCount = 0
        const val MaxActionButtonCount = 2
    }

    val isClickable
        get() = clickable.value

    val hasThumbnail
        get() = thumbnailChecked.value

    val hasText
        get() = textChecked.value

    val hasSubtitle
        get() = subtitleChecked.value

    val hasButton1
        get() = actionButtonCount.value > 0

    val hasButton2
        get() = actionButtonCount.value > 1

    val hasDivider
        get() = dividerEnabled.value
}