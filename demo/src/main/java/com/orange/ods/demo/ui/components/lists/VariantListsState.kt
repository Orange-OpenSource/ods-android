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
fun rememberVariantListsState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    selectedSize: MutableState<VariantListsState.Size> = rememberSaveable { mutableStateOf(VariantListsState.Size.SingleLine) },
    selectedLeading: MutableState<VariantListsState.Leading> = rememberSaveable { mutableStateOf(VariantListsState.Leading.None) },
    selectedTrailing: MutableState<VariantListsState.Trailing> = rememberSaveable { mutableStateOf(VariantListsState.Trailing.None) },
    dividerEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) = remember(selectedSize) {
    VariantListsState(bottomSheetScaffoldState, selectedSize, selectedLeading, selectedTrailing, dividerEnabled)
}

@ExperimentalMaterialApi
class VariantListsState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val selectedSize: MutableState<Size>,
    val selectedLeading: MutableState<Leading>,
    val selectedTrailing: MutableState<Trailing>,
    val dividerEnabled: MutableState<Boolean>
) {
    enum class Size {
        SingleLine, TwoLine, ThreeLine
    }

    enum class Leading {
        None, Icon, CircularImage, SquareImage, WideImage
    }

    enum class Trailing {
        None, Checkbox, Switch, Icon, Caption
    }

    val trailings: List<Trailing>
        get() = when (selectedSize.value) {
            Size.SingleLine,
            Size.TwoLine -> listOf(Trailing.None, Trailing.Checkbox, Trailing.Switch, Trailing.Icon)
            Size.ThreeLine -> listOf(Trailing.None, Trailing.Caption)
        }

    fun resetTrailing() {
        selectedTrailing.value = Trailing.None
    }
}