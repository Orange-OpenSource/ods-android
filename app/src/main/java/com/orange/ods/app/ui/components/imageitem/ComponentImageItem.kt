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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.IconPainterValue
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.imageitem.OdsImageItem
import com.orange.ods.compose.component.imageitem.OdsImageItemTitleType
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentImageItem() {
    val imageItemCustomizationState = rememberImageItemCustomizationState()
    var iconChecked by rememberSaveable { mutableStateOf(false) }
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.random() }

    with(imageItemCustomizationState) {
        if (type.value == OdsImageItemTitleType.None) {
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
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
                    outlinedChips = true
                ) {
                    Subtitle(textRes = R.string.component_element_type)
                    OdsChoiceChip(textRes = R.string.component_image_item_overlay_text, value = OdsImageItemTitleType.Overlay)
                    OdsChoiceChip(textRes = R.string.component_image_item_below_text, value = OdsImageItemTitleType.Below)
                    OdsChoiceChip(textRes = R.string.component_element_none, value = OdsImageItemTitleType.None)
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
                        top = dimensionResource(id = R.dimen.screen_horizontal_margin),
                        start = dimensionResource(id = R.dimen.screen_horizontal_margin)
                    ),
                horizontalAlignment = Alignment.Start,
            ) {
                val imageSize = 200.dp
                val height = when (type.value) {
                    OdsImageItemTitleType.Below -> imageSize + dimensionResource(id = R.dimen.image_item_title_height)
                    OdsImageItemTitleType.Overlay,
                    OdsImageItemTitleType.None -> imageSize
                }
                OdsImageItem(
                    image = rememberAsyncImagePainter(
                        model = recipe.imageUrl,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        error = painterResource(id = R.drawable.placeholder)
                    ),
                    uncheckedIcon = if (hasIcon) painterResource(id = R.drawable.ic_heart_outlined) else null,
                    checkedIcon = if (hasIcon) painterResource(id = R.drawable.ic_heart) else null,
                    title = if (hasText) recipe.title else null,
                    modifier = Modifier
                        .width(imageSize)
                        .height(height),
                    iconChecked = iconChecked,
                    iconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_icon_desc),
                    onIconCheckedChange = { checked -> iconChecked = checked },
                    displayTitle = if (isOverlay) OdsImageItemTitleType.Overlay else if (isBelow) OdsImageItemTitleType.Below else OdsImageItemTitleType.None,
                )
                CodeImplementationColumn(
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_m))
                ) {
                    FunctionCallCode(
                        name = OdsComposable.OdsImageItem.name,
                        exhaustiveParameters = false,
                        parameters = {
                            stringRepresentation("displayTitle", type.value)
                            if (hasText) title(recipe.title)
                            image()
                            checked(iconChecked)
                            lambda("onIconCheckedChange")
                            if (hasIcon) simple("checkedIcon", IconPainterValue)
                            if (hasIcon) simple("uncheckedIcon", IconPainterValue)
                        })
                }
            }
        }
    }
}