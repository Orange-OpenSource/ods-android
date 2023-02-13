/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.sheets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.demo.R
import com.orange.ods.demo.domain.recipes.LocalRecipes
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.demo.ui.utilities.composable.CommonTechnicalTextColumn
import com.orange.ods.demo.ui.utilities.composable.TechnicalText


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentSheetBottom() {

    val sheetsbottomCustomizationState = rememberSheetsbottomCustomizationState()

    with(sheetsbottomCustomizationState) {


        ComponentCustomizationBottomSheetScaffold(
            titleResId = R.string.component_sheet_recipes,
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),

            bottomSheetContent = {
                when (content.value) {
                    SheetsbottomCustomizationState.Content.Empty -> {
                        Box(modifier = Modifier.height(200.dp))
                    }

                    SheetsbottomCustomizationState.Content.Example -> {
                        val recipes = LocalRecipes.current
                        recipes.take(3).forEach { recipe ->
                            OdsListItem(
                                Modifier.iconType(OdsListItemIconType.Icon),
                                icon = recipe.iconResId?.let { iconRes ->
                                    {
                                        OdsListItemIcon(
                                            painterResource(id = iconRes)
                                        )
                                    }
                                },
                                text = recipe.title
                            )
                        }
                    }
                }
            }
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.screen_vertical_margin))
                        .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
                ) {
                    OdsTextBody1(text = stringResource(id = R.string.component_sheet_customize))

                    OdsTextSubtitle1(
                        text = stringResource(id = R.string.component_content),
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s))
                    )

                    OdsChoiceChipsFlowRow(
                        selectedChip = content,
                        outlinedChips = true,
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs))
                    ) {
                        OdsChoiceChip(textRes = R.string.component_sheet_empty, value = SheetsbottomCustomizationState.Content.Empty)
                        OdsChoiceChip(textRes = R.string.component_sheet_example, value = SheetsbottomCustomizationState.Content.Example)
                    }
                }

                CodeImplementationColumn {
                    CommonTechnicalTextColumn(
                        componentName = OdsComponent.OdsBottomSheetScaffold.name
                    ) {
                        TechnicalText(text = "sheetContent = {")
                        TechnicalText(text = "  // add your content here")
                        TechnicalText(text = "}")
                    }
                }
            }
        }
    }
}