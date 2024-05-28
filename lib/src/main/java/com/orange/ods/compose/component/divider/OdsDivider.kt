/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.compose.component.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

private const val OdsDividerAlpha = 0.12f

/**
 * An [OdsDivider] is a thin line of 1dp thickness that groups content in lists and layouts.
 *
 * @param modifier Modifier to be applied to the divider.
 */
@Composable
@OdsComposable
fun OdsDivider(
    modifier: Modifier = Modifier,
) {
    HorizontalDivider(modifier = modifier, color = OdsTheme.colors.onSurface.copy(alpha = OdsDividerAlpha))
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsDivider() = OdsPreview {
    OdsDivider()
}
