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
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.withStyledAttributes
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsIconToggleButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var checkedPainter by mutableStateOf<Drawable?>(null)
    var uncheckedPainter by mutableStateOf<Drawable?>(null)
    var iconContentDescription by mutableStateOf<String?>("")
    var selectedPainter by mutableStateOf<Boolean>(false)
    var onCheckedChange by mutableStateOf<(Boolean) -> Unit>({})
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsIconToggleButton) {
            selectedPainter = getBoolean(R.styleable.OdsIconToggleButton_selectedPainter, false)
            iconContentDescription = getString(R.styleable.OdsIconToggleButton_iconContentDescription).orEmpty()
            checkedPainter = getResourceIdOrNull(R.styleable.OdsIconToggleButton_checkedPainter)?.let { AppCompatResources.getDrawable(context, it) }
            uncheckedPainter = getResourceIdOrNull(R.styleable.OdsIconToggleButton_uncheckedPainter)?.let { AppCompatResources.getDrawable(context, it) }
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsIconToggleButton_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsIconToggleButton(
            checked = selectedPainter,
            onCheckedChange = onCheckedChange,
            uncheckedPainter = rememberDrawablePainter(drawable = uncheckedPainter),
            checkedPainter = rememberDrawablePainter(drawable = checkedPainter),
            iconContentDescription = iconContentDescription,
            enabled = isEnabled,
            displaySurface = displaySurface
        )
    }
}