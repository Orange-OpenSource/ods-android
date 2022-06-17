/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.chip

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@ExperimentalMaterialApi
@Composable
fun OdsFilterChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hasBorder: Boolean = false,
    enabled: Boolean = true,
    selected: Boolean = false,
    leadingIcon: Painter? = null,
    leadingAvatar: Painter? = null,
    leadingElementContentDescription: String? = null,
    onCancel: (() -> Unit)? = null
) {
    FilterChip(selected =, onClick = { /*TODO*/ }) {
        
    }
}
