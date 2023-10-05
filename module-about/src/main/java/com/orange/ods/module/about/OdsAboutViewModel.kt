/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.orange.ods.module.about.configuration.OdsAboutModuleConfiguration

class OdsAboutViewModel : ViewModel() {
    var configuration: OdsAboutModuleConfiguration? by mutableStateOf(null)
        private set

    fun configureAboutModule(configuration: OdsAboutModuleConfiguration) {
        this.configuration = configuration
    }
}