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

package com.orange.ods.theme.orange.guideline

import com.orange.ods.theme.annotation.ExperimentalOdsApi
import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.OdsGuideline
import com.orange.ods.theme.typography.OdsTypography

@ExperimentalOdsApi
class OrangeGuideline(typography: OdsTypography) : OdsGuideline(typography) {

    override val guidelineColors: List<GuidelineColor>
        get() = OrangeGuidelineColors

}