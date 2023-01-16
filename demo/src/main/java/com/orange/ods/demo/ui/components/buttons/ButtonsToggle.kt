/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.ButtonTechnicalTextColumn
import com.orange.ods.demo.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.demo.ui.utilities.composable.RowSurroundedTechnicalText
import com.orange.ods.demo.ui.utilities.composable.Title

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonsToggle() {
    val buttonToggleCustomizationState = rememberButtonToggleCustomizationState()

    with(buttonToggleCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                ComponentCountRow(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    title = stringResource(id = R.string.component_buttons_toggle_count),
                    count = toggleCount,
                    minusIconContentDescription = stringResource(id = R.string.component_buttons_toggle_remove),
                    plusIconContentDescription = stringResource(id = R.string.component_buttons_toggle_add),
                    minCount = ButtonToggleCustomizationState.MinToggleCount,
                    maxCount = ButtonToggleCustomizationState.MaxToggleCount
                )
            }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
            ) {
                val textRes = if (toggleCount.value > 1) R.string.component_buttons_toggle_subtitle_group else R.string.component_buttons_toggle_subtitle_single
                Title(textRes = textRes, horizontalPadding = true)

                val onCheckedToggleChanged: (MutableState<Set<Int>>, Int, Boolean) -> Unit = { checkedToggleIndexes, index, checked ->
                    with(checkedToggleIndexes) {
                        value = if (checked) value + index else value - index
                    }
                }

                val defaultCheckedToggleIndexes = rememberSaveable { mutableStateOf(emptySet<Int>()) }

                ToggleRow(
                    checkedToggleIndexes = defaultCheckedToggleIndexes.value,
                    onCheckedToggleChanged = { index, checked ->
                        onCheckedToggleChanged(defaultCheckedToggleIndexes, index, checked)
                    },
                    toggleCount = toggleCount.value
                )

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                val forcedCheckedToggleIndexes = rememberSaveable { mutableStateOf(emptySet<Int>()) }

                InvertedBackgroundColumn {
                    ToggleRow(
                        checkedToggleIndexes = forcedCheckedToggleIndexes.value,
                        onCheckedToggleChanged = { index, changed ->
                            onCheckedToggleChanged(forcedCheckedToggleIndexes, index, changed)
                        },
                        toggleCount = toggleCount.value,
                        displaySurface = displaySurface
                    )
                }

                CodeImplementationColumn {
                    if (toggleCount.value > 1) {
                        RowSurroundedTechnicalText {
                            repeat(toggleCount.value) {
                                ButtonTechnicalTextColumn(
                                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_s)),
                                    componentName = OdsComponent.OdsIconToggleButton.name,
                                    enabled = true
                                )
                            }
                        }
                    } else {
                        ButtonTechnicalTextColumn(
                            componentName = OdsComponent.OdsIconToggleButton.name,
                            enabled = true
                        )
                    }
                }
            }
        }

    }
}

@Composable
private fun ToggleRow(
    checkedToggleIndexes: Set<Int>,
    onCheckedToggleChanged: (Int, Boolean) -> Unit,
    toggleCount: Int = 1,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val iconsResources = listOf(R.drawable.ic_coffee, R.drawable.ic_cooking_pot, R.drawable.ic_ice_cream)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.spacing_m))
            .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin)),
        horizontalArrangement = Arrangement.Center
    ) {
        iconsResources.take(toggleCount).forEachIndexed { index, iconRes ->
            OdsIconToggleButton(
                checked = checkedToggleIndexes.contains(index),
                onCheckedChange = { checked ->
                    onCheckedToggleChanged(index, checked)
                },
                icon = painterResource(id = iconRes),
                contentDescription = "",
                displaySurface = displaySurface
            )
        }
    }
}
