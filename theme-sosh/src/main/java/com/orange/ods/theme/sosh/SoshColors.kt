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

import androidx.compose.ui.graphics.Color
import com.orange.ods.compose.theme.OdsColors

fun soshLightColors(
    coreSurface: Color = White100,
    coreOnSurface: Color = Black900,
    systemStatusBarBackground: Color = Blue,
    systemNavigationBarBackground: Color = Black900,
    componentBackgroundDisabled: Color = Grey4,
    componentContentDisabled: Color = White100,
    topBarBackground: Color = Blue,
    topBarContent: Color = White100,
    bottomNavigationBarBackground: Color = Black900,
    bottomNavigationBarContent: Color = White100,
    bottomNavigationBarContentSelected: Color = Main2
): OdsColors = OdsColors(
    coreSurface = coreSurface,
    coreOnSurface = coreOnSurface,
    systemStatusBarBackground = systemStatusBarBackground,
    systemNavigationBarBackground = systemNavigationBarBackground,
    componentBackgroundDisabled = componentBackgroundDisabled,
    componentContentDisabled = componentContentDisabled,
    topBarBackground = topBarBackground,
    topBarContent = topBarContent,
    bottomNavigationBarBackground = bottomNavigationBarBackground,
    bottomNavigationBarContent = bottomNavigationBarContent,
    bottomNavigationBarContentSelected = bottomNavigationBarContentSelected,
    isLight = true
)

fun soshDarkColors(
    coreSurface: Color = DarkSurfaceDefault,
    coreOnSurface: Color = White100,
    systemStatusBarBackground: Color = Black900,
    systemNavigationBarBackground: Color = Black900,
    componentBackgroundDisabled: Color = DarkDisabled,
    componentContentDisabled: Color = Grey3,
    topBarBackground: Color = Black900,
    topBarContent: Color = White100,
    bottomNavigationBarBackground: Color = Black900,
    bottomNavigationBarContent: Color = White100,
    bottomNavigationBarContentSelected: Color = Main1,
    cardBackground: Color = Black900,
    cardContent: Color = White100
): OdsColors = OdsColors(
    coreSurface = coreSurface,
    coreOnSurface = coreOnSurface,
    systemStatusBarBackground = systemStatusBarBackground,
    systemNavigationBarBackground = systemNavigationBarBackground,
    componentBackgroundDisabled = componentBackgroundDisabled,
    componentContentDisabled = componentContentDisabled,
    topBarBackground = topBarBackground,
    topBarContent = topBarContent,
    bottomNavigationBarBackground = bottomNavigationBarBackground,
    bottomNavigationBarContent = bottomNavigationBarContent,
    bottomNavigationBarContentSelected = bottomNavigationBarContentSelected,
    cardBackground = cardBackground,
    cardContent = cardContent,
    isLight = false
)