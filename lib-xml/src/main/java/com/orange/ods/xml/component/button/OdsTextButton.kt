/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.xml.component.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.component.OdsAbstractComposeView

class OdsTextButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var text by mutableStateOf("")
    var onClick by mutableStateOf({})
    var icon by mutableStateOf<Drawable?>(null)
    var style by mutableStateOf(OdsTextButtonStyle.Default)
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    @Composable
    override fun OdsContent() {
        OdsTextButton(
            text = text,
            onClick = onClick,
            icon = icon?.let { rememberDrawablePainter(drawable = it) },
            enabled = isEnabled,
            style = style,
            displaySurface = displaySurface
        )
    }
}