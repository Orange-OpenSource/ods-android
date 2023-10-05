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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.module.about.configuration.FileAboutItem
import com.orange.ods.module.about.configuration.OdsAboutMenuItem
import com.orange.ods.module.about.configuration.OdsAboutModuleConfiguration
import com.orange.ods.module.about.configuration.OdsAboutShareData

class AboutCustomizationViewModel : ViewModel() {

    val additionalLinksCount = mutableStateOf(0)

    var selectedOptions by mutableStateOf(emptyList<AboutOptions>())

    @Composable
    fun aboutModuleConfiguration(): OdsAboutModuleConfiguration {
        val context = LocalContext.current
        return OdsAboutModuleConfiguration(
            appName = stringResource(id = R.string.module_about_demo_app_name),
            appVersion = if (selectedOptions.contains(AboutOptions.Version)) stringResource(id = R.string.module_about_demo_version) else null,
            appDescription = if (selectedOptions.contains(AboutOptions.Description)) stringResource(id = R.string.module_about_demo_description) else null,
            shareData = if (selectedOptions.contains(AboutOptions.Share)) {
                OdsAboutShareData(
                    stringResource(id = R.string.module_about_demo_share_title),
                    stringResource(id = R.string.module_about_demo_share_text)
                )
            } else {
                null
            },
            onFeedbackButtonClick = if (selectedOptions.contains(AboutOptions.Feedback)) {
                { clickOnElement(context, context.getString(R.string.module_about_demo_feedback_button_element)) }
            } else {
                null
            },
            customMenuItems = customMenuItems(additionalLinksCount.value)
        )
    }

    @Composable
    private fun customMenuItems(itemCount: Int): List<OdsAboutMenuItem> {
        val customMenuItems = mutableListOf<OdsAboutMenuItem>()
        for (i in 1..itemCount) {
            customMenuItems.add(
                FileAboutItem(
                    painterResource(id = com.orange.ods.module.about.R.drawable.ic_tasklist),
                    "Custom item $i",
                    1,
                    "item$i",
                    FileAboutItem.FileFormat.Html
                )
            )
        }
        return customMenuItems
    }
}
