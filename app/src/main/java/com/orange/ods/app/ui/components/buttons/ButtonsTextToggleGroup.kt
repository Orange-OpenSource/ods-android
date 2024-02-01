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
    var selectedTextButtonIndex by rememberSaveable { mutableIntStateOf(0) }
    val textButtons = LocalRecipes.current
        .first()
        .ingredients
        .take(customizationState.toggleCount.intValue)
        .mapIndexed { index, ingredient ->
            OdsTextToggleButtonsRow.TextButton(ingredient.food.name, { selectedTextButtonIndex = index }, customizationState.isEnabled)
        }

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {
            ToggleButtonsRow(
                selectedTextButtonIndex = selectedTextButtonIndex,
                textButtons = textButtons,
                sameItemsWeight = hasSameItemsWeight,
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                ToggleButtonsRow(
                    selectedTextButtonIndex = selectedTextButtonIndex,
                    textButtons = textButtons,
                    sameItemsWeight = hasSameItemsWeight,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))) {
                FunctionCallCode(
                    name = OdsComposable.OdsTextToggleButtonsRow.name,
                    exhaustiveParameters = false,
                    parameters = {
                        stringRepresentation("selectedTextButtonIndex", selectedTextButtonIndex)
                        list("textToggleButtons") {
                            textButtons.forEach { textButton ->
                                classInstance<OdsTextToggleButtonsRow.TextButton> {
                                    text(textButton.text)
                                    onClick()
                                    enabled(customizationState.isEnabled)
                                }
                            }
                        }
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
    selectedTextButtonIndex: Int,
    textButtons: List<OdsTextToggleButtonsRow.TextButton>,
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
            selectedTextButtonIndex = selectedTextButtonIndex,
            textButtons = textButtons,
            sameItemsWeight = sameItemsWeight,
            displaySurface = displaySurface
        )
    }
}