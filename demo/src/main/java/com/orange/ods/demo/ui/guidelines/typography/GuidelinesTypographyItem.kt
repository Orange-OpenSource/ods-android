/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines.typography

import androidx.annotation.AttrRes
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.orange.ods.demo.R

data class TypographyItem(
    val name: String,
    val style: TextStyle,
    val jetpackUsage: String,
    @AttrRes
    val xmlResource: Int
)

@Composable
internal fun getTypographyItems(): List<TypographyItem> = listOf(
    TypographyItem(
        name = "Headline 1",
        style = MaterialTheme.typography.h1,
        jetpackUsage = "MaterialTheme.typography.h1",
        xmlResource = R.attr.textAppearanceHeadline1,
    ),
    TypographyItem(
        name = "Headline 2",
        style = MaterialTheme.typography.h2,
        jetpackUsage = "MaterialTheme.typography.h2",
        xmlResource = R.attr.textAppearanceHeadline2,
    ),
    TypographyItem(
        name = "Headline 3",
        style = MaterialTheme.typography.h3,
        jetpackUsage = "MaterialTheme.typography.h3",
        xmlResource = R.attr.textAppearanceHeadline3,
    ),
    TypographyItem(
        name = "Headline 4",
        style = MaterialTheme.typography.h4,
        jetpackUsage = "MaterialTheme.typography.h4",
        xmlResource = R.attr.textAppearanceHeadline4,
    ),
    TypographyItem(
        name = "Headline 5",
        style = MaterialTheme.typography.h5,
        jetpackUsage = "MaterialTheme.typography.h5",
        xmlResource = R.attr.textAppearanceHeadline5,
    ),
    TypographyItem(
        name = "Headline 6",
        style = MaterialTheme.typography.h6,
        jetpackUsage = "MaterialTheme.typography.h6",
        xmlResource = R.attr.textAppearanceHeadline6,
    ),
    TypographyItem(
        name = "Subtitle1",
        style = MaterialTheme.typography.subtitle1,
        jetpackUsage = "MaterialTheme.typography.subtitle1",
        xmlResource = R.attr.textAppearanceSubtitle1,
    ),
    TypographyItem(
        name = "Subtitle2",
        style = MaterialTheme.typography.subtitle2,
        jetpackUsage = "MaterialTheme.typography.subtitle2",
        xmlResource = R.attr.textAppearanceSubtitle2,
    ),
    TypographyItem(
        name = "Body1",
        style = MaterialTheme.typography.body1,
        jetpackUsage = "MaterialTheme.typography.body1",
        xmlResource = R.attr.textAppearanceBody1,
    ),
    TypographyItem(
        name = "Body2",
        style = MaterialTheme.typography.body2,
        jetpackUsage = "MaterialTheme.typography.body2",
        xmlResource = R.attr.textAppearanceBody2,
    ),
    TypographyItem(
        name = "Button",
        style = MaterialTheme.typography.button,
        jetpackUsage = "MaterialTheme.typography.button",
        xmlResource = R.attr.textAppearanceButton,
    ),
    TypographyItem(
        name = "Caption",
        style = MaterialTheme.typography.caption,
        jetpackUsage = "MaterialTheme.typography.caption",
        xmlResource = R.attr.textAppearanceCaption,
    ),
    TypographyItem(
        name = "Overline",
        style = MaterialTheme.typography.overline,
        jetpackUsage = "MaterialTheme.typography.overline",
        xmlResource = R.attr.textAppearanceOverline,
    )
)