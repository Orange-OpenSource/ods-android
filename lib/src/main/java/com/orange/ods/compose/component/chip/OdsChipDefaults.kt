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

import androidx.compose.material.ChipDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.orange.ods.compose.theme.LocalDarkThemeEnabled
import com.orange.ods.compose.theme.OdsTheme

internal object OdsChipDefaults {

    /**
     * The color opacity used for chip's surface overlay.
     */
    internal const val SurfaceOverlayOpacity = 0.12f

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun chipColors(
        backgroundColor: Color = OdsTheme.colors.onSurface.copy(alpha = SurfaceOverlayOpacity)
            .compositeOver(OdsTheme.colors.surface),
        contentColor: Color = OdsTheme.colors.onSurface.copy(alpha = ChipDefaults.ContentOpacity)
    ) = ChipDefaults.chipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        leadingIconContentColor = contentColor.copy(alpha = ChipDefaults.LeadingIconOpacity),
        disabledBackgroundColor = backgroundColor.copy(alpha = ContentAlpha.disabled)
    )

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun outlinedChipColors(
        backgroundColor: Color = OdsTheme.colors.surface,
        contentColor: Color = OdsTheme.colors.onSurface.copy(alpha = ChipDefaults.ContentOpacity),
    ) = ChipDefaults.outlinedChipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        leadingIconContentColor = if (LocalDarkThemeEnabled.current) contentColor else contentColor.copy(alpha = ChipDefaults.LeadingIconOpacity)
    )

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun filterChipColors(
        backgroundColor: Color = OdsTheme.colors.onSurface.copy(alpha = SurfaceOverlayOpacity)
            .compositeOver(OdsTheme.colors.surface),
        contentColor: Color = OdsTheme.colors.onSurface.copy(alpha = ChipDefaults.ContentOpacity),
    ) = ChipDefaults.filterChipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        leadingIconColor = contentColor.copy(alpha = ChipDefaults.LeadingIconOpacity),
        disabledBackgroundColor = backgroundColor.copy(alpha = ContentAlpha.disabled)
    )

}