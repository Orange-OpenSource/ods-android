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

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.orange.ods.app.ui.about.addAboutGraph
import com.orange.ods.app.ui.components.addComponentsGraph
import com.orange.ods.app.ui.guidelines.addGuidelinesGraph
import com.orange.ods.app.ui.search.SearchScreen

/**
 * Destinations used in the [MainScreen].
 */
object MainDestinations {
    const val HomeRoute = "home"
    const val SearchRoute = "search"
}

fun NavGraphBuilder.appNavGraph(
    navigateToElement: (String, Long?, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    searchedText: MutableState<TextFieldValue>
) {
    navigation(
        route = MainDestinations.HomeRoute,
        startDestination = BottomNavigationSection.Guidelines.route
    ) {
        addBottomNavigationGraph(navigateToElement)
    }

    addGuidelinesGraph()
    addComponentsGraph(navigateToElement, upPress)
    addAboutGraph()

    composable(
        route = MainDestinations.SearchRoute
    ) { from ->
        SearchScreen(
            searchedText,
            onResultItemClick = { route, id -> navigateToElement(route, id, from) }
        )
    }
}
