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
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
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
                        modifier = Modifier.alpha(if (isEmpty) 0.0f else 1.0f),
                        leadingIcon = recipe.iconResId?.let { iconRes ->
                            OdsListItem.LeadingIcon(OdsListItem.LeadingIcon.Type.Icon, painterResource(id = iconRes), "")
                        },
                        text = recipe.title
                    )
                }
            }
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Column(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
                        .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
                ) {
                    OdsTextBody1(text = stringResource(id = R.string.component_sheet_bottom_customize))
                    OdsTextSubtitle1(
                        text = stringResource(id = R.string.component_element_content),
                        modifier = Modifier.padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
                    )
                    OdsChoiceChipsFlowRow(
                        value = content.value,
                        onValueChange = { value -> content.value = value },
                        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                        chips = listOf(
                            OdsChoiceChip(text = stringResource(id = R.string.component_element_empty), value = SheetsBottomCustomizationState.Content.Empty),
                            OdsChoiceChip(
                                text = stringResource(id = R.string.component_element_example),
                                value = SheetsBottomCustomizationState.Content.Example
                            )
                        )
                    )
                }

                CodeImplementationColumn {
                    FunctionCallCode(
                        name = OdsComposable.OdsBottomSheetScaffold.name,
                        exhaustiveParameters = false,
                        parameters = { lambda("sheetContent") }
                    )
                }
            }
        }
    }
}