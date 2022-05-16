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

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.compose.theme.odsDarkThemeColors
import com.orange.ods.compose.theme.odsLightThemeColors

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * An [IconButton] with two states, for icons that can be toggled 'on' and 'off', such as a
 * bookmark icon, or a navigation icon that opens a drawer.
 *
 * @param checked whether this IconToggleButton is currently checked
 * @param onCheckedChange callback to be invoked when this icon is selected
 * @param iconRes Resource identifier of the icon displayed
 * @param contentDescription Content description associated to the icon
 * @param modifier optional [Modifier] for this IconToggleButton
 * @param enabled enabled whether or not this [IconToggleButton] will handle input events and appear
 * enabled for semantics purposes
 * @param displayAppearance optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
fun OdsButtonToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    @DrawableRes
    iconRes: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        interactionSource = remember { DisabledInteractionSource() }
    ) {
        val iconTint by animateColorAsState(MaterialTheme.colors.buttonToggleIconColor(displayAppearance, checked))
        val backgroundAlpha by animateFloatAsState(if (checked) 0.12f else 0f)
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors
                        .buttonToggleBackgroundColor(displayAppearance)
                        .copy(alpha = backgroundAlpha)
                )
                .padding(12.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = contentDescription,
                tint = iconTint
            )
        }
    }
}

@Composable
private fun Colors.buttonToggleIconColor(displayAppearance: OdsDisplayAppearance, checked: Boolean) =
    when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> if (checked) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
        OdsDisplayAppearance.ON_DARK -> if (checked) odsDarkThemeColors.primary else odsDarkThemeColors.onSurface
        OdsDisplayAppearance.ON_LIGHT -> if (checked) odsLightThemeColors.primary else odsLightThemeColors.onSurface
    }

@Composable
private fun Colors.buttonToggleBackgroundColor(displayAppearance: OdsDisplayAppearance) =
    when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> MaterialTheme.colors.primary
        OdsDisplayAppearance.ON_DARK -> odsDarkThemeColors.primary
        OdsDisplayAppearance.ON_LIGHT -> odsLightThemeColors.primary
    }