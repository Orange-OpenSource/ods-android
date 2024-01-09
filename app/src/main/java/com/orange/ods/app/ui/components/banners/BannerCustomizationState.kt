/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.components.banners

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberBannerCustomizationState(
    buttonsCount: MutableIntState = rememberSaveable { mutableIntStateOf(BannerCustomizationState.MinActionButtonCount) },
    shortMessage: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    imageChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(buttonsCount, shortMessage, imageChecked) {
        BannerCustomizationState(buttonsCount, shortMessage, imageChecked)
    }

class BannerCustomizationState(
    val buttonsCount: MutableIntState,
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
        get() = buttonsCount.intValue > 0

    val hasSecondButton
        get() = buttonsCount.intValue > 1

    val hasShortMessage
        get() = shortMessage.value
}