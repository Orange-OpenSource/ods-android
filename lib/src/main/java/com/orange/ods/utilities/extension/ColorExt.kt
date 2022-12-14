/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.utilities.extension

import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Color.enable(enabled: Boolean) = if (enabled) this else copy(alpha = ContentAlpha.disabled)
