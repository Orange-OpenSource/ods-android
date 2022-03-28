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

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.orange.ods.compose.component.OdsTopAppBar
import com.orange.ods.demo.R

@Composable
fun TopAppBar(title: String, isDarkMode: Boolean, onThemeChange: (Boolean) -> Unit) {
    UpdateSystemBarsColor(MaterialTheme.colors.background)
    OdsTopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(onClick = {
                onThemeChange(!isDarkMode)
            }) {
                if (isDarkMode) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ui_light_mode),
                        contentDescription = stringResource(id = R.string.theme_changer_icon_content_description_light)

                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ui_dark_mode),
                        contentDescription = stringResource(id = R.string.theme_changer_icon_content_description_dark)
                    )
                }
            }
        }
    )
}

@Composable
private fun UpdateSystemBarsColor(backgroundColor: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = backgroundColor,
        )
    }
}