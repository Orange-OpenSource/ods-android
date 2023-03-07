/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.navigationdrawers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberNavigationDrawersCustomizationState(
    subTitleChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    listIconChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    listDividerChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    contentExampleChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    sectionLabelChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    header: MutableState<ComponentNavigationDrawersContentState.Header> = rememberSaveable { mutableStateOf(ComponentNavigationDrawersContentState.Header.Avatar) },
) =
    remember(subTitleChecked, listIconChecked, listDividerChecked, contentExampleChecked, header, sectionLabelChecked) {
        ComponentNavigationDrawersContentState(subTitleChecked, listIconChecked, listDividerChecked, contentExampleChecked, sectionLabelChecked, header)
    }

class ComponentNavigationDrawersContentState(
    val subTitleChecked: MutableState<Boolean>,
    val listIconChecked: MutableState<Boolean>,
    val contentExampleChecked: MutableState<Boolean>,
    val listDividerChecked: MutableState<Boolean>,
    val sectionLabelChecked: MutableState<Boolean>,
    val header: MutableState<Header>
) {
    val isSubTitleChecked
        get() = subTitleChecked.value

    val isListIcon
        get() = listIconChecked.value

    val isListDivider
        get() = listDividerChecked.value

    val isContentExample
        get() = contentExampleChecked.value

    val isSectionLabel
        get() = sectionLabelChecked.value

    enum class Header {
        Avatar, Background, None
    }
}