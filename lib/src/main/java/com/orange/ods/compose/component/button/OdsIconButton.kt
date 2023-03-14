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

import androidx.compose.foundation.background
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDarkRippleTheme
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsLightRippleTheme
import com.orange.ods.compose.theme.OdsRippleTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.utilities.extension.enable

/**
 * OdsIconButton is a clickable icon, used to represent actions. An OdsIconButton has an overall minimum
 * touch target size of 48 x 48dp, to meet accessibility guidelines. It contains an [Icon] centered
 * inside the OdsIconButton.
 * If using a custom icon, note that the typical size for the internal icon is 24 x 24 dp.
 *
 * This component is typically used inside an App Bar for the navigation icon / actions.
 *
 * @param onClick the lambda to be invoked when this icon is pressed
 * @param painter the painter to be drawn inside the IconButton
 * @param contentDescription the content description associated to this OdsIconButton.
 * @param modifier optional [Modifier] for this IconButton
 * @param enabled whether or not this OdsIconButton will handle input events and appear enabled for
 * semantics purposes, true by default
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComponentApi
fun OdsIconButton(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
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
        OdsIconButton(
            onClick = onClick,
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier.background(color = OdsTheme.colors.iconButtonBackgroundColor(displaySurface = displaySurface)),
            enabled = enabled,
            tint = OdsTheme.colors.iconButtonIconColor(displaySurface = displaySurface)
        )
    }
}

/**
 * OdsIconButton is a clickable icon, used to represent actions. An OdsIconButton has an overall minimum
 * touch target size of 48 x 48dp, to meet accessibility guidelines. It contains an [Icon] centered
 * inside the OdsIconButton.
 * If using a custom icon, note that the typical size for the internal icon is 24 x 24 dp.
 *
 * This component is typically used inside an App Bar for the navigation icon / actions.
 *
 * @param onClick the lambda to be invoked when this icon is pressed
 * @param imageVector the ImageVector to be drawn inside the IconButton
 * @param contentDescription the content description associated to this OdsIconButton.
 * @param modifier optional [Modifier] for this IconButton
 * @param enabled whether or not this OdsIconButton will handle input events and appear enabled for
 * semantics purposes, true by default
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComponentApi
fun OdsIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
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
        OdsIconButton(
            onClick = onClick,
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier.background(color = OdsTheme.colors.iconButtonBackgroundColor(displaySurface = displaySurface)),
            enabled = enabled,
            tint = OdsTheme.colors.iconButtonIconColor(displaySurface = displaySurface)
        )
    }
}

@Composable
internal fun OdsIconButton(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Icon(painter = painter, contentDescription = contentDescription, tint = tint.enable(enabled = enabled))
    }
}

@Composable
internal fun OdsIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Icon(imageVector = imageVector, contentDescription = contentDescription, tint = tint.enable(enabled = enabled))
    }
}

@Composable
private fun OdsColors.iconButtonBackgroundColor(displaySurface: OdsDisplaySurface) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> Color.Unspecified
        OdsDisplaySurface.Dark -> OdsTheme.darkThemeColors.surface
        OdsDisplaySurface.Light -> OdsTheme.lightThemeColors.surface
    }

@Composable
private fun OdsColors.iconButtonIconColor(displaySurface: OdsDisplaySurface) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.onSurface
        OdsDisplaySurface.Dark -> OdsTheme.darkThemeColors.onSurface
        OdsDisplaySurface.Light -> OdsTheme.lightThemeColors.onSurface
    }

@UiModePreviews.Default
@Composable
private fun PreviewOdsIconButtonWithPainter() = Preview {
    OdsIconButton(
        onClick = {},
        painter = painterResource(id = android.R.drawable.ic_dialog_info),
        contentDescription = ""
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsIconButtonWithImageVector() = Preview {
    OdsIconButton(
        onClick = {},
        imageVector = Icons.Filled.Add,
        contentDescription = ""
    )
}
