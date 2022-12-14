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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.utilities.extension.enable

internal object OdsChipDefaults {

    /**
     * The color opacity used for chip's surface overlay.
     */
    internal const val SurfaceOverlayOpacity = 0.12f

    /**
     * The color opacity used for a selected chip's leading icon or content overlay.
     */
    private const val SelectedOverlayOpacity = 0.16f

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
        disabledBackgroundColor = backgroundColor.enable(enabled = false)
    )

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun outlinedChipColors(
        backgroundColor: Color = OdsTheme.colors.surface,
        contentColor: Color = OdsTheme.colors.onSurface.copy(alpha = ChipDefaults.ContentOpacity),
    ) = ChipDefaults.outlinedChipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        leadingIconContentColor = if (isSystemInDarkTheme()) contentColor else contentColor.copy(alpha = ChipDefaults.LeadingIconOpacity)
    )

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun filterChipColors(
        backgroundColor: Color = OdsTheme.colors.onSurface.copy(alpha = SurfaceOverlayOpacity).compositeOver(OdsTheme.colors.surface),
        contentColor: Color = OdsTheme.colors.onSurface.copy(alpha = ChipDefaults.ContentOpacity),
        leadingIconColor: Color = contentColor.copy(alpha = ChipDefaults.LeadingIconOpacity)
    ) = ChipDefaults.filterChipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        leadingIconColor = leadingIconColor,
        selectedBackgroundColor = OdsTheme.colors.onSurface.copy(alpha = SurfaceOverlayOpacity).compositeOver(backgroundColor),
        selectedContentColor = OdsTheme.colors.onSurface.copy(alpha = SelectedOverlayOpacity).compositeOver(contentColor),
        selectedLeadingIconColor = OdsTheme.colors.onSurface.copy(alpha = SelectedOverlayOpacity).compositeOver(leadingIconColor),
        disabledBackgroundColor = backgroundColor.enable(enabled = false)
    )

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun outlinedFilterChipColors(
        backgroundColor: Color = OdsTheme.colors.surface,
        contentColor: Color = OdsTheme.colors.onSurface.copy(alpha = ChipDefaults.ContentOpacity),
        leadingIconColor: Color = contentColor.copy(alpha = ChipDefaults.LeadingIconOpacity)
    ) = ChipDefaults.outlinedFilterChipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        leadingIconColor = leadingIconColor,
        selectedBackgroundColor = OdsTheme.colors.onSurface.copy(alpha = SurfaceOverlayOpacity).compositeOver(backgroundColor),
        selectedContentColor = OdsTheme.colors.onSurface.copy(alpha = SelectedOverlayOpacity).compositeOver(contentColor),
        selectedLeadingIconColor = OdsTheme.colors.onSurface.copy(alpha = SelectedOverlayOpacity).compositeOver(leadingIconColor),
        disabledBackgroundColor = backgroundColor.enable(enabled = false)
    )
}