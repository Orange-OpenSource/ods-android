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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsRadioButton
import com.orange.ods.compose.text.OdsTextBody1

@Composable
fun LabelledCheckbox(
    label: String,
    checked: MutableState<Boolean>,
    enabled: Boolean = true
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        OdsCheckbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it },
            enabled = enabled,
        )
        OdsTextBody1(text = label)
    }
}

@Composable
fun LabelledRadioButton(
    selectedRadio: MutableState<String>,
    currentRadio: String,
    label: String,
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier.clickable {
            selectedRadio.value = currentRadio
            onClick.invoke()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        OdsRadioButton(
            selected = selectedRadio.value == currentRadio,
            onClick = {},
            enabled = enabled
        )
        OdsTextBody1(text = label)
    }
}