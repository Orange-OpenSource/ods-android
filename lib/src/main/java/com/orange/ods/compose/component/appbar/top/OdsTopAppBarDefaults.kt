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

package com.orange.ods.compose.component.appbar.top

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.orange.ods.compose.theme.OdsTheme

object OdsTopAppBarDefaults {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun topAppBarColors() = TopAppBarDefaults.topAppBarColors().copy(
        containerColor = OdsTheme.colors.components.topAppBar.container,
        titleContentColor = OdsTheme.colors.components.topAppBar.content,
        navigationIconContentColor = OdsTheme.colors.components.topAppBar.content,
        actionIconContentColor = OdsTheme.colors.components.topAppBar.content
    )
}