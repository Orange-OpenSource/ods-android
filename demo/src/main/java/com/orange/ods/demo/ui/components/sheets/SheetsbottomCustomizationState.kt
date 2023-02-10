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
fun rememberSheetsbottomCustomizationState(
    content: MutableState<SheetsbottomCustomizationState.Content> = rememberSaveable { mutableStateOf(SheetsbottomCustomizationState.Content.Empty) },
) =
    remember(
        content,
    ) {
        SheetsbottomCustomizationState(
            content,
        )
    }

class SheetsbottomCustomizationState(

    val content: MutableState<Content>,
) {

    enum class Content {
        Empty, Example
    }
    

}