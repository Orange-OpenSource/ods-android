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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ods.app.R

val LocalMainTopAppBarManager = staticCompositionLocalOf<MainTopAppBarManager> { error("CompositionLocal LocalMainTopAppBarManager not present") }

interface MainTopAppBarManager {

    fun updateTopAppBar(topAppBarConfiguration: TopAppBarConfiguration)

    fun updateTopAppBarTitle(titleRes: Int)
}

@Composable
fun rememberMainTopAppBarState(
    titleRes: MutableState<Int> = rememberSaveable { mutableStateOf(R.string.navigation_item_guidelines) },
    actionCount: MutableState<Int> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.actionCount) },
    navigationIconEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.isNavigationIconEnabled) },
    overflowMenuEnabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(MainTopAppBarState.DefaultConfiguration.isOverflowMenuEnabled) }
) =
    remember(titleRes, actionCount, navigationIconEnabled, overflowMenuEnabled) {
        MainTopAppBarState(titleRes, actionCount, navigationIconEnabled, overflowMenuEnabled)
    }

class MainTopAppBarState(
    val titleRes: MutableState<Int>,
    val actionCount: MutableState<Int>,
    private val navigationIconEnabled: MutableState<Boolean>,
    private val overflowMenuEnabled: MutableState<Boolean>
) : MainTopAppBarManager {

    companion object {
        val DefaultConfiguration = TopAppBarConfiguration(
            isNavigationIconEnabled = true,
            actionCount = 3,
            isOverflowMenuEnabled = false
        )
    }

    // ----------------------------------------------------------
    // TopAppBar state source of truth
    // ----------------------------------------------------------

    val isNavigationIconEnabled: Boolean
        get() = navigationIconEnabled.value

    val isOverflowMenuEnabled: Boolean
        get() = overflowMenuEnabled.value

    override fun updateTopAppBar(topAppBarConfiguration: TopAppBarConfiguration) {
        navigationIconEnabled.value = topAppBarConfiguration.isNavigationIconEnabled
        actionCount.value = topAppBarConfiguration.actionCount
        overflowMenuEnabled.value = topAppBarConfiguration.isOverflowMenuEnabled
    }

    override fun updateTopAppBarTitle(titleRes: Int) {
        this.titleRes.value = titleRes
    }

}

data class TopAppBarConfiguration constructor(
    val isNavigationIconEnabled: Boolean,
    val actionCount: Int,
    val isOverflowMenuEnabled: Boolean
)
