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

import android.content.Context
import coil.request.ImageRequest

fun ImageRequest.Builder.reloadOnUiModeChange(darkModeEnabled: Boolean) = setParameter("darkModeEnabled", darkModeEnabled)

fun buildImageRequest(context: Context, data: Any?, darkModeEnabled: Boolean): ImageRequest {
    return ImageRequest.Builder(context)
        .data(data)
        .reloadOnUiModeChange(darkModeEnabled)
        .build()
}
