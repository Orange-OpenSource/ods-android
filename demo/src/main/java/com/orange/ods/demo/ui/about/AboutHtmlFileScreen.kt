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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import com.orange.ods.demo.R

private const val FILE_PATH = "file:///android_res/raw/"

@Composable
fun AboutHtmlFileScreen(
    aboutItemId: Long,
    updateTopBarTitle: (Int) -> Unit
) {
    val aboutItem = remember { aboutItems.firstOrNull { item -> item.id == aboutItemId } }

    aboutItem?.let { item ->
        updateTopBarTitle(item.titleRes)
        val context = LocalContext.current
        AndroidView(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin),
                vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin)
            ),
            factory = {
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    loadUrl("${FILE_PATH}${item.fileName}")
                    setBackgroundColor(Color.TRANSPARENT)
                }
            })
    }
}