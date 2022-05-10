/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberDialogCustomizationState(
    titleChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    iconChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    button1Checked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    button2Checked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
) =
    remember(titleChecked, iconChecked, button1Checked, button2Checked) {
        DialogCustomizationState(titleChecked, iconChecked, button1Checked, button2Checked)
    }

class DialogCustomizationState(
    val titleChecked: MutableState<Boolean>,
    val iconChecked: MutableState<Boolean>,
    val button1Checked: MutableState<Boolean>,
    val button2Checked: MutableState<Boolean>
) {
}