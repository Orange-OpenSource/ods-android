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

package com.orange.ods.compose.component.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.extension.enable
import com.orange.ods.compose.theme.OdsTheme

/**
 * Contains classes to build an [com.orange.ods.compose.component.button.OdsButton].
 */
object OdsButton {

    /**
     * A button icon in an [OdsButton].
     * It is non-clickable and no content description is needed cause a button label is always present.
     */
    class Icon private constructor(
        val graphicsObject: Any
    ) : OdsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, "") {

        /**
         * Creates an instance of [OdsButton.Icon].
         *
         * @param painter Painter of the icon.
         */
        constructor(painter: Painter) : this(painter as Any)

        /**
         * Creates an instance of [OdsButton.Icon].
         *
         * @param imageVector Image vector of the icon.
         */
        constructor(imageVector: ImageVector) : this(imageVector as Any)

        /**
         * Creates an instance of [OdsButton.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         */
        constructor(bitmap: ImageBitmap) : this(bitmap as Any)

        @Composable
        override fun Content(modifier: Modifier) {
            super.Content(modifier = modifier.size(ButtonDefaults.IconSize))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        }
    }

    /**
     * Specifying an [OdsButton.Style] allow to display a button with specific colors.
     */
    enum class Style {
        Default, Primary, FunctionalPositive, FunctionalNegative;

        companion object

        @Composable
        internal fun getColors(): ButtonColors {
            return when (this) {
                Default -> odsDefaultButtonColors()
                Primary -> odsPrimaryButtonColors()
                FunctionalPositive -> odsPositiveButtonColors()
                FunctionalNegative -> odsNegativeButtonColors()
            }
        }
    }

    @Composable
    private fun odsDefaultButtonColors() = buttonColors(
        backgroundColor = OdsTheme.colors.onSurface,
        contentColor = OdsTheme.colors.surface
    )

    @Composable
    private fun odsPrimaryButtonColors() = buttonColors(
        backgroundColor = OdsTheme.colors.primary,
        contentColor = OdsTheme.colors.onPrimary
    )

    @Composable
    private fun odsPositiveButtonColors() = buttonColors(
        backgroundColor = OdsTheme.colors.functional.positive,
        contentColor = OdsTheme.colors.functional.onPositive
    )

    @Composable
    private fun odsNegativeButtonColors() = buttonColors(
        backgroundColor = OdsTheme.colors.functional.negative,
        contentColor = OdsTheme.colors.functional.onNegative
    )

    @Composable
    private fun buttonColors(backgroundColor: Color, contentColor: Color) = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = OdsTheme.colors.onSurface.copy(alpha = 0.12f),
        disabledContentColor = OdsTheme.colors.onSurface.enable(enabled = false)
    )
}