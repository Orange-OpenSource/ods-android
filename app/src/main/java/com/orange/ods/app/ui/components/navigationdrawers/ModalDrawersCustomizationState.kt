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
    contentExampleChecked: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    header: MutableState<ComponentNavigationDrawersContentState.Header> = rememberSaveable { mutableStateOf(ComponentNavigationDrawersContentState.Header.Avatar) },
    content: MutableState<ComponentNavigationDrawersContentState.Content> = rememberSaveable { mutableStateOf(ComponentNavigationDrawersContentState.Content.None) }
) =
    remember(subTitleChecked, listIconChecked, contentExampleChecked, header, content) {
        ComponentNavigationDrawersContentState(
            subTitleChecked,
            listIconChecked,
            contentExampleChecked,
            content,
            header
        )
    }

class ComponentNavigationDrawersContentState(
    val subTitleChecked: MutableState<Boolean>,
    val listIconChecked: MutableState<Boolean>,
    val contentExampleChecked: MutableState<Boolean>,
    val content: MutableState<Content>,
    val header: MutableState<Header>
) {
    val isSubTitleChecked
        get() = subTitleChecked.value

    val isListIcon
        get() = listIconChecked.value

    val isContentExample
        get() = contentExampleChecked.value

    enum class Header {
        Avatar, Background, None
    }

    enum class Content {
        Divider, Label, None
    }
    
    val hasAvatar
        get() = header.value == Header.Avatar

    val hasBackground
        get() = header.value == Header.Background

    val hasDivider
        get() = content.value == Content.Divider

    val hasLabel
        get() = content.value == Content.Label
}