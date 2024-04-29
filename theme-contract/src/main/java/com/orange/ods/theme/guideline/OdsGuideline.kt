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
}