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

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.orange.ods.compose.component.OdsBottomNavigation
import com.orange.ods.compose.component.OdsBottomNavigationItem
import com.orange.ods.compose.theme.OdsMaterialTheme

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val default = isSystemInDarkTheme()
    var isDarkMode by remember { mutableStateOf(default) }
    var topAppBarTitle by remember { mutableStateOf("") }
    OdsMaterialTheme(isDarkMode) {
        Scaffold(
            topBar = {
                TopAppBar(title = topAppBarTitle, isDarkMode = isDarkMode) { isDarkMode = it }
            },
            bottomBar = { BottomNavigationBar(navController) }
        ) {
            AppNavigation(navController = navController, onSetScreenTitle = { topAppBarTitle = it })
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
                icon =  { Icon(painter = painterResource(id = item.icon), contentDescription = stringResource(id = item.title)) },
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