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
import androidx.compose.foundation.clickable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsRadioButton
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.component.list.OdsListItem

@ExperimentalMaterialApi
@Composable
fun CheckboxListItem(@StringRes labelRes: Int, checked: MutableState<Boolean>, enabled: Boolean = true) {
    OdsListItem(
        modifier = Modifier.clickable { checked.value = !checked.value },
        text = stringResource(id = labelRes),
        trailing = {
            OdsCheckbox(
                checked = checked.value,
                onCheckedChange = { checked.value = it },
                enabled = enabled
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun <T> RadioButtonListItem(@StringRes labelRes: Int, selectedRadio: MutableState<T>, currentRadio: T, onClick: () -> Unit = {}, enabled: Boolean = true) {
    val clickAction = {
        selectedRadio.value = currentRadio
        onClick.invoke()
    }
    OdsListItem(
        modifier = Modifier.clickable { clickAction() },
        text = stringResource(id = labelRes),
        trailing = {
            OdsRadioButton(
                selected = selectedRadio.value == currentRadio,
                onClick = { clickAction() },
                enabled = enabled
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun SwitchListItem(@StringRes labelRes: Int, checked: MutableState<Boolean>, enabled: Boolean = true) {
    OdsListItem(
        modifier = Modifier.clickable { checked.value = !checked.value },
        text = stringResource(id = labelRes),
        trailing = {
            OdsSwitch(
                checked = checked.value,
                onCheckedChange = { checked.value = it },
                enabled = enabled
            )
        }
    )
}
