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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.extension.isDarkModeEnabled
import com.orange.ods.compose.component.appbar.top.OdsLargeTopAppBarInternal
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarActionButton
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarInternal
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarNavigationIcon
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarOverflowMenuActionItem
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemTrailingRadioButton
import com.orange.ods.compose.component.textfield.search.OdsSearchTextField
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    shouldShowUpNavigationIcon: Boolean,
    topAppBarState: MainTopAppBarState,
    upPress: () -> Unit,
    onSearchActionClick: () -> Unit,
    mainViewModel: MainViewModel = viewModel(),
    scrollBehavior: TopAppBarScrollBehavior?
) {
    val themeManager = LocalThemeManager.current
    var changeThemeDialogVisible by remember { mutableStateOf(false) }
    val showSearchAction = topAppBarState.titleRes.value == R.string.navigation_item_search

    val title = stringResource(id = topAppBarState.titleRes.value)
    val navigationIcon = if (shouldShowUpNavigationIcon && topAppBarState.isNavigationIconEnabled) {
        OdsTopAppBarNavigationIcon(Icons.Filled.ArrowBack, stringResource(id = R.string.top_app_bar_back_icon_desc), upPress)
    } else {
        null
    }

    val actions = if (showSearchAction) {
        listOf(getTopAppBarSearchTextFieldAction(searchedText = topAppBarState.searchedText))
    } else {
        getTopAppBarActions(topAppBarState, onSearchActionClick) { changeThemeDialogVisible = true }
    }

    val overflowMenuActions = getTopAppBarOverflowMenuActions(topAppBarState)

    if (topAppBarState.isLarge) {
        OdsLargeTopAppBarInternal(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions,
            overflowMenuActions = overflowMenuActions,
            scrollBehavior = if (topAppBarState.hasScrollBehavior) scrollBehavior else null
        )
    } else {
        OdsTopAppBarInternal(
            title = title,
            navigationIcon = navigationIcon,
            actions = actions,
            overflowMenuActions = overflowMenuActions,
            elevated = false // elevation is managed in [MainScreen] cause of tabs
        )
    }

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
private fun getTopAppBarSearchTextFieldAction(searchedText: MutableState<TextFieldValue>): OdsComponentContent<Nothing> {
    return object : OdsComponentContent<Nothing>() {

        @Composable
        override fun Content(modifier: Modifier) {
            val focusRequester = remember { FocusRequester() }
            OdsSearchTextField(
                value = searchedText.value,
                onValueChange = { value ->
                    searchedText.value = value
                },
                placeholder = stringResource(id = R.string.search_text_field_hint),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }
    }
}

@Composable
private fun getTopAppBarActions(
    state: MainTopAppBarState,
    onSearchActionClick: () -> Unit,
    onChangeThemeActionClick: () -> Unit
): List<OdsTopAppBarActionButton> {
    return state.actions.value.mapNotNull { action ->
        when (action) {
            TopAppBarConfiguration.Action.Search -> getTopAppBarSearchAction(onClick = onSearchActionClick)
            TopAppBarConfiguration.Action.Theme -> getTopAppBarChangeThemeAction(onClick = onChangeThemeActionClick)
            TopAppBarConfiguration.Action.Mode -> getTopAppBarChangeModeAction()
            TopAppBarConfiguration.Action.OverflowMenu -> null
            is TopAppBarConfiguration.Action.Custom -> getTopAppBarCustomAction(action = action)
        }
    }
}

@Composable
private fun getTopAppBarChangeThemeAction(onClick: () -> Unit): OdsTopAppBarActionButton {
    return OdsTopAppBarActionButton(
        onClick = onClick,
        painter = painterResource(id = R.drawable.ic_palette),
        contentDescription = stringResource(id = R.string.top_app_bar_action_change_mode_to_dark_desc)
    )
}

@Composable
private fun getTopAppBarChangeModeAction(): OdsTopAppBarActionButton {
    val configuration = LocalConfiguration.current
    val themeManager = LocalThemeManager.current

    val painterRes = if (configuration.isDarkModeEnabled) R.drawable.ic_ui_light_mode else R.drawable.ic_ui_dark_mode
    val iconDesc =
        if (configuration.isDarkModeEnabled) R.string.top_app_bar_action_change_mode_to_light_desc else R.string.top_app_bar_action_change_mode_to_dark_desc

    return OdsTopAppBarActionButton(
        onClick = { themeManager.darkModeEnabled = !configuration.isDarkModeEnabled },
        painter = painterResource(id = painterRes),
        contentDescription = stringResource(id = iconDesc)
    )
}

@Composable
private fun getTopAppBarSearchAction(onClick: () -> Unit): OdsTopAppBarActionButton {
    return OdsTopAppBarActionButton(
        onClick = onClick,
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = stringResource(id = R.string.search_content_description)
    )
}

@Composable
private fun getTopAppBarOverflowMenuActions(state: MainTopAppBarState): List<OdsTopAppBarOverflowMenuActionItem> {
    return if (state.actions.value.contains(TopAppBarConfiguration.Action.OverflowMenu)) {
        val context = LocalContext.current
        LocalRecipes.current.map { recipe ->
            OdsTopAppBarOverflowMenuActionItem(
                text = recipe.title,
                onClick = { clickOnElement(context, recipe.title) }
            )
        }
    } else {
        emptyList()
    }
}

@Composable
private fun getTopAppBarCustomAction(action: TopAppBarConfiguration.Action.Custom): OdsTopAppBarActionButton {
    val context = LocalContext.current
    return OdsTopAppBarActionButton(
        onClick = { clickOnElement(context, action.name) },
        painter = painterResource(id = action.iconResId),
        contentDescription = action.name
    )
}

@Composable
private fun ChangeThemeDialog(themeManager: ThemeManager, dismissDialog: () -> Unit, onThemeSelected: () -> Unit) {
    var selectedThemeName by rememberSaveable { mutableStateOf(themeManager.currentThemeConfiguration.name) }

    Dialog(onDismissRequest = dismissDialog) {
        Column(modifier = Modifier.background(OdsTheme.colors.surface)) {
            OdsTextH6(
                text = stringResource(R.string.top_app_bar_action_change_theme_desc),
                modifier = Modifier
                    .padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_m), bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
                    .padding(horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin))
            )
            themeManager.themeConfigurations.forEach { themeConfiguration ->
                OdsListItem(
                    text = themeConfiguration.name,
                    trailing = OdsListItemTrailingRadioButton(
                        selectedThemeName == themeConfiguration.name,
                        {
                            selectedThemeName = themeConfiguration.name
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