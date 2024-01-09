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

package com.orange.ods.app.ui.components.sheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable


@Composable
fun rememberSheetsBottomCustomizationState(
    content: MutableState<SheetsBottomCustomizationState.Content> = rememberSaveable { mutableStateOf(SheetsBottomCustomizationState.Content.Empty) },
) = remember(content) { SheetsBottomCustomizationState(content) }

class SheetsBottomCustomizationState(
    val content: MutableState<Content>,
) {
    enum class Content {
        Empty, Example
    }
}