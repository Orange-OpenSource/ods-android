/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.extension

import android.content.Context

/**
 * @return the dp corresponding to the given [pixelSize]
 */
fun Context.getDpFromPx(pixelSize: Int) = pixelSize / this.resources.displayMetrics.density