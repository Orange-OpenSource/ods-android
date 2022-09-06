/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.card

import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalMaterialApi
@Composable
internal fun OdsCard(modifier: Modifier, onClick: (() -> Unit)?, content: @Composable () -> Unit) {
    if (onClick != null) {
        Card(modifier = modifier, onClick = onClick, content = content)
    } else {
        Card(modifier = modifier, content = content)
    }
}