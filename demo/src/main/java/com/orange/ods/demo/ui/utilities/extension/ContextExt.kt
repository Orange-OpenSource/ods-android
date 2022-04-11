/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities

import android.content.Context

//Method to get the resource name with the color id
fun Context.getStringName(res: Int): String =
    this.resources.getResourceName(res).split('/').last()