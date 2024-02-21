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
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsOutlinedButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var text by mutableStateOf("")
    var icon by mutableStateOf<Drawable?>(null)
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    var onClick by mutableStateOf({})

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsOutlinedButton) {
            text = getString(R.styleable.OdsOutlinedButton_text).orEmpty()
            icon = getResourceIdOrNull(R.styleable.OdsOutlinedButton_icon)?.let { AppCompatResources.getDrawable(context, it) }
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsOutlinedButton_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsOutlinedButton(
            text = text,
            onClick = onClick,
            icon = icon?.let { OdsButton.Icon(rememberDrawablePainter(drawable = it)) },
            enabled = isEnabled,
            displaySurface = displaySurface
        )
    }

}