/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.app.ui.components.floatingactionbuttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsExtendedFloatingActionButton
import com.orange.ods.compose.component.button.OdsFloatingActionButton
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentFloatingActionButton() {
    val context = LocalContext.current
    val fabCustomizationState = rememberFabCustomizationState()

    with(fabCustomizationState) {
        if (size.value == FabCustomizationState.Size.Mini) {
            resetTextAndWidth()
        }

        val modifier = Modifier
            .let {
                if (isFullScreenWidth) it
                    .padding(
                        start = dimensionResource(id = com.orange.ods.R.dimen.spacing_m),
                        end = dimensionResource(id = com.orange.ods.R.dimen.spacing_m),
                        bottom = 64.dp
                    )
                    .fillMaxWidth()
                else it
            }

        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            floatingActionButton = {
                if (hasText) {
                    OdsExtendedFloatingActionButton(
                        onClick = {
                            clickOnElement(context, context.getString(R.string.component_floating_action_button))
                        },
                        text = stringResource(id = R.string.component_floating_action_button_add),
                        icon = OdsFloatingActionButton.Icon(painterResource(id = R.drawable.ic_plus), ""),
                        modifier = modifier
                    )
                } else {
                    OdsFloatingActionButton(
                        onClick = {
                            clickOnElement(context, context.getString(R.string.component_floating_action_button))
                        },
                        mini = size.value == FabCustomizationState.Size.Mini,
                        icon = OdsFloatingActionButton.Icon(
                            painterResource(id = R.drawable.ic_plus),
                            stringResource(id = R.string.component_floating_action_button_add)
                        ),
                        modifier = modifier
                    )
                }
            },
            floatingActionButtonPosition = if (isFullScreenWidth) FabPosition.Center else FabPosition.End,
            bottomSheetContent = {
                Subtitle(textRes = R.string.component_size, horizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    selectedChoiceChipIndex = FabCustomizationState.Size.entries.indexOf(size.value),
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                    choiceChips = FabCustomizationState.Size.entries.map { size ->
                        val textResId = when (size) {
                            FabCustomizationState.Size.Default -> R.string.component_floating_action_button_size_default
                            FabCustomizationState.Size.Mini -> R.string.component_floating_action_button_size_mini
                        }
                        OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = textResId), { this.size.value = size })
                    }
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_element_text),
                    trailing = OdsListItem.TrailingSwitch(text.value, { text.value = it }, isTextEnabled)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_floating_action_button_full_screen_width),
                    trailing = OdsListItem.TrailingSwitch(fullScreenWidth.value, { fullScreenWidth.value = it }, isFullScreenWidthEnabled)
                )
            }) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                val usedComponentName = if (hasText) OdsComposable.OdsExtendedFloatingActionButton.name else OdsComposable.OdsFloatingActionButton.name
                CodeImplementationColumn(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
                ) {
                    FunctionCallCode(
                        name = usedComponentName,
                        exhaustiveParameters = false,
                        parameters = {
                            classInstance<OdsFloatingActionButton.Icon>("icon") {
                                painter()
                                contentDescription("")
                            }
                            if (this@with.size.value == FabCustomizationState.Size.Mini) stringRepresentation("mini", true)
                            if (hasText) string("text", "Add")
                            if (isFullScreenWidth) fillMaxWidth()
                        }
                    )
                }
            }
        }
    }
}