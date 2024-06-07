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

package com.orange.ods.module.about.ui.utilities.extension

import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import timber.log.Timber

internal fun Context.launchUrl(url: String) {
    try {
        CustomTabsIntent.Builder()
            .setUrlBarHidingEnabled(true)
            .build()
            .launchUrl(this, Uri.parse(url))
    } catch (e: ActivityNotFoundException) {
        Timber.e("${e.message}: ${e.stackTrace}")
    }
}
