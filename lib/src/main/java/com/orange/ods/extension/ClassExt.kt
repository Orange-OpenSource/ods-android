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

val Class<*>.simpleNestedName: String
    get() = name.substringAfterLast(".").replace("$", ".")
