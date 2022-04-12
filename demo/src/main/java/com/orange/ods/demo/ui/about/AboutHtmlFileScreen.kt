/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.about

import android.graphics.Color
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

private const val FILE_PATH = "file:///android_res/raw/"

@Composable
fun AboutHtmlFileScreen(fileName: String?) {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        factory = {
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadUrl("${FILE_PATH}${fileName}")
                setBackgroundColor(Color.TRANSPARENT)
            }
        })
}