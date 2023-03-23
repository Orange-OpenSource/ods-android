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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ods.app.R

val LocalMainTopAppBarManager = staticCompositionLocalOf<MainTopAppBarManager> { error("CompositionLocal LocalMainTopAppBarManager not present") }

interface MainTopAppBarManager {
    var titleResId: Int
    var searchedTextState: MutableState<TextFieldValue>
    val overflowMenuEnabled: Boolean
    val actionCount: Int
    var extended: Boolean
    val navigationIconEnabled: Boolean

    fun setConfiguration(configuration: TopAppBarConfiguration)

    fun setTabs(tabsConfiguration: MainTabsConfiguration)

    /**
     * Reset top app bar to default display
     */
    fun reset()
}

@Composable
fun rememberMainTopAppBarState(
    titleResIdState: MutableState<Int> = rememberSaveable { mutableStateOf(R.string.navigation_item_guidelines) },
    actionCountState: MutableState<Int> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.actionCount) },
    navigationIconState: MutableState<Boolean> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.isNavigationIconEnabled) },
    overflowMenuState: MutableState<Boolean> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.isOverflowMenuEnabled) },
    searchedTextState: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue("")) },
    extendedState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    tabsState: MainTabsState = rememberMainTabsState()
) =
    remember(titleResIdState, searchedTextState, actionCountState, navigationIconState, overflowMenuState, extendedState, tabsState) {
        MainTopAppBarState(titleResIdState, searchedTextState, actionCountState, navigationIconState, overflowMenuState, extendedState, tabsState)
    }


class MainTopAppBarState(
    titleResIdState: MutableState<Int>,
    override var searchedTextState: MutableState<TextFieldValue>,
    private val actionCountState: MutableState<Int>,
    private val navigationIconState: MutableState<Boolean>,
    private val overflowMenuState: MutableState<Boolean>,
    private var extendedState: MutableState<Boolean>,
    val tabsState: MainTabsState
) : MainTopAppBarManager {

    companion object {
        val DefaultConfiguration = TopAppBarConfiguration(
            isNavigationIconEnabled = true,
            actionCount = 2,
            isOverflowMenuEnabled = false
        )
    }

    // ----------------------------------------------------------
    // TopAppBar state source of truth
    // ----------------------------------------------------------

    override var titleResId by titleResIdState
    override val overflowMenuEnabled by overflowMenuState
    override val actionCount by actionCountState
    override var extended by extendedState
    override val navigationIconEnabled by navigationIconState

    override fun setConfiguration(configuration: TopAppBarConfiguration) {
        navigationIconState.value = configuration.isNavigationIconEnabled
        actionCountState.value = configuration.actionCount
        overflowMenuState.value = configuration.isOverflowMenuEnabled
    }

    override fun setTabs(tabsConfiguration: MainTabsConfiguration) {
        tabsState.updateTopAppBarTabs(tabsConfiguration)
    }

    override fun reset() {
        extended = false
        tabsState.clearTopAppBarTabs()
        setConfiguration(DefaultConfiguration)
    }
}

data class TopAppBarConfiguration constructor(
    val isNavigationIconEnabled: Boolean,
    val actionCount: Int,
    val isOverflowMenuEnabled: Boolean
)
