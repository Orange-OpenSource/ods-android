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

package com.orange.ods.theme.innovationcup

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
import com.orange.ods.theme.OdsComponentsConfiguration
import com.orange.ods.theme.OdsThemeColors
import com.orange.ods.theme.OdsThemeConfigurationContract
import kotlinx.parcelize.Parcelize

@Parcelize
class InnovationCupThemeConfiguration : OdsThemeConfigurationContract<InnovationCupPalette> {

    override val name: String
        get() = "Innovation Cup"

    override val colors: OdsThemeColors<InnovationCupPalette>
        get() = OdsThemeColors(InnovationCupLightColors, InnovationCupDarkColors, InnovationCupPalette)

    override val shapes: Shapes
        get() = Shapes().copy(
            small = RoundedCornerShape(8.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(4.dp)
        )

    override val componentsConfiguration: OdsComponentsConfiguration
        get() = object : OdsComponentsConfiguration() {
            override val chipStyle: ComponentStyle
                get() = ComponentStyle.Filled
            override val textFieldStyle: ComponentStyle
                get() = ComponentStyle.Filled
        }

}