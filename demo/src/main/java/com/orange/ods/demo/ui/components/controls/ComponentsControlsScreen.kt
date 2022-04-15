/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.controls

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.ComponentsSubLevelNavigationItem
import com.orange.ods.demo.ui.components.utilities.ComponentHeader

private data class ControlsMenuItem(@StringRes val titleRes: Int, val route: String)

private val controlsMenuItems = listOf(
    ControlsMenuItem(R.string.component_controls_checkboxes, ComponentsSubLevelNavigationItem.ControlsCheckboxes.route),
    ControlsMenuItem(R.string.component_controls_radio_buttons, ComponentsSubLevelNavigationItem.ControlsRadioButtons.route),
    ControlsMenuItem(R.string.component_controls_switches, ComponentsSubLevelNavigationItem.ControlsSwitches.route),
    ControlsMenuItem(R.string.component_controls_sliders, ComponentsSubLevelNavigationItem.ControlsSliders.route)
)

@Composable
@ExperimentalMaterialApi
fun ComponentsControlsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = R.dimen.ods_spacing_s))
    ) {
        ComponentHeader(imageRes = R.drawable.picture_component_controls, description = R.string.component_controls_description)
        Column(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s))
        ) {
            controlsMenuItems.forEach { item ->
                OdsListItem(text = stringResource(id = item.titleRes), modifier = Modifier.clickable {
                    navController.navigate(item.route)
                })
            }
        }
    }
}