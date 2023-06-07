/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.menu

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.textfield.OdsExposedDropdownMenuTrailing
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/07a69b-menus/b/862cbb" class="external" target="_blank">ODS menus</a>.
 *
 * @param label The label of the text field
 * @param items The [OdsExposedDropdownMenuItem]s displayed in the dropdown menu
 * @param selectedItem The selected item displayed in the text field
 * @param onItemSelectionChange The action executed when a dropdown menu item is selected. Can be used to get the menu value.
 * @param modifier The modifier to be applied to the menu
 * @param enabled controls the enabled state of the [OdsExposedDropdownMenu]. When `false`, the dropdown menu text field will
 * be neither clickable nor focusable, visually it will appear in the disabled UI state
 */
@OdsComposable
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OdsExposedDropdownMenu(
    label: String,
    items: List<OdsExposedDropdownMenuItem>,
    selectedItem: MutableState<OdsExposedDropdownMenuItem>,
    onItemSelectionChange: (OdsExposedDropdownMenuItem) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OdsTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedItem.value.label,
            onValueChange = {},
            readOnly = true,
            label = label,
            leadingIcon = selectedItem.value.icon,
            trailing = OdsExposedDropdownMenuTrailing(expanded = if (enabled) expanded else false, enabled = enabled),
            enabled = enabled
        )
        OdsDropdownMenu(
            modifier = Modifier.exposedDropdownSize(),
            expanded = if (enabled) expanded else false,
            onDismissRequest = { expanded = false },
            content = {
                items.forEach { item ->
                    OdsDropdownMenuItem(text = item.label, icon = item.icon, onClick = {
                        selectedItem.value = item
                        expanded = false
                        onItemSelectionChange(item)
                    })
                }
            }
        )
    }
}

data class OdsExposedDropdownMenuItem(val label: String, val icon: Painter? = null)

/**
 * This Saver implementation converts OdsExposedDropdownMenuItem object which we don't know how to save to String which we can save.
 */
class OdsExposedDropdownMenuItemSaver : Saver<OdsExposedDropdownMenuItem, String> {
    override fun restore(value: String) = OdsExposedDropdownMenuItem(label = value)
    override fun SaverScope.save(value: OdsExposedDropdownMenuItem) = value.label
}

/**
 * Note: Please use Android Studio preview interactive mode to see the OdsExposedDropdownMenu preview cause expanded is a target state.
 */
@UiModePreviews.Default
@Composable
private fun PreviewOdsDropdownMenu(@PreviewParameter(OdsDropdownMenuPreviewParameterProvider::class) enabled: Boolean) = Preview {
    val items = listOf(
        OdsExposedDropdownMenuItem("Email", painterResource(id = android.R.drawable.ic_dialog_email)),
        OdsExposedDropdownMenuItem("Map", painterResource(id = android.R.drawable.ic_dialog_map)),
        OdsExposedDropdownMenuItem("Dialer", painterResource(id = android.R.drawable.ic_dialog_dialer)),
        OdsExposedDropdownMenuItem("Info", painterResource(id = android.R.drawable.ic_dialog_info))
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
