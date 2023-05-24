/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
