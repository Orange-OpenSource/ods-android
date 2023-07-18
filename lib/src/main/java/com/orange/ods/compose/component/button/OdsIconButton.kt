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
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.icon.OdsIcon
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme

/**
 * OdsIconButton is a clickable icon, used to represent actions. An OdsIconButton has an overall minimum
 * touch target size of 48 x 48dp, to meet accessibility guidelines. It contains an [Icon] centered
 * inside the OdsIconButton.
 * If using a custom icon, note that the typical size for the internal icon is 24 x 24 dp.
 *
 * This component is typically used inside an App Bar for the navigation icon / actions.
 *
 * @param onClick the lambda to be invoked when this icon is pressed
 * @param painter the painter to be drawn inside the OdsIconButton
 * @param contentDescription the content description associated to this OdsIconButton.
 * @param modifier optional [Modifier] for this OdsIconButton
 * @param enabled whether or not this OdsIconButton will handle input events and appear enabled for
 * semantics purposes, true by default
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsIconButton(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    OdsIconButton(
        onClick = onClick,
        graphicsObject = painter as Any,
        contentDescription = contentDescription,
        modifier = modifier,
        enabled = enabled,
        displaySurface = displaySurface
    )
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
 * @param imageVector [ImageVector] to draw inside this OdsIconButton
 * @param contentDescription the content description associated to this OdsIconButton.
 * @param modifier optional [Modifier] for this OdsIconButton
 * @param enabled whether or not this OdsIconButton will handle input events and appear enabled for
 * semantics purposes, true by default
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    OdsIconButton(
        onClick = onClick,
        graphicsObject = imageVector as Any,
        contentDescription = contentDescription,
        modifier = modifier,
        enabled = enabled,
        displaySurface = displaySurface
    )
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
 * @param bitmap [ImageBitmap] to draw inside this OdsIconButton
 * @param contentDescription the content description associated to this OdsIconButton.
 * @param modifier optional [Modifier] for this OdsIconButton
 * @param enabled whether or not this OdsIconButton will handle input events and appear enabled for
 * semantics purposes, true by default
 * @param displaySurface optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsIconButton(
    onClick: () -> Unit,
    bitmap: ImageBitmap,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    OdsIconButton(
        onClick = onClick,
        graphicsObject = bitmap as Any,
        contentDescription = contentDescription,
        modifier = modifier,
        enabled = enabled,
        displaySurface = displaySurface
    )
}

@Composable
internal fun OdsIconButton(
    onClick: () -> Unit,
    graphicsObject: Any,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    CompositionLocalProvider(
        LocalRippleTheme provides displaySurface.rippleTheme
    ) {
        OdsIconButton(
            onClick = onClick,
            graphicsObject = graphicsObject,
            contentDescription = contentDescription,
            modifier = modifier.background(color = iconButtonBackgroundColor(displaySurface = displaySurface)),
            enabled = enabled,
            tint = iconButtonTintColor(displaySurface = displaySurface)
        )
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

@Composable
private fun iconButtonBackgroundColor(displaySurface: OdsDisplaySurface) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> Color.Unspecified
        OdsDisplaySurface.Dark -> OdsTheme.darkThemeColors.surface
        OdsDisplaySurface.Light -> OdsTheme.lightThemeColors.surface
    }

@UiModePreviews.Default
@Composable
private fun PreviewOdsIconButton() = Preview {
    OdsIconButton(
        onClick = {},
        painter = painterResource(id = android.R.drawable.ic_dialog_info),
        contentDescription = ""
    )
}