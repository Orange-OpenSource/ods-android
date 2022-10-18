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
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.ThemeColors
import com.orange.ods.theme.guideline.OdsDemoGuideline
import com.orange.ods.theme.orange.guideline.OrangeGuideline
import kotlinx.parcelize.Parcelize

@Parcelize
class OrangeThemeConfiguration : OdsThemeConfigurationContract {

    override val name: String
        get() = "Orange"

    override val colors: ThemeColors
        get() = ThemeColors(OrangeLightColors, OrangeDarkColors)

    override val typography: Typography
        get() = OrangeTypography

    override val demoGuideline: OdsDemoGuideline
        get() = OrangeGuideline(typography)
}
