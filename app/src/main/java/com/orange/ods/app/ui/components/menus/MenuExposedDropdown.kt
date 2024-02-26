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

package com.orange.ods.app.ui.components.menus

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.menu.OdsExposedDropdownMenu

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuExposedDropdown() {
    val customizationState = rememberMenuDropdownCustomizationState()

    val context = LocalContext.current
    val recipes = LocalRecipes.current.take(4)

    val dropdownItems = recipes.map { recipe ->
        OdsExposedDropdownMenu.Item(label = recipe.title, iconResId = recipe.iconResId)
    }
    val textOnlyDropdownItems = recipes.map { recipe ->
        OdsExposedDropdownMenu.Item(label = recipe.title)
    }

    var items by remember { mutableStateOf(dropdownItems) }

    with(customizationState) {
        val selectedItem: MutableState<OdsExposedDropdownMenu.Item> = rememberSaveable { mutableStateOf(dropdownItems.first()) }
        if (hasIcons) {
            items = dropdownItems
            selectedItem.value = dropdownItems.first { selectedItem.value.label == it.label }
        } else {
            items = textOnlyDropdownItems
            selectedItem.value = textOnlyDropdownItems.first { selectedItem.value.label == it.label }
        }

        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsListItem.TrailingSwitch(enabled.value, { enabled.value = it })
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_menu_icons),
                    trailing = OdsListItem.TrailingSwitch(icons.value, { icons.value = it })
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(
                        top = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin),
                        bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)
                    )
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
            ) {
                val label = stringResource(id = R.string.data_recipe)
                OdsExposedDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)),
                    label = label,
                    items = items,
                    selectedItem = selectedItem,
                    onItemSelectionChange = { item ->
                        clickOnElement(context, item.label)
                    },
                    enabled = isEnabled
                )

                CodeImplementationColumn {
                    FunctionCallCode(
                        name = OdsComposable.OdsExposedDropdownMenu.name,
                        exhaustiveParameters = false,
                        parameters = {
                            string("label", label)
                            list("items") {
                                items.forEach { item ->
                                    classInstance<OdsExposedDropdownMenu.Item> {
                                        string("label", item.label)
                                        if (hasIcons) simple("iconResId", "<drawable id>")
                                    }
                                }
                            }
                            mutableState("selectedItem", "<selected item>")
                            lambda("onItemSelectionChange")
                            if (!isEnabled) enabled(false)
                        }
                    )
                }
            }
        }
    }
}