/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.orange.ods.compose.component.OdsBottomNavigation
import com.orange.ods.compose.component.OdsBottomNavigationItem
import com.orange.ods.compose.theme.OdsMaterialTheme

@ExperimentalMaterialApi
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val default = isSystemInDarkTheme()
    var isDarkModeEnabled by remember { mutableStateOf(default) }

    // Change isSystemInDarkTheme() value to make switching theme working with custom color
    if (isDarkModeEnabled) {
        LocalConfiguration.current.uiMode = UI_MODE_NIGHT_YES
    } else {
        LocalConfiguration.current.uiMode = UI_MODE_NIGHT_NO
    }

    val navController = rememberNavController()
    var topAppBarTitle by remember { mutableStateOf("") }

    val bottomBarState = isCurrentScreenFromHome(navController)

    OdsMaterialTheme(isDarkModeEnabled) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = topAppBarTitle,
                    isDarkMode = isDarkModeEnabled,
                    navController = navController
                ) { isDarkModeEnabled = it }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = bottomBarState,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    BottomNavigationBar(navController)
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavigation(
                    navController = navController,
                    onSetScreenTitle = { topAppBarTitle = it })
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem.Guidelines,
        NavigationItem.Components,
        NavigationItem.Modules,
        NavigationItem.About
    )

    OdsBottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach { item ->
            OdsBottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = stringResource(id = item.title)) },
                label = stringResource(id = item.title),
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun isCurrentScreenFromHome(navController: NavHostController): Boolean {
    return when {
        currentRoute(navController) == NavigationItem.Guidelines.route
            || currentRoute(navController) == NavigationItem.Components.route
            || currentRoute(navController) == NavigationItem.Modules.route
            || currentRoute(navController) == NavigationItem.About.route -> {
            true
        }
        else -> false
    }
}