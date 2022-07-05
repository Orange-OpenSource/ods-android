/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsRadioButton
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.component.list.OdsListItem

@ExperimentalMaterialApi
@Composable
fun CheckboxListItem(@StringRes labelRes: Int, checked: MutableState<Boolean>, enabled: Boolean = true) {
    OdsListItem(
        modifier = if (enabled) Modifier.toggleable(
            value = checked.value,
            role = Role.Checkbox,
            onValueChange = { checked.value = !checked.value }) else Modifier,
        text = stringResource(id = labelRes),
        trailing = {
            OdsCheckbox(
                checked = checked.value,
                onCheckedChange = null,
                enabled = enabled
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun <T> RadioButtonListItem(@StringRes labelRes: Int, selectedRadio: MutableState<T>, currentRadio: T, onClick: () -> Unit = {}, enabled: Boolean = true) {
    val selected = selectedRadio.value == currentRadio
    OdsListItem(
        modifier = if (enabled) Modifier.toggleable(
            value = selected,
            role = Role.RadioButton,
            onValueChange = {
                selectedRadio.value = currentRadio
                onClick.invoke()
            }) else Modifier,
        text = stringResource(id = labelRes),
        trailing = {
            OdsRadioButton(
                selected = selected,
                onClick = null,
                enabled = enabled
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun SwitchListItem(@StringRes labelRes: Int, checked: MutableState<Boolean>, enabled: Boolean = true) {
    OdsListItem(
        modifier = if (enabled) Modifier.toggleable(
            value = checked.value,
            role = Role.Switch,
            onValueChange = {
                checked.value = !checked.value
            }) else Modifier,
        text = stringResource(id = labelRes),
        trailing = {
            OdsSwitch(
                checked = checked.value,
                onCheckedChange = null,
                enabled = enabled
            )
        }
    )
}
