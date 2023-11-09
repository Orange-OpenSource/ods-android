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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.icon.OdsIcon
import com.orange.ods.compose.component.icon.OdsIconDefaults
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.extension.orElse

/**
 * Used to layout an icon in a component.
 */
abstract class OdsIconBuilder<T> internal constructor(
    private val graphicsObject: Any,
    private val contentDescription: String,
    protected var enabled: Boolean = true,
    private val onClick: (() -> Unit)? = null,
    protected var displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) : OdsComponentBuilder<T>() where T : OdsComponentBuilder.ExtraParameters {

    protected open val tint: Color?
        @Composable
        get() = null

    protected constructor(
        painter: Painter,
        contentDescription: String,
        enabled: Boolean = true,
        onClick: (() -> Unit)? = null
    ) : this(painter as Any, contentDescription, enabled, onClick)

    protected constructor(
        imageVector: ImageVector,
        contentDescription: String,
        enabled: Boolean = true,
        onClick: (() -> Unit)? = null
    ) : this(imageVector as Any, contentDescription, enabled, onClick)

    protected constructor(
        bitmap: ImageBitmap,
        contentDescription: String,
        enabled: Boolean = true,
        onClick: (() -> Unit)? = null
    ) : this(bitmap as Any, contentDescription, enabled, onClick)

    @Composable
    override fun Content(modifier: Modifier) {
        val tint = tint.orElse { OdsIconDefaults.tint() }
        onClick?.let { onClick ->
            OdsIconButton(
                onClick = onClick,
                graphicsObject = graphicsObject,
                contentDescription = contentDescription,
                tint = tint,
                modifier = modifier,
                enabled = enabled
            )
        }.orElse {
            OdsIcon(graphicsObject = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = tint, enabled = enabled)
        }
    }
}
