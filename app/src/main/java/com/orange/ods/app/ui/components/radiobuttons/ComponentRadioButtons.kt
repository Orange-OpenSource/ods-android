/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.radiobuttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
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
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsRadioButtonTrailing
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentRadioButtons() {
    val enabled = rememberSaveable { mutableStateOf(true) }

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
            val recipes = LocalRecipes.current.take(3)
            val selectedRadio = rememberSaveable { mutableStateOf(recipes.firstOrNull()?.title) }
            Column(modifier = Modifier.selectableGroup()) {
                recipes.forEach { recipe ->
                    OdsListItem(
                        text = recipe.title,
                        trailing = OdsRadioButtonTrailing(
                            selectedRadio = selectedRadio,
                            currentRadio = recipe.title,
                            enabled = enabled.value
                        )
                    )
                }
            }

            CodeImplementation(OdsComponent.OdsRadioButton.name).CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                componentParameters = mutableListOf(
                    ComponentParameter.Selected(false),
                    ComponentParameter.OnClick
                ).apply {
                    if (!enabled.value) add(ComponentParameter.Enabled(false))
                }
            )
        }
    }
}