/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.imagetile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsIconButtonIcon
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.imagetile.OdsImageTile
import com.orange.ods.compose.component.imagetile.OdsImageTileIconToggleButton
import com.orange.ods.compose.component.imagetile.OdsImageTileImage
import com.orange.ods.compose.component.imagetile.OdsImageTileLegendAreaDisplayType
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentImageTile() {
    val context = LocalContext.current
    val imageTileCustomizationState = rememberImageTileCustomizationState()
    var iconChecked by rememberSaveable { mutableStateOf(false) }
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.random() }

    with(imageTileCustomizationState) {
        if (type.value == OdsImageTileLegendAreaDisplayType.None) {
            iconDisplayed.value = false
        }
        if (!hasIcon) {
            iconChecked = false
        }
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsChoiceChipsFlowRow(
                    selectedChip = type,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                    outlinedChips = true
                ) {
                    Subtitle(textRes = R.string.component_image_tile_caption_display_type)
                    OdsChoiceChip(textRes = R.string.component_image_tile_caption_display_overlay, value = OdsImageTileLegendAreaDisplayType.Overlay)
                    OdsChoiceChip(textRes = R.string.component_image_tile_caption_display_below, value = OdsImageTileLegendAreaDisplayType.Below)
                    OdsChoiceChip(textRes = R.string.component_element_none, value = OdsImageTileLegendAreaDisplayType.None)
                }
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsSwitchTrailing(checked = iconDisplayed, enabled = hasText)
                )
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin),
                        start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)
                    ),
                horizontalAlignment = Alignment.Start,
            ) {
                val imageSize = 200.dp
                val height = when (type.value) {
                    OdsImageTileLegendAreaDisplayType.Below -> imageSize + dimensionResource(id = com.orange.ods.R.dimen.image_tile_legend_area_height)
                    OdsImageTileLegendAreaDisplayType.Overlay,
                    OdsImageTileLegendAreaDisplayType.None -> imageSize
                }
                OdsImageTile(
                    modifier = Modifier
                        .width(imageSize)
                        .height(height),
                    onClick = { clickOnElement(context, context.getString(R.string.component_image_tile)) },
                    image = OdsImageTileImage(
                        rememberAsyncImagePainter(
                            model = recipe.imageUrl,
                            placeholder = painterResource(id = R.drawable.placeholder),
                            error = painterResource(id = R.drawable.placeholder)
                        ), ""
                    ),
                    title = if (hasText) recipe.title else null,
                    legendAreaDisplayType = when {
                        isOverlay -> OdsImageTileLegendAreaDisplayType.Overlay
                        isBelow -> OdsImageTileLegendAreaDisplayType.Below
                        else -> OdsImageTileLegendAreaDisplayType.None
                    },
                    icon = if (hasIcon) {
                        OdsImageTileIconToggleButton(
                            uncheckedIcon = OdsIconButtonIcon(
                                painterResource(id = R.drawable.ic_heart_outlined),
                                stringResource(id = R.string.component_button_icon_toggle_favorite_add_icon_desc)
                            ),
                            checkedIcon = OdsIconButtonIcon(
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
                        name = OdsComposable.OdsImageTile.name,
                        exhaustiveParameters = false,
                        parameters = {
                            stringRepresentation("legendAreaDisplayType", type.value)
                            if (hasText) title(recipe.title)
                            classInstance("image", OdsImageTileImage::class.java) {
                                painter()
                                contentDescription("")
                            }
                            checked(iconChecked)
                            lambda("onIconCheckedChange")
                            if (hasIcon) {
                                classInstance("uncheckedIcon", OdsIconButtonIcon::class.java) {
                                    painter()
                                    contentDescription("")
                                }
                                classInstance("checkedIcon", OdsIconButtonIcon::class.java) {
                                    painter()
                                    contentDescription("")
                                }
                            }
                        })
                }
            }
        }
    }
}