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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.chips.ChipCustomizationState.ChipType
import com.orange.ods.app.ui.components.chips.ChipCustomizationState.LeadingElement
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.app.ui.utilities.extension.buildImageRequest
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.extension.orElse
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip(variant: Variant) {
    val chipCustomizationState = rememberChipCustomizationState(chipType = rememberSaveable { mutableStateOf(ChipType.fromVariant(variant)) })

    with(chipCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                if (isInputChip) {
                    Subtitle(textRes = R.string.component_element_leading, horizontalPadding = true)
                    OdsChoiceChipsFlowRow(
                        selectedChoiceChipIndex = ChipCustomizationState.LeadingElement.entries.indexOf(leadingElement.value),
                        modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium),
                        choiceChips = ChipCustomizationState.LeadingElement.entries.map { leadingElement ->
                            val textResId = when (leadingElement) {
                                LeadingElement.None -> R.string.component_element_none
                                LeadingElement.Avatar -> R.string.component_element_avatar
                                LeadingElement.Icon -> R.string.component_element_icon
                            }
                            OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = textResId), { this.leadingElement.value = leadingElement })
                        }
                    )
                } else {
                    resetLeadingElement()
                }

                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsListItem.TrailingSwitch(enabled.value, { enabled.value = it })
                )
            }) {
            ChipTypeDemo(chipType.value) {
                Chip(chipCustomizationState = chipCustomizationState)
            }
        }
    }
}

@Composable
fun ChipTypeDemo(chipType: ChipType, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(
                horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin),
                vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin)
            )
    ) {
        OdsText(
            modifier = Modifier.padding(bottom = OdsTheme.spacings.small),
            text = stringResource(id = chipType.descriptionRes),
            style = OdsTheme.typography.bodyMedium
        )
        content()
    }
}

@Composable
private fun Chip(chipCustomizationState: ChipCustomizationState) {
    val context = LocalContext.current
    val cancelCrossLabel = stringResource(id = R.string.component_element_cancel_cross)
    val recipes = LocalRecipes.current.take(4)

    with(chipCustomizationState) {
        if (isChoiceChip) {
            OdsChoiceChipsFlowRow(
                selectedChoiceChipIndex = selectedChoiceChipIndex.value.orElse { 0 },
                modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium),
                choiceChips = recipes.mapIndexed { index, recipe ->
                    OdsChoiceChipsFlowRow.ChoiceChip(
                        text = recipe.title,
                        { selectedChoiceChipIndex.value = index },
                        enabled = isEnabled
                    )
                }
            )

            Spacer(modifier = Modifier.padding(top = OdsTheme.spacings.small))

            CodeImplementationColumn {
                FunctionCallCode(
                    name = OdsComposable.OdsChoiceChipsFlowRow.name,
                    parameters = {
                        stringRepresentation("selectedChoiceChipIndex", selectedChoiceChipIndex.value.toString())
                        list("choiceChips") {
                            recipes.forEach { recipe ->
                                classInstance<OdsChoiceChipsFlowRow.ChoiceChip> {
                                    text(recipe.title)
                                    if (!isEnabled) enabled(false)
                                    onClick()
                                }
                            }
                        }
                    }
                )
            }
        } else {
            val recipe = recipes.firstOrNull()
            OdsChip(
                text = recipe?.title.orEmpty(),
                onClick = { clickOnElement(context, recipe?.title.orEmpty()) },
                leading = when {
                    isActionChip || hasLeadingIcon -> recipe?.iconResId?.let { OdsChip.LeadingIcon(painterResource(id = it), "") }
                    hasLeadingAvatar -> {
                        val darkModeEnabled = LocalThemeManager.current.darkModeEnabled
                        OdsChip.LeadingAvatar(
                            rememberAsyncImagePainter(
                                model = buildImageRequest(context, recipe?.imageUrl, darkModeEnabled),
                                placeholder = painterResource(id = DrawableManager.getPlaceholderSmallResId()),
                                error = painterResource(id = DrawableManager.getPlaceholderSmallResId(error = true))
                            ), ""
                        )
                    }
                    else -> null
                },
                enabled = isEnabled,
                onCancel = if (isInputChip) {
                    { clickOnElement(context, cancelCrossLabel) }
                } else null
            )

            Spacer(modifier = Modifier.padding(top = OdsTheme.spacings.small))

            CodeImplementationColumn {
                val leadingParameterName = "leading"
                FunctionCallCode(
                    name = OdsComposable.OdsChip.name,
                    exhaustiveParameters = false,
                    parameters = {
                        text(recipe?.title.orEmpty())
                        when {
                            isActionChip || hasLeadingIcon ->
                                classInstance<OdsChip.LeadingIcon>(leadingParameterName) {
                                    painter()
                                    contentDescription("")
                                }
                            hasLeadingAvatar ->
                                classInstance<OdsChip.LeadingAvatar>(leadingParameterName) {
                                    image()
                                    contentDescription("")
                                }
                        }
                        if (!isEnabled) enabled(false)
                        onClick()
                        if (isInputChip) lambda("onCancel")
                    })
            }
        }
    }
}