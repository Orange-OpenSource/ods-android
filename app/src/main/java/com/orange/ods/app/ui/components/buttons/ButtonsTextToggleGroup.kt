/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsTextToggleButtonsRow
import com.orange.ods.compose.component.button.OdsTextToggleButtonsRowItem
import com.orange.ods.compose.theme.OdsDisplaySurface

@Composable
fun ButtonsTextToggleButtonsRow(customizationState: ButtonCustomizationState) {
    val textToggleButtons =
        LocalRecipes.current.distinctBy { it.title }.take(ButtonCustomizationState.MaxToggleCount).map { recipe ->
            OdsTextToggleButtonsRowItem(recipe.title, customizationState.isEnabled)
        }

    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            ToggleButtonsRow(
                textToggleButtons = textToggleButtons,
                selectedIndex = selectedIndex,
                onSelectedIndexChange = { index -> selectedIndex = index },
                toggleCount = toggleCount.value
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                ToggleButtonsRow(
                    textToggleButtons = textToggleButtons,
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = { index -> selectedIndex = index },
                    toggleCount = toggleCount.value,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsTextToggleButtonsRow.name,
                    exhaustiveParameters = false,
                    parameters = {
                        list("textToggleButtons") {
                            repeat(toggleCount.value) {
                                classInstance(OdsTextToggleButtonsRowItem::class.java) {
                                    string("text", "text bottom")
                                    selected(customizationState.isEnabled)
                                }
                            }
                        }
                        stringRepresentation("selectedButtonIndex", selectedIndex)
                    }
                )
            }
        }
    }
}

@Composable
private fun ToggleButtonsRow(
    textToggleButtons: List<OdsTextToggleButtonsRowItem>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    toggleCount: Int,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.spacing_m))
            .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin)),
        horizontalArrangement = Arrangement.Center
    ) {
        OdsTextToggleButtonsRow(
            textToggleButtons = textToggleButtons.take(toggleCount),
            selectedIndex = selectedIndex,
            onSelectedIndexChange = onSelectedIndexChange,
            displaySurface = displaySurface
        )
    }
}