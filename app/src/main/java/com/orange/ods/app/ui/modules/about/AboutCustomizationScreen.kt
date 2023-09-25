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

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.modules.Module
import com.orange.ods.app.ui.modules.ModuleDetailColumn
import com.orange.ods.compose.component.chip.OdsFilterChip
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.module.about.configuration.OdsAboutModuleConfiguration
import com.orange.ods.module.about.configuration.OdsAboutShareData


enum class AboutOptions(@StringRes val labelResId: Int) {
    Version(R.string.module_about_customization_version),
    Description(R.string.module_about_customization_description),
    Share(R.string.module_about_customization_share),
    Feedback(R.string.module_about_customization_feedback)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutCustomizationScreen(navigateToAboutModule: () -> Unit, configureAboutModule: (OdsAboutModuleConfiguration) -> Unit) {
    val context = LocalContext.current
    ModuleDetailColumn(module = Module.About, onViewDemoButtonClick = { navigateToAboutModule() }) {
        var selectedOptions by rememberSaveable { mutableStateOf(emptyList<AboutOptions>()) }

        OdsTextBody2(
            modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
            text = stringResource(id = R.string.module_about_customization)
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
        ) {
            AboutOptions.values().forEach { option ->
                OdsFilterChip(
                    text = stringResource(id = option.labelResId),
                    onClick = {
                        selectedOptions = with(selectedOptions) { if (contains(option)) minus(option) else plus(option) }
                    },
                    selected = selectedOptions.contains(option),
                )
            }
        }

        configureAboutModule(OdsAboutModuleConfiguration(
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
            }
        ))
    }
}