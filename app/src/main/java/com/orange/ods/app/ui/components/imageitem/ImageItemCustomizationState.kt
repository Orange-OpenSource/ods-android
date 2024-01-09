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