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
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.listitem.OdsListItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberListItemCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    lineCount: MutableIntState = rememberSaveable { mutableIntStateOf(ListItemCustomizationState.DefaultLineCount) },
    selectedLeadingIconType: MutableState<OdsListItem.LeadingIcon.Type?> = rememberSaveable { mutableStateOf(null) },
    selectedTrailing: MutableState<Class<out OdsListItem.Trailing>?> = rememberSaveable { mutableStateOf(null) },
) = remember(lineCount) {
    ListItemCustomizationState(bottomSheetScaffoldState, lineCount, selectedLeadingIconType, selectedTrailing)
}

@OptIn(ExperimentalMaterialApi::class)
class ListItemCustomizationState(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val lineCount: MutableIntState,
    val selectedLeadingIconType: MutableState<OdsListItem.LeadingIcon.Type?>,
    val selectedTrailing: MutableState<Class<out OdsListItem.Trailing>?>,
) {
    companion object {
        const val DefaultLineCount = 2
        const val MinLineCount = 1
        const val MaxLineCount = 3
    }

    val trailings: List<Class<out OdsListItem.Trailing>?>
        get() = if (lineCount.intValue < MaxLineCount) {
            listOf(
                null,
                OdsListItem.TrailingCheckbox::class.java,
                OdsListItem.TrailingSwitch::class.java,
                OdsListItem.TrailingRadioButton::class.java,
                OdsListItem.TrailingIcon::class.java
            )
        } else {
            listOf(null, OdsListItem.TrailingCaption::class.java)
        }

    fun resetTrailing() {
        selectedTrailing.value = null
    }
}