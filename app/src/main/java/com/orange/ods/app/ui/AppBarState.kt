/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.AppBarAction.Companion.defaultActions
import com.orange.ods.app.ui.components.appbars.top.TopAppBarCustomizationState
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.extension.orElse
import kotlin.math.max

val LocalAppBarManager = staticCompositionLocalOf<AppBarManager> { error("CompositionLocal AppBarManager not present") }

interface AppBarManager {
    var searchedText: TextFieldValue

    fun setCustomAppBar(customAppBarConfiguration: CustomAppBarConfiguration)

    fun updateAppBarTabs(tabsConfiguration: TabsConfiguration)
    fun clearAppBarTabs()
}

/**
 * AppBar state source of truth.
 *
 * The app bar is managed according to the [Screen] displayed except if it has a custom app bar or if the screen cannot be found in the app [Screen]s (for
 * example an about module screen). In this case, the app bar is displayed according to the provided [CustomAppBarConfiguration].
 */
class AppBarState(
    private val navigationState: AppNavigationState,
    searchedText: MutableState<TextFieldValue>,
    private val customAppBarConfiguration: MutableState<CustomAppBarConfiguration>,
    val tabsState: AppBarTabsState
) : AppBarManager {

    private val isCustom: Boolean
        @Composable get() = navigationState.currentScreen?.hasCustomAppBar != false

    private val showNavigationIcon: Boolean
        @Composable get() = (isCustom && customAppBarConfiguration.value.isNavigationIconEnabled)
                || (!isCustom && navigationState.currentScreen?.isHome(navigationState.previousRoute) == false)

    val type: Screen.TopAppBarType
        @Composable get() = navigationState.currentScreen?.topAppBarType.orElse { Screen.TopAppBarType.Default }

    val title: String
        @Composable get() = if (isCustom) {
            customAppBarConfiguration.value.title
        } else {
            navigationState.currentScreen?.title?.asString().orEmpty()
        }

    val actions: List<OdsTopAppBar.ActionButton>
        @Composable get() {
            val screenAppBarActions = navigationState.currentScreen?.getAppBarActions(navigationState.previousRoute).orEmpty()
            return if (isCustom) {
                val context = LocalContext.current
                val customActionCount = max(0, customAppBarConfiguration.value.actionCount - AppBarAction.defaultActions.size)
                val customActions = NavigationItem.entries
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

    override var searchedText by searchedText

    override fun setCustomAppBar(customAppBarConfiguration: CustomAppBarConfiguration) {
        this.customAppBarConfiguration.value = customAppBarConfiguration
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
    navigationState: AppNavigationState,
    searchedText: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue("")) },
    customAppBarConfiguration: MutableState<CustomAppBarConfiguration> = remember { mutableStateOf(CustomAppBarConfiguration.Default) },
    tabsState: AppBarTabsState = rememberAppBarTabsState()
) = remember(navigationState, searchedText, customAppBarConfiguration, tabsState) {
    AppBarState(navigationState, searchedText, customAppBarConfiguration, tabsState)
}

data class CustomAppBarConfiguration constructor(
    val title: String,
    val actionCount: Int,
    val isNavigationIconEnabled: Boolean = true,
    val isLarge: Boolean = false,
    val scrollBehavior: TopAppBarCustomizationState.ScrollBehavior = TopAppBarCustomizationState.ScrollBehavior.Collapsible,
    val isOverflowMenuEnabled: Boolean = false
) {
    companion object {
        val Default = CustomAppBarConfiguration(
            title = "",
            actionCount = defaultActions.size,
        )
    }
}