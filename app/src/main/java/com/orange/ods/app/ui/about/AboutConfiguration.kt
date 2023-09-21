/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.about

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.module.about.configuration.OdsAboutModuleConfiguration
import com.orange.ods.module.about.utilities.VersionHelper

@Composable
fun aboutConfiguration() = OdsAboutModuleConfiguration(
    appName = stringResource(id = R.string.about_app_name),
    appVersion = VersionHelper.getFromPackageInfo(context = LocalContext.current),
    appDescription = stringResource(id = R.string.about_description)
)

