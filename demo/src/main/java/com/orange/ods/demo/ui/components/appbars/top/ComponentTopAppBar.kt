/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.appbars.top

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTopAppBarManager
import com.orange.ods.demo.ui.TopAppBarConfiguration
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@ExperimentalMaterialApi
@Composable
fun ComponentTopAppBar() {
    val customizationState = rememberTopAppBarCustomizationState()

    LocalMainTopAppBarManager.current.updateTopAppBar(
        TopAppBarConfiguration(
            isNavigationIconEnabled = customizationState.isNavigationIconEnabled,
            actionCount = customizationState.actionCount.value,
            isOverflowMenuEnabled = customizationState.isOverflowMenuEnabled
        )
    )

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            SwitchListItem(labelRes = R.string.component_app_bars_top_element_navigation_icon, checked = customizationState.navigationIconEnabled)
            ComponentCountRow(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                title = stringResource(id = R.string.component_app_bars_top_actions_count),
                count = customizationState.actionCount,
                minusIconContentDescription = stringResource(id = R.string.component_app_bars_top_remove_action),
                plusIconContentDescription = stringResource(id = R.string.component_app_bars_top_add_action),
                minCount = customizationState.minActionCount,
                maxCount = customizationState.maxActionCountSelectable
            )
            SwitchListItem(
                labelRes = R.string.component_app_bars_top_element_overflow_menu,
                checked = customizationState.overflowMenuEnabled,
                enabled = customizationState.isOverflowMenuSwitchEnabled
            )

        }) {
        // Nothing to display in screen
    }
}