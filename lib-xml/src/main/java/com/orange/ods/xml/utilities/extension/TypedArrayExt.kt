/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.xml.utilities.extension

import android.content.res.TypedArray
import androidx.annotation.AnyRes
import androidx.annotation.StyleableRes
import androidx.core.content.res.getResourceIdOrThrow

@AnyRes
internal fun TypedArray.getResourceIdOrNull(@StyleableRes index: Int): Int? {
    return try {
        getResourceIdOrThrow(index)
    } catch (_: Throwable) {
        null
    }
}
