/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.extension.isDarkModeEnabled

@Composable
fun OdsDemoTopAppBar(
    titleRes: Int,
    shouldShowUpNavigationIcon: Boolean,
    state: OdsDemoTopAppBarState,
    upPress: () -> Unit,
    updateTheme: (Boolean) -> Unit
) {
    OdsTopAppBar(
        title = stringResource(id = titleRes),
        navigationIcon = if (shouldShowUpNavigationIcon && state.isNavigationIconEnabled) {
            {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_icon_content_description)
                )
            }
        } else null,
        onNavigationIconClick = upPress,
        actions = {
            val context = LocalContext.current
            repeat(state.actionCount.value) { index ->
                if (index == 0) {
                    val configuration = LocalConfiguration.current
                    IconButton(onClick = {
                        updateTheme(!configuration.isDarkModeEnabled)
                    }) {
                        if (configuration.isDarkModeEnabled) {
                            ActionIcon(iconRes = R.drawable.ic_ui_light_mode, contentDescriptionRes = R.string.theme_changer_icon_content_description_light)
                        } else {
                            ActionIcon(iconRes = R.drawable.ic_ui_dark_mode, contentDescriptionRes = R.string.theme_changer_icon_content_description_dark)
                        }
                    }
                } else {
                    val action = appBarTopDemoActions[index - 1]
                    IconButton(onClick = { clickOnElement(context, context.getString(action.titleRes)) }) {
                        ActionIcon(iconRes = action.iconRes, contentDescriptionRes = action.titleRes)
                    }
                }
            }
            if (state.isOverflowMenuEnabled) {
                OverflowMenu()
            }
        },
        elevated = false // elevation is managed in [OdsDemoApp] cause of tabs
    )
}

@Composable
private fun OverflowMenu() {
    var showMenu by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box {
        IconButton(onClick = { showMenu = !showMenu }) {
            Icon(Icons.Filled.MoreVert, contentDescription = stringResource(id = R.string.component_app_bars_top_element_overflow_menu))
        }
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            appBarTopDemoOverflowActions.forEach {
                DropdownMenuItem(onClick = { clickOnElement(context, context.getString(it.titleRes)) }) {
                    OdsTextBody1(text = stringResource(id = it.titleRes))
                }
            }
        }
    }

}

@Composable
private fun ActionIcon(@DrawableRes iconRes: Int, @StringRes contentDescriptionRes: Int) {
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = stringResource(id = contentDescriptionRes)
    )
}

private val appBarTopDemoActions = listOf(
    AppBarTopAction(R.drawable.ic_heart, R.string.component_app_bars_top_action_favourites),
    AppBarTopAction(R.drawable.ic_alert, R.string.component_app_bars_top_action_alerts),
)

private val appBarTopDemoOverflowActions = listOf(
    AppBarTopAction(R.drawable.ic_account, R.string.component_app_bars_top_action_account),
    AppBarTopAction(R.drawable.ic_settings, R.string.component_app_bars_top_action_settings)
)

private data class AppBarTopAction(@DrawableRes val iconRes: Int, @StringRes val titleRes: Int)