/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
fun rememberOdsPasswordTextFieldState(
    passwordVisible: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
) =
    remember(passwordVisible) {
        OdsPasswordTextFieldState(passwordVisible)
    }

class OdsPasswordTextFieldState(
    val passwordVisible: MutableState<Boolean>
) {
    val isPasswordVisible
        get() = passwordVisible.value

    val visualTransformation
        get() = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
}