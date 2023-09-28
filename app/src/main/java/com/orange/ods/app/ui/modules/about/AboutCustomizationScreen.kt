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
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.modules.Module
import com.orange.ods.app.ui.modules.ModuleDetailColumn
import com.orange.ods.compose.component.chip.OdsFilterChip
import com.orange.ods.compose.text.OdsTextBody2

enum class AboutCustomizationOptions(@StringRes val labelResId: Int) {
    Version(R.string.module_about_customization_version),
    Description(R.string.module_about_customization_description),
    Share(R.string.module_about_customization_share),
    Feedback(R.string.module_about_customization_feedback)
}

private const val MinLinkItemCount = 0
private const val MaxLinkItemCount = 10

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutCustomizationScreen(navigateToAboutModule: () -> Unit, viewModel: AboutCustomizationViewModel) {
    with(viewModel) {
        ModuleDetailColumn(module = Module.About, onViewDemoButtonClick = { navigateToAboutModule() }) {

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
                AboutCustomizationOptions.values().forEach { option ->
                    OdsFilterChip(
                        text = stringResource(id = option.labelResId),
                        onClick = {
                            selectedOptions = with(selectedOptions) { if (contains(option)) minus(option) else plus(option) }
                        },
                        selected = selectedOptions.contains(option),
                    )
                }

            }

            ComponentCountRow(
                title = stringResource(id = R.string.module_about_customization_additional_links),
                count = additionalLinksCount,
                minusIconContentDescription = stringResource(id = R.string.module_about_customization_additional_link_remove),
                plusIconContentDescription = stringResource(id = R.string.module_about_customization_additional_link_add),
                minCount = MinLinkItemCount,
                maxCount = MaxLinkItemCount
            )

        }
    }
}