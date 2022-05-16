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
import com.orange.ods.demo.ui.components.SubComponent

@ExperimentalMaterialApi
@Composable
fun SubComponentList(subComponent: SubComponent) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        SubComponentListContent(subComponent)
    }
}

@ExperimentalMaterialApi
@Composable
private fun SubComponentListContent(subComponent: SubComponent) {
    when (subComponent) {
        SubComponent.ListsOneLine -> ListOneLineContent()
        SubComponent.ListsTwoLines -> ListTwoLinesContent()
        SubComponent.ListsThreeLines -> ListThreeLinesContent()
        else -> {}
    }
}