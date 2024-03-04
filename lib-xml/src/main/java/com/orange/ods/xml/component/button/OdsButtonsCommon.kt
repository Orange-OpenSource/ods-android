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

package com.orange.ods.xml.component.button

import androidx.compose.runtime.Composable
import com.orange.ods.compose.theme.TweakedTheme

@Composable
internal fun OdsButtonContent(invertedTheme: Boolean, content: @Composable () -> Unit) {
    if (invertedTheme) {
        TweakedTheme {
            content()
        }
    } else {
        content()
    }
}