/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
