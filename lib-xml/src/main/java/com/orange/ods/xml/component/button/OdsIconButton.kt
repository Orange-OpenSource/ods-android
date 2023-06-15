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
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.component.OdsAbstractComposeView

class OdsIconButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var onClick by mutableStateOf({})
    var icon by mutableStateOf<Drawable?>(null)
    var iconContentDescription by mutableStateOf<String>("")
    var displaySurface by mutableStateOf<OdsDisplaySurface>(OdsDisplaySurface.Default)

    @Composable
    override fun OdsContent() {
        OdsIconButton(
            onClick = onClick,
            painter = rememberDrawablePainter(drawable = icon),
            contentDescription = iconContentDescription,
            enabled = isEnabled,
            displaySurface = displaySurface
        )
    }
}