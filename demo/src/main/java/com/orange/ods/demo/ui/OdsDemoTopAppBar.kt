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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.orange.ods.compose.component.OdsTopAppBar
import com.orange.ods.demo.R

@Composable
fun OdsDemoTopAppBar(
    titleRes: Int,
    darkModeEnabled: Boolean,
    shouldShowUpNavigationIcon: Boolean,
    navigateUp: () -> Unit,
    updateTheme: (Boolean) -> Unit
) {
    SystemBarsColorSideEffect(MaterialTheme.colors.background)
    OdsTopAppBar(
        title = {
            Text(text = stringResource(id = titleRes))
        },
        navigationIcon = if (shouldShowUpNavigationIcon) {
            { UpNavigationIcon(navigateUp) }
        } else null,
        actions = {
            IconButton(onClick = {
                updateTheme(!darkModeEnabled)
            }) {
                if (darkModeEnabled) {
                    ThemeIcon(iconRes = R.drawable.ic_ui_light_mode, contentDescriptionRes = R.string.theme_changer_icon_content_description_light)
                } else {
                    ThemeIcon(iconRes = R.drawable.ic_ui_dark_mode, contentDescriptionRes = R.string.theme_changer_icon_content_description_dark)
                }
            }
        }
    )
}

@Composable
private fun ThemeIcon(@DrawableRes iconRes: Int, @StringRes contentDescriptionRes: Int) {
    Icon(
        painter = painterResource(id = iconRes),
        contentDescription = stringResource(id = contentDescriptionRes)
    )
}

@Composable
private fun UpNavigationIcon(navigateUp: () -> Unit) {
    IconButton(onClick = { navigateUp() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_icon_content_description)
        )
    }
}

@Composable
private fun SystemBarsColorSideEffect(backgroundColor: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = backgroundColor,
        )
    }
}