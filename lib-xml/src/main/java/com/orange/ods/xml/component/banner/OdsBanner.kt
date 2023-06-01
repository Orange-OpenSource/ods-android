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
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsBanner @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var message by mutableStateOf("")
    var button1Text by mutableStateOf("")
    var onButton1Click by mutableStateOf({})
    var image by mutableStateOf<Drawable?>(null)
    var imageContentDescription by mutableStateOf<String?>(null)
    var button2Text by mutableStateOf<String?>(null)
    var onButton2Click by mutableStateOf<(() -> Unit)?>(null)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsBanner) {
            message = getString(R.styleable.OdsBanner_message).orEmpty()
            button1Text = getString(R.styleable.OdsBanner_button1Text).orEmpty()
            image = getResourceIdOrNull(R.styleable.OdsBanner_image)?.let { AppCompatResources.getDrawable(context, it) }
            imageContentDescription = getString(R.styleable.OdsBanner_imageContentDescription)
            button2Text = getString(R.styleable.OdsBanner_button2Text)
        }
    }

    @Composable
    override fun OdsContent() {
        OdsBanner(
            message = message,
            button1Text = button1Text,
            onButton1Click = onButton1Click,
            image = image?.let { rememberDrawablePainter(drawable = it) },
            imageContentDescription = imageContentDescription,
            button2Text = button2Text,
            onButton2Click = onButton2Click
        )
    }
}
