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

package com.orange.ods.compose.component.button

import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * An [IconButton] with two states, for icons that can be toggled 'on' and 'off', such as a
 * bookmark icon, or a navigation icon that opens a drawer.
 *
 * @param checked Controls the checked state of the button.
 * @param onCheckedChange Callback invoked when the button is checked.
 * @param uncheckedIcon Icon displayed when the button is unchecked.
 * @param checkedIcon Icon displayed when the button is checked.
 * @param modifier [Modifier] applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be clickable.
 */
@Composable
@OdsComposable
fun OdsIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    uncheckedIcon: OdsIconButton.Icon,
    checkedIcon: OdsIconButton.Icon,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled
    ) {
        val icon = if (checked) checkedIcon else uncheckedIcon
        icon.Content(OdsIconButton.Icon.ExtraParameters(enabled))
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsIconToggleButton(@PreviewParameter(OdsIconToggleButtonPreviewParameterProvider::class) enabled: Boolean) = Preview {
    var checked by remember { mutableStateOf(false) }
    OdsIconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        uncheckedIcon = OdsIconButton.Icon(painterResource(id = android.R.drawable.ic_dialog_info), "Info"),
        checkedIcon = OdsIconButton.Icon(painterResource(id = android.R.drawable.ic_dialog_alert), "Alert"),
        enabled = enabled
    )
}

private class OdsIconToggleButtonPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(true, false)
