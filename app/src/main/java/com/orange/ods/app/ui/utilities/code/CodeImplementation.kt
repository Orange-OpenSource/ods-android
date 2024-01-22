/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.utilities.code

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalUiFramework
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.compose.component.menu.OdsExposedDropdownMenu
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.extension.orElse

@Composable
fun CodeImplementationColumn(
    modifier: Modifier = Modifier,
    contentBackground: Boolean = true,
    xmlContent: (@Composable () -> Unit)? = null,
    composeContent: @Composable () -> Unit
) {
    var currentUiFramework by LocalUiFramework.current
    // Reset current UI framework to Compose when displaying the content
    // shouldResetUiFramework is used to avoid calling LaunchedEffect on configuration changes (for instance on device rotation)
    var shouldResetUiFramework by rememberSaveable { mutableStateOf(true) }
    if (shouldResetUiFramework) {
        LaunchedEffect(Unit) {
            shouldResetUiFramework = false
            currentUiFramework = UiFramework.Compose
        }
    }

    Column(
        modifier = modifier.padding(
            vertical = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)
        )
    ) {
        UiFrameworkChoice()
        if (currentUiFramework == UiFramework.Compose) {
            if (contentBackground) {
                CodeBackgroundColumn(composeContent)
            } else {
                composeContent()
            }
        } else {
            xmlContent?.let {
                it()
            }.orElse {
                CodeBackgroundColumn {
                    TechnicalText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(id = com.orange.ods.R.dimen.spacing_xs)),
                        text = stringResource(id = R.string.component_not_yet_available)
                    )
                }
            }
        }
    }
}

@Composable
private fun UiFrameworkChoice() {
    val context = LocalContext.current
    val currentUiFramework = LocalUiFramework.current
    val uiFrameworkItems = UiFramework.entries.map { uiFramework ->
        OdsExposedDropdownMenu.Item(label = stringResource(id = uiFramework.labelResId), iconResId = uiFramework.iconResId)
    }
    val selectedUiFramework = rememberSaveable(currentUiFramework.value) {
        val selectedUiFramework = currentUiFramework.value
        val selectedUiFrameworkIndex = UiFramework.entries.indexOf(selectedUiFramework)
        mutableStateOf(uiFrameworkItems[selectedUiFrameworkIndex])
    }

    OdsExposedDropdownMenu(
        label = stringResource(id = R.string.code_implementation),
        items = uiFrameworkItems,
        selectedItem = selectedUiFramework,
        onItemSelectionChange = { selectedItem ->
            currentUiFramework.value = UiFramework.entries.first { context.getString(it.labelResId) == selectedItem.label }
        }
    )
}

@Composable
fun CodeBackgroundColumn(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .background(
                OdsTheme.colors.onSurface.copy(alpha = 0.12f),
                shape = RoundedCornerShape(10f)
            )
            .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_s), vertical = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
            .semantics(mergeDescendants = true) {}) {
        content()
    }
}

@Composable
fun IndentCodeColumn(content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))) {
        content()
    }
}