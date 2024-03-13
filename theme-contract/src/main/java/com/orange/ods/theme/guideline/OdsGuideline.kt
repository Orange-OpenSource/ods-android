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

package com.orange.ods.theme.guideline

import com.orange.ods.theme.annotation.ExperimentalOdsApi
import com.orange.ods.theme.typography.OdsTypography

/**
 * This class defines what will be displayed in the ODS application guideline part.
 * Extend this class and override its properties to allow the application to display the guideline
 * elements (colors, typography) of your theme configuration.
 */
@ExperimentalOdsApi
open class OdsGuideline(private val typography: OdsTypography) {

    /**
     * Colors displayed in the guideline part of the ODS application
     */
    open val guidelineColors: List<GuidelineColor>
        get() = emptyList()

    /**
     * Typography displayed in the guideline part of the ODS application
     */
    open val guidelineTypography: List<GuidelineTextStyle>
        get() {
            with(typography) {
                val properties =
                    listOf(::headlineL, ::headlineS, ::titleL, ::titleM, ::titleS, ::bodyL, ::bodyM, ::bodyS, ::labelL, ::labelS)

                return properties.mapNotNull { property ->
                    val (name, xmlResource) = when (property) {
                        ::headlineL -> "Headline L" to com.google.android.material.R.attr.textAppearanceHeadlineLarge
                        ::headlineS -> "Headline S" to com.google.android.material.R.attr.textAppearanceHeadlineSmall
                        ::titleL -> "Title L" to com.google.android.material.R.attr.textAppearanceTitleLarge
                        ::titleM -> "Title M" to com.google.android.material.R.attr.textAppearanceTitleMedium
                        ::titleS -> "Title S" to com.google.android.material.R.attr.textAppearanceTitleSmall
                        ::bodyL -> "Body L" to com.google.android.material.R.attr.textAppearanceBodyLarge
                        ::bodyM -> "Body M" to com.google.android.material.R.attr.textAppearanceBodyMedium
                        ::bodyS -> "Body S" to com.google.android.material.R.attr.textAppearanceBodySmall
                        ::labelL -> "Label L" to com.google.android.material.R.attr.textAppearanceLabelLarge
                        ::labelS -> "Label S" to com.google.android.material.R.attr.textAppearanceLabelSmall
                        else -> null to null
                    }
                    if (name != null && xmlResource != null) {
                        GuidelineTextStyle(
                            name = name,
                            textStyle = property(),
                            composeStyle = "OdsTheme.typography.${property.name}",
                            xmlResource = xmlResource,
                            allCaps = isAllCapsTextStyle(property())
                        )
                    } else {
                        null
                    }
                }
            }
        }
}