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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun OdsCard(modifier: Modifier, onClick: (() -> Unit)?, content: @Composable () -> Unit) {
    val backgroundColor = OdsTheme.colors.surface
    val contentColor = OdsTheme.colors.onSurface

    if (onClick != null) {
        Card(
            modifier = modifier.semantics(mergeDescendants = true) {
                role = Role.Button
            }, backgroundColor = backgroundColor, contentColor = contentColor, onClick = onClick, content = content
        )
    } else {
        Card(
            modifier = modifier, backgroundColor = backgroundColor, contentColor = contentColor, content = content
        )
    }
}