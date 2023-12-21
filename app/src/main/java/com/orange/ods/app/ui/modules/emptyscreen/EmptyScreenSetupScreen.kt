/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.modules.emptyscreen

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
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem

enum class EmptyScreenUsage(
    @StringRes val choiceLabelRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val textRes: Int,
    @StringRes val buttonLabelRes: Int,
    @DrawableRes val illustrationRes: Int? = null
) {
    FirstUse(
        R.string.module_emptyScreen_usageFirstUse_choice,
        R.string.module_emptyScreen_usageFirstUse_title,
        R.string.module_emptyScreen_usageFirstUse_text,
        R.string.module_emptyScreen_usageFirstUse_buttonLabel,
        R.drawable.il_empty_state_first_use

    ),
    UserCleared(
        R.string.module_emptyScreen_usageUserCleared_choice,
        R.string.module_emptyScreen_usageUserCleared_title,
        R.string.module_emptyScreen_usageUserCleared_text,
        R.string.module_emptyScreen_usageUserCleared_buttonLabel
    ),
    Error(
        R.string.module_emptyScreen_usageError_choice,
        R.string.module_emptyScreen_usageError_title,
        R.string.module_emptyScreen_usageError_text,
        R.string.module_emptyScreen_usageError_buttonLabel,
        R.drawable.il_empty_state_error
    ),
    NoData(
        R.string.module_emptyScreen_usageNoData_choice,
        R.string.module_emptyScreen_usageNoData_title,
        R.string.module_emptyScreen_usageNoData_text,
        R.string.module_emptyScreen_usageNoData_buttonLabel,
        R.drawable.il_empty_state_no_data
    )
}

@Composable
fun EmptyScreenSetupScreen(onViewDemoButtonClick: () -> Unit, viewModel: EmptyScreenViewModel = viewModel()) {
    with(viewModel) {
        ModuleDetailColumn(module = Module.EmptyScreen, onViewDemoButtonClick = onViewDemoButtonClick) {

            Subtitle(textRes = R.string.module_emptyScreen_usage_setup, horizontalPadding = true)
            OdsChoiceChipsFlowRow(
                value = usage,
                onValueChange = { value -> usage = value },
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                chips = EmptyScreenUsage.entries.map { emptyScreenUsage ->
                    OdsChoiceChip(
                        text = stringResource(id = emptyScreenUsage.choiceLabelRes),
                        value = emptyScreenUsage
                    )
                }
            )

            OdsListItem(
                text = stringResource(id = R.string.module_emptyScreen_button_setup),
                trailing = OdsListItem.TrailingSwitch(button, { button = it })
            )
            OdsListItem(
                text = stringResource(id = R.string.component_element_text),
                trailing = OdsListItem.TrailingSwitch(text, { text = it })
            )
        }
    }
}