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

package com.orange.ods.app.ui.modules.emptystate

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orange.ods.app.R
import com.orange.ods.app.ui.modules.Module
import com.orange.ods.app.ui.modules.ModuleDetailColumn
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem

enum class EmptyStateUsage(
    @StringRes val choiceLabelRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val textRes: Int,
    @StringRes val buttonLabelRes: Int,
    @DrawableRes val illustrationRes: Int
) {
    FirstUse(
        R.string.module_emptyState_usageFirstUse_choice,
        R.string.module_emptyState_usageFirstUse_title,
        R.string.module_emptyState_usageFirstUse_text,
        R.string.module_emptyState_usageFirstUse_buttonLabel,
        R.drawable.il_empty_state_first_use

    ),
    UserCleared(
        R.string.module_emptyState_usageUserCleared_choice,
        R.string.module_emptyState_usageUserCleared_title,
        R.string.module_emptyState_usageUserCleared_text,
        R.string.module_emptyState_usageUserCleared_buttonLabel,
        R.drawable.il_empty_state_user_clear
    ),
    Error(
        R.string.module_emptyState_usageError_choice,
        R.string.module_emptyState_usageError_title,
        R.string.module_emptyState_usageError_text,
        R.string.module_emptyState_usageError_buttonLabel,
        R.drawable.il_empty_state_error
    ),
    NoData(
        R.string.module_emptyState_usageNoData_choice,
        R.string.module_emptyState_usageNoData_title,
        R.string.module_emptyState_usageNoData_text,
        R.string.module_emptyState_usageNoData_buttonLabel,
        R.drawable.il_empty_state_no_data
    )
}

@Composable
fun EmptyStateCustomizationScreen(onViewDemoButtonClick: () -> Unit, viewModel: EmptyStateCustomizationViewModel = viewModel()) {
    with(viewModel) {
        ModuleDetailColumn(module = Module.EmptyState, onViewDemoButtonClick = onViewDemoButtonClick) {
            Subtitle(textRes = R.string.module_emptyState_usage_customization, horizontalPadding = true)
            OdsChoiceChipsFlowRow(
                selectedChoiceChipIndex = EmptyStateUsage.entries.indexOf(usage),
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                choiceChips = EmptyStateUsage.entries.map { emptyStateUsage ->
                    OdsChoiceChipsFlowRow.ChoiceChip(
                        stringResource(id = emptyStateUsage.choiceLabelRes),
                        { usage = emptyStateUsage }
                    )
                }
            )

            OdsListItem(
                text = stringResource(id = R.string.module_emptyState_button_customization),
                trailing = OdsListItem.TrailingSwitch(button, { button = it })
            )
            OdsListItem(
                text = stringResource(id = R.string.component_element_text),
                trailing = OdsListItem.TrailingSwitch(text, { text = it })
            )
        }
    }
}