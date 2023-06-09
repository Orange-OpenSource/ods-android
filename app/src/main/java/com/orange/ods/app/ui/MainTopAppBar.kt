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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.extension.isDarkModeEnabled
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarActionButton
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarOverflowMenuBox
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsRadioButtonTrailing
import com.orange.ods.compose.component.menu.OdsDropdownMenuItem
import com.orange.ods.compose.component.textfield.search.OdsSearchTextField
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun MainTopAppBar(
    shouldShowUpNavigationIcon: Boolean,
    topAppBarState: MainTopAppBarState,
    upPress: () -> Unit,
    onSearchActionClick: () -> Unit,
    mainViewModel: MainViewModel = viewModel()
) {
    val themeManager = LocalThemeManager.current
    var changeThemeDialogVisible by remember { mutableStateOf(false) }

    OdsTopAppBar(
        title = stringResource(id = topAppBarState.titleRes.value),
        navigationIcon = if (shouldShowUpNavigationIcon && topAppBarState.isNavigationIconEnabled) {
            {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.top_app_bar_back_icon_desc)
                )
            }
        } else null,
        onNavigationIconClick = upPress,
        actions = {
            if (topAppBarState.titleRes.value == R.string.navigation_item_search) {
                val focusRequester = remember { FocusRequester() }
                OdsSearchTextField(
                    value = topAppBarState.searchedText.value,
                    onValueChange = { value ->
                        topAppBarState.searchedText.value = value
                    },
                    placeholder = stringResource(id = R.string.search_text_field_hint),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
            } else {
                TopAppBarActions(topAppBarState, onSearchActionClick) { changeThemeDialogVisible = true }
            }
        },
        elevated = false // elevation is managed in [MainScreen] cause of tabs
    )

    if (changeThemeDialogVisible) {
        ChangeThemeDialog(
            themeManager = themeManager,
            dismissDialog = {
                changeThemeDialogVisible = false
            },
            onThemeSelected = {
                mainViewModel.storeUserThemeName(themeManager.currentThemeConfiguration.name)
            }
        )
    }

}

@Composable
private fun TopAppBarActions(state: MainTopAppBarState, onSearchActionClick: () -> Unit, onChangeThemeActionClick: () -> Unit) {
    state.actions.value.forEach { action ->
        when (action) {
            TopAppBarConfiguration.Action.Search -> TopAppBarSearchActionButton(onClick = onSearchActionClick)
            TopAppBarConfiguration.Action.Theme -> TopAppBarChangeThemeActionButton(onClick = onChangeThemeActionClick)
            TopAppBarConfiguration.Action.Mode -> TopAppBarChangeModeActionButton()
            TopAppBarConfiguration.Action.OverflowMenu -> TopAppBarOverflowMenuBox()
            is TopAppBarConfiguration.Action.Custom -> TopAppBarCustomActionButton(action = action)
        }
    }
}

@Composable
private fun TopAppBarChangeThemeActionButton(onClick: () -> Unit) {
    OdsTopAppBarActionButton(
        onClick = onClick,
        painter = painterResource(id = R.drawable.ic_palette),
        contentDescription = stringResource(id = R.string.top_app_bar_action_change_mode_to_dark_desc)
    )
}

@Composable
private fun TopAppBarChangeModeActionButton() {
    val configuration = LocalConfiguration.current
    val mainThemeManager = LocalThemeManager.current

    val painterRes = if (configuration.isDarkModeEnabled) R.drawable.ic_ui_light_mode else R.drawable.ic_ui_dark_mode
    val iconDesc =
        if (configuration.isDarkModeEnabled) R.string.top_app_bar_action_change_mode_to_light_desc else R.string.top_app_bar_action_change_mode_to_dark_desc

    OdsTopAppBarActionButton(
        onClick = { mainThemeManager.darkModeEnabled = !configuration.isDarkModeEnabled },
        painter = painterResource(id = painterRes),
        contentDescription = stringResource(id = iconDesc)
    )
}

@Composable
private fun TopAppBarSearchActionButton(onClick: () -> Unit) {
    OdsTopAppBarActionButton(
        onClick = onClick,
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = stringResource(id = R.string.search_content_description)
    )
}

@Composable
private fun TopAppBarOverflowMenuBox() {
    val context = LocalContext.current
    OdsTopAppBarOverflowMenuBox(
        overflowIconContentDescription = stringResource(id = R.string.component_app_bars_top_element_overflow_menu)
    ) {
        LocalRecipes.current.forEach { recipe ->
            OdsDropdownMenuItem(
                text = recipe.title,
                onClick = { clickOnElement(context, recipe.title) }
            )
        }
    }
}

@Composable
private fun TopAppBarCustomActionButton(action: TopAppBarConfiguration.Action.Custom) {
    val context = LocalContext.current
    OdsTopAppBarActionButton(
        onClick = { clickOnElement(context, action.name) },
        painter = painterResource(id = action.iconResId),
        contentDescription = action.name
    )
}

@Composable
private fun ChangeThemeDialog(themeManager: ThemeManager, dismissDialog: () -> Unit, onThemeSelected: () -> Unit) {
    val selectedRadio = rememberSaveable { mutableStateOf(themeManager.currentThemeConfiguration.name) }

    Dialog(onDismissRequest = dismissDialog) {
        Column(modifier = Modifier.background(OdsTheme.colors.surface)) {
            OdsTextH6(
                text = stringResource(R.string.top_app_bar_action_change_theme_desc),
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.spacing_m), bottom = dimensionResource(id = R.dimen.spacing_s))
                    .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
            )
            themeManager.themeConfigurations.forEach { themeConfiguration ->
                OdsListItem(
                    text = themeConfiguration.name,
                    trailing = OdsRadioButtonTrailing(
                        selectedRadio = selectedRadio,
                        currentRadio = themeConfiguration.name,
                        onClick = {
                            if (themeConfiguration != themeManager.currentThemeConfiguration) {
                                themeManager.currentThemeConfiguration = themeConfiguration
                                onThemeSelected()
                            }
                            dismissDialog()
                        }
                    )
                )
            }
        }
    }
}