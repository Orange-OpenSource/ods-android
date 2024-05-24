/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.modules.about

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.modules.Module
import com.orange.ods.app.ui.modules.ModuleDetailColumn
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.chip.OdsFilterChip
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

enum class AboutCustomizationAppSection(@StringRes val labelResId: Int) {
    Version(R.string.module_about_customization_app_section_version),
    Description(R.string.module_about_customization_app_section_description),
    Share(R.string.module_about_customization_app_section_share),
    Feedback(R.string.module_about_customization_app_section_feedback)
}

enum class AboutCustomizationOptionalItem(@StringRes val labelResId: Int) {
    AppNews(R.string.module_about_customization_optional_item_app_news),
    LegalInformation(R.string.module_about_customization_optional_item_legal_information),
    RateTheApp(R.string.module_about_customization_optional_item_rate_app),
}

private const val MinLinkItemCount = 0
private const val MaxLinkItemCount = 10

@Composable
fun AboutCustomizationScreen(navigateToAboutDemo: () -> Unit, viewModel: AboutCustomizationViewModel) {
    with(viewModel) {
        ModuleDetailColumn(module = Module.About, onViewDemoButtonClick = navigateToAboutDemo) {
            OdsText(
                modifier = Modifier
                    .padding(top = OdsTheme.spacings.small)
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                text = stringResource(id = R.string.module_about_customization),
                style = OdsTheme.typography.bodyMedium
            )

            Subtitle(textRes = R.string.module_about_customization_app_sections, horizontalPadding = true)
            CustomizationChipsFlowRow {
                AboutCustomizationAppSection.entries.forEach { appSection: AboutCustomizationAppSection ->
                    OdsFilterChip(
                        text = stringResource(id = appSection.labelResId),
                        onClick = {
                            selectedAppSections = with(selectedAppSections) { if (contains(appSection)) minus(appSection) else plus(appSection) }
                        },
                        selected = selectedAppSections.contains(appSection),
                    )
                }
            }

            Subtitle(textRes = R.string.module_about_customization_optional_items, horizontalPadding = true)
            CustomizationChipsFlowRow {
                AboutCustomizationOptionalItem.entries.forEach { item: AboutCustomizationOptionalItem ->
                    OdsFilterChip(
                        text = stringResource(id = item.labelResId),
                        onClick = {
                            selectedOptionalItems = with(selectedOptionalItems) { if (contains(item)) minus(item) else plus(item) }
                        },
                        selected = selectedOptionalItems.contains(item),
                    )
                }
            }

            ComponentCountRow(
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                title = stringResource(id = R.string.module_about_customization_additional_items),
                count = additionalLinksCount,
                minusIconContentDescription = stringResource(id = R.string.module_about_customization_additional_item_remove),
                plusIconContentDescription = stringResource(id = R.string.module_about_customization_additional_item_add),
                minCount = MinLinkItemCount,
                maxCount = MaxLinkItemCount
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CustomizationChipsFlowRow(content: @Composable () -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = OdsTheme.spacings.extraSmall)
            .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
        horizontalArrangement = Arrangement.spacedBy(OdsTheme.spacings.small),
        verticalArrangement = Arrangement.spacedBy((-4).dp),
        content = { content() }
    )
}
