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
import com.orange.ods.theme.OdsColors
import com.orange.ods.theme.OdsThemeSettings
import com.orange.ods.theme.guideline.OdsDemoGuideline
import com.orange.ods.theme.orange.guideline.OrangeGuideline
import kotlinx.parcelize.Parcelize

@Parcelize
class OrangeThemeSettings : OdsThemeSettings {

    override val lightThemeColors: OdsColors
        get() = OrangeLightColors

    override val darkThemeColors: OdsColors
        get() = OrangeDarkColors

    override val typography: Typography
        get() = OrangeTypography

    override val odsDemoGuideline: OdsDemoGuideline
        get() = OrangeGuideline()
}
