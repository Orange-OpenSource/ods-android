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

package com.orange.ods.compose.component.textfield

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable

internal object OdsTextFieldDefaults {

    @Composable
    fun textFieldColors() = TextFieldDefaults.textFieldColors(
        leadingIconColor = OdsTheme.colors.onSurface,
        trailingIconColor = OdsTheme.colors.onSurface,
        errorTrailingIconColor = OdsTheme.colors.onSurface,
        focusedLabelColor = OdsTheme.colors.onSurface
    )

    @Composable
    fun outlinedTextFieldColors() = TextFieldDefaults.outlinedTextFieldColors(
        leadingIconColor = OdsTheme.colors.onSurface,
        trailingIconColor = OdsTheme.colors.onSurface,
        errorTrailingIconColor = OdsTheme.colors.onSurface,
        focusedLabelColor = OdsTheme.colors.onSurface,
        errorLabelColor = OdsTheme.colors.onSurface,
    )

    @Composable
    fun trailingTextColor(isValueEmpty: Boolean, isTextFieldEnabled: Boolean) =
        if (isValueEmpty || !isTextFieldEnabled) {
            OdsTheme.colors.onSurface.enable(enabled = false)
        } else {
            OdsTheme.colors.onSurface
        }
}