/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.orange.OrangeThemeConfiguration

val LocalAboutModuleConfiguration = staticCompositionLocalOf<AboutModuleConfiguration> { error("CompositionLocal LocalAboutModuleConfiguration not present") }

@Composable
fun AboutModule(configuration: AboutModuleConfiguration) {
    CompositionLocalProvider(
        LocalAboutModuleConfiguration provides configuration,
    ) {
        OdsTheme(themeConfiguration = OrangeThemeConfiguration()) {
            val aboutState = rememberAboutModuleState()
            Scaffold(
                backgroundColor = OdsTheme.colors.background,
                topBar = {
                    //TODO Add SystemBarsColorSideEffect() to the lib
                    OdsTopAppBar(
                        title = stringResource(id = R.string.about_top_bar_title),
                        actions = configuration.topAppBarActions,
                        overflowMenuActions = configuration.topAppBarOverflowMenuActions
                    )
                },
            ) { innerPadding ->
                NavHost(
                    navController = aboutState.navController, startDestination = AboutDestinations.HomeRoute, modifier = Modifier.padding(innerPadding)
                ) {
                    aboutNavGraph()
                }
            }
        }
    }
}

private fun NavGraphBuilder.aboutNavGraph() {
    navigation(
        route = AboutDestinations.HomeRoute,
        startDestination = AboutDestinations.MainScreenRoute
    ) {
        composable(AboutDestinations.MainScreenRoute) { _ ->
            AboutMainScreen()
        }
    }
}