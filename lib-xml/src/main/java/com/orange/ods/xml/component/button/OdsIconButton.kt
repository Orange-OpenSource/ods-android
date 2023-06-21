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
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsIconButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var onClick by mutableStateOf({})
    var leadingIcon by mutableStateOf<Drawable?>(null)
    var iconContentDescription by mutableStateOf("")
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsIconButton) {
            iconContentDescription = getString(R.styleable.OdsIconButton_iconContentDescription).orEmpty()
            leadingIcon = getResourceIdOrNull(R.styleable.OdsIconButton_leadingIcon)?.let { AppCompatResources.getDrawable(context, it) }
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsIconButton_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsIconButton(
            onClick = onClick,
            painter = rememberDrawablePainter(drawable = leadingIcon),
            contentDescription = iconContentDescription,
            enabled = isEnabled,
            displaySurface = displaySurface
        )
    }
}