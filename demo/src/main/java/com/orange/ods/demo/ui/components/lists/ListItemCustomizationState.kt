/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.lists

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

object ComponentListItem {
    const val DefaultLineCount = 2
    const val MinLineCount = 1
    const val MaxLineCount = 3
}

@ExperimentalMaterialApi
@Composable
fun rememberListItemCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    lineCount: MutableState<Int> = rememberSaveable { mutableStateOf(ComponentListItem.DefaultLineCount) },
    selectedLeading: MutableState<ListItemCustomizationState.Leading> = rememberSaveable { mutableStateOf(ListItemCustomizationState.Leading.None) },
    selectedTrailing: MutableState<ListItemCustomizationState.Trailing> = rememberSaveable { mutableStateOf(ListItemCustomizationState.Trailing.None) },
    dividerEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) = remember(lineCount) {
    ListItemCustomizationState(bottomSheetScaffoldState, lineCount, selectedLeading, selectedTrailing, dividerEnabled)
}

@ExperimentalMaterialApi
class ListItemCustomizationState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val lineCount: MutableState<Int>,
    val selectedLeading: MutableState<Leading>,
    val selectedTrailing: MutableState<Trailing>,
    val dividerEnabled: MutableState<Boolean>
) {

    enum class Leading {
        None, Icon, CircularImage, SquareImage, WideImage
    }

    enum class Trailing {
        None, Checkbox, Switch, Icon, Caption
    }

    val trailings: List<Trailing>
        get() = if (lineCount.value < ComponentListItem.MaxLineCount) {
            listOf(Trailing.None, Trailing.Checkbox, Trailing.Switch, Trailing.Icon)
        } else {
            listOf(Trailing.None, Trailing.Caption)
        }

    fun resetTrailing() {
        selectedTrailing.value = Trailing.None
    }
}