/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.menus

import androidx.compose.foundation.clickable
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
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.list.OdsIconTrailing
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.component.menu.OdsDropdownMenuItem
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement

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
                    trailing = OdsSwitchTrailing(checked = icons)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_menu_divider),
                    trailing = OdsSwitchTrailing(checked = dividerExample)
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top = dimensionResource(id = R.dimen.screen_vertical_margin), bottom = dimensionResource(id = R.dimen.spacing_s))
            ) {
                OdsTextBody1(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    text = stringResource(id = R.string.component_menu_dropdown_description)
                )

                Box(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s))) {
                    OdsListItem(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s)),
                        text = recipe.title,
                        secondaryText = recipe.subtitle,
                        trailing = OdsIconTrailing(
                            modifier = Modifier.clickable { menuExpanded = true },
                            painter = rememberVectorPainter(image = Icons.Filled.MoreVert),
                            contentDescription = stringResource(id = R.string.component_menu_show_ingredients),
                        )
                    )
                    OdsDropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }, offset = DpOffset(x = (-100).dp, y = (-10).dp)) {
                        recipes.take(MenuDropdownCustomizationState.MenuItemCount).forEachIndexed { index, recipe ->
                            OdsDropdownMenuItem(
                                text = recipe.title,
                                icon = if (hasIcons && recipe.iconResId != null) painterResource(id = recipe.iconResId) else null,
                                onClick = { clickOnElement(context, recipe.title) }
                            )
                            if (hasDividerExample && index == 2) {
                                OdsDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}