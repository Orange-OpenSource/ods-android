/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.imageitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.imageitem.OdsImageItem
import com.orange.ods.compose.component.listitem.OdsListItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentImageItem() {
    val context = LocalContext.current
    val imageItemCustomizationState = rememberImageItemCustomizationState()
    var iconChecked by rememberSaveable { mutableStateOf(false) }
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.random() }

    with(imageItemCustomizationState) {
        if (type.value == OdsImageItem.LegendAreaDisplayType.None) {
            iconDisplayed.value = false
        }
        if (!hasIcon) {
            iconChecked = false
        }
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                Subtitle(textRes = R.string.component_image_item_legend_area_display_type, horizontalPadding = true)
                OdsChoiceChipsFlowRow(
                    value = type.value,
                    onValueChange = { value -> type.value = value },
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                    chips = listOf(
                        OdsChoiceChip(
                            text = stringResource(R.string.component_image_item_legend_area_display_type_overlay),
                            value = OdsImageItem.LegendAreaDisplayType.Overlay
                        ),
                        OdsChoiceChip(
                            text = stringResource(R.string.component_image_item_legend_area_display_type_below),
                            value = OdsImageItem.LegendAreaDisplayType.Below
                        ),
                        OdsChoiceChip(text = stringResource(R.string.component_element_none), value = OdsImageItem.LegendAreaDisplayType.None),
                    )
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsListItem.TrailingSwitch(iconDisplayed.value, { iconDisplayed.value = it }, hasText)
                )
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        top = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin),
                        start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)
                    ),
                horizontalAlignment = Alignment.Start,
            ) {
                val imageSize = 200.dp
                val height = when (type.value) {
                    OdsImageItem.LegendAreaDisplayType.Below -> imageSize + dimensionResource(id = com.orange.ods.R.dimen.image_item_legend_area_height)
                    OdsImageItem.LegendAreaDisplayType.Overlay,
                    OdsImageItem.LegendAreaDisplayType.None -> imageSize
                }
                OdsImageItem(
                    modifier = Modifier
                        .width(imageSize)
                        .height(height),
                    onClick = { clickOnElement(context, context.getString(R.string.component_image_item)) },
                    image = OdsImageItem.Image(
                        rememberAsyncImagePainter(
                            model = recipe.imageUrl,
                            placeholder = painterResource(id = R.drawable.placeholder),
                            error = painterResource(id = R.drawable.placeholder)
                        ), ""
                    ),
                    title = if (hasText) recipe.title else null,
                    legendAreaDisplayType = type.value,
                    icon = if (hasIcon) {
                        OdsImageItem.IconToggleButton(
                            uncheckedIcon = OdsIconButton.Icon(
                                painterResource(id = R.drawable.ic_heart_outlined),
                                stringResource(id = R.string.component_button_icon_toggle_favorite_add_icon_desc)
                            ),
                            checkedIcon = OdsIconButton.Icon(
                                painterResource(id = R.drawable.ic_heart),
                                stringResource(id = R.string.component_button_icon_toggle_favorite_remove_icon_desc)
                            ),
                            checked = iconChecked,
                            onCheckedChange = { checked -> iconChecked = checked },
                        )
                    } else {
                        null
                    }
                )
                CodeImplementationColumn(
                    modifier = Modifier.padding(end = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                ) {
                    FunctionCallCode(
                        name = OdsComposable.OdsImageItem.name,
                        exhaustiveParameters = false,
                        parameters = {
                            enum("legendAreaDisplayType", type.value)
                            if (hasText) title(recipe.title)
                            classInstance<OdsImageItem.Image>("image") {
                                painter()
                                contentDescription("")
                            }
                            if (hasIcon) {
                                classInstance<OdsImageItem.IconToggleButton>("icon") {
                                    checked(iconChecked)
                                    lambda("onCheckedChange")
                                    classInstance<OdsIconButton.Icon>("uncheckedIcon") {
                                        painter()
                                        contentDescription("")
                                    }
                                    classInstance<OdsIconButton.Icon>("checkedIcon") {
                                        painter()
                                        contentDescription("")
                                    }
                                }
                            }
                        })
                }
            }
        }
    }
}