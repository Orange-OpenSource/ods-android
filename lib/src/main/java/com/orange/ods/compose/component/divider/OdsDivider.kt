/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.divider

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

private const val OdsDividerAlpha = 0.12f

/**
 * An [OdsDivider] is a thin line of 1dp thickness that groups content in lists and layouts.
 *
 * @param modifier Modifier to be applied to the divider.
 * @param startIndent start offset of this line, no offset by default.
 */
@Composable
@OdsComposable
fun OdsDivider(
    modifier: Modifier = Modifier,
    startIndent: Dp = 0.dp
) {
    Divider(modifier = modifier, startIndent = startIndent, color = OdsTheme.colors.onSurface.copy(alpha = OdsDividerAlpha))
}

@UiModePreviews.Default
@Composable
fun PreviewOdsDivider() = Preview {
    OdsDivider()
}
