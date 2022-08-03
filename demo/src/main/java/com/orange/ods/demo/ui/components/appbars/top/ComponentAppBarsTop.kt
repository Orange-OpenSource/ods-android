/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.appbars.top

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.orange.ods.demo.ui.components.Variant

@ExperimentalMaterialApi
@Composable
fun ComponentAppBarsTop(variant: Variant) {
    when (variant) {
        Variant.AppBarsTopRegular -> VariantAppBarsTopRegular()
        Variant.AppBarsTopExtended -> VariantAppBarsTopExtended()
        else -> {}
    }
}