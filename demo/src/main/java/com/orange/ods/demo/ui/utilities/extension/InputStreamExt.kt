/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.extension

import java.io.InputStream
import java.util.*

fun InputStream.contentAsString(): String? {
    val scanner = Scanner(this).useDelimiter("\\A")
    return if (scanner.hasNext()) scanner.next() else null
}
