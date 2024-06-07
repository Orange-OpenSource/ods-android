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

package com.orange.ods.module.moreapps.ui.configuration

import java.util.Locale

/**
 * More apps module configuration.
 */
data class OdsMoreAppsConfiguration(
    /**
     * The AppsPlus API key.
     */
    val apiKey: String,

    /**
     *
     */
    val locale: Locale = Locale.getDefault()
)