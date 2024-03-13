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

package com.orange.ods.xml.component

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.xml.theme.OdsXml
import com.orange.ods.xml.utilities.extension.xml

abstract class OdsAbstractComposeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AbstractComposeView(context, attrs) {

    @Composable
    override fun Content() {
        val darkThemeEnabled = when (OdsTheme.xml.uiMode) {
            OdsXml.UiMode.Automatic -> isSystemInDarkTheme()
            OdsXml.UiMode.Light -> false
            OdsXml.UiMode.Dark -> true
        }
        OdsTheme(
            themeConfiguration = OdsTheme.xml.themeConfiguration,
            darkThemeEnabled = darkThemeEnabled
        ) {
            OdsContent()
        }
    }

    @Composable
    abstract fun OdsContent()
}
