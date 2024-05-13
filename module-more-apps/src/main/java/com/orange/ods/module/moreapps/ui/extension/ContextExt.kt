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

package com.orange.ods.module.moreapps.ui.extension

import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

//TODO Duplicate code with ContextExt.kt from module-about. Need to create an independent module to factorize extensions or keep it duplicate? :/
internal fun Context.launchUrl(url: String) {
    try {
        CustomTabsIntent.Builder()
            .setUrlBarHidingEnabled(true)
            .build()
            .launchUrl(this, Uri.parse(url))
    } catch (e: ActivityNotFoundException) {
        println("${e.message}: ${e.stackTrace}")
    }
}
