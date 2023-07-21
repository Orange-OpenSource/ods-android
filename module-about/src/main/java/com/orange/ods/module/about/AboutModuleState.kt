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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


/**
 * Destinations used in the About module.
 */
object AboutDestinations {
    const val HomeRoute = "about"
    const val MainScreenRoute = "about/main"
}

/**
 * Remembers and creates an instance of [AboutModuleState]
 */
@Composable
fun rememberAboutModuleState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    AboutModuleState(navController)
}

class AboutModuleState(
    val navController: NavHostController,
)
