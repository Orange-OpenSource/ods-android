/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme.sosh

import com.orange.ods.compose.theme.OdsColors
import com.orange.ods.compose.theme.OdsCustomTheme
import kotlinx.parcelize.Parcelize

@Parcelize
class SoshTheme : OdsCustomTheme() {

    override val lightThemeColors: OdsColors
        get() = soshLightColors()
    override val darkThemeColors: OdsColors
        get() = soshDarkColors()

    override fun getTypography() = SoshTypography
}