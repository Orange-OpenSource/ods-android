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
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.getResourceIdOrNull

class OdsIconToggleButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var checkedIcon by mutableStateOf<Drawable?>(null)
    var uncheckedIcon by mutableStateOf<Drawable?>(null)
    var checkedIconContentDescription by mutableStateOf<String?>(null)
    var uncheckedIconContentDescription by mutableStateOf<String?>(null)
    var checked by mutableStateOf(false)
    var onCheckedChange by mutableStateOf<(Boolean) -> Unit>({})
    var inverseTheme by mutableStateOf(false)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsIconToggleButton) {
            checked = getBoolean(R.styleable.OdsIconToggleButton_checked, false)
            checkedIconContentDescription = getString(R.styleable.OdsIconToggleButton_checkedIconContentDescription).orEmpty()
            uncheckedIconContentDescription = getString(R.styleable.OdsIconToggleButton_uncheckedIconContentDescription).orEmpty()
            checkedIcon = getResourceIdOrNull(R.styleable.OdsIconToggleButton_checkedIcon)?.let { AppCompatResources.getDrawable(context, it) }
            uncheckedIcon = getResourceIdOrNull(R.styleable.OdsIconToggleButton_uncheckedIcon)?.let { AppCompatResources.getDrawable(context, it) }
            inverseTheme = getBoolean(R.styleable.OdsIconToggleButton_inverseTheme, false)
        }
    }

    @Composable
    override fun OdsContent() {
        OdsButtonContent(inverseTheme = inverseTheme) {
            OdsIconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                uncheckedIcon = OdsIconButton.Icon(rememberDrawablePainter(drawable = uncheckedIcon), uncheckedIconContentDescription.orEmpty()),
                checkedIcon = OdsIconButton.Icon(rememberDrawablePainter(drawable = checkedIcon), checkedIconContentDescription.orEmpty()),
                enabled = isEnabled,
            )
        }
    }
}