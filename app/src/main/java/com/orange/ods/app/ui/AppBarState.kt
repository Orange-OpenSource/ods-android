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
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarActionButtonBuilder
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarNavigationIconBuilder
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarOverflowMenuActionItemBuilder
import com.orange.ods.compose.component.content.OdsComponentBuilder
import com.orange.ods.extension.orElse
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
 * The app bar is managed according to the [Screen] displayed except when its type is [ScreenType.WithCustomizableTopAppBar]. In this case, the app bar is
 * displayed according to the provided [AppBarConfiguration].
 */
class AppBarState(
    private val navController: NavController,
    private val searchText: MutableState<TextFieldValue>,
    private val customAppBarConfiguration: MutableState<AppBarConfiguration>,
    val tabsState: AppBarTabsState
) : AppBarManager {

    companion object {
        val CustomDefaultConfiguration = AppBarConfiguration(
            isLarge = false,
            largeTitleRes = R.string.empty,
            scrollBehavior = TopAppBarCustomizationState.ScrollBehavior.Collapsible,
            isNavigationIconEnabled = true,
            actionCount = defaultActions.size,
            isOverflowMenuEnabled = false
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
        @Composable get() = currentScreen?.hasCustomAppBar.orElse { false }

    private val showNavigationIcon: Boolean
        @Composable get() = (isCustom && customAppBarConfiguration.value.isNavigationIconEnabled)
                || (!isCustom && currentScreen?.isHome == false)

    val isLarge: Boolean
        @Composable get() = currentScreen?.isLargeAppBar == true

    val title: String
        @Composable get() = if (isCustom) {
            stringResource(id = customAppBarConfiguration.value.largeTitleRes)
        } else {
            currentScreen?.title?.asString().orEmpty()
        }

    val actions: List<OdsComponentBuilder<Nothing>>
        @Composable get() {
            val screenAppBarActions = currentScreen?.getAppBarActions { searchText.value = it }.orEmpty()
            return if (isCustom) {
                val context = LocalContext.current
                val customActionCount = max(0, customAppBarConfiguration.value.actionCount - AppBarAction.defaultActions.size)
                val customActions = NavigationItem.values()
                    .take(customActionCount)
                    .map {
                        val contentDescription = stringResource(id = it.textResId)
                        OdsTopAppBarActionButtonBuilder(painter = painterResource(id = it.iconResId), contentDescription = contentDescription) {
                            clickOnElement(context, contentDescription)
                        }
                    }
                screenAppBarActions.take(customAppBarConfiguration.value.actionCount) + customActions
            } else {
                screenAppBarActions
            }
        }

    val overflowMenuActions: List<OdsTopAppBarOverflowMenuActionItemBuilder>
        @Composable get() = if (isCustom && customAppBarConfiguration.value.isOverflowMenuEnabled) {
            val context = LocalContext.current
            LocalRecipes.current.map { recipe ->
                OdsTopAppBarOverflowMenuActionItemBuilder(
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
        OdsTopAppBarNavigationIconBuilder(Icons.Filled.ArrowBack, stringResource(id = R.string.top_app_bar_back_icon_desc), upPress)
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
    val isLarge: Boolean,
    val largeTitleRes: Int,
    val scrollBehavior: TopAppBarCustomizationState.ScrollBehavior,
    val isNavigationIconEnabled: Boolean,
    val actionCount: Int,
    val isOverflowMenuEnabled: Boolean
)