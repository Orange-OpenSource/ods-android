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

import androidx.compose.material.ContentAlpha
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.utilities.extension.enable

internal object OdsTextFieldDefaults {

    @Composable
    fun textFieldColors() = TextFieldDefaults.textFieldColors(
        textColor = OdsTheme.colors.onSurface,
        backgroundColor = OdsTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity),
        cursorColor = OdsTheme.colors.primary,
        errorCursorColor = OdsTheme.colors.error,
        focusedIndicatorColor = OdsTheme.colors.primary.copy(alpha = ContentAlpha.high),
        unfocusedIndicatorColor = OdsTheme.colors.onSurface.copy(alpha = TextFieldDefaults.UnfocusedIndicatorLineOpacity),
        errorIndicatorColor = OdsTheme.colors.error,
        leadingIconColor = OdsTheme.colors.onSurface,
        trailingIconColor = OdsTheme.colors.onSurface,
        errorTrailingIconColor = OdsTheme.colors.onSurface,
        focusedLabelColor = OdsTheme.colors.onSurface,
        unfocusedLabelColor = OdsTheme.colors.onSurface.copy(ContentAlpha.medium),
        errorLabelColor = OdsTheme.colors.error,
        placeholderColor = OdsTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    )

    @Composable
    fun outlinedTextFieldColors() = TextFieldDefaults.outlinedTextFieldColors(
        textColor = OdsTheme.colors.onSurface,
        cursorColor = OdsTheme.colors.primary,
        errorCursorColor = OdsTheme.colors.error,
        focusedBorderColor = OdsTheme.colors.primary.copy(alpha = ContentAlpha.high),
        unfocusedBorderColor = OdsTheme.colors.onSurface.enable(enabled = false),
        errorBorderColor = OdsTheme.colors.error,
        leadingIconColor = OdsTheme.colors.onSurface,
        trailingIconColor = OdsTheme.colors.onSurface,
        errorTrailingIconColor = OdsTheme.colors.onSurface,
        focusedLabelColor = OdsTheme.colors.onSurface,
        unfocusedLabelColor = OdsTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
        errorLabelColor = OdsTheme.colors.onSurface,
        placeholderColor = OdsTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
    )

    @Composable
    fun trailingTextColor(isValueEmpty: Boolean, isTextFieldEnabled: Boolean) =
        if (isValueEmpty || !isTextFieldEnabled) {
            OdsTheme.colors.onSurface.enable(enabled = false)
        } else {
            OdsTheme.colors.onSurface
        }
}