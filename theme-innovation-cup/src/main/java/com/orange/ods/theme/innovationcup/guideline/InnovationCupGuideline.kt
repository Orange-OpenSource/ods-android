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

package com.orange.ods.theme.innovationcup.guideline

import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.OdsGuideline
import com.orange.ods.theme.typography.OdsTypography

class InnovationCupGuideline(typography: OdsTypography) : OdsGuideline(typography) {

    override val guidelineColors: List<GuidelineColor>
        get() = InnovationCupGuidelineColors

}