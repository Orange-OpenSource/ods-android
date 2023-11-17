/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons.icons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.compose.component.listitem.OdsListItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentButtonsIcons(variant: Variant) {
    val customizationState = rememberButtonIconCustomizationState()

    with(customizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                if (variant == Variant.ButtonsIconToggleGroup) {
                    ComponentCountRow(
                        modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                        title = stringResource(id = R.string.component_button_icon_toggle_count),
                        count = toggleCount,
                        minusIconContentDescription = stringResource(id = R.string.component_button_icon_toggle_remove),
                        plusIconContentDescription = stringResource(id = R.string.component_button_icon_toggle_add),
                        minCount = ButtonIconCustomizationState.MinToggleCount,
                        maxCount = ButtonIconCustomizationState.MaxToggleCount
                    )
                }
                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsListItem.TrailingSwitch(enabled.value, { enabled.value = it })
                )
            }) {

            when (variant) {
                Variant.ButtonsIcon -> ButtonsIcon(customizationState)
                Variant.ButtonsIconToggle -> ButtonsIconToggle(customizationState)
                Variant.ButtonsIconToggleGroup -> ButtonsIconToggleGroup(customizationState)
                else -> {}
            }
        }
    }
}