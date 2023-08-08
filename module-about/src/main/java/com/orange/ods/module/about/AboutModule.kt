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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.orange.OrangeThemeConfiguration

val LocalAboutModuleConfiguration = staticCompositionLocalOf<AboutModuleConfiguration> { error("CompositionLocal LocalAboutModuleConfiguration not present") }

/**
 * Entry point of the About module.
 *
 * @param configuration the about module configuration (@see [AboutModuleConfiguration])
 * @param fullScreen if true, the module will be displayed in its own Scaffold. Otherwise only the content will be displayed to allow integration in your app Scaffold.
 */
@Composable
fun AboutModule(configuration: AboutModuleConfiguration, fullScreen: Boolean = false) {
    CompositionLocalProvider(
        LocalAboutModuleConfiguration provides configuration,
    ) {
        OdsTheme(themeConfiguration = OrangeThemeConfiguration()) {
            val aboutState = rememberAboutModuleState()
            if (fullScreen) {
                AboutModuleScaffold { innerPadding ->
                    AboutModuleNavHost(navController = aboutState.navController, modifier = Modifier.padding(innerPadding))
                }
            } else {
                AboutModuleNavHost(navController = aboutState.navController)
            }
        }
    }
}

@Composable
private fun AboutModuleScaffold(content: @Composable (PaddingValues) -> Unit) {
    val configuration = LocalAboutModuleConfiguration.current
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
        content(innerPadding)
    }
}

@Composable
private fun AboutModuleNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController, startDestination = AboutDestinations.HomeRoute, modifier = modifier
    ) {
        aboutNavGraph()
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