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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentHeader

@Composable
fun ComponentsListsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ComponentHeader(
            imageRes = R.drawable.picture_component_lists,
            description = R.string.component_lists_description
        )
    }
}