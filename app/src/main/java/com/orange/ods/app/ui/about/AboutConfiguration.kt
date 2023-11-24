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
import com.orange.ods.module.about.configuration.OdsAboutShareData
import com.orange.ods.module.about.configuration.OdsAboutUrlMenuItem
import com.orange.ods.module.about.utilities.VersionHelper
import com.orange.ods.module.about.utilities.extension.launchUrl

const val RateTheAppUrl = "https://play.google.com/apps/testing/com.orange.ods.app"
private const val ShareUrl = "http://oran.ge/dsapp"
private const val FeedbackUrl = "https://github.com/login?return_to=https%3A%2F%2Fgithub.com%2FOrange-OpenSource%2Fods-android%2Fissues%2Fnew%2Fchoose"

@Composable
fun aboutConfiguration(): OdsAboutModuleConfiguration {
    val appBarManager = LocalAppBarManager.current
    val context = LocalContext.current
    return OdsAboutModuleConfiguration(
        appName = stringResource(id = R.string.about_app_name),
        privacyPolicyMenuItemFile = OdsAboutFileMenuItem.File(R.raw.about_privacy_policy, OdsAboutFileMenuItem.FileFormat.Html),
        termsOfServiceMenuItemFile = OdsAboutFileMenuItem.File(R.raw.about_terms_of_service, OdsAboutFileMenuItem.FileFormat.Html),
        appVersion = VersionHelper.getFromPackageInfo(context = LocalContext.current),
        appDescription = stringResource(id = R.string.about_description),
        shareData = OdsAboutShareData(
            stringResource(id = R.string.app_name),
            String.format(stringResource(id = R.string.about_share_text), ShareUrl)
        ),
        onFeedbackButtonClick = {
            context.launchUrl(FeedbackUrl)
        },
        appNewsMenuItemFileRes = R.raw.about_app_news,
        rateTheAppUrl = RateTheAppUrl,
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
                OdsAboutFileMenuItem.File(R.raw.changelog, OdsAboutFileMenuItem.FileFormat.Markdown)
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
