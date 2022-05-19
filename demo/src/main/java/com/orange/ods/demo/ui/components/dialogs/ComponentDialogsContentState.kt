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
fun rememberComponentDialogsContentState(
    titleChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    dismissButtonChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
) =
    remember(titleChecked, dismissButtonChecked) {
        ComponentDialogsContentState(titleChecked, dismissButtonChecked)
    }

class ComponentDialogsContentState(
    val titleChecked: MutableState<Boolean>,
    val dismissButtonChecked: MutableState<Boolean>,
) {
    val isTitleChecked
        get() = titleChecked.value

    val isDismissButtonChecked
        get() = dismissButtonChecked.value
}