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

package com.orange.ods.app.ui.modules.moreapps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.orange.ods.app.BuildConfig
import com.orange.ods.module.moreapps.ui.configuration.OdsMoreAppsConfiguration

class MoreAppsCustomizationViewModel : ViewModel() {
    var filter: String? by mutableStateOf(null)
    
    fun moreAppsConfiguration() = OdsMoreAppsConfiguration(apiKey = BuildConfig.APPS_PLUS_API_KEY, filter = filter)
}