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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.orange.ods.app.R
import com.orange.ods.app.databinding.OdsIconToggleButtonsGroupBinding
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.components.buttons.InvertedBackgroundColumn
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRow
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRowItem
import com.orange.ods.compose.theme.OdsDisplaySurface

@Composable
fun ButtonsIconToggleGroup(customizationState: ButtonIconCustomizationState) {
    val iconToggleButtons =
        LocalRecipes.current.distinctBy { it.iconResId }.filter { it.iconResId != null }.take(ButtonIconCustomizationState.MaxToggleCount).map { recipe ->
            OdsIconToggleButtonsRowItem(painterResource(id = recipe.iconResId!!), recipe.title, customizationState.enabled.value)
        }

    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {

            ToggleButtonsRow(
                iconToggleButtons = iconToggleButtons,
                selectedIndex = selectedIndex,
                onSelectedIndexChange = { index -> selectedIndex = index },
                toggleCount = toggleCount.value
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                ToggleButtonsRow(
                    iconToggleButtons = iconToggleButtons,
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = { index -> selectedIndex = index },
                    toggleCount = toggleCount.value,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                xmlAvailable = true
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsIconToggleButtonsRow.name,
                    exhaustiveParameters = false,
                    parameters = {
                        list("iconsToggleButtons") {
                            repeat(toggleCount.value) {
                                classInstance(OdsIconToggleButtonsRowItem::class.java) {
                                    painter()
                                    string("iconDescription", "icon description")
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
    iconToggleButtons: List<OdsIconToggleButtonsRowItem>,
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
        val buttons = iconToggleButtons.take(toggleCount)
        UiFramework<OdsIconToggleButtonsGroupBinding>(
            compose = {
                OdsIconToggleButtonsRow(
                    iconToggleButtons = buttons,
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = onSelectedIndexChange,
                    displaySurface = displaySurface
                )
            }, xml = {
                this.odsIconToggleButtonsRow.iconToggleButtons = buttons
                this.selectedIndex = selectedIndex
                this.displaySurface = displaySurface
                this.odsIconToggleButtonsRow.onSelectedIndexChange = onSelectedIndexChange
            }
        )
    }
}