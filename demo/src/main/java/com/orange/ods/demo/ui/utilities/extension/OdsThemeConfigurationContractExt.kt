/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.extension

import com.orange.ods.theme.OdsThemeConfigurationContract
import com.orange.ods.theme.orange.OrangeThemeConfiguration

val OdsThemeConfigurationContract.isOrange: Boolean
    get() = name == OrangeThemeConfiguration.OrangeThemeName
