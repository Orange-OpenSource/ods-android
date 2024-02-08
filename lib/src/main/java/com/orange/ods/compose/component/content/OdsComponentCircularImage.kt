/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
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

abstract class OdsComponentCircularImage<T> internal constructor(
    extraParametersClass: Class<T>,
    graphicsObject: Any,
    contentDescription: String
) : OdsComponentImage<T>(
    extraParametersClass,
    graphicsObject,
    contentDescription,
    contentScale = ContentScale.Crop
) where T : OdsComponentContent.ExtraParameters {

    /**
     * Creates an instance of [OdsComponentCircularImage].
     *
     * @param extraParametersClass The extra parameters class.
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsComponentCircularImage].
     */
    protected constructor(extraParametersClass: Class<T>, painter: Painter, contentDescription: String) : this(
        extraParametersClass,
        painter as Any,
        contentDescription
    )

    /**
     * Creates an instance of [OdsComponentCircularImage].
     *
     * @param extraParametersClass The extra parameters class.
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsComponentCircularImage].
     */
    protected constructor(extraParametersClass: Class<T>, imageVector: ImageVector, contentDescription: String) : this(
        extraParametersClass,
        imageVector as Any,
        contentDescription
    )

    /**
     * Creates an instance of [OdsComponentCircularImage].
     *
     * @param extraParametersClass The extra parameters class.
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsComponentCircularImage].
     */
    protected constructor(extraParametersClass: Class<T>, bitmap: ImageBitmap, contentDescription: String) : this(
        extraParametersClass,
        bitmap as Any,
        contentDescription
    )

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
}
