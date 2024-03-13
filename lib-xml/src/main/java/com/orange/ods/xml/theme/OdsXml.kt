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

package com.orange.ods.xml.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.orange.OrangeThemeConfiguration

class OdsXml {

    enum class UiMode {
        Automatic, Light, Dark
    }

    var themeConfiguration by mutableStateOf<OdsThemeConfigurationContract>(OrangeThemeConfiguration())
    var uiMode by mutableStateOf(UiMode.Automatic)
}
