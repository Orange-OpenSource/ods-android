/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.cards

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.orange.ods.demo.ui.components.Variant

@ExperimentalMaterialApi
@Composable
fun ComponentCard(variant: Variant) {
    when (variant) {
        Variant.CardImageFirst -> CardImageFirst()
        Variant.CardSmall -> CardSmall()
        Variant.CardTitleFirst -> CardTitleFirst()
        else -> {}
    }
}