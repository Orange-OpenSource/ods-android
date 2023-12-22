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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.orange.ods.app.R
import com.orange.ods.app.ui.CustomAppBarConfiguration
import com.orange.ods.app.ui.LocalAppBarManager
import com.orange.ods.app.ui.about.RateTheAppUrl
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.module.about.ui.configuration.OdsAboutConfiguration
import com.orange.ods.module.about.ui.configuration.OdsAboutFileMenuItem
import com.orange.ods.module.about.ui.configuration.OdsAboutMenuItem
import com.orange.ods.module.about.ui.configuration.OdsAboutShareData

class AboutCustomizationViewModel : ViewModel() {

    val additionalLinksCount = mutableIntStateOf(0)
    var selectedAppSections by mutableStateOf(emptyList<AboutCustomizationAppSection>())
    var selectedOptionalItems by mutableStateOf(emptyList<AboutCustomizationOptionalItem>())

    @Composable
    fun aboutConfiguration(): OdsAboutConfiguration {
        val context = LocalContext.current
        val appBarManager = LocalAppBarManager.current
        return OdsAboutConfiguration(
            appName = stringResource(id = R.string.module_about_demo_app_name),
            privacyPolicyMenuItemFile = OdsAboutFileMenuItem.File(R.raw.about_privacy_policy, OdsAboutFileMenuItem.File.Format.Html),
            termsOfServiceMenuItemFile = OdsAboutFileMenuItem.File(R.raw.about_terms_of_service, OdsAboutFileMenuItem.File.Format.Html),
            appVersion = if (selectedAppSections.contains(AboutCustomizationAppSection.Version)) stringResource(id = R.string.module_about_demo_version) else null,
            appDescription = if (selectedAppSections.contains(AboutCustomizationAppSection.Description)) stringResource(id = R.string.module_about_demo_description) else null,
            shareData = if (selectedAppSections.contains(AboutCustomizationAppSection.Share)) {
                OdsAboutShareData(
                    stringResource(id = R.string.module_about_demo_share_title),
                    stringResource(id = R.string.module_about_demo_share_text)
                )
            } else {
                null
            },
            appNewsMenuItemFileRes = if (selectedOptionalItems.contains(AboutCustomizationOptionalItem.AppNews)) R.raw.about_app_news else null,
            legalInformationMenuItemFile = if (selectedOptionalItems.contains(AboutCustomizationOptionalItem.LegalInformation)) OdsAboutFileMenuItem.File(
                R.raw.about_demo_legal_information_item,
                OdsAboutFileMenuItem.File.Format.Html
            ) else null,
            rateTheAppUrl = if (selectedOptionalItems.contains(AboutCustomizationOptionalItem.RateTheApp)) RateTheAppUrl else null,
            onFeedbackButtonClick = if (selectedAppSections.contains(AboutCustomizationAppSection.Feedback)) {
                { clickOnElement(context, context.getString(R.string.module_about_demo_feedback_button_element)) }
            } else {
                null
            },
            customMenuItems = customMenuItems(additionalLinksCount.intValue),
            onScreenChange = { title ->
                appBarManager.setCustomAppBar(CustomAppBarConfiguration(title = title, actionCount = 0))
            }
        )
    }

    @Composable
    private fun customMenuItems(itemCount: Int): List<OdsAboutMenuItem> {
        val customMenuItems = mutableListOf<OdsAboutMenuItem>()
        for (i in 1..itemCount) {
            customMenuItems.add(
                OdsAboutFileMenuItem(
                    painterResource(id = com.orange.ods.module.about.R.drawable.ic_tasklist),
                    "Custom item $i",
                    1,
                    OdsAboutFileMenuItem.File(R.raw.about_demo_custom_item, OdsAboutFileMenuItem.File.Format.Html)
                )
            )
        }
        return customMenuItems
    }
}
