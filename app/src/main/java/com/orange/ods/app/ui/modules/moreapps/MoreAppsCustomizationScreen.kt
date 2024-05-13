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

package com.orange.ods.app.ui.modules.moreapps

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.modules.Module
import com.orange.ods.app.ui.modules.ModuleDetailColumn
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.text.OdsText
import com.orange.ods.theme.typography.OdsTextStyle

// Filters labels set in Apps+ applications list demo
private const val CarouselFilter = "carousel"
private const val SectionsFilter = "sections"

private enum class AppsPlusFilter(@StringRes val choiceLabelRes: Int, val value: String?) {
    None(R.string.component_element_none, null),
    WithCarousel(R.string.module_moreApps_customization_filter_carousel, CarouselFilter),
    WithSections(R.string.module_moreApps_customization_filter_sections, SectionsFilter);

    companion object {
        fun fromValue(value: String?) = when (value) {
            CarouselFilter -> WithCarousel
            SectionsFilter -> WithSections
            else -> None
        }
    }
}

@Composable
fun MoreAppsCustomizationScreen(navigateToMoreAppsDemo: () -> Unit, viewModel: MoreAppsCustomizationViewModel) {
    var selectedFilter by remember { mutableStateOf(AppsPlusFilter.fromValue(viewModel.filter)) }
    ModuleDetailColumn(module = Module.MoreApps, onViewDemoButtonClick = navigateToMoreAppsDemo, showCustomizeSubtitle = false) {
        Column(modifier = Modifier.padding(horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin))) {
            Subtitle(textRes = R.string.module_moreApps_customization_subtitle)
            OdsText(
                modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                text = stringResource(id = R.string.module_moreApps_customization_text),
                style = OdsTextStyle.BodyM
            )
            Subtitle(textRes = R.string.module_moreApps_customization_filter)
        }
        OdsChoiceChipsFlowRow(
            selectedChoiceChipIndex = AppsPlusFilter.entries.indexOf(selectedFilter),
            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
            choiceChips = AppsPlusFilter.entries.map { filter ->
                OdsChoiceChipsFlowRow.ChoiceChip(
                    stringResource(id = filter.choiceLabelRes),
                    onClick = {
                        viewModel.filter = filter.value
                        selectedFilter = filter
                    }
                )
            }
        )
    }
}