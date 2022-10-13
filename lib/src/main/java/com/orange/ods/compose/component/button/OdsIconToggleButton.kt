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

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsSupportedColors

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * An [IconButton] with two states, for icons that can be toggled 'on' and 'off', such as a
 * bookmark icon, or a navigation icon that opens a drawer.
 *
 * @param checked whether this IconToggleButton is currently checked
 * @param onCheckedChange callback to be invoked when this icon is selected
 * @param icon Painter of the icon displayed
 * @param contentDescription Content description associated to the icon
 * @param modifier optional [Modifier] for this IconToggleButton
 * @param enabled enabled whether or not this [IconToggleButton] will handle input events and appear
 * enabled for semantics purposes
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComponentApi
fun OdsIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    icon: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        interactionSource = remember { DisabledInteractionSource() }
    ) {
        val iconTint by animateColorAsState(OdsTheme.colors.buttonToggleIconColor(displaySurface, checked))
        val backgroundAlpha by animateFloatAsState(if (checked) 0.12f else 0f)
        Box(
            modifier = Modifier
                .background(
                    color = OdsTheme.colors
                        .buttonToggleBackgroundColor(displaySurface)
                        .copy(alpha = backgroundAlpha)
                )
                .padding(12.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                tint = iconTint
            )
        }
    }
}

@Composable
private fun OdsSupportedColors.buttonToggleIconColor(displaySurface: OdsDisplaySurface, checked: Boolean) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> if (checked) OdsTheme.colors.primary else OdsTheme.colors.onSurface
        OdsDisplaySurface.Dark -> if (checked) OdsTheme.darkThemeColors.primary else OdsTheme.darkThemeColors.onSurface
        OdsDisplaySurface.Light -> if (checked) OdsTheme.lightThemeColors.primary else OdsTheme.lightThemeColors.onSurface
    }

@Composable
private fun OdsSupportedColors.buttonToggleBackgroundColor(displaySurface: OdsDisplaySurface) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.primary
        OdsDisplaySurface.Dark -> OdsTheme.darkThemeColors.primary
        OdsDisplaySurface.Light -> OdsTheme.lightThemeColors.primary
    }

@Composable
private fun PreviewOdsIconToggleButton() = Preview {
    val checked = remember { mutableStateOf(false) }
    OdsIconToggleButton(
        checked = checked.value,
        onCheckedChange = { checked.value = it },
        icon = painterResource(id = android.R.drawable.ic_btn_speak_now),
        contentDescription = "Microphone"
    )
}

@Preview(name = "OdsIconToggleButton - Light")
@Composable
private fun PreviewOdsIconToggleButtonLight() = PreviewOdsIconToggleButton()

@Preview(
    name = "OdsIconToggleButton - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsIconToggleButtonDark() = PreviewOdsIconToggleButton()
