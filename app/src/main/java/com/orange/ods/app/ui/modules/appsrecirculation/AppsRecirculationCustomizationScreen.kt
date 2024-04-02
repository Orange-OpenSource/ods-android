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

package com.orange.ods.app.ui.modules.appsrecirculation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orange.ods.app.R
import com.orange.ods.app.ui.modules.Module
import com.orange.ods.app.ui.modules.ModuleDetailColumn
import com.orange.ods.compose.component.listitem.OdsListItem

@Composable
fun AppsRecirculationCustomizationScreen(onViewDemoButtonClick: () -> Unit, viewModel: AppsRecirculationCustomizationViewModel = viewModel()) {
    with(viewModel) {
        ModuleDetailColumn(module = Module.AppsRecirculation, onViewDemoButtonClick = onViewDemoButtonClick) {
            OdsListItem(
                text = stringResource(id = R.string.module_appsRecirculation_categorizeApps_customization),
                trailing = OdsListItem.TrailingSwitch(categorizeApps, { categorizeApps = it })
            )
        }
    }
}