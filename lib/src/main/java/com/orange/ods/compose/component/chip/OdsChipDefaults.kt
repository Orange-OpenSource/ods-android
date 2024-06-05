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

package com.orange.ods.compose.component.chip

import androidx.compose.material3.FilterChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.compositeOver
import com.orange.ods.compose.extension.enable
import com.orange.ods.compose.theme.OdsTheme

internal object OdsChipDefaults {

    /**
     * The color opacity used for chip's surface overlay.
     */
    internal const val SurfaceOverlayOpacity = 0.12f

    @Composable
    fun OdsTheme.defaultFilterChipColors() = FilterChipDefaults.filterChipColors(
        selectedContainerColor = colors.surfaceVariant,
        selectedLabelColor = colors.onSurfaceVariant,
        selectedLeadingIconColor = colors.onSurfaceVariant,
        disabledLeadingIconColor = colors.onSurfaceVariant.enable(enabled = false).compositeOver(colors.surfaceVariant),
        disabledSelectedContainerColor = colors.surfaceVariant.enable(enabled = false)
    )

}