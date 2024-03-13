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

package com.orange.ods.app.ui.utilities

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

sealed class UiString {

    data class StringValue(val value: String) : UiString()

    class StringResource(
        @StringRes val resourceId: Int,
        vararg val args: Any
    ) : UiString()

    @Composable
    fun asString(): String {
        return when (this) {
            is StringValue -> value
            is StringResource -> stringResource(id = resourceId, formatArgs = args)
        }
    }
}

sealed class UiPainter {

    data class PainterValue(val value: Painter) : UiPainter()

    class PainterResource(@DrawableRes val resourceId: Int) : UiPainter()

    @Composable
    fun asPainter(): Painter {
        return when (this) {
            is PainterValue -> value
            is PainterResource -> painterResource(id = resourceId)
        }
    }
}