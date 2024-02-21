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
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface

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
 * @param displaySurface [OdsDisplaySurface] applied to the button. It allows to force the button display on light or dark surface. By default, the appearance applied is based on the system night mode value.
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
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides displaySurface.rippleTheme
    ) {
        IconToggleButton(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled
        ) {
            val icon = if (checked) checkedIcon else uncheckedIcon
            icon.Content(OdsIconButton.Icon.ExtraParameters(enabled, displaySurface))
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsIconToggleButton() = Preview {
    var checked by remember { mutableStateOf(false) }
    OdsIconToggleButton(
        checked = checked,
        onCheckedChange = { checked = it },
        uncheckedIcon = OdsIconButton.Icon(painterResource(id = android.R.drawable.ic_media_play), "Play"),
        checkedIcon = OdsIconButton.Icon(painterResource(id = android.R.drawable.ic_media_pause), "Pause"),
    )
}
