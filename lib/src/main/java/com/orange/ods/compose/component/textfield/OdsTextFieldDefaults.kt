/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.textfield

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.utilities.extension.enable

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