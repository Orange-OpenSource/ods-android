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

package com.orange.ods.app.ui.utilities.extension

import android.content.Context
import coil.request.ImageRequest

fun ImageRequest.Builder.reloadOnUiModeChange(darkModeEnabled: Boolean) = setParameter("darkModeEnabled", darkModeEnabled)

fun buildImageRequest(context: Context, data: Any?, darkModeEnabled: Boolean): ImageRequest {
    return ImageRequest.Builder(context)
        .data(data)
        .reloadOnUiModeChange(darkModeEnabled)
        .build()
}
