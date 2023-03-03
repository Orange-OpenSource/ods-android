/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.progress

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.orange.ods.demo.ui.components.Variant

@Composable
fun ComponentProgress(variant: Variant) {

    Column {
        when (variant) {
            Variant.ProgressLinear -> ProgressLinear()
            Variant.ProgressCircular -> ProgressCircular()
            else -> {}
        }

    }
}
