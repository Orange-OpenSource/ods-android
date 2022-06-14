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

import androidx.compose.ui.graphics.Color
import com.orange.ods.compose.theme.OdsColors

fun orangeLightColors(
    coreSurface: Color = White100,
    coreOnSurface: Color = Black900,
    componentBackgroundDisabled: Color = Black900.copy(alpha = 0.12f),
    componentContentDisabled: Color = Black900.copy(alpha = 0.38f),
    bottomNavigationBarContentSelected: Color = Orange200,
): OdsColors = OdsColors(
    coreSurface = coreSurface,
    coreOnSurface = coreOnSurface,
    componentBackgroundDisabled = componentBackgroundDisabled,
    componentContentDisabled = componentContentDisabled,
    bottomNavigationBarContentSelected = bottomNavigationBarContentSelected,
    isLight = true
)

fun orangeDarkColors(
    coreSurface: Color = Black900,
    coreOnSurface: Color = White100,
    componentBackgroundDisabled: Color = White100.copy(alpha = 0.12f),
    componentContentDisabled: Color = White100.copy(alpha = 0.38f),
    bottomNavigationBarContentSelected: Color = Orange100,
): OdsColors = OdsColors(
    coreSurface = coreSurface,
    coreOnSurface = coreOnSurface,
    componentBackgroundDisabled = componentBackgroundDisabled,
    componentContentDisabled = componentContentDisabled,
    bottomNavigationBarContentSelected = bottomNavigationBarContentSelected,
    isLight = false
)