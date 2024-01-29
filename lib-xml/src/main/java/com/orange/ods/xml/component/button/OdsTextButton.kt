/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
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
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsTextButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var text by mutableStateOf("")
    var onClick by mutableStateOf({})
    var icon by mutableStateOf<Drawable?>(null)
    var style by mutableStateOf(OdsTextButton.Style.Default)
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsTextButton) {
            text = getString(R.styleable.OdsTextButton_text).orEmpty()
            icon = getResourceIdOrNull(R.styleable.OdsTextButton_icon)?.let { AppCompatResources.getDrawable(context, it) }
            style = OdsTextButton.Style.fromXmlAttrValue(getInteger(R.styleable.OdsTextButton_odsTextButtonStyle, 0))
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsTextButton_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsTextButton(
            text = text,
            onClick = onClick,
            icon = icon?.let { OdsButton.Icon(rememberDrawablePainter(drawable = it)) },
            enabled = isEnabled,
            style = style,
            displaySurface = displaySurface
        )
    }
}

internal object OdsTextButtonBindingAdapter {

    @JvmStatic
    @BindingAdapter("odsTextButtonStyle")
    fun com.orange.ods.xml.component.button.OdsTextButton.setOdsTextButtonStyle(style: OdsTextButton.Style) {
        this.style = style
    }
}
