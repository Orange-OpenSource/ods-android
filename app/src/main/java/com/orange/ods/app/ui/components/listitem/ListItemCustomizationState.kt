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
import com.orange.ods.compose.component.list.OdsListItemTrailingBuilder
import com.orange.ods.compose.component.list.OdsListItemTrailingCaptionBuilder
import com.orange.ods.compose.component.list.OdsListItemTrailingCheckboxBuilder
import com.orange.ods.compose.component.list.OdsListItemTrailingIconBuilder
import com.orange.ods.compose.component.list.OdsListItemTrailingRadioButtonBuilder
import com.orange.ods.compose.component.list.OdsListItemTrailingSwitchBuilder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberListItemCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    lineCount: MutableState<Int> = rememberSaveable { mutableStateOf(ListItemCustomizationState.DefaultLineCount) },
    selectedIconType: MutableState<OdsListItemIconType?> = rememberSaveable { mutableStateOf(null) },
    selectedTrailing: MutableState<Class<out OdsListItemTrailingBuilder>?> = rememberSaveable { mutableStateOf(null) },
) = remember(lineCount) {
    ListItemCustomizationState(bottomSheetScaffoldState, lineCount, selectedIconType, selectedTrailing)
}

@OptIn(ExperimentalMaterialApi::class)
class ListItemCustomizationState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val lineCount: MutableState<Int>,
    val selectedIconType: MutableState<OdsListItemIconType?>,
    val selectedTrailing: MutableState<Class<out OdsListItemTrailingBuilder>?>,
) {
    companion object {
        const val DefaultLineCount = 2
        const val MinLineCount = 1
        const val MaxLineCount = 3
    }

    val trailings: List<Class<out OdsListItemTrailingBuilder>?>
        get() = if (lineCount.value < MaxLineCount) {
            listOf(
                null,
                OdsListItemTrailingCheckboxBuilder::class.java,
                OdsListItemTrailingSwitchBuilder::class.java,
                OdsListItemTrailingRadioButtonBuilder::class.java,
                OdsListItemTrailingIconBuilder::class.java
            )
        } else {
            listOf(null, OdsListItemTrailingCaptionBuilder::class.java)
        }

    fun resetTrailing() {
        selectedTrailing.value = null
    }
}