/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.components.listitem

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.listitem.OdsListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberListItemCustomizationState(
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    secondaryTextLineCount: MutableState<OdsListItem.SecondaryTextLineCount?> = rememberSaveable { mutableStateOf(OdsListItem.SecondaryTextLineCount.One) },
    selectedLeadingIconType: MutableState<OdsListItem.LeadingIcon.Type?> = rememberSaveable { mutableStateOf(null) },
    selectedTrailing: MutableState<Class<out OdsListItem.Trailing>?> = rememberSaveable { mutableStateOf(null) },
) = remember(bottomSheetScaffoldState, secondaryTextLineCount, selectedLeadingIconType, selectedTrailing) {
    ListItemCustomizationState(bottomSheetScaffoldState, secondaryTextLineCount, selectedLeadingIconType, selectedTrailing)
}

class ListItemCustomizationState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val bottomSheetScaffoldState: BottomSheetScaffoldState,
    val secondaryTextLineCount: MutableState<OdsListItem.SecondaryTextLineCount?>,
    val selectedLeadingIconType: MutableState<OdsListItem.LeadingIcon.Type?>,
    val selectedTrailing: MutableState<Class<out OdsListItem.Trailing>?>,
) {
    val trailings: List<Class<out OdsListItem.Trailing>?>
        get() = if (secondaryTextLineCount.value != OdsListItem.SecondaryTextLineCount.Two) {
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