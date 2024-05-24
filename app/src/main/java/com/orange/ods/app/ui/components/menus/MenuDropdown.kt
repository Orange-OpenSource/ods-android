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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuDropdown() {
    val customizationState = rememberMenuDropdownCustomizationState()

    var menuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.ingredients.isNotEmpty() }.random() }

    with(customizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_menu_icons),
                    trailing = OdsListItem.TrailingSwitch(icons.value, { icons.value = it })
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_menu_divider),
                    trailing = OdsListItem.TrailingSwitch(dividerExample.value, { dividerExample.value = it })
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(
                        top = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin),
                        bottom = OdsTheme.spacings.small
                    )
            ) {
                OdsText(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                    text = stringResource(id = R.string.component_menu_dropdown_description),
                    style = OdsTheme.typography.bodyLarge
                )

                val dividerIndex = 1

                Box(modifier = Modifier.padding(top = OdsTheme.spacings.small)) {
                    OdsListItem(
                        modifier = Modifier.padding(top = OdsTheme.spacings.small),
                        text = recipe.title,
                        secondaryText = recipe.subtitle,
                        trailing = OdsListItem.TrailingIcon(
                            rememberVectorPainter(image = Icons.Filled.MoreVert),
                            stringResource(id = R.string.component_menu_show_ingredients)
                        ) { menuExpanded = true }
                    )

                    val items = recipes.take(MenuDropdownCustomizationState.MenuItemCount)
                        .mapIndexed { index, recipe ->
                            OdsDropdownMenu.Item(
                                text = recipe.title,
                                icon = if (hasIcons && recipe.iconResId != null) painterResource(id = recipe.iconResId) else null,
                                divider = hasDividerExample && index == dividerIndex,
                                enabled = index != MenuDropdownCustomizationState.MenuItemCount - 2,
                                onClick = { clickOnElement(context, recipe.title) }
                            )
                        }
                    OdsDropdownMenu(
                        items = items,
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                        offset = DpOffset(x = (-100).dp, y = (-10).dp)
                    )
                }

                CodeImplementationColumn(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
                ) {
                    FunctionCallCode(
                        name = OdsComposable.OdsDropdownMenu.name,
                        exhaustiveParameters = false,
                        parameters = {
                            stringRepresentation("expanded", menuExpanded)
                            lambda("onDismissRequest")
                            list("items") {
                                recipes.take(2).forEachIndexed { index, recipe ->
                                    classInstance<OdsDropdownMenu.Item> {
                                        string("text", recipe.title)
                                        if (hasIcons && recipe.iconResId != null) icon()
                                        if (hasDividerExample && index == dividerIndex) stringRepresentation("divider", true)
                                        onClick()
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}