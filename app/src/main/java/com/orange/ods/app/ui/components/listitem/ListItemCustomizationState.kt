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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ods.compose.component.listitem.OdsListItem

@Composable
fun rememberListItemCustomizationState(
    secondaryTextType: MutableState<ListItemCustomizationState.SecondaryTextType> = rememberSaveable { mutableStateOf(ListItemCustomizationState.SecondaryTextType.None) },
    selectedLeadingIconType: MutableState<OdsListItem.LeadingIcon.Type?> = rememberSaveable { mutableStateOf(null) },
    selectedTrailing: MutableState<Class<out OdsListItem.Trailing>?> = rememberSaveable { mutableStateOf(null) },
) = remember(secondaryTextType) {
    ListItemCustomizationState(secondaryTextType, selectedLeadingIconType, selectedTrailing)
}

class ListItemCustomizationState(
    val secondaryTextType: MutableState<SecondaryTextType>,
    val selectedLeadingIconType: MutableState<OdsListItem.LeadingIcon.Type?>,
    val selectedTrailing: MutableState<Class<out OdsListItem.Trailing>?>,
) {
    enum class SecondaryTextType {
        None, Subtitle, Description
    }

    val trailings: List<Class<out OdsListItem.Trailing>?>
        get() = if (secondaryTextType.value != SecondaryTextType.Description) {
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