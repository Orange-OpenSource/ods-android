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

@Composable
fun rememberCardCustomizationState(
    clickable: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    thumbnailChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    textChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    subtitleChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    button1Checked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    button2Checked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
) =
    remember(clickable, thumbnailChecked, textChecked, subtitleChecked, button1Checked, button2Checked) {
        CardCustomizationState(clickable, thumbnailChecked, textChecked, subtitleChecked, button1Checked, button2Checked)
    }

class CardCustomizationState(
    val clickable: MutableState<Boolean>,
    val thumbnailChecked: MutableState<Boolean>,
    val textChecked: MutableState<Boolean>,
    val subtitleChecked: MutableState<Boolean>,
    val button1Checked: MutableState<Boolean>,
    val button2Checked: MutableState<Boolean>
) {
    val isClickable
        get() = clickable.value
    
    val hasThumbnail
        get() = thumbnailChecked.value

    val hasText
        get() = textChecked.value

    val hasSubtitle
        get() = subtitleChecked.value

    val hasButton1
        get() = button1Checked.value

    val hasButton2
        get() = button2Checked.value
}