/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.guidelines.spacing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.guidelines.Guideline
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ods.app.ui.utilities.extension.isOrange
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.text.OdsTextTitleM
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

private val ratioFormatter = DecimalFormat("0.#", DecimalFormatSymbols(Locale.ENGLISH))

@Composable
fun GuidelineSpacingScreen() {
    LazyColumn(contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))) {
        item {
            DetailScreenHeader(
                imageRes = DrawableManager.getDrawableResIdForCurrentTheme(resId = R.drawable.il_spacing),
                imageAlignment = Guideline.Spacing.imageAlignment,
                descriptionRes = R.string.guideline_spacing_description
            )
        }
        item {
            OdsTextTitleM(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
                    .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                text = stringResource(id = R.string.guideline_spacing_subtitle)
            )
        }
        items(Spacing.entries) { spacing ->
            val dp = spacing.getDp()
            val ratio = spacing.getRatio()
            OdsListItem(
                text = spacing.tokenName,
                secondaryText = stringResource(id = R.string.guideline_spacing_dp, dp.value.toInt()) + "\n",
                secondaryTextLineCount = OdsListItem.SecondaryTextLineCount.Two,
                leadingIcon = OdsListItem.LeadingIcon(OdsListItem.LeadingIcon.Type.SquareImage, rememberGuidelineSpacingPainter(spacing = spacing), ""),
                trailing = OdsListItem.TrailingCaption(
                    stringResource(
                        id = R.string.guideline_spacing_ratio,
                        if (ratio == 0.0f) "-" else ratioFormatter.format(ratio)
                    )
                ),
                divider = true
            )
        }
    }
}

@Composable
private fun rememberGuidelineSpacingPainter(spacing: Spacing): GuidelineSpacingPainter {
    with(LocalDensity.current) {
        val width = dimensionResource(id = com.orange.ods.R.dimen.list_wide_image_width).toPx()
        val height = dimensionResource(id = com.orange.ods.R.dimen.list_wide_image_height).toPx()
        val spacingWidth = dimensionResource(id = spacing.dimenRes).coerceAtLeast(1.dp).toPx() // Spacing width is at least 1 dp to make spacing-none visible
        val isOrangeTheme = LocalThemeManager.current.currentThemeConfiguration.isOrange
        val spacingColor = if (isOrangeTheme) 0xff4bb4e6 else 0xff949494

        return remember(spacing) {
            GuidelineSpacingPainter(Size(width, height), spacingWidth, spacingColor)
        }
    }
}

private class GuidelineSpacingPainter(override val intrinsicSize: Size, val spacingWidth: Float, val spacingColor: Long) : Painter() {

    override fun DrawScope.onDraw() {
        // Background
        drawRect(Color(0xfff2f2f7))
        // Banner
        val bannerHeight = 16.dp
        drawRect(
            Color(0xff595959),
            Offset(0.0f, ((intrinsicSize.height - bannerHeight.toPx()) / 2.0f)),
            Size(intrinsicSize.width, bannerHeight.toPx())
        )
        // Spacing
        drawRect(
            Color(spacingColor),
            Offset(((intrinsicSize.width - spacingWidth) / 2.0f), 0.0f),
            Size(spacingWidth, intrinsicSize.height)
        )
    }
}
