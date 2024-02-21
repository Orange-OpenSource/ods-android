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
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var icon by mutableStateOf<Drawable?>(null)
    var text by mutableStateOf("")
    var style by mutableStateOf(OdsButton.Style.Default)
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    var onClick by mutableStateOf({})

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsButton) {
            text = getString(R.styleable.OdsButton_text).orEmpty()
            icon = getResourceIdOrNull(R.styleable.OdsButton_icon)?.let { AppCompatResources.getDrawable(context, it) }
            style = OdsButton.Style.fromXmlAttrValue(getInteger(R.styleable.OdsButton_odsButtonStyle, 0))
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsButton_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsButton(
            text = text,
            onClick = onClick,
            icon = icon?.let { icon ->
                OdsButton.Icon(rememberDrawablePainter(drawable = icon))
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
    fun com.orange.ods.xml.component.button.OdsButton.setOdsButtonStyle(style: OdsButton.Style) {
        this.style = style
    }
}
