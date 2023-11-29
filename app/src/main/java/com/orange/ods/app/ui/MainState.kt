/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
