/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

/**
 * The ODS buttons shape
 * Override theme value cause Orange buttons are squared
 */
internal val odsButtonShape = RoundedCornerShape(0.dp)

/**
 * The icon displayed in every type of buttons
 *
 * @param painter Painter of the icon
 */
@Composable
internal fun ButtonIcon(painter: Painter) {
    Icon(
        painter = painter,
        contentDescription = null,
        modifier = Modifier.size(ButtonDefaults.IconSize)
    )
    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
}