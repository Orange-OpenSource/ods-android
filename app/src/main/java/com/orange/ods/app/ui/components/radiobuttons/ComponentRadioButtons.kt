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

package com.orange.ods.app.ui.components.radiobuttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
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
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentRadioButtons() {
    var enabled by rememberSaveable { mutableStateOf(true) }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_state_enabled),
                trailing = OdsListItem.TrailingSwitch(enabled, { enabled = it })
            )
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = OdsTheme.spacings.small)
        ) {
            val recipes = LocalRecipes.current.take(3)
            var selectedRecipe by rememberSaveable { mutableStateOf(recipes.firstOrNull()) }
            Column(modifier = Modifier.selectableGroup()) {
                recipes.forEach { recipe ->
                    OdsListItem(
                        text = recipe.title,
                        trailing = OdsListItem.TrailingRadioButton(selectedRecipe == recipe, { selectedRecipe = recipe }, enabled)
                    )
                }
            }

            CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))) {
                FunctionCallCode(
                    name = OdsComposable.OdsRadioButton.name,
                    exhaustiveParameters = false,
                    parameters = {
                        selected(false)
                        onClick()
                        if (!enabled) enabled(false)
                    }
                )
            }
        }
    }
}