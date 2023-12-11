/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.xml.component.banner

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
import com.orange.ods.compose.component.banner.OdsBanner
import com.orange.ods.extension.ifNotNull
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsBanner @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var message by mutableStateOf("")
    var image by mutableStateOf<Drawable?>(null)
    var imageContentDescription by mutableStateOf<String?>(null)
    var firstButtonText by mutableStateOf<String?>(null)
    var onFirstButtonClick  by mutableStateOf<(() -> Unit)?>(null)
    var secondButtonText by mutableStateOf<String?>(null)
    var onSecondButtonClick by mutableStateOf<(() -> Unit)?>(null)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsBanner) {
            message = getString(R.styleable.OdsBanner_message).orEmpty()
            image = getResourceIdOrNull(R.styleable.OdsBanner_image)?.let { AppCompatResources.getDrawable(context, it) }
            imageContentDescription = getString(R.styleable.OdsBanner_imageContentDescription)
            firstButtonText = getString(R.styleable.OdsBanner_firstButtonText)
            secondButtonText = getString(R.styleable.OdsBanner_secondButtonText)
        }
    }

    @Composable
    override fun OdsContent() {
        OdsBanner(
            message = message,
            image = image?.let { image ->
                val painter = rememberDrawablePainter(drawable = image)
                OdsBanner.Image(painter, imageContentDescription.orEmpty())
            },
            firstButton = ifNotNull(firstButtonText, onFirstButtonClick) { text, onClick ->
                OdsBanner.Button(text, onClick)
            },
            secondButton = ifNotNull(secondButtonText, onSecondButtonClick) { text, onClick ->
                OdsBanner.Button(text, onClick)
            }
        )
    }
}
