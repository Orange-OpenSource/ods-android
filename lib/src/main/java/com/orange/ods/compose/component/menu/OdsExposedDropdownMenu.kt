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

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.textfield.OdsDropdownMenuTrailing
import com.orange.ods.compose.component.textfield.OdsTextField
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/07a69b-menus/b/862cbb" class="external" target="_blank">ODS menus</a>.
 *
 * @see androidx.compose.material.DropdownMenu
 *
 * @param modifier The modifier to be applied to the menu
 */
@OdsComponentApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OdsExposedDropdownMenu(
    items: List<String>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember {
        mutableStateOf(items[0])
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OdsTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = "Label",
            trailing = OdsDropdownMenuTrailing(expanded = expanded),
            enabled = enabled
        )
        OdsDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            content = {
                items.forEach { item ->
                    OdsDropdownMenuItem(text = item, onClick = {
                        selectedItem = item
                        expanded = false
                    })
                }
            }
        )
    }
}

/**
 * Note: Please use Android Studio preview interactive mode to see the OdsExposedDropdownMenu preview cause expanded is a target state.
 */
@UiModePreviews.Default
@Composable
private fun PreviewOdsDropdownMenu() = Preview {

}
