/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.switches

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
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.ComponentCode
import com.orange.ods.app.ui.utilities.composable.TextValueParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentSwitches() {
    val enabled = rememberSaveable { mutableStateOf(true) }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_state_enabled),
                trailing = OdsSwitchTrailing(
                    checked = enabled,
                )
            )
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.spacing_s))
        ) {
            val recipes = LocalRecipes.current.take(3)
            recipes.forEach { recipe ->
                val checked = rememberSaveable { mutableStateOf(false) }
                OdsListItem(
                    text = recipe.title,
                    trailing = OdsSwitchTrailing(
                        checked = checked,
                        enabled = enabled.value
                    )
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            ) {
                ComponentCode(name = OdsComponent.OdsSwitch.name, parameters = mutableListOf(
                    TextValueParameter.Checked(false),
                    TextValueParameter.OnCheckedChange
                ).apply {
                    if (!enabled.value) add(TextValueParameter.Enabled(false))
                })
            }
        }
    }
}
