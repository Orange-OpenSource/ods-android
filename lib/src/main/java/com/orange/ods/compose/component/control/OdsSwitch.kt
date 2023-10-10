/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.control

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00" class="external" target="_blank">ODS switch</a>.
 *
 * Switches toggle the state of a single item on or off.
 *
 * @param checked Controls the checked state of the switch
 * @param onCheckedChange Callback invoked on switch check. If `null`, then this is passive and relies entirely on a higher-level component to control
 * the "checked" state.
 * @param modifier [Modifier] applied to the switch
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
            colors = SwitchDefaults.colors(uncheckedThumbColor = OdsTheme.colors.component.switch.uncheckedThumb)
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSwitch() = Preview {
    var checked by remember { mutableStateOf(false) }
    OdsSwitch(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}
