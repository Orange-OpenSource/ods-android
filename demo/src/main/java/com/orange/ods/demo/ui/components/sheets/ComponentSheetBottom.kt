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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.demo.R
import com.orange.ods.demo.domain.recipes.LocalRecipes
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.demo.ui.utilities.composable.FloatingActionButtonTechnicalTextColumn


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentSheetBottom() {

    val sheetsbottomCustomizationState = rememberSheetsbottomCustomizationState()

    with(sheetsbottomCustomizationState) {


        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),

            bottomSheetContent = {
                when (content.value) {
                    SheetsbottomCustomizationState.Content.Empty -> {
                        OdsListItem(
                            text = ""
                        )
                    }

                    SheetsbottomCustomizationState.Content.Example -> {
                        val recipes = LocalRecipes.current
                        recipes.forEach { recipe ->
                            OdsListItem(
                                //icon = recipe.iconResId?.let { iconRes ->
                                //   {
                                //       OdsListItemIcon(
                                //          painter = painterResource(id = iconRes),
                                //          contentDescription = null
                                //     )
                                //}
                                //},
                                text = recipe.title
                            )
                        }

                    }

                }
            }
        ) {
            Column() {
                Column(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.screen_vertical_margin))
                        .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
                ) {
                    OdsTextH6(text = stringResource(id = R.string.component_sheet_customize))
                    OdsTextCaption(text = stringResource(id = R.string.component_content))

                    OdsChoiceChipsFlowRow(
                        selectedChip = content,
                        outlinedChips = true
                    ) {
                        OdsChoiceChip(textRes = R.string.component_sheet_empty, value = SheetsbottomCustomizationState.Content.Empty)
                        OdsChoiceChip(textRes = R.string.component_sheet_example, value = SheetsbottomCustomizationState.Content.Example)
                    }
                }

                CodeImplementationColumn {
                    FloatingActionButtonTechnicalTextColumn(
                        componentName = ""
                    )
                }
            }
        }
    }
}