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

package com.orange.ods.compose.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.orange.OrangeThemeConfiguration

internal fun ComposeContentTestRule.setOdsContent(composable: @Composable () -> Unit) {
    setContent {
        OdsTheme(themeConfiguration = OrangeThemeConfiguration(), content = composable)
    }
}
