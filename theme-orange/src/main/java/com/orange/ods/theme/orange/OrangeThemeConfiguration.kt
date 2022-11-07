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

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.ui.unit.dp
import com.orange.ods.theme.OdsThemeColors
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.guideline.OdsDemoGuideline
import com.orange.ods.theme.orange.guideline.OrangeGuideline
import kotlinx.parcelize.Parcelize

@Parcelize
class OrangeThemeConfiguration : OdsThemeConfigurationContract {

    companion object {
        const val OrangeThemeName = "Orange"
    }

    override val name: String
        get() = OrangeThemeName

    override val colors: OdsThemeColors
        get() = OdsThemeColors(OrangeLightColors, OrangeDarkColors)

    override val typography: Typography
        get() = OrangeTypography

    override val shapes: Shapes
        get() = Shapes().copy(
            small = RoundedCornerShape(0.dp)
        )

    override val demoGuideline: OdsDemoGuideline
        get() = OrangeGuideline(typography)
}
