/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.checkboxes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.CodeImplementation
import com.orange.ods.app.ui.utilities.composable.ComponentParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.list.OdsCheckboxTrailing
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentCheckboxes() {
    val enabled = rememberSaveable { mutableStateOf(true) }
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.ingredients.count() >= 3 }.random() }

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
                .padding(vertical = dimensionResource(id = R.dimen.spacing_s))
        ) {
            recipe.ingredients.take(3).forEachIndexed { index, ingredient ->
                OdsListItem(
                    text = ingredient.food.name,
                    trailing = OdsCheckboxTrailing(
                        checked = rememberSaveable { mutableStateOf(index == 0) },
                        enabled = enabled.value
                    )
                )
            }

            CodeImplementation(OdsComponent.OdsCheckbox.name).CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                componentParameters = mutableListOf<ComponentParameter>(
                    ComponentParameter.Checked(false),
                    ComponentParameter.OnCheckedChange
                ).apply {
                    if (!enabled.value) add(ComponentParameter.Enabled(false))
                }
            )
        }
    }
}