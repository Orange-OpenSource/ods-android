/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.utilities.extension

import android.webkit.WebView
import androidx.annotation.RawRes
import androidx.compose.ui.graphics.Color
import com.orange.ods.extension.contentAsString
import com.orange.ods.module.about.R
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.guideline.toHexString

fun WebView.injectLightDarkModeCss(isDarkModeEnabled: Boolean, colors: OdsColors) {
    injectCss(R.raw.base_style, colors)
    val css = if (isDarkModeEnabled) R.raw.dark_style else R.raw.light_style
    injectCss(css, colors)
}

private fun WebView.injectCss(@RawRes cssResource: Int, colors: OdsColors) {
    val injectCssFunction = context.resources.openRawResource(R.raw.inject_css).contentAsString().orEmpty()
    val css = context.resources.openRawResource(cssResource).contentAsString().orEmpty()
    val javascript = String.format(injectCssFunction, css.trim { it <= ' ' })
    val code = "javascript:(function() { $javascript })()".replaceThemeColors(colors)
    loadUrl(code)
}

private fun CharSequence.replaceThemeColors(colors: OdsColors): String {
    return replaceThemeColor(colors.primary, colors::primary.name)
}

private fun CharSequence.replaceThemeColor(color: Color, name: String): String {
    return replace("<<OdsTheme.colors.$name>>".toRegex(), color.toHexString())
}
