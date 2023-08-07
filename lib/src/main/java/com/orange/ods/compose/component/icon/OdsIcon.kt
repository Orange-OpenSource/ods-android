/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.icon

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ods.utilities.extension.enable

@Composable
internal fun OdsIcon(
    graphicsObject: Any,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = OdsIconDefaults.tint(),
    enabled: Boolean = true,
) {
    val iconTint = tint.enable(enabled = enabled)
    when (graphicsObject) {
        is Painter -> Icon(painter = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
        is ImageVector -> Icon(imageVector = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
        is ImageBitmap -> Icon(bitmap = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
        else -> {}
    }
}
