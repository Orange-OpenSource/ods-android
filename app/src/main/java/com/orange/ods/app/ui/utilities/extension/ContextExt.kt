/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.utilities.extension

import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

//Method to get the resource name with the color id
fun Context.getStringName(res: Int): String =
    this.resources.getResourceName(res).split('/').last()

fun Context.launchUrl(url: String) {
    try {
        CustomTabsIntent.Builder()
            .setUrlBarHidingEnabled(true)
            .build()
            .launchUrl(this, Uri.parse(url))
    } catch (e: ActivityNotFoundException) {
        println("${e.message}: ${e.stackTrace}")
    }
}
