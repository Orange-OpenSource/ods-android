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

package com.orange.ods.app.ui.utilities.extension

import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.orange.OrangeThemeConfiguration

val OdsThemeConfigurationContract.isOrange: Boolean
    get() = name == OrangeThemeConfiguration.OrangeThemeName
