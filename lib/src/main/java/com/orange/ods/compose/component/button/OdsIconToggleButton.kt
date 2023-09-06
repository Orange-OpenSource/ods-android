/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
 * @param checked Whether this OdsIconToggleButton is currently checked
 * @param onCheckedChange Callback to be invoked when this icon is selected
 * @param uncheckedIcon The icon displayed when unchecked
 * @param checkedIcon The icon displayed when checked
 * @param modifier [Modifier] for this OdsIconToggleButton
 * @param enabled Whether or not this OdsIconToggleButton will handle input events and appear
 * enabled for semantics purposes
 * @param displaySurface Allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    uncheckedIcon: OdsIconButtonIcon,
    checkedIcon: OdsIconButtonIcon,
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
            icon.Content(enabled = enabled, displaySurface = displaySurface)
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
        uncheckedIcon = OdsIconButtonIcon(painterResource(id = android.R.drawable.ic_media_play), "Play"),
        checkedIcon = OdsIconButtonIcon(painterResource(id = android.R.drawable.ic_media_pause), "Pause"),
    )
}
