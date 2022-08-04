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

@ExperimentalMaterialApi
@Composable
fun rememberListItemCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    selectedItemSize: MutableState<ListItemCustomizationState.ItemSize> = rememberSaveable { mutableStateOf(ListItemCustomizationState.ItemSize.SingleLine) },
    selectedLeading: MutableState<ListItemCustomizationState.Leading> = rememberSaveable { mutableStateOf(ListItemCustomizationState.Leading.None) },
    selectedTrailing: MutableState<ListItemCustomizationState.Trailing> = rememberSaveable { mutableStateOf(ListItemCustomizationState.Trailing.None) },
    dividerEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) = remember(selectedItemSize) {
    ListItemCustomizationState(bottomSheetScaffoldState, selectedItemSize, selectedLeading, selectedTrailing, dividerEnabled)
}

@ExperimentalMaterialApi
class ListItemCustomizationState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val selectedItemSize: MutableState<ItemSize>,
    val selectedLeading: MutableState<Leading>,
    val selectedTrailing: MutableState<Trailing>,
    val dividerEnabled: MutableState<Boolean>
) {
    enum class ItemSize {
        SingleLine, TwoLine, ThreeLine
    }

    enum class Leading {
        None, Icon, CircularImage, SquareImage, WideImage
    }

    enum class Trailing {
        None, Checkbox, Switch, Icon, Caption
    }

    val trailings: List<Trailing>
        get() = when (selectedItemSize.value) {
            ItemSize.SingleLine,
            ItemSize.TwoLine -> listOf(Trailing.None, Trailing.Checkbox, Trailing.Switch, Trailing.Icon)
            ItemSize.ThreeLine -> listOf(Trailing.None, Trailing.Caption)
        }

    fun resetTrailing() {
        selectedTrailing.value = Trailing.None
    }
}