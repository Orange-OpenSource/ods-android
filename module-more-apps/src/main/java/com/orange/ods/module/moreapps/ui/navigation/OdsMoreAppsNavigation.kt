/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.module.moreapps.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.orange.ods.module.moreapps.ui.OdsMoreAppsScreen
import com.orange.ods.module.moreapps.ui.configuration.OdsMoreAppsConfiguration

object OdsMoreAppsDestinations {
    const val MoreAppsRoute = "ods/module/moreApps/"
}

/**
 * Add this graph to your app in order to integrate the ODS More apps module.
 */
fun NavGraphBuilder.odsMoreAppsGraph(configuration: () -> OdsMoreAppsConfiguration) {
    composable(route = OdsMoreAppsDestinations.MoreAppsRoute) { _ ->
        OdsMoreAppsScreen(configuration())
    }
}