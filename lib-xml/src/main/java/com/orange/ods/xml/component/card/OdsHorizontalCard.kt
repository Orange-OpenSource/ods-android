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

package com.orange.ods.xml.component.card

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
import com.orange.ods.compose.component.card.OdsCard
import com.orange.ods.compose.component.card.OdsHorizontalCard
import com.orange.ods.extension.ifNotNull
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsHorizontalCard @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var image by mutableStateOf<Drawable?>(null)
    var imageContentDescription by mutableStateOf("")
    var title by mutableStateOf("")
    var subtitle by mutableStateOf<String?>(null)
    var text by mutableStateOf<String?>(null)
    var firstButtonText by mutableStateOf<String?>(null)
    var onFirstButtonClick by mutableStateOf<(() -> Unit)?>(null)
    var secondButtonText by mutableStateOf<String?>(null)
    var onSecondButtonClick by mutableStateOf<(() -> Unit)?>(null)
    var imagePosition by mutableStateOf(OdsCard.Image.Position.Start)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsHorizontalCard) {
            image = getResourceIdOrNull(R.styleable.OdsHorizontalCard_image)?.let { AppCompatResources.getDrawable(context, it) }
            imageContentDescription = getString(R.styleable.OdsHorizontalCard_imageContentDescription).orEmpty()
            title = getString(R.styleable.OdsHorizontalCard_title).orEmpty()
            subtitle = getString(R.styleable.OdsHorizontalCard_subtitle)
            text = getString(R.styleable.OdsHorizontalCard_text)
            firstButtonText = getString(R.styleable.OdsHorizontalCard_firstButtonText)
            secondButtonText = getString(R.styleable.OdsHorizontalCard_secondButtonText)
            imagePosition = OdsCard.Image.Position.fromXmlAttrValue(getInteger(R.styleable.OdsHorizontalCard_odsHorizontalCardImagePosition, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsHorizontalCard(
            title = title,
            image = OdsCard.Image(rememberDrawablePainter(drawable = image), imageContentDescription),
            subtitle = subtitle,
            text = text,
            firstButton = ifNotNull(firstButtonText, onFirstButtonClick) { text, onClick ->
                OdsCard.Button(text, onClick)
            },
            secondButton = ifNotNull(secondButtonText, onSecondButtonClick) { text, onClick ->
                OdsCard.Button(text, onClick)
            },
            imagePosition = imagePosition
        )

    }

}

internal object OdsHorizontalCardBindingAdapter {

    @JvmStatic
    @BindingAdapter("odsHorizontalCardImagePosition")
    fun com.orange.ods.xml.component.card.OdsHorizontalCard.setOdsHorizontalCardImagePosition(position: OdsCard.Image.Position) {
        this.imagePosition = position
    }
}
