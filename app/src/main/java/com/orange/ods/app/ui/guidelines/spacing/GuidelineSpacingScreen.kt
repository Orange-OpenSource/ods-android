/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.guidelines.spacing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.guidelines.Guideline
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ods.app.ui.utilities.extension.isOrange
import com.orange.ods.compose.component.list.OdsCaptionTrailing
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.divider
import com.orange.ods.compose.text.OdsTextSubtitle1
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

private val ratioFormatter = DecimalFormat("0.#", DecimalFormatSymbols(Locale.ENGLISH))

@Composable
fun GuidelineSpacingScreen() {
    LocalMainTopAppBarManager.current.titleResId = R.string.guideline_spacing

    LazyColumn(contentPadding = PaddingValues(bottom = dimensionResource(id = R.dimen.spacing_m))) {
        item {
            DetailScreenHeader(
                imageRes = DrawableManager.getDrawableResIdForCurrentTheme(resId = R.drawable.il_spacing),
                imageAlignment = Guideline.Spacing.imageAlignment,
                descriptionRes = R.string.guideline_spacing_description
            )
        }
        item {
            OdsTextSubtitle1(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
                    .padding(vertical = dimensionResource(id = R.dimen.spacing_m)),
                text = stringResource(id = R.string.guideline_spacing_subtitle)
            )
        }
        items(Spacing.values()) { spacing ->
            val dividerStartIndent = dimensionResource(id = R.dimen.guideline_spacing_image_width) + dimensionResource(id = R.dimen.spacing_m).times(2)
            val dp = spacing.getDp()
            val ratio = spacing.getRatio()
            OdsListItem(
                modifier = Modifier.divider(startIndent = dividerStartIndent),
                text = spacing.tokenName,
                secondaryText = stringResource(id = R.string.guideline_spacing_dp, dp.value.toInt()) + "\n",
                singleLineSecondaryText = false,
                icon = { GuidelineSpacingImage(spacing = spacing) },
                trailing = OdsCaptionTrailing(
                    text = stringResource(
                        id = R.string.guideline_spacing_ratio,
                        if (ratio == 0.0f) "-" else ratioFormatter.format(ratio)
                    )
                )
            )
        }
    }
}

@Composable
private fun GuidelineSpacingImage(spacing: Spacing) {
    // Spacing width is at least 1 dp to make spacing-none visible
    val spacingWidth = dimensionResource(id = spacing.dimenRes).coerceAtLeast(1.dp)
    val imageWidth = dimensionResource(id = R.dimen.guideline_spacing_image_width)
    val imageHeight = dimensionResource(id = R.dimen.guideline_spacing_image_height)
    val isOrangeTheme = LocalThemeManager.current.currentThemeConfiguration.isOrange

    Canvas(
        modifier = Modifier
            .width(imageWidth)
            .height(imageHeight),
    ) {
        // Background
        drawRect(Color(0xfff2f2f7))
        // Banner
        val bannerHeight = 16.dp
        drawRect(
            Color(0xff595959),
            Offset(0.0f, ((imageHeight - bannerHeight) / 2.0f).toPx()),
            Size(imageWidth.toPx(), bannerHeight.toPx())
        )
        // Spacing
        val spacingColor = if (isOrangeTheme) 0xff4bb4e6 else 0xff949494
        drawRect(
            Color(spacingColor),
            Offset(((imageWidth - spacingWidth) / 2.0f).toPx(), 0.0f),
            Size(spacingWidth.toPx(), imageHeight.toPx())
        )
    }
}
