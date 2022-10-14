/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.orange.demo

import com.orange.ods.theme.GuidelineColor
import com.orange.ods.theme.demo.OdsDemoGuideline

class OrangeDemoGuideline : OdsDemoGuideline() {

    override val guidelineColors: List<GuidelineColor>
        get() = OrangeGuidelineColors
    
}