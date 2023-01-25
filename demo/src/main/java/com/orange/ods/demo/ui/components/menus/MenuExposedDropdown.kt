/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.menus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.menu.OdsExposedDropdownMenu
import com.orange.ods.demo.R
import com.orange.ods.demo.domain.recipes.LocalRecipes
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuExposedDropdown() {
    val enabled = remember { mutableStateOf(true) }
    val recipes = LocalRecipes.current

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_state_enabled),
                trailing = OdsSwitchTrailing(checked = enabled)
            )
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = dimensionResource(id = R.dimen.screen_vertical_margin), bottom = dimensionResource(id = R.dimen.spacing_s))
        ) {
            OdsExposedDropdownMenu(
                items = recipes.map { it.title },
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_s))
                    .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                enabled = enabled.value
            )
        }
    }
}