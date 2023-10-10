/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.content

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

abstract class OdsComponentCircleImage private constructor(
    graphicsObject: Any,
    contentDescription: String
) : OdsComponentImage<Nothing>(graphicsObject, contentDescription, contentScale = ContentScale.Crop) {

    /**
     * Creates an instance of [OdsComponentCircleImage].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsComponentCircleImage].
     */
    constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

    /**
     * Creates an instance of [OdsComponentCircleImage].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsComponentCircleImage].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

    /**
     * Creates an instance of [OdsComponentCircleImage].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsComponentCircleImage].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
}
