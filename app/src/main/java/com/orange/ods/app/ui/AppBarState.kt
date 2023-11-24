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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.AppBarAction.Companion.defaultActions
import com.orange.ods.app.ui.AppBarState.Companion.CustomDefaultConfiguration
import com.orange.ods.app.ui.components.appbars.top.TopAppBarCustomizationState
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import kotlin.math.max

val LocalAppBarManager = staticCompositionLocalOf<AppBarManager> { error("CompositionLocal AppBarManager not present") }

interface AppBarManager {
    val searchedText: TextFieldValue

    fun setCustomAppBar(appBarConfiguration: AppBarConfiguration)

    fun updateAppBarTabs(tabsConfiguration: TabsConfiguration)
    fun clearAppBarTabs()
}

/**
 * AppBar state source of truth.
 *
 * The app bar is managed according to the [Screen] displayed except if it has a custom app bar or if the screen cannot be find in the app [Screen]s (for 
 * example an about module screen). In this case, the app bar is displayed according to the provided [AppBarConfiguration].
 */
class AppBarState(
    private val navController: NavController,
    private val searchText: MutableState<TextFieldValue>,
    private val customAppBarConfiguration: MutableState<AppBarConfiguration>,
    val tabsState: AppBarTabsState
) : AppBarManager {

    companion object {
        val CustomDefaultConfiguration = AppBarConfiguration(
            title = "",
            actionCount = defaultActions.size,
        )
    }

    private val currentBackStackEntry: NavBackStackEntry?
        @Composable get() = navController.currentBackStackEntryAsState().value

    private val currentScreenRoute: String?
        @Composable get() = currentBackStackEntry?.destination?.route

    private val currentScreenRouteArgs: Bundle?
        @Composable get() = currentBackStackEntry?.arguments

    private val currentScreen: Screen?
        @Composable get() = currentScreenRoute?.let { getScreen(it, currentScreenRouteArgs) }

    private val isCustom: Boolean
        @Composable get() = currentScreen?.hasCustomAppBar != false

    private val showNavigationIcon: Boolean
        @Composable get() = (isCustom && customAppBarConfiguration.value.isNavigationIconEnabled)
                    || (!isCustom && currentScreen?.isHome == false)

    val isLarge: Boolean
        @Composable get() = currentScreen?.isLargeAppBar == true

    val title: String
        @Composable get() = if (isCustom) {
            customAppBarConfiguration.value.title
        } else {
            currentScreen?.title?.asString().orEmpty()
        }

    val actions: List<OdsComponentContent<Nothing>>
        @Composable get() {
            val screenAppBarActions = currentScreen?.getAppBarActions { searchText.value = it }.orEmpty()
            return if (isCustom) {
                val context = LocalContext.current
                val customActionCount = max(0, customAppBarConfiguration.value.actionCount - AppBarAction.defaultActions.size)
                val customActions = NavigationItem.values()
                    .take(customActionCount)
                    .map {
                        val contentDescription = stringResource(id = it.textResId)
                        OdsTopAppBar.ActionButton(painter = painterResource(id = it.iconResId), contentDescription = contentDescription) {
                            clickOnElement(context, contentDescription)
                        }
                    }
                screenAppBarActions.take(customAppBarConfiguration.value.actionCount) + customActions
            } else {
                screenAppBarActions
            }
        }

    val overflowMenuItems: List<OdsDropdownMenu.Item>
        @Composable get() = if (isCustom && customAppBarConfiguration.value.isOverflowMenuEnabled) {
            val context = LocalContext.current
            LocalRecipes.current.map { recipe ->
                OdsDropdownMenu.Item(
                    text = recipe.title,
                    onClick = { clickOnElement(context, recipe.title) }
                )
            }
        } else {
            emptyList()
        }

    val hasScrollBehavior: Boolean
        get() = customAppBarConfiguration.value.scrollBehavior != TopAppBarCustomizationState.ScrollBehavior.None

    @Composable
    fun getNavigationIcon(upPress: () -> Unit) = if (showNavigationIcon) {
        OdsTopAppBar.NavigationIcon(Icons.Filled.ArrowBack, stringResource(id = R.string.top_app_bar_back_icon_desc), upPress)
    } else {
        null
    }

    // ----------------------------------------------------------
    // AppBarManager implementation
    // ----------------------------------------------------------

    override val searchedText: TextFieldValue
        get() = searchText.value

    override fun setCustomAppBar(appBarConfiguration: AppBarConfiguration) {
        customAppBarConfiguration.value = appBarConfiguration
    }

    override fun updateAppBarTabs(tabsConfiguration: TabsConfiguration) {
        tabsState.updateAppBarTabs(tabsConfiguration)
    }

    override fun clearAppBarTabs() {
        tabsState.clearAppBarTabs()
    }

}

@Composable
fun rememberAppBarState(
    navController: NavController,
    searchedText: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue("")) },
    customAppBarConfiguration: MutableState<AppBarConfiguration> = remember { mutableStateOf(CustomDefaultConfiguration) },
    tabsState: AppBarTabsState = rememberAppBarTabsState()
) = remember(navController, searchedText, customAppBarConfiguration, tabsState) {
    AppBarState(navController, searchedText, customAppBarConfiguration, tabsState)
}

data class AppBarConfiguration constructor(
    val title: String,
    val isNavigationIconEnabled: Boolean = true,
    val isLarge: Boolean = false,
    val scrollBehavior: TopAppBarCustomizationState.ScrollBehavior =  TopAppBarCustomizationState.ScrollBehavior.Collapsible,
    val actionCount: Int = 0,
    val isOverflowMenuEnabled: Boolean = false
)