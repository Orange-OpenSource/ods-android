/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

class MainState(
    val themeState: ThemeState,
    val navigationState: AppNavigationState,
    val appBarState: AppBarState,
    val uiFramework: MutableState<UiFramework>
) {

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    val shouldShowBottomBar: Boolean
        @Composable
        get() = navigationState.currentScreen?.isHome(navigationState.previousRoute) == true

}

@Composable
fun rememberMainState(
    themeState: ThemeState,
    appNavigationState: AppNavigationState = rememberAppNavigationState(),
    appBarState: AppBarState = rememberAppBarState(appNavigationState),
    uiFramework: MutableState<UiFramework> = rememberSaveable { mutableStateOf(UiFramework.Compose) }
) = remember(themeState, appNavigationState, appBarState, uiFramework) {
    MainState(themeState, appNavigationState, appBarState, uiFramework)
}
