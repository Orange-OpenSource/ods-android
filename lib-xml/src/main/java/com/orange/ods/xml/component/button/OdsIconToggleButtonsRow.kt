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
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.withStyledAttributes
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRow
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.xml.R
import com.orange.ods.xml.component.OdsAbstractComposeView
import com.orange.ods.xml.utilities.extension.fromXmlAttrValue

class OdsIconToggleButtonsRow @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : OdsAbstractComposeView(context, attrs) {

    var iconButtons by mutableStateOf<List<OdsIconToggleButtonsRow.IconButton>>(emptyList())
    var selectedIconButtonIndex by mutableIntStateOf(0)
    var displaySurface by mutableStateOf(OdsDisplaySurface.Default)

    init {
        context.withStyledAttributes(attrs, R.styleable.OdsIconToggleButtonsRow) {
            selectedIconButtonIndex = getInt(R.styleable.OdsIconToggleButtonsRow_selectedIconButtonIndex, 0)
            displaySurface = OdsDisplaySurface.fromXmlAttrValue(getInteger(R.styleable.OdsIconToggleButtonsRow_displaySurface, 0))
        }
    }

    @Composable
    override fun OdsContent() {
        OdsIconToggleButtonsRow(
            selectedIconButtonIndex = selectedIconButtonIndex,
            iconButtons = iconButtons,
            displaySurface = displaySurface
        )
    }
}
