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

package com.orange.ods.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.AppBarAction.Companion.defaultActions
import com.orange.ods.app.ui.utilities.extension.isDarkModeEnabled
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar

enum class AppBarAction {
    Search, ChangeTheme, ChangeMode;

    companion object {
        val defaultActions = listOf(ChangeTheme, ChangeMode)
    }

    @Composable
    fun getOdsTopAppBarAction(onActionClick: (AppBarAction) -> Unit) = when (this) {
        ChangeTheme -> getChangeThemeAction(onActionClick)
        ChangeMode -> getChangeModeAction(onActionClick)
        Search -> getSearchAction(onActionClick)
    }
}

@Composable
fun getDefaultActions(onActionClick: (AppBarAction) -> Unit): List<OdsTopAppBar.ActionButton> =
    defaultActions.map { it.getOdsTopAppBarAction(onActionClick = onActionClick) }

@Composable
fun getHomeActions(onActionClick: (AppBarAction) -> Unit): List<OdsTopAppBar.ActionButton> =
    listOf(getSearchAction(onActionClick)) + getDefaultActions(onActionClick = onActionClick)

@Composable
private fun getSearchAction(onClick: (AppBarAction) -> Unit) = OdsTopAppBar.ActionButton(
    onClick = { onClick(AppBarAction.Search) },
    painter = painterResource(id = R.drawable.ic_search),
    contentDescription = stringResource(id = R.string.search_content_description)
)

@Composable
private fun getChangeThemeAction(onClick: (AppBarAction) -> Unit) = OdsTopAppBar.ActionButton(
    onClick = { onClick(AppBarAction.ChangeTheme) },
    painter = painterResource(id = R.drawable.ic_palette),
    contentDescription = stringResource(id = R.string.top_app_bar_action_change_theme_desc)
)

@Composable
private fun getChangeModeAction(onClick: (AppBarAction) -> Unit): OdsTopAppBar.ActionButton {
    val configuration = LocalConfiguration.current

    val painterRes = if (configuration.isDarkModeEnabled) R.drawable.ic_ui_light_mode else R.drawable.ic_ui_dark_mode
    val iconDesc =
        if (configuration.isDarkModeEnabled) R.string.top_app_bar_action_change_mode_to_light_desc else R.string.top_app_bar_action_change_mode_to_dark_desc

    return OdsTopAppBar.ActionButton(
        onClick = { onClick(AppBarAction.ChangeMode) },
        painter = painterResource(id = painterRes),
        contentDescription = stringResource(id = iconDesc)
    )
}
