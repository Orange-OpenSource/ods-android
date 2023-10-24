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

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.ComponentsNavigation
import com.orange.ods.app.ui.components.ComponentsNavigation.ComponentDemoRoute
import com.orange.ods.app.ui.components.ComponentsNavigation.ComponentDetailRoute
import com.orange.ods.app.ui.components.ComponentsNavigation.ComponentVariantDemoRoute
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.guidelines.GuidelinesNavigation
import com.orange.ods.app.ui.utilities.UiString
import com.orange.ods.compose.component.content.OdsComponentContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Returns the [Screen] corresponding to the given [route].
 */
fun getScreen(route: String?, args: Bundle?): Screen? = route?.let {
    val matchElementRouteResult = Regex("^(.+)/\\{.+\\}$").find(route)
    if (matchElementRouteResult != null) {
        // Specific element route -> get element id
        val (routeRoot) = matchElementRouteResult.destructured
        when (routeRoot) {
            ComponentDetailRoute, ComponentDemoRoute -> Screen.Component(args?.getLong(ComponentsNavigation.ComponentIdKey))
            ComponentVariantDemoRoute -> Screen.ComponentVariant(args?.getLong(ComponentsNavigation.ComponentVariantIdKey))
            else -> null
        }
    } else {
        // Simple route
        val screens = Screen::class.sealedSubclasses.mapNotNull { it.objectInstance }
        screens.firstOrNull { screen -> screen.route == route }
    }
}

/**
 * Defines application common changing elements for each screen.
 * It allows to manage top app bar display according to the screen.
 */
sealed class Screen(
    val route: String,
    val isLargeAppBar: Boolean = false,
    val title: UiString? = null,
    val type: ScreenType = ScreenType.Default,
    val overflowMenuActions: List<AppBarOverflowMenuAction> = emptyList()
) {

    companion object {
        private val _appBarActionClicked = MutableSharedFlow<AppBarAction>(extraBufferCapacity = 1)
        val appBarActionClicked: Flow<AppBarAction> = _appBarActionClicked.asSharedFlow()
    }

    @Composable
    fun getAppBarActions(onSearchedTextChange: (TextFieldValue) -> Unit): List<OdsComponentContent<Nothing>> = when (type) {
        ScreenType.Home -> getHomeActions { action -> _appBarActionClicked.tryEmit(action) }
        ScreenType.Search -> listOf(getSearchFieldAction(onSearchedTextChange))
        ScreenType.WithCustomizableTopAppBar, ScreenType.Default -> getAlwaysVisibleActions { action -> _appBarActionClicked.tryEmit(action) }
    }

    // Bottom navigation screens

    data object Guidelines : Screen(
        route = BottomBarItem.Guidelines.route,
        title = UiString.StringResource(R.string.navigation_item_guidelines),
        type = ScreenType.Home
    )

    data object Components : Screen(
        route = BottomBarItem.Components.route,
        title = UiString.StringResource(R.string.navigation_item_components),
        type = ScreenType.Home
    )

    data object Modules : Screen(
        route = BottomBarItem.Modules.route,
        title = UiString.StringResource(R.string.navigation_item_modules),
        type = ScreenType.Home
    )

    data object About : Screen(
        route = BottomBarItem.About.route,
        title = UiString.StringResource(R.string.navigation_item_about),
        type = ScreenType.Home
    )

    // Guideline screens

    data object GuidelineColor : Screen(
        route = GuidelinesNavigation.GuidelineColor,
        title = UiString.StringResource(R.string.guideline_color),
    )

    data object GuidelineTypography : Screen(
        route = GuidelinesNavigation.GuidelineTypography,
        title = UiString.StringResource(R.string.guideline_typography),
    )

    data object GuidelineSpacing : Screen(
        route = GuidelinesNavigation.GuidelineSpacing,
        title = UiString.StringResource(R.string.guideline_spacing),
    )

    // Components screens

    data class Component(val componentId: Long?) : Screen(
        route = ComponentDetailRoute,
        title = com.orange.ods.app.ui.components.Component.fromId(componentId)?.titleRes?.let { UiString.StringResource(it) }
    )

    data class ComponentVariant(val variantId: Long?) : Screen(
        route = ComponentVariantDemoRoute,
        title = Variant.fromId(variantId)?.titleRes?.let { UiString.StringResource(it) },
        isLargeAppBar = Variant.fromId(variantId)?.largeTopAppBar == true,
        type = if (Variant.fromId(variantId)?.customizableTopAppBar == true) ScreenType.WithCustomizableTopAppBar else ScreenType.Default
    )

    // Search screen

    data object Search : Screen(
        route = MainNavigation.SearchRoute,
        type = ScreenType.Search
    )

}

enum class ScreenType {
    Home, Search, WithCustomizableTopAppBar, Default
}