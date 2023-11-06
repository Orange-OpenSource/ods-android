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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemTrailingCheckbox
import com.orange.ods.compose.component.list.OdsListItemTrailingSwitch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentCheckboxes() {
    var enabled by rememberSaveable { mutableStateOf(true) }
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.ingredients.count() >= 3 }.random() }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_state_enabled),
                trailing = OdsListItemTrailingSwitch(enabled, { enabled = it })
            )
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
        ) {
            recipe.ingredients.take(3).forEachIndexed { index, ingredient ->
                var checked by rememberSaveable { mutableStateOf(index == 0) }
                OdsListItem(
                    text = ingredient.food.name,
                    trailing = OdsListItemTrailingCheckbox(checked, { checked = it }, enabled)
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsCheckbox.name,
                    exhaustiveParameters = false,
                    parameters = {
                        checked(false)
                        onCheckedChange()
                        if (!enabled) enabled(false)
                    }
                )
            }
        }
    }
}