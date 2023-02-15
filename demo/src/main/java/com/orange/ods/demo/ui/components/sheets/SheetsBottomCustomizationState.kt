/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.sheets

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