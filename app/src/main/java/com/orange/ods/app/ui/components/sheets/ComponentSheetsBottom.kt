/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.CodeImplementation
import com.orange.ods.app.ui.utilities.composable.ComponentParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextSubtitle1


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentSheetsBottom() {

    val customizationState = rememberSheetsBottomCustomizationState()

    with(customizationState) {
        ComponentCustomizationBottomSheetScaffold(
            titleResId = R.string.component_sheet_bottom_recipes,
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                val isEmpty = content.value == SheetsBottomCustomizationState.Content.Empty
                val recipes = LocalRecipes.current
                recipes.take(3).forEach { recipe ->
                    OdsListItem(
                        Modifier
                            .iconType(OdsListItemIconType.Icon)
                            .alpha(if (isEmpty) 0.0f else 1.0f),
                        icon = recipe.iconResId?.let { iconRes ->
                            { OdsListItemIcon(painterResource(id = iconRes)) }
                        },
                        text = recipe.title
                    )
                }
            }
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Column(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.screen_vertical_margin))
                        .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
                ) {
                    OdsTextBody1(text = stringResource(id = R.string.component_sheet_bottom_customize))
                    OdsTextSubtitle1(
                        text = stringResource(id = R.string.component_element_content),
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s))
                    )

                    OdsChoiceChipsFlowRow(
                        selectedChip = content,
                        outlinedChips = true,
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs))
                    ) {
                        OdsChoiceChip(textRes = R.string.component_element_empty, value = SheetsBottomCustomizationState.Content.Empty)
                        OdsChoiceChip(textRes = R.string.component_element_example, value = SheetsBottomCustomizationState.Content.Example)
                    }
                }

                CodeImplementation(OdsComponent.OdsBottomSheetScaffold.name).CodeImplementationColumn(
                    componentParameters = listOf(ComponentParameter.LambdaValueParameter("sheetContent"))
                )
            }
        }
    }
}