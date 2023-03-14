/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.chips

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.compose.component.chip.OdsChip
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.LocalMainThemeManager
import com.orange.ods.app.ui.components.chips.ChipCustomizationState.ChipType
import com.orange.ods.app.ui.components.chips.ChipCustomizationState.LeadingElement
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.theme.OdsComponentsConfiguration.ComponentStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip() {
    val context = LocalContext.current
    val chipCustomizationState = rememberChipCustomizationState()

    with(chipCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                Subtitle(textRes = R.string.component_type, horizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    selectedChip = chipType,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    outlinedChips = true
                ) {
                    OdsChoiceChip(
                        textRes = R.string.component_chip_type_input,
                        value = ChipType.Input,
                        modifier = Modifier.chipTypeSemantics(
                            context = context, focusedChipType = ChipType.Input, selectedChipType = chipType.value
                        )
                    )
                    OdsChoiceChip(
                        textRes = R.string.component_chip_type_choice, value = ChipType.Choice,
                        modifier = Modifier.chipTypeSemantics(context = context, focusedChipType = ChipType.Choice, selectedChipType = chipType.value)
                    )
                    OdsChoiceChip(
                        textRes = R.string.component_chip_type_action, value = ChipType.Action,
                        modifier = Modifier.chipTypeSemantics(context = context, focusedChipType = ChipType.Action, selectedChipType = chipType.value)
                    )
                }

                if (isInputChip) {
                    Subtitle(textRes = R.string.component_element_leading, horizontalPadding = true)
                    OdsChoiceChipsFlowRow(
                        selectedChip = leadingElement,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        outlinedChips = true
                    ) {
                        OdsChoiceChip(textRes = R.string.component_element_none, value = LeadingElement.None)
                        OdsChoiceChip(textRes = R.string.component_element_avatar, value = LeadingElement.Avatar)
                        OdsChoiceChip(textRes = R.string.component_element_icon, value = LeadingElement.Icon)
                    }
                } else {
                    resetLeadingElement()
                }

                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsSwitchTrailing(
                        checked = enabled
                    )
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
            .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
    ) {
        Subtitle(textRes = chipType.nameRes)
        OdsTextBody2(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs), bottom = dimensionResource(id = R.dimen.spacing_s)),
            text = stringResource(id = chipType.descriptionRes)
        )
        content()
    }
}

private fun Modifier.chipTypeSemantics(context: Context, focusedChipType: ChipType, selectedChipType: ChipType) = this.semantics {
    stateDescription = if (selectedChipType == focusedChipType) {
        "${context.getString(R.string.state_selected)}\n${context.getString(focusedChipType.nameRes)}\n${context.getString(focusedChipType.descriptionRes)}"
    } else context.getString(R.string.state_not_selected)
}

@Composable
private fun Chip(chipCustomizationState: ChipCustomizationState) {
    val context = LocalContext.current
    val outlinedChips = LocalMainThemeManager.current.currentThemeConfiguration.componentsConfiguration.chipStyle == ComponentStyle.Outlined
    val cancelCrossLabel = stringResource(id = R.string.component_element_cancel_cross)
    val recipes = LocalRecipes.current.take(4)

    with(chipCustomizationState) {
        if (isChoiceChip) {
            OdsChoiceChipsFlowRow(selectedChip = choiceChipIndexSelected, outlinedChips = outlinedChips) {
                recipes.forEachIndexed { index, recipe ->
                    OdsChoiceChip(
                        text = recipe.title,
                        value = index,
                        enabled = isEnabled
                    )
                }
            }
        } else {
            val recipe = recipes.firstOrNull()
            OdsChip(
                text = recipe?.title.orEmpty(),
                onClick = { clickOnElement(context, recipe?.title.orEmpty()) },
                outlined = outlinedChips,
                leadingIcon = if (isActionChip || hasLeadingIcon) recipe?.iconResId?.let { painterResource(id = it) } else null,
                leadingAvatar = if (hasLeadingAvatar) {
                    rememberAsyncImagePainter(
                        model = recipe?.imageUrl,
                        placeholder = painterResource(id = R.drawable.placeholder_small),
                        error = painterResource(id = R.drawable.placeholder_small)
                    )
                } else null,
                enabled = isEnabled,
                onCancel = if (isInputChip) {
                    { clickOnElement(context, cancelCrossLabel) }
                } else null
            )
        }
    }
}