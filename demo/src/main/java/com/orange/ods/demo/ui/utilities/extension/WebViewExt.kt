/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.extension

import android.webkit.WebView
import androidx.annotation.RawRes
import com.orange.ods.demo.R

fun WebView.injectCss(@RawRes cssResource: Int) {
    val injectCssFunction = context.resources.openRawResource(R.raw.inject_css).contentAsString().orEmpty()
    val css = context.resources.openRawResource(cssResource).contentAsString().orEmpty()
    val javascript = String.format(injectCssFunction, css.trim { it <= ' ' })
    val code = "javascript:(function() { $javascript })()"
    loadUrl(code)
}

fun WebView.injectLightDarkModeCss(isDarkModeEnabled: Boolean) {
    injectCss(R.raw.base_style)
    val css = if (isDarkModeEnabled) R.raw.dark_style else R.raw.light_style
    injectCss(css)
}
