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

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.orange.ods.compose.extension.enable
import com.orange.ods.compose.theme.OdsTheme

internal object OdsTextFieldDefaults {

    @Composable
    fun textFieldColors() = TextFieldDefaults.colors(
        focusedLeadingIconColor = OdsTheme.colors.onSurface,
        unfocusedLeadingIconColor = OdsTheme.colors.onSurface,
        focusedTrailingIconColor = OdsTheme.colors.onSurface,
        unfocusedTrailingIconColor = OdsTheme.colors.onSurface,
        errorTrailingIconColor = OdsTheme.colors.onSurface,
        focusedLabelColor = OdsTheme.colors.onSurface
    )

    @Composable
    fun outlinedTextFieldColors() = OutlinedTextFieldDefaults.colors(
        focusedLeadingIconColor = OdsTheme.colors.onSurface,
        unfocusedLeadingIconColor = OdsTheme.colors.onSurface,
        focusedTrailingIconColor = OdsTheme.colors.onSurface,
        unfocusedTrailingIconColor = OdsTheme.colors.onSurface,
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