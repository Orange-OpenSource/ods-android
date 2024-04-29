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

package com.orange.ods.app.ui.components.chips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.app.ui.utilities.extension.buildImageRequest
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.chip.OdsFilterChip
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun ChipFilter() {
    val chipCustomizationState = rememberChipCustomizationState(chipType = rememberSaveable { mutableStateOf(ChipCustomizationState.ChipType.Filter) })
    val context = LocalContext.current
    val darkModeEnabled = LocalThemeManager.current.darkModeEnabled
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.ingredients.count() >= 3 }.random() }

    with(chipCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                Subtitle(textRes = R.string.component_element_leading, horizontalPadding = true)
                val leadingElements = listOf(ChipCustomizationState.LeadingElement.None, ChipCustomizationState.LeadingElement.Avatar)
                OdsChoiceChipsFlowRow(
                    selectedChoiceChipIndex = leadingElements.indexOf(leadingElement.value),
                    modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium.dp),
                    choiceChips = leadingElements.map { leadingElement ->
                        val textResId =
                            if (leadingElement == ChipCustomizationState.LeadingElement.None) R.string.component_element_none else R.string.component_element_avatar
                        OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = textResId), { this.leadingElement.value = leadingElement })
                    }
                )

                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsListItem.TrailingSwitch(enabled.value, { enabled.value = it })
                )
            }) {
            var selectedChipIndexes by rememberSaveable { mutableStateOf(emptySet<Int>()) }
            ChipTypeDemo(chipType = chipType.value) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(OdsTheme.spacings.small.dp)
                ) {
                    recipe.ingredients.forEachIndexed { index, ingredient ->
                        OdsFilterChip(
                            text = ingredient.food.name,
                            leadingAvatar = if (hasLeadingAvatar) {
                                OdsChip.LeadingAvatar(
                                    rememberAsyncImagePainter(
                                        model = buildImageRequest(context, ingredient.food.imageUrl, darkModeEnabled),
                                        placeholder = painterResource(id = DrawableManager.getPlaceholderSmallResId()),
                                        error = painterResource(id = DrawableManager.getPlaceholderSmallResId(error = true))
                                    ), ""
                                )
                            } else null,
                            onClick = {
                                selectedChipIndexes = with(selectedChipIndexes) { if (contains(index)) minus(index) else plus(index) }
                            },
                            selected = selectedChipIndexes.contains(index),
                            enabled = isEnabled,
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(top = OdsTheme.spacings.small.dp))

                CodeImplementationColumn {
                    FunctionCallCode(
                        name = "FlowRow",
                        parameters = { simple("mainAxisSpacing", "8.dp") }
                    ) {
                        recipe.ingredients.forEachIndexed { index, ingredient ->
                            FunctionCallCode(
                                name = OdsComposable.OdsFilterChip.name,
                                exhaustiveParameters = false,
                                parameters = {
                                    text(ingredient.food.name)
                                    if (hasLeadingAvatar) {
                                        classInstance<OdsChip.LeadingAvatar>("leadingAvatar") {
                                            image()
                                            contentDescription("")
                                        }
                                    }
                                    onClick()
                                    if (selectedChipIndexes.contains(index)) selected(true)
                                    if (!isEnabled) enabled(false)
                                })
                        }
                    }
                }
            }
        }
    }
}
