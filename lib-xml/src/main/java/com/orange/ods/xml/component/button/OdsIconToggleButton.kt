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
import com.orange.ods.compose.component.button.OdsIconButtonIconBuilder
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsIconToggleButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var checkedIcon by mutableStateOf<Drawable?>(null)
    var uncheckedIcon by mutableStateOf<Drawable?>(null)
    var checkedIconContentDescription by mutableStateOf<String?>(null)
    var uncheckedIconContentDescription by mutableStateOf<String?>(null)
    var checked by mutableStateOf(false)
    var onCheckedChange by mutableStateOf<(Boolean) -> Unit>({})
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsIconToggleButton) {
            checked = getBoolean(R.styleable.OdsIconToggleButton_checked, false)
            checkedIconContentDescription = getString(R.styleable.OdsIconToggleButton_checkedIconContentDescription).orEmpty()
            uncheckedIconContentDescription = getString(R.styleable.OdsIconToggleButton_uncheckedIconContentDescription).orEmpty()
            checkedIcon = getResourceIdOrNull(R.styleable.OdsIconToggleButton_checkedIcon)?.let { AppCompatResources.getDrawable(context, it) }
            uncheckedIcon = getResourceIdOrNull(R.styleable.OdsIconToggleButton_uncheckedIcon)?.let { AppCompatResources.getDrawable(context, it) }
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsIconToggleButton_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsIconToggleButton(
            checked = checked,
            onCheckedChange = onCheckedChange,
            uncheckedIcon = OdsIconButtonIconBuilder(rememberDrawablePainter(drawable = uncheckedIcon), uncheckedIconContentDescription.orEmpty()),
            checkedIcon = OdsIconButtonIconBuilder(rememberDrawablePainter(drawable = checkedIcon), checkedIconContentDescription.orEmpty()),
            enabled = isEnabled,
            displaySurface = displaySurface
        )
    }
}