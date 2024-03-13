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

package com.orange.ods.compose.component.textfield.password

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
internal fun rememberOdsPasswordTextFieldState(
    enabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    visualisationIcon: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    passwordVisible: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(enabled, visualisationIcon, passwordVisible) {
        OdsPasswordTextFieldState(enabled, visualisationIcon, passwordVisible)
    }

internal class OdsPasswordTextFieldState(
    val enabled: MutableState<Boolean>,
    val visualisationIcon: MutableState<Boolean>,
    val passwordVisible: MutableState<Boolean>
) {
    val isPasswordVisible
        get() = if (!visualisationIcon.value) false else passwordVisible.value

    val visualTransformation
        get() = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
}