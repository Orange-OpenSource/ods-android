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
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.icon.OdsIcon
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface

/**
 * OdsIconButton is a clickable icon, used to represent actions. An OdsIconButton has an overall minimum
 * touch target size of 48 x 48dp, to meet accessibility guidelines. It contains an [Icon] centered
 * inside the OdsIconButton.
 * If using a custom icon, note that the typical size for the internal icon is 24 x 24 dp.
 *
 * This component is typically used inside an App Bar for the navigation icon / actions.
 *
 * @param icon The icon to be drawn inside the OdsIconButton
 * @param onClick The lambda to be invoked when this icon is pressed
 * @param modifier The [Modifier] for this OdsIconButton
 * @param enabled Whether or not this [OdsIconButton] will handle input events and appear enabled for
 * semantics purposes, true by default
 * @param displaySurface Force the button display on a dark or light surface. By default the appearance applied
 * is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsIconButton(
    icon: OdsIconButtonIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides displaySurface.rippleTheme
    ) {
        IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
            icon.Content(enabled, displaySurface)
        }
    }
}

@Composable
internal fun OdsIconButton(
    onClick: () -> Unit,
    graphicsObject: Any,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        OdsIcon(graphicsObject = graphicsObject, contentDescription = contentDescription, tint = tint, enabled = enabled)
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsIconButton() = Preview {
    OdsIconButton(
        onClick = {},
        icon = OdsIconButtonIcon(painterResource(id = android.R.drawable.ic_dialog_info), ""),
    )
}