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

package com.orange.ods.compose.component.control

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.colors.fromToken

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00" class="external" target="_blank">ODS switch</a>.
 *
 * Switches toggle the state of a single item on or off.
 *
 * @param checked Controls the checked state of the switch.
 * @param onCheckedChange Callback invoked on switch check. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the "checked" state.
 * @param modifier [Modifier] applied to the switch.
 * @param enabled Controls the enabled state of the switch. When `false`, the switch will not be checkable and appears disabled.
 */
@Composable
@OdsComposable
fun OdsSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    CompositionLocalProvider(LocalRippleTheme provides OdsPrimaryRippleTheme) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.switch.selectedHandleColor),
                checkedTrackColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.switch.selectedTrackColor),
                uncheckedThumbColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.switch.unselectedHandleColor),
                uncheckedTrackColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.switch.unselectedTrackColor),
                uncheckedBorderColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.switch.unselectedTrackOutlineColor),
            )
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSwitch() = OdsPreview {
    var checked by remember { mutableStateOf(false) }
    OdsSwitch(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}
