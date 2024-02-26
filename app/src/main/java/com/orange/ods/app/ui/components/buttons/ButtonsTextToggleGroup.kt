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

package com.orange.ods.app.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.annotation.ExperimentalOdsApi
import com.orange.ods.compose.component.button.OdsTextToggleButtonsRow
import com.orange.ods.compose.theme.OdsDisplaySurface

@Composable
fun ButtonsTextToggleButtonsRow(customizationState: ButtonCustomizationState) {
    val textToggleButtons =
        LocalRecipes.current.first().ingredients.take(ButtonCustomizationState.MaxToggleCount).map { ingredient ->
            OdsTextToggleButtonsRow.Item(ingredient.food.name, customizationState.isEnabled)
        }

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {
            ToggleButtonsRow(
                textToggleButtons = textToggleButtons,
                selectedIndex = selectedIndex,
                onSelectedIndexChange = { index -> selectedIndex = index },
                toggleCount = toggleCount.intValue,
                sameItemsWeight = hasSameItemsWeight,
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                ToggleButtonsRow(
                    textToggleButtons = textToggleButtons,
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = { index -> selectedIndex = index },
                    toggleCount = toggleCount.intValue,
                    sameItemsWeight = hasSameItemsWeight,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))) {
                FunctionCallCode(
                    name = OdsComposable.OdsTextToggleButtonsRow.name,
                    exhaustiveParameters = false,
                    parameters = {
                        list("textToggleButtons") {
                            textToggleButtons.take(toggleCount.intValue).forEach { item ->
                                classInstance<OdsTextToggleButtonsRow.Item> {
                                    text(item.text)
                                    enabled(customizationState.isEnabled)
                                }
                            }
                        }
                        stringRepresentation("selectedIndex", selectedIndex)
                        stringRepresentation("sameItemsWeight", hasSameItemsWeight)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalOdsApi::class)
@Composable
private fun ToggleButtonsRow(
    textToggleButtons: List<OdsTextToggleButtonsRow.Item>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    toggleCount: Int,
    sameItemsWeight: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_m))
            .padding(horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin)),
        horizontalArrangement = Arrangement.Center
    ) {
        OdsTextToggleButtonsRow(
            textToggleButtons = textToggleButtons.take(toggleCount),
            selectedIndex = selectedIndex,
            onSelectedIndexChange = onSelectedIndexChange,
            sameItemsWeight = sameItemsWeight,
            displaySurface = displaySurface
        )
    }
}