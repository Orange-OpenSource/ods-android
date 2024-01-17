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

package com.orange.ods.app.ui.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberDialogCustomizationState(
    titleChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    dismissButtonChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    openDialog: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(titleChecked, dismissButtonChecked, openDialog) {
        ComponentDialogsContentState(titleChecked, dismissButtonChecked, openDialog)
    }

class ComponentDialogsContentState(
    val titleChecked: MutableState<Boolean>,
    val dismissButtonChecked: MutableState<Boolean>,
    val openDialog: MutableState<Boolean>
) {
    val isTitleChecked
        get() = titleChecked.value

    val isDismissButtonChecked
        get() = dismissButtonChecked.value

    val shouldOpenDialog
        get() = openDialog.value
}