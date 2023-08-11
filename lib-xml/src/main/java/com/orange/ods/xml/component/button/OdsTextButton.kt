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
import androidx.databinding.BindingAdapter
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.orange.ods.compose.component.button.OdsButtonIcon
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsTextButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var text by mutableStateOf("")
    var onClick by mutableStateOf({})
    var icon by mutableStateOf<Drawable?>(null)
    var style by mutableStateOf(OdsTextButtonStyle.Default)
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsTextButton) {
            text = getString(R.styleable.OdsTextButton_text).orEmpty()
            icon = getResourceIdOrNull(R.styleable.OdsTextButton_icon)?.let { AppCompatResources.getDrawable(context, it) }
            style = OdsTextButtonStyle.fromXmlAttrValue(getInteger(R.styleable.OdsTextButton_textButtonStyle, 0))
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsTextButton_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsTextButton(
            text = text,
            onClick = onClick,
            icon = icon?.let { OdsButtonIcon(rememberDrawablePainter(drawable = it)) },
            enabled = isEnabled,
            style = style,
            displaySurface = displaySurface
        )
    }
}

internal object OdsTextButtonBindingAdapter {

    @JvmStatic
    @BindingAdapter("textButtonStyle")
    fun OdsTextButton.setTextButtonStyle(style: OdsTextButtonStyle) {
        this.style = style
    }
}
