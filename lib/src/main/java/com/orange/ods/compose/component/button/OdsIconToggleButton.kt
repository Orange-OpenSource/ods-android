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

import androidx.compose.material.Icon
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDarkRippleTheme
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsLightRippleTheme
import com.orange.ods.compose.theme.OdsRippleTheme
import com.orange.ods.utilities.extension.enable

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * An [IconButton] with two states, for icons that can be toggled 'on' and 'off', such as a
 * bookmark icon, or a navigation icon that opens a drawer.
 *
 * @param checked whether this OdsIconToggleButton is currently checked
 * @param onCheckedChange callback to be invoked when this icon is selected
 * @param uncheckedPainter Painter of the icon displayed when unchecked
 * @param checkedPainter Painter of the icon displayed when checked
 * @param iconContentDescription Content description associated to the icon
 * @param modifier optional [Modifier] for this OdsIconToggleButton
 * @param enabled enabled whether or not this OdsIconToggleButton will handle input events and appear
 * enabled for semantics purposes
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    uncheckedPainter: Painter,
    checkedPainter: Painter,
    iconContentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides when (displaySurface) {
            OdsDisplaySurface.Default -> OdsRippleTheme
            OdsDisplaySurface.Light -> OdsLightRippleTheme
            OdsDisplaySurface.Dark -> OdsDarkRippleTheme
        }
    ) {
        IconToggleButton(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled
        ) {
            Icon(
                painter = if (checked) checkedPainter else uncheckedPainter,
                contentDescription = iconContentDescription,
                tint = iconButtonTintColor(displaySurface).enable(enabled = enabled)
            )
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
        uncheckedPainter = painterResource(id = android.R.drawable.ic_media_play),
        checkedPainter = painterResource(id = android.R.drawable.ic_media_pause),
        iconContentDescription = "Play"
    )
}
