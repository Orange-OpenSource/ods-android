/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.ods.demo.ui.components.Variant

@ExperimentalMaterialApi
@Composable
fun ComponentList(variant: Variant) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        VariantListContent(variant)
    }
}

@ExperimentalMaterialApi
@Composable
private fun VariantListContent(variant: Variant) {
    when (variant) {
        Variant.ListsOneLine -> ListOneLineContent()
        Variant.ListsTwoLines -> ListTwoLinesContent()
        Variant.ListsThreeLines -> ListThreeLinesContent()
        else -> {}
    }
}