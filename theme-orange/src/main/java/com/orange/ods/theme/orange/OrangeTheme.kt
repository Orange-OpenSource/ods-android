/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.orange

import androidx.compose.material.Typography
import com.orange.ods.theme.GuidelineColor
import com.orange.ods.theme.OdsSupportedColors
import com.orange.ods.theme.OdsSupportedTheme
import com.orange.ods.theme.orange.guideline.OrangeGuidelineColors
import kotlinx.parcelize.Parcelize

@Parcelize
class OrangeTheme : OdsSupportedTheme {

    override val lightThemeColors: OdsSupportedColors
        get() = OrangeLightColors

    override val darkThemeColors: OdsSupportedColors
        get() = OrangeDarkColors

    override val typography: Typography
        get() = OrangeTypography

    override val guidelineColors: List<GuidelineColor>
        get() = OrangeGuidelineColors

}
