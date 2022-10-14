/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.demo

import com.orange.ods.theme.GuidelineColor

/**
 * This class defines what will be displayed in the ODS Demo Application guideline part.
 * Extend this class and override its properties to allow the Demo Application to display the guideline
 * elements (colors, typography) of your custom theme.
 */
open class OdsDemoGuideline {

    /**
     * The colors of the [OdsThemeSettings] guideline
     */
    open val guidelineColors: List<GuidelineColor>
        get() = emptyList()

}