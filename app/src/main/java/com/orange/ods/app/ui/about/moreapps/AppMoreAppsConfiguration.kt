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

package com.orange.ods.app.ui.about.moreapps

import com.orange.ods.app.BuildConfig
import com.orange.ods.module.moreapps.ui.configuration.OdsMoreAppsConfiguration

fun appMoreAppsConfiguration() = OdsMoreAppsConfiguration(
    apiKey = BuildConfig.APPS_PLUS_API_KEY
)