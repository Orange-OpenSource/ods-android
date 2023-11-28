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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.CustomAppBarConfiguration
import com.orange.ods.app.ui.LocalAppBarManager
import com.orange.ods.module.about.configuration.OdsAboutFileMenuItem
import com.orange.ods.module.about.configuration.OdsAboutModuleConfiguration
import com.orange.ods.module.about.configuration.OdsAboutUrlMenuItem
import com.orange.ods.module.about.utilities.VersionHelper

@Composable
fun aboutConfiguration(): OdsAboutModuleConfiguration {
    val appBarManager = LocalAppBarManager.current
    return OdsAboutModuleConfiguration(
        appName = stringResource(id = R.string.about_app_name),
        privacyPolicyMenuItemFileRes = R.raw.about_privacy_policy,
        termsOfServiceMenuItemFileRes = R.raw.about_terms_of_service,
        appVersion = VersionHelper.getFromPackageInfo(context = LocalContext.current),
        appDescription = stringResource(id = R.string.about_description),
        customMenuItems = listOf(
            OdsAboutUrlMenuItem(
                painterResource(id = R.drawable.ic_tools),
                stringResource(id = R.string.about_menu_design_guidelines),
                1,
                "https://system.design.orange.com/0c1af118d/p/019ecc-android/"
            ),
            OdsAboutFileMenuItem(
                painterResource(id = com.orange.ods.module.about.R.drawable.ic_tasklist),
                stringResource(id = R.string.about_menu_changelog),
                2,
                R.raw.changelog,
                OdsAboutFileMenuItem.FileFormat.Markdown
            ),
            OdsAboutUrlMenuItem(
                painterResource(id = R.drawable.ic_comments),
                stringResource(id = R.string.about_menu_report_issue),
                3,
                "https://github.com/Orange-OpenSource/ods-android/issues/new/choose"
            )
        ),
        onScreenChange = { title ->
            appBarManager.setCustomAppBar(CustomAppBarConfiguration(title = title, actionCount = 0))
        }
    )
}
