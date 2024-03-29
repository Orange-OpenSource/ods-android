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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.orange.ods.app.ui.modules.emptystate.EmptyStateUsage

class AppsRecirculationCustomizationViewModel : ViewModel() {
    var categorizeApps by mutableStateOf(true)
}