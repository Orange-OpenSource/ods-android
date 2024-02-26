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

package com.orange.ods.compose.component.menu

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.textfield.OdsExposedDropdownMenuTrailing
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.component.utilities.enabledStateDescription
import kotlinx.parcelize.Parcelize

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/07a69b-menus/b/862cbb" class="external" target="_blank">ODS menus</a>.
 *
 * @param label The label of the text field.
 * @param items List of [OdsExposedDropdownMenu.Item] displayed in the dropdown menu.
 * @param selectedItem Selected item displayed into the text field.
 * @param onItemSelectionChange Callback invoked when a dropdown menu item is selected. It can be used to get the menu value.
 * @param modifier [Modifier] applied to the dropdown menu.
 * @param enabled Controls the enabled state of the dropdown menu. When `false`, the dropdown menu text field will be neither clickable nor focusable,
 * visually it will appear in the disabled state.
 */
@OdsComposable
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OdsExposedDropdownMenu(
    label: String,
    items: List<OdsExposedDropdownMenu.Item>,
    selectedItem: MutableState<OdsExposedDropdownMenu.Item>,
    onItemSelectionChange: (OdsExposedDropdownMenu.Item) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val menuBoxStateDescription = enabledStateDescription(enabled = enabled)
    val menuBoxAction = if (enabled) stringResource(id = com.orange.ods.R.string.ods_dropdownMenu_open_actionA11y) else ""

    ExposedDropdownMenuBox(
        modifier = modifier
            .clearAndSetSemantics {
                contentDescription = "$label, ${selectedItem.value.label}, $menuBoxAction"
                stateDescription = menuBoxStateDescription
            }
            .focusProperties { canFocus = false },
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OdsTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            value = selectedItem.value.label,
            onValueChange = {},
            readOnly = true,
            label = label,
            leadingIcon = selectedItem.value.iconResId?.let { OdsTextField.LeadingIcon(painterResource(id = it), "") },
            trailing = OdsExposedDropdownMenuTrailing(expanded = if (enabled) expanded else false),
            enabled = enabled
        )
        OdsDropdownMenu(
            expanded = if (enabled) expanded else false,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize(),
            items = items.map { item ->
                OdsDropdownMenu.Item(text = item.label, icon = item.iconResId?.let { painterResource(id = it) }) {
                    selectedItem.value = item
                    expanded = false
                    onItemSelectionChange(item)
                }
            }
        )
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.menu.OdsExposedDropdownMenu].
 */
object OdsExposedDropdownMenu {
    @Parcelize
    data class Item(val label: String, @DrawableRes val iconResId: Int? = null) : Parcelable
}

/**
 * Note: Please use Android Studio preview interactive mode to see the OdsExposedDropdownMenu preview cause expanded is a target state.
 */
@UiModePreviews.Default
@Composable
private fun PreviewOdsDropdownMenu(@PreviewParameter(OdsDropdownMenuPreviewParameterProvider::class) enabled: Boolean) = Preview {
    val items = listOf(
        OdsExposedDropdownMenu.Item("Email", android.R.drawable.ic_dialog_email),
        OdsExposedDropdownMenu.Item("Map", android.R.drawable.ic_dialog_map),
        OdsExposedDropdownMenu.Item("Dialer", android.R.drawable.ic_dialog_dialer),
        OdsExposedDropdownMenu.Item("Info", android.R.drawable.ic_dialog_info)
    )
    val selectedItem = remember { mutableStateOf(items.first()) }
    OdsExposedDropdownMenu(
        label = "Label",
        items = items,
        selectedItem = selectedItem,
        onItemSelectionChange = { },
        enabled = enabled
    )
}

private class OdsDropdownMenuPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
