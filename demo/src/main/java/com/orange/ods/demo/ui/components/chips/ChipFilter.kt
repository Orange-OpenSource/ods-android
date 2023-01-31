/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.chips

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.chip.OdsFilterChip
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.demo.R
import com.orange.ods.demo.domain.recipes.Ingredient
import com.orange.ods.demo.domain.recipes.LocalRecipes
import com.orange.ods.demo.ui.LocalMainThemeManager
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.theme.OdsComponentsConfiguration.ComponentStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipFilter() {
    val chipCustomizationState = rememberChipCustomizationState(chipType = rememberSaveable { mutableStateOf(ChipCustomizationState.ChipType.Filter) })
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.ingredients.count() >= 3 }.random() }

    with(chipCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {

                Subtitle(textRes = R.string.component_element_leading, horizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    selectedChip = leadingElement,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    outlinedChips = true
                ) {
                    OdsChoiceChip(textRes = R.string.component_element_none, value = ChipCustomizationState.LeadingElement.None)
                    OdsChoiceChip(textRes = R.string.component_element_avatar, value = ChipCustomizationState.LeadingElement.Avatar)
                }

                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsSwitchTrailing(
                        checked = enabled
                    )
                )
            }) {
            ChipTypeDemo(chipType = chipType.value) {
                FlowRow(modifier = Modifier.fillMaxWidth(), mainAxisSpacing = dimensionResource(id = R.dimen.spacing_s)) {
                    recipe.ingredients.forEach { ingredient ->
                        FilterChip(ingredient = ingredient, customizationState = chipCustomizationState)
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterChip(ingredient: Ingredient, customizationState: ChipCustomizationState) {
    val outlinedChips = LocalMainThemeManager.current.currentThemeConfiguration.components.chipStyle == ComponentStyle.Outlined
    val selected = rememberSaveable { mutableStateOf(false) }
    OdsFilterChip(
        text = ingredient.food.name,
        leadingAvatar = if (customizationState.hasLeadingAvatar) {
            rememberAsyncImagePainter(
                model = ingredient.food.imageUrl,
                placeholder = painterResource(id = R.drawable.placeholder_small),
                error = painterResource(id = R.drawable.placeholder_small)
            )
        } else null,
        onClick = { selected.value = !selected.value },
        outlined = outlinedChips,
        selected = selected.value,
        enabled = customizationState.isEnabled,
    )
}