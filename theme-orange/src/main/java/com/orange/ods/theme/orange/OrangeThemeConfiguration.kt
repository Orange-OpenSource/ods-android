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

package com.orange.ods.theme.orange

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp
import com.orange.ods.theme.OdsThemeColors
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.typography.OdsTypography
import kotlinx.parcelize.Parcelize

@Parcelize
class OrangeThemeConfiguration : OdsThemeConfigurationContract<OrangeColorPalette> {

    companion object {
        const val OrangeThemeName = "Orange"
    }

    override val name: String
        get() = OrangeThemeName

    override val colors: OdsThemeColors<OrangeColorPalette>
        get() = OdsThemeColors(OrangeLightColors, OrangeDarkColors, OrangeColorPalette)

    override val typography: OdsTypography
        get() = OrangeTypography

    override val shapes: Shapes
        get() = Shapes().copy(
            small = RoundedCornerShape(0.dp)
        )
}
