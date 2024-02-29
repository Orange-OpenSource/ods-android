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

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale

/**
 * An image in a component.
 */
abstract class OdsComponentImage<T> internal constructor(
    extraParametersClass: Class<T>,
    private val graphicsObject: Any,
    private val contentDescription: String,
    private val alignment: Alignment = Alignment.Center,
    private val contentScale: ContentScale = ContentScale.Fit
) : OdsComponentContent<T>(extraParametersClass) where T : OdsComponentContent.ExtraParameters {

    protected constructor(
        extraParametersClass: Class<T>,
        painter: Painter,
        contentDescription: String,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(extraParametersClass, painter as Any, contentDescription, alignment, contentScale)

    protected constructor(
        extraParametersClass: Class<T>,
        imageVector: ImageVector,
        contentDescription: String,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(extraParametersClass, imageVector as Any, contentDescription, alignment, contentScale)

    protected constructor(
        extraParametersClass: Class<T>,
        bitmap: ImageBitmap,
        contentDescription: String,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Fit
    ) : this(extraParametersClass, bitmap as Any, contentDescription, alignment, contentScale)

    @Composable
    override fun Content(modifier: Modifier) {
        when (graphicsObject) {
            is Painter -> Image(
                painter = graphicsObject,
                contentDescription = contentDescription,
                modifier = modifier,
                alignment = alignment,
                contentScale = contentScale
            )
            is ImageVector -> Image(
                imageVector = graphicsObject,
                contentDescription = contentDescription,
                modifier = modifier,
                alignment = alignment,
                contentScale = contentScale
            )
            is ImageBitmap -> Image(
                bitmap = graphicsObject,
                contentDescription = contentDescription,
                modifier = modifier,
                alignment = alignment,
                contentScale = contentScale
            )
            else -> {}
        }
    }
}
