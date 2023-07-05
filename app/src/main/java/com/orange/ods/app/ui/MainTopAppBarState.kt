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

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.appbars.top.TopAppBarCustomizationState
import kotlinx.parcelize.Parcelize

val LocalMainTopAppBarManager = staticCompositionLocalOf<MainTopAppBarManager> { error("CompositionLocal LocalMainTopAppBarManager not present") }

interface MainTopAppBarManager {
    fun updateTopAppBar(topAppBarConfiguration: TopAppBarConfiguration)
    fun updateTopAppBarTitle(titleRes: Int)

    /** If set to true, a large top app bar will be displayed **/
    fun setLargeTopAppBar(value: Boolean)

    fun updateTopAppBarTabs(tabsConfiguration: TabsConfiguration)
    fun clearTopAppBarTabs()
}

@Composable
fun rememberMainTopAppBarState(
    titleRes: MutableState<Int> = rememberSaveable { mutableStateOf(R.string.navigation_item_guidelines) },
    actions: MutableState<List<TopAppBarConfiguration.Action>> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.actions) },
    navigationIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.isNavigationIconEnabled) },
    searchedText: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue("")) },
    large: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    scrollBehavior: MutableState<TopAppBarCustomizationState.ScrollBehavior> = rememberSaveable { mutableStateOf(TopAppBarCustomizationState.ScrollBehavior.Collapsible) },
    tabsState: MainTabsState = rememberMainTabsState(),
) =
    remember(titleRes, actions, searchedText, navigationIconEnabled, large, scrollBehavior, tabsState) {
        MainTopAppBarState(titleRes, actions, searchedText, navigationIconEnabled, large, scrollBehavior, tabsState)
    }


class MainTopAppBarState(
    val titleRes: MutableState<Int>,
    val actions: MutableState<List<TopAppBarConfiguration.Action>>,
    var searchedText: MutableState<TextFieldValue>,
    private val navigationIconEnabled: MutableState<Boolean>,
    private var large: MutableState<Boolean>,
    private var scrollBehavior: MutableState<TopAppBarCustomizationState.ScrollBehavior>,
    val tabsState: MainTabsState,
) : MainTopAppBarManager {

    companion object {
        val DefaultConfiguration = TopAppBarConfiguration(
            isLarge = false,
            scrollBehavior = TopAppBarCustomizationState.ScrollBehavior.Collapsible,
            isNavigationIconEnabled = true,
            actions = listOf(TopAppBarConfiguration.Action.Theme, TopAppBarConfiguration.Action.Mode)
        )
    }

    // ----------------------------------------------------------
    // TopAppBar state source of truth
    // ----------------------------------------------------------

    val isLarge: Boolean
        get() = large.value

    val hasScrollBehavior: Boolean
        get() = scrollBehavior.value != TopAppBarCustomizationState.ScrollBehavior.None

    val isNavigationIconEnabled: Boolean
        get() = navigationIconEnabled.value

    override fun setLargeTopAppBar(value: Boolean) {
        this.large.value = value
    }

    override fun updateTopAppBar(topAppBarConfiguration: TopAppBarConfiguration) {
        large.value = topAppBarConfiguration.isLarge
        scrollBehavior.value = topAppBarConfiguration.scrollBehavior
        navigationIconEnabled.value = topAppBarConfiguration.isNavigationIconEnabled
        actions.value = topAppBarConfiguration.actions
    }

    override fun updateTopAppBarTitle(titleRes: Int) {
        this.titleRes.value = titleRes
    }

    override fun updateTopAppBarTabs(tabsConfiguration: TabsConfiguration) {
        tabsState.updateTopAppBarTabs(tabsConfiguration)
    }

    override fun clearTopAppBarTabs() {
        tabsState.clearTopAppBarTabs()
    }
}

data class TopAppBarConfiguration constructor(
    val isLarge: Boolean,
    val scrollBehavior: TopAppBarCustomizationState.ScrollBehavior,
    val isNavigationIconEnabled: Boolean,
    val actions: List<Action>
) {

    sealed class Action : Parcelable {

        @Parcelize
        object Search : Action()

        @Parcelize
        object Theme : Action()

        @Parcelize
        object Mode : Action()

        @Parcelize
        object OverflowMenu : Action()

        @Parcelize
        class Custom(val name: String, @DrawableRes val iconResId: Int) : Action()
    }

    class Builder {

        private var isLarge = false
        private var scrollBehavior = TopAppBarCustomizationState.ScrollBehavior.Collapsible
        private var isNavigationIconEnabled = true
        private var actions = mutableListOf<Action>()

        fun large(value: Boolean) = apply { isLarge = value }

        fun scrollBehavior(value: TopAppBarCustomizationState.ScrollBehavior) = apply { scrollBehavior = value }

        fun navigationIconEnabled(enabled: Boolean) = apply { isNavigationIconEnabled = enabled }

        fun actions(builderAction: MutableList<Action>.() -> Unit) = apply { actions.builderAction() }

        fun prependAction(action: Action) = apply {
            actions.remove(action)
            actions { add(0, action) }
        }

        fun appendAction(action: Action) = apply {
            actions.remove(action)
            actions { add(action) }
        }

        fun build() = TopAppBarConfiguration(isLarge, scrollBehavior, isNavigationIconEnabled, actions)
    }

    fun newBuilder() = Builder().apply {
        navigationIconEnabled(isNavigationIconEnabled)
        large(isLarge)
        scrollBehavior(scrollBehavior)
        actions {
            clear()
            addAll(actions)
        }
    }
}
