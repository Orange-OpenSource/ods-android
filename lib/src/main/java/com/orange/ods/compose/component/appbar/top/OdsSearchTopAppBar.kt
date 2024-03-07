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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.textfield.search.OdsSearchTextField
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/23e0e6-app-bars/b/620966" class="external" target="_blank">ODS Top App Bar</a>.
 *
 * The search variant contains a search text field and an optional navigation icon. Search results are often displayed in the screen below.
 *
 * @param placeholder Text placeholder displayed in the search text field when search value is empty.
 * @param value Value of the search text field.
 * @param onValueChange Callback invoked when the search value changes. The new value is available in parameter.
 * @param modifier [Modifier] applied to the top app bar.
 * @param navigationIcon Icon displayed at the start of the search top app bar before the text field.
 * @param elevated Controls the elevation of the top app bar: `true` to set an elevation to the top app bar (a shadow is displayed below), `false` otherwise.
 */
@Composable
@OdsComposable
fun OdsSearchTopAppBar(
    placeholder: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: OdsTopAppBar.NavigationIcon? = null,
    elevated: Boolean = true
) {
    TopAppBar(
        title = { },
        modifier = modifier,
        navigationIcon = navigationIcon?.let { { it.Content() } },
        actions = {
            val focusRequester = remember { FocusRequester() }
            OdsSearchTextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = placeholder,
                modifier = modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        },
        backgroundColor = OdsTheme.colors.component.topAppBar.barBackground,
        contentColor = OdsTheme.colors.component.topAppBar.barContent,
        elevation = if (elevated) AppBarDefaults.TopAppBarElevation else 0.dp
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSearchTopAppBar() = Preview {
    OdsSearchTopAppBar(
        placeholder = "Enter text to search",
        value = TextFieldValue(),
        onValueChange = {},
        navigationIcon = OdsTopAppBar.NavigationIcon(Icons.AutoMirrored.Filled.ArrowBack, "") {},
    )
}