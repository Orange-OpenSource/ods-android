/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.modules.about

import androidx.compose.runtime.Composable
import com.orange.ods.module.about.AboutModule
import com.orange.ods.module.about.AboutModuleConfiguration

@Composable
fun AboutDemoScreen(configuration: AboutModuleConfiguration) {
    AboutModule(configuration = configuration)
}