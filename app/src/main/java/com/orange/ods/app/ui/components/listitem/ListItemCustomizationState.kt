/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.listitem

import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.OdsListItemTrailing
import com.orange.ods.compose.component.list.OdsListItemTrailingCaption
import com.orange.ods.compose.component.list.OdsListItemTrailingCheckbox
import com.orange.ods.compose.component.list.OdsListItemTrailingIcon
import com.orange.ods.compose.component.list.OdsListItemTrailingRadioButton
import com.orange.ods.compose.component.list.OdsListItemTrailingSwitch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberListItemCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    lineCount: MutableState<Int> = rememberSaveable { mutableStateOf(ListItemCustomizationState.DefaultLineCount) },
    selectedIconType: MutableState<OdsListItemIconType?> = rememberSaveable { mutableStateOf(null) },
    selectedTrailing: MutableState<Class<out OdsListItemTrailing>?> = rememberSaveable { mutableStateOf(null) },
) = remember(lineCount) {
    ListItemCustomizationState(bottomSheetScaffoldState, lineCount, selectedIconType, selectedTrailing)
}

@OptIn(ExperimentalMaterialApi::class)
class ListItemCustomizationState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val lineCount: MutableState<Int>,
    val selectedIconType: MutableState<OdsListItemIconType?>,
    val selectedTrailing: MutableState<Class<out OdsListItemTrailing>?>,
) {
    companion object {
        const val DefaultLineCount = 2
        const val MinLineCount = 1
        const val MaxLineCount = 3
    }

    val trailings: List<Class<out OdsListItemTrailing>?>
        get() = if (lineCount.value < MaxLineCount) {
            listOf(
                null,
                OdsListItemTrailingCheckbox::class.java,
                OdsListItemTrailingSwitch::class.java,
                OdsListItemTrailingRadioButton::class.java,
                OdsListItemTrailingIcon::class.java
            )
        } else {
            listOf(null, OdsListItemTrailingCaption::class.java)
        }

    fun resetTrailing() {
        selectedTrailing.value = null
    }
}