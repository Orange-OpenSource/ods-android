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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.menu.OdsExposedDropdownMenu
import com.orange.ods.compose.component.menu.OdsExposedDropdownMenuItem
import com.orange.ods.demo.R
import com.orange.ods.demo.domain.recipes.LocalRecipes
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuExposedDropdown() {
    val customizationState = rememberMenuDropdownCustomizationState()

    val context = LocalContext.current
    val recipes = LocalRecipes.current.take(4)

    val dropdownItems = recipes.map { recipe ->
        OdsExposedDropdownMenuItem(label = recipe.title, icon = recipe.iconResId?.let { painterResource(id = it) })
    }
    val textOnlyDropdownItems = recipes.map { recipe ->
        OdsExposedDropdownMenuItem(label = recipe.title)
    }

    var items by remember { mutableStateOf(dropdownItems) }

    with(customizationState) {
        val selectedItem: MutableState<OdsExposedDropdownMenuItem>
        if (hasIcons) {
            items = dropdownItems
            selectedItem = remember { mutableStateOf(dropdownItems.first()) }
        } else {
            items = textOnlyDropdownItems
            selectedItem = remember { mutableStateOf(textOnlyDropdownItems.first()) }
        }

        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsSwitchTrailing(checked = enabled)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_menu_icons),
                    trailing = OdsSwitchTrailing(checked = icons)
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top = dimensionResource(id = R.dimen.screen_vertical_margin), bottom = dimensionResource(id = R.dimen.spacing_s))
                    .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
            ) {
                OdsExposedDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.spacing_s)),
                    label = stringResource(id = R.string.data_recipe),
                    items = items,
                    selectedItem = selectedItem,
                    onItemSelectionChange = { item ->
                        selectedItem.value = item
                        clickOnElement(context, item.label)
                    },
                    enabled = isEnabled
                )
            }
        }
    }
}