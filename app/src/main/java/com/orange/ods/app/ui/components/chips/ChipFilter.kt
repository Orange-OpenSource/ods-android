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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.LocalMainThemeManager
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.ImagePainterValue
import com.orange.ods.app.ui.utilities.composable.PredefinedParameter
import com.orange.ods.app.ui.utilities.composable.SimpleParameter
import com.orange.ods.app.ui.utilities.composable.StringRepresentationParameter
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.chip.OdsFilterChip
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.theme.OdsComponentsConfiguration.ComponentStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipFilter() {
    val chipCustomizationState = rememberChipCustomizationState(chipType = rememberSaveable { mutableStateOf(ChipCustomizationState.ChipType.Filter) })
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.ingredients.count() >= 3 }.random() }
    val outlinedChips = LocalMainThemeManager.current.currentThemeConfiguration.componentsConfiguration.chipStyle == ComponentStyle.Outlined

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
            var selectedChipIndexes by rememberSaveable { mutableStateOf(emptySet<Int>()) }
            ChipTypeDemo(chipType = chipType.value) {
                FlowRow(modifier = Modifier.fillMaxWidth(), mainAxisSpacing = dimensionResource(id = R.dimen.spacing_s)) {
                    recipe.ingredients.forEachIndexed { index, ingredient ->
                        OdsFilterChip(
                            text = ingredient.food.name,
                            leadingAvatar = if (hasLeadingAvatar) {
                                rememberAsyncImagePainter(
                                    model = ingredient.food.imageUrl,
                                    placeholder = painterResource(id = R.drawable.placeholder_small),
                                    error = painterResource(id = R.drawable.placeholder_small)
                                )
                            } else null,
                            onClick = {
                                selectedChipIndexes = with(selectedChipIndexes) { if (contains(index)) minus(index) else plus(index) }
                            },
                            outlined = outlinedChips,
                            selected = selectedChipIndexes.contains(index),
                            enabled = isEnabled,
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                CodeImplementationColumn {
                    FunctionCallCode(
                        name = "FlowRow",
                        parameters = listOf(SimpleParameter("mainAxisSpacing", "dimensionResource(id = R.dimen.spacing_s))"))
                    ) {
                        recipe.ingredients.forEachIndexed { index, ingredient ->
                            FunctionCallCode(
                                name = OdsComponent.OdsFilterChip.name,
                                exhaustiveParameters = false,
                                parameters = buildList {
                                    add(PredefinedParameter.Text(ingredient.food.name))
                                    if (hasLeadingAvatar) add(SimpleParameter("leadingAvatar", ImagePainterValue))
                                    add(PredefinedParameter.OnClick)
                                    if (!outlinedChips) add(StringRepresentationParameter("outlined", outlinedChips))
                                    if (selectedChipIndexes.contains(index)) add(PredefinedParameter.Selected(true))
                                    if (!isEnabled) add(PredefinedParameter.Enabled(false))
                                })
                        }
                    }
                }
            }
        }
    }
}
