/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.orange.guideline

import com.orange.ods.theme.guideline.GuidelineTextStyle
import com.orange.ods.theme.orange.OrangeTypography
import com.orange.ods.theme.orange.R

val OrangeGuidelineTypography = listOf(
    GuidelineTextStyle(
        name = "Headline 1",
        textStyle = OrangeTypography.h1,
        composeStyle = "OdsTheme.typography.h1",
        xmlResource = R.attr.textAppearanceHeadline1,
    ),
    GuidelineTextStyle(
        name = "Headline 2",
        textStyle = OrangeTypography.h2,
        composeStyle = "OdsTheme.typography.h2",
        xmlResource = R.attr.textAppearanceHeadline2,
    ),
    GuidelineTextStyle(
        name = "Headline 3",
        textStyle = OrangeTypography.h3,
        composeStyle = "OdsTheme.typography.h3",
        xmlResource = R.attr.textAppearanceHeadline3,
    ),
    GuidelineTextStyle(
        name = "Headline 4",
        textStyle = OrangeTypography.h4,
        composeStyle = "OdsTheme.typography.h4",
        xmlResource = R.attr.textAppearanceHeadline4,
    ),
    GuidelineTextStyle(
        name = "Headline 5",
        textStyle = OrangeTypography.h5,
        composeStyle = "OdsTheme.typography.h5",
        xmlResource = R.attr.textAppearanceHeadline5,
    ),
    GuidelineTextStyle(
        name = "Headline 6",
        textStyle = OrangeTypography.h6,
        composeStyle = "OdsTheme.typography.h6",
        xmlResource = R.attr.textAppearanceHeadline6,
    ),
    GuidelineTextStyle(
        name = "Subtitle1",
        textStyle = OrangeTypography.subtitle1,
        composeStyle = "OdsTheme.typography.subtitle1",
        xmlResource = R.attr.textAppearanceSubtitle1,
    ),
    GuidelineTextStyle(
        name = "Subtitle2",
        textStyle = OrangeTypography.subtitle2,
        composeStyle = "OdsTheme.typography.subtitle2",
        xmlResource = R.attr.textAppearanceSubtitle2,
    ),
    GuidelineTextStyle(
        name = "Body1",
        textStyle = OrangeTypography.body1,
        composeStyle = "OdsTheme.typography.body1",
        xmlResource = R.attr.textAppearanceBody1,
    ),
    GuidelineTextStyle(
        name = "Body2",
        textStyle = OrangeTypography.body2,
        composeStyle = "OdsTheme.typography.body2",
        xmlResource = R.attr.textAppearanceBody2,
    ),
    GuidelineTextStyle(
        name = "BUTTON",
        textStyle = OrangeTypography.button,
        composeStyle = "OdsTheme.typography.button",
        xmlResource = R.attr.textAppearanceButton,
    ),
    GuidelineTextStyle(
        name = "Caption",
        textStyle = OrangeTypography.caption,
        composeStyle = "OdsTheme.typography.caption",
        xmlResource = R.attr.textAppearanceCaption,
    ),
    GuidelineTextStyle(
        name = "Overline",
        textStyle = OrangeTypography.overline,
        composeStyle = "OdsTheme.typography.overline",
        xmlResource = R.attr.textAppearanceOverline,
    )
)