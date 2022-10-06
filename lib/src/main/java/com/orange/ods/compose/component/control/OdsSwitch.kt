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

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.theme.Grey400
import com.orange.ods.compose.theme.LocalDarkThemeEnabled
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00" class="external" target="_blank">ODS switch</a>.
 *
 * Switches toggle the state of a single item on or off.
 *
 * @param checked whether or not this component is checked
 * @param onCheckedChange callback to be invoked when Switch is being clicked,
 * therefore the change of checked state is requested.  If null, then this is passive
 * and relies entirely on a higher-level component to control the "checked" state.
 * @param modifier Modifier to be applied to the switch layout
 * @param enabled whether the component is enabled or grayed out
 */
@Composable
@OdsComponentApi
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
            colors = odsSwitchColors()
        )
    }
}

@Composable
private fun odsSwitchColors() = SwitchDefaults.colors(uncheckedThumbColor = if (LocalDarkThemeEnabled.current) Grey400 else MaterialTheme.colors.surface)

@Composable
private fun PreviewOdsSwitch() = Preview {
    val checked = remember { mutableStateOf(false) }
    OdsSwitch(
        checked = checked.value,
        onCheckedChange = { checked.value = it }
    )
}

@Preview(name = "OdsSwitch - Light")
@Composable
private fun PreviewOdsSwitchLight() = PreviewOdsSwitch()

@Preview(
    name = "OdsSwitch - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsSwitchDark() = PreviewOdsSwitch()
