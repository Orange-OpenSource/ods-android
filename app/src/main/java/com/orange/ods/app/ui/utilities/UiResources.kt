/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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