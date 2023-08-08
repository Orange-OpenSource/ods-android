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

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.utilities.compat.PackageManagerCompat
import com.orange.ods.app.ui.utilities.extension.versionCode
import com.orange.ods.extension.ifNotNull
import com.orange.ods.extension.orElse
import com.orange.ods.module.about.AboutModule
import com.orange.ods.module.about.AboutModuleConfiguration

@Composable
fun AboutScreen() {
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.navigation_item_about)

    val context = LocalContext.current

    AboutModule(
        configuration = AboutModuleConfiguration(
            appName = stringResource(id = R.string.about_app_name),
            appVersion = getVersion(context),
            appDescription = stringResource(id = R.string.about_description)
        )
    )
}

private fun getVersion(context: Context): String {
    val packageInfo = ifNotNull(context.packageManager, context.packageName) { packageManager, packageName ->
        PackageManagerCompat.getPackageInfo(packageManager, packageName, 0)
    }
    return packageInfo?.let {
        String.format(context.resources.getString(R.string.about_app_version), packageInfo.versionName, packageInfo.versionCode())
    }.orElse {
        context.resources.getString(R.string.about_app_version)
    }
}