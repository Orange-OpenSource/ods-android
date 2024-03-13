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

package com.orange.ods.app.ui.guidelines

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.orange.ods.app.R

@Immutable
enum class Guideline(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    val route: String
) {
    Color(R.string.guideline_color, R.drawable.il_color, GuidelinesNavigation.GuidelineColor),
    Typography(R.string.guideline_typography, R.drawable.il_typography, GuidelinesNavigation.GuidelineTypography),
    Spacing(R.string.guideline_spacing, R.drawable.il_spacing, GuidelinesNavigation.GuidelineSpacing);

    val imageBackgroundColor = Color(0xff1b1b1b)

    val imageContentScale: ContentScale
        get() = when (this) {
            Color -> ContentScale.FillBounds
            Typography, Spacing -> ContentScale.Fit
        }

    val imageAlignment: Alignment
        get() = when (this) {
            Color, Typography -> Alignment.Center
            Spacing -> Alignment.BottomCenter
        }
}
