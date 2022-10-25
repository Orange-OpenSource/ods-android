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

import androidx.compose.material.Typography
import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.OdsDemoGuideline

class OrangeGuideline(typography: Typography) : OdsDemoGuideline(typography) {

    override val guidelineColors: List<GuidelineColor>
        get() = OrangeGuidelineColors
    
}