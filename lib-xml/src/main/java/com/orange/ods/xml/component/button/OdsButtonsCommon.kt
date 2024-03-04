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
import com.orange.ods.compose.theme.InvertedTheme

@Composable
internal fun OdsButtonContent(invertedTheme: Boolean, content: @Composable () -> Unit) {
    if (invertedTheme) {
        InvertedTheme {
            content()
        }
    } else {
        content()
    }
}