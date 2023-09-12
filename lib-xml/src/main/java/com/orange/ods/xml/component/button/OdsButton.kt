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
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonIcon
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var icon by mutableStateOf<Drawable?>(null)
    var text by mutableStateOf("")
    var style by mutableStateOf(OdsButtonStyle.Default)
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    var onClick by mutableStateOf({})

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsButton) {
            text = getString(R.styleable.OdsButton_text).orEmpty()
            icon = getResourceIdOrNull(R.styleable.OdsButton_icon)?.let { AppCompatResources.getDrawable(context, it) }
            style = OdsButtonStyle.fromXmlAttrValue(getInteger(R.styleable.OdsButton_odsButtonStyle, 0))
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsButton_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsButton(
            text = text,
            onClick = onClick,
            icon = icon?.let { icon ->
                OdsButtonIcon(rememberDrawablePainter(drawable = icon))
            },
            enabled = isEnabled,
            style = style,
            displaySurface = displaySurface
        )
    }

}

internal object OdsButtonBindingAdapter {

    @JvmStatic
    @BindingAdapter("odsButtonStyle")
    fun OdsButton.setOdsButtonStyle(style: OdsButtonStyle) {
        this.style = style
    }
}
