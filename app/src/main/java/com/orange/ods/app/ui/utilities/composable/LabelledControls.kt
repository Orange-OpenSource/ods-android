/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.utilities.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsRadioButton
import com.orange.ods.compose.text.OdsTextBodyL
import com.orange.ods.compose.theme.OdsTheme

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
        OdsTextBodyL(text = label)
    }
}

@Composable
fun <T> LabelledRadioButton(
    selectedRadio: MutableState<T>,
    currentRadio: T,
    label: String,
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    val clickAction = {
        selectedRadio.value = currentRadio
        onClick.invoke()
    }
    Row(
        modifier = if (enabled) Modifier.clickable { clickAction.invoke() } else Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OdsRadioButton(
            selected = selectedRadio.value == currentRadio,
            onClick = { clickAction.invoke() },
            enabled = enabled
        )
        Text(
            text = label,
            style = OdsTheme.typography.bodyL,
            color = OdsTheme.colors.onSurface.copy(alpha = if (enabled) ContentAlpha.high else ContentAlpha.disabled)
        )
    }
}