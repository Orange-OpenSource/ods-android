/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.about

import android.annotation.SuppressLint
import android.graphics.Color
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.utilities.Markdown
import com.orange.ods.app.ui.utilities.extension.injectLightDarkModeCss
import com.orange.ods.app.ui.utilities.extension.isDarkModeEnabled
import com.orange.ods.app.ui.utilities.launchUrl
import com.orange.ods.compose.theme.OdsTheme
import java.io.BufferedReader
import java.nio.charset.StandardCharsets

private const val FilePath = "file:///android_res/raw/"

@Composable
fun AboutFileScreen(aboutItemId: Long) {
    val aboutItem = remember { aboutItems.firstOrNull { item -> item.id == aboutItemId } as? FileAboutItem }

    aboutItem?.let { item ->
        LocalMainTopAppBarManager.current.titleResId = item.titleRes
        val context = LocalContext.current
        val configuration = LocalConfiguration.current
        val colors = OdsTheme.colors
        val horizontalPadding = dimensionResource(id = R.dimen.screen_horizontal_margin).value
        val verticalPadding = dimensionResource(id = R.dimen.screen_vertical_margin).value
        AndroidView(
            factory = {
                WebView(context).apply {
                    @SuppressLint("SetJavaScriptEnabled")
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            view?.loadUrl("javascript:(function(){ document.body.style.padding = '${verticalPadding}px ${horizontalPadding}px' })();");
                            view?.injectLightDarkModeCss(configuration.isDarkModeEnabled, colors)
                        }

                        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                            request?.url?.let { url ->
                                context.launchUrl(url.toString())
                            }

                            return true
                        }
                    }

                    val fileContent = resources.openRawResource(item.fileRes)
                        .bufferedReader()
                        .use(BufferedReader::readText)
                    val html = when (item.fileFormat) {
                        FileAboutItem.FileFormat.Html -> fileContent
                        FileAboutItem.FileFormat.Markdown -> Markdown.toHtml(fileContent)
                    }
                    // Use loadDataWithBaseURL instead of loadData otherwise CSS won't work
                    loadDataWithBaseURL(FilePath, html, "text/html; charset=UTF-8", StandardCharsets.UTF_8.name(), null)

                    setBackgroundColor(Color.TRANSPARENT)
                }
            },
            update = {
                it.injectLightDarkModeCss(configuration.isDarkModeEnabled, colors)
            })
    }
}
