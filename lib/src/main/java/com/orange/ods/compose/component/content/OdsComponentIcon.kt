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
 * An icon in a component.
 */
abstract class OdsComponentIcon<T> internal constructor(
    extraParametersClass: Class<T>,
    private val graphicsObject: Any,
    private val contentDescription: String,
    protected var enabled: Boolean = true,
    private val onClick: (() -> Unit)? = null,
    protected var displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) : OdsComponentContent<T>(extraParametersClass) where T : OdsComponentContent.ExtraParameters {

    protected open val tint: Color?
        @Composable
        get() = null

    protected constructor(
        extraParametersClass: Class<T>,
        painter: Painter,
        contentDescription: String,
        enabled: Boolean = true,
        onClick: (() -> Unit)? = null,
    ) : this(extraParametersClass, painter as Any, contentDescription, enabled, onClick)

    protected constructor(
        extraParametersClass: Class<T>,
        imageVector: ImageVector,
        contentDescription: String,
        enabled: Boolean = true,
        onClick: (() -> Unit)? = null
    ) : this(extraParametersClass, imageVector as Any, contentDescription, enabled, onClick)

    protected constructor(
        extraParametersClass: Class<T>,
        bitmap: ImageBitmap,
        contentDescription: String,
        enabled: Boolean = true,
        onClick: (() -> Unit)? = null
    ) : this(extraParametersClass, bitmap as Any, contentDescription, enabled, onClick)

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
