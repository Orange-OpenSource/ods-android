/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons.icons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.buttons.InvertedBackgroundColumn
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CommonButtonTechnicalTextColumn
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRow
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRowItem
import com.orange.ods.compose.theme.OdsDisplaySurface

@Composable
fun ButtonsIconToggleGroup(customizationState: ButtonIconCustomizationState) {
    val iconToggleButtons =
        LocalRecipes.current.distinctBy { it.iconResId }.filter { it.iconResId != null }.take(ButtonIconCustomizationState.MaxToggleCount).map { recipe ->
            OdsIconToggleButtonsRowItem(painterResource(id = recipe.iconResId!!), recipe.title)
        }

    val selectedButtonIndex = rememberSaveable { mutableStateOf(0) }

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            ToggleButtonsRow(
                iconToggleButtons = iconToggleButtons,
                selectedButtonIndex = selectedButtonIndex,
                toggleCount = toggleCount.value
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                ToggleButtonsRow(
                    iconToggleButtons = iconToggleButtons,
                    selectedButtonIndex = selectedButtonIndex,
                    toggleCount = toggleCount.value,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn {
                CommonButtonTechnicalTextColumn(componentName = OdsComponent.OdsIconToggleButtonsRow.name) {
                    TechnicalText(text = "iconsToggleButtons = listOf(")
                    repeat(iconToggleButtons.take(toggleCount.value).size) {
                        TechnicalText(text = "  OdsIconToggleButtonsRowItem(")
                        TechnicalText(text = "    // ...")
                        TechnicalText(text = "  ),")
                    }
                    TechnicalText(text = "),")
                    TechnicalText(text = "selectedButtonIndex = ${selectedButtonIndex.value}")
                }
            }
        }
    }
}

@Composable
private fun ToggleButtonsRow(
    iconToggleButtons: List<OdsIconToggleButtonsRowItem>,
    selectedButtonIndex: MutableState<Int>,
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
        OdsIconToggleButtonsRow(
            iconToggleButtons = iconToggleButtons.take(toggleCount),
            selectedButtonIndex = selectedButtonIndex,
            displaySurface = displaySurface
        )
    }
}