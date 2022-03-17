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

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.demo.R

@Composable
fun TopAppBar(title: String, isDarkMode: Boolean, onThemeChange: (Boolean) -> Unit) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(onClick = {
                onThemeChange(!isDarkMode)
            }) {
                Icon(
                    painter = painterResource(
                        id = if (isDarkMode) {
                            R.drawable.ic_ui_light_mode
                        } else {
                            R.drawable.ic_ui_dark_mode
                        }
                    ),
                    contentDescription = if (isDarkMode) {
                        stringResource(id = R.string.theme_changer_icon_content_description_light)
                    } else {
                        stringResource(id = R.string.theme_changer_icon_content_description_dark)
                    }
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}