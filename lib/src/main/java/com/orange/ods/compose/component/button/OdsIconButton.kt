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

import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
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
 * @param painter the painter to be drawn inside the IconButton
 * @param contentDescription the content description associated to this OdsIconButton.
 * @param modifier optional [Modifier] for this IconButton
 * @param enabled whether or not this OdsIconButton will handle input events and appear enabled for
 * semantics purposes, true by default
 * @param tint the icon tint, onSurface by default
 */
@Composable
@OdsComponentApi
fun OdsIconButton(
    onClick: () -> Unit,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = OdsTheme.colors.onSurface
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Icon(painter = painter, contentDescription = contentDescription, tint = getIconColor(tint = tint, enabled = enabled))
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
 * @param tint the icon tint, onSurface by default
 */
@Composable
@OdsComponentApi
fun OdsIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tint: Color = OdsTheme.colors.onSurface
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Icon(imageVector = imageVector, contentDescription = contentDescription, tint = getIconColor(tint = tint, enabled = enabled))
    }
}

@Composable
private fun getIconColor(tint: Color, enabled: Boolean) = if (enabled) tint else tint.copy(alpha = ContentAlpha.disabled)

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
