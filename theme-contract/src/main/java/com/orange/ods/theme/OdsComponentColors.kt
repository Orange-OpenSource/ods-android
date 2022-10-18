/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme

import androidx.compose.ui.graphics.Color


data class OdsBottomNavigationColors(
    val barBackground: Color,
    val barContent: Color,
    val itemSelected: Color,
    val itemUnselected: Color = itemSelected.copy(alpha = 0.74f)
)

data class OdsTopAppBarColors(
    val barBackground: Color,
    val barContent: Color
)

data class OdsSwitchColors(
    val uncheckedThumb: Color
)