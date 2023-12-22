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
import com.orange.ods.compose.text.OdsTextBody2

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

            OdsTextBody2(
                modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                text = stringResource(id = R.string.module_about_customization)
            )

            Subtitle(textRes = R.string.module_about_customization_app_sections)
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

            Subtitle(textRes = R.string.module_about_customization_optional_items)
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
            .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
        verticalArrangement = Arrangement.spacedBy((-4).dp),
        content = { content() }
    )
}
