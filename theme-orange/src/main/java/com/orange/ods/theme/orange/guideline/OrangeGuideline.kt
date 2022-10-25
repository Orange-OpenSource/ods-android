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

import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.GuidelineTextStyle
import com.orange.ods.theme.guideline.OdsDemoGuideline

class OrangeGuideline : OdsDemoGuideline() {

    override val guidelineColors: List<GuidelineColor>
        get() = OrangeGuidelineColors

    override val guidelineTypography: List<GuidelineTextStyle>
        get() = OrangeGuidelineTypography
}